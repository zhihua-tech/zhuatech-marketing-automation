/* Copyright 2026 上海如静知华信息科技有限公司 · https://www.zhuatech.cn/ */
package cn.zhuatech.marketingautomation.service;
import jakarta.validation.constraints.*;
import org.springframework.stereotype.Service;
import java.util.*;
@Service public class DomainDecisionService {
 public DecisionResult assess(DecisionRequest request) { double roi=request.spend()==0?0:(request.revenue()-request.spend())*100d/request.spend();double conversion=request.leads()==0?0:request.opportunities()*100d/request.leads();int score=100;List<String> actions=new ArrayList<>();if(request.spend()>request.budget()){score-=30;actions.add("停止超预算投放并申请变更");}if(roi<0){score-=30;actions.add("调整低回报渠道和人群");}if(conversion<5){score-=20;actions.add("优化线索培育与评分规则");}if(request.consentRate()<95){score-=30;actions.add("清理无有效营销同意的受众");}return result(score,actions,"SCALE","OPTIMIZE","PAUSE",Map.of("roiPercent",Math.round(roi*10)/10d,"conversionPercent",Math.round(conversion*10)/10d,"budgetUtilization",request.budget()==0?0:Math.round(request.spend()*1000/request.budget())/10d)); }
 private DecisionResult result(int raw,List<String> actions,String good,String warn,String bad,Map<String,Object> metrics) { int score=Math.max(0,Math.min(100,raw));String decision=score>=80?good:score>=50?warn:bad;return new DecisionResult(decision,score,metrics,List.copyOf(actions)); }
 private DecisionResult riskResult(int raw,List<String> actions,String good,String warn,String bad,Map<String,Object> metrics) { int score=Math.max(0,Math.min(100,raw));String decision=score>=70?bad:score>=40?warn:good;return new DecisionResult(decision,score,metrics,List.copyOf(actions)); }
 public record DecisionRequest(
        @NotBlank String campaignNo,
        @PositiveOrZero double budget,
        @PositiveOrZero double spend,
        @PositiveOrZero int leads,
        @PositiveOrZero int opportunities,
        @PositiveOrZero double revenue,
        @DecimalMin("0") @DecimalMax("100") double consentRate) {}
 public record DecisionResult(String decision,int score,Map<String,Object> metrics,List<String> actions) {}
}
