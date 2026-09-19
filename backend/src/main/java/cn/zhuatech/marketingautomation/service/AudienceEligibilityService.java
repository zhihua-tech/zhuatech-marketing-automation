/* Copyright 2026 上海如静知华信息科技有限公司 · https://www.zhuatech.cn/ */
package cn.zhuatech.marketingautomation.service;
import jakarta.validation.Valid;import jakarta.validation.constraints.*;import org.springframework.stereotype.Service;import java.time.*;import java.util.*;
@Service public class AudienceEligibilityService{
 public EligibilityResult evaluate(@Valid EligibilityRequest request){Set<String>keys=new HashSet<>();List<ContactResult>results=new ArrayList<>();int eligible=0;
  for(Contact contact:request.contacts()){List<String>reasons=new ArrayList<>();if(!keys.add(contact.contactKey()))reasons.add("受众重复");if(!contact.consentGranted())reasons.add("未取得有效营销同意");if(contact.suppressed())reasons.add("命中全局抑制名单");if(contact.contactsLast7Days()>=request.frequencyCap7Days())reasons.add("达到七日触达频控上限");if(contact.channelOptOut())reasons.add("当前渠道已退订");if(isQuiet(request.sendAt().getHour(),request.quietHourStart(),request.quietHourEnd()))reasons.add("当前处于静默时段");String status=reasons.isEmpty()?"ELIGIBLE":"EXCLUDED";if(reasons.isEmpty())eligible++;results.add(new ContactResult(contact.contactKey(),status,reasons));}
  return new EligibilityResult(request.campaignNo(),eligible,results.size()-eligible,results,eligible==0?"NO_ELIGIBLE_AUDIENCE":"READY");
 }
 private boolean isQuiet(int hour,int start,int end){if(start==end)return false;return start<end?hour>=start&&hour<end:hour>=start||hour<end;}
 public record EligibilityRequest(@NotBlank String campaignNo,@NotNull LocalDateTime sendAt,@Min(1) int frequencyCap7Days,@Min(0) @Max(23) int quietHourStart,@Min(0) @Max(23) int quietHourEnd,@NotEmpty List<@Valid Contact>contacts){}
 public record Contact(@NotBlank String contactKey,boolean consentGranted,boolean suppressed,@Min(0) int contactsLast7Days,boolean channelOptOut){}
 public record ContactResult(String contactKey,String status,List<String>reasons){}
 public record EligibilityResult(String campaignNo,int eligibleCount,int excludedCount,List<ContactResult>contacts,String decision){}
}
