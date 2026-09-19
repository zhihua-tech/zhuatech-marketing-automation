/* Copyright 2026 上海如静知华信息科技有限公司 · https://www.zhuatech.cn/ */
package cn.zhuatech.marketingautomation;import org.junit.jupiter.api.Test;import org.springframework.beans.factory.annotation.Autowired;import org.springframework.boot.test.context.SpringBootTest;import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;import org.springframework.http.MediaType;import org.springframework.test.web.servlet.MockMvc;import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
/**
 * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
 */
@SpringBootTest @AutoConfigureMockMvc class AudienceEligibilityApiTests{@Autowired MockMvc mvc;static final String BODY="""
 {"campaignNo":"CMP-1","sendAt":"2026-09-20T10:00:00","frequencyCap7Days":3,"quietHourStart":21,"quietHourEnd":8,"contacts":[{"contactKey":"C-1","consentGranted":true,"suppressed":false,"contactsLast7Days":1,"channelOptOut":false},{"contactKey":"C-2","consentGranted":false,"suppressed":false,"contactsLast7Days":0,"channelOptOut":false},{"contactKey":"C-3","consentGranted":true,"suppressed":true,"contactsLast7Days":4,"channelOptOut":false}]}
 """;
 /**
  * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
  */
 @Test void enforcesConsentSuppressionFrequencyAndQuietHours()throws Exception{mvc.perform(post("/api/advanced/marketing/audience-eligibility").with(httpBasic("operator","operator123")).contentType(MediaType.APPLICATION_JSON).content(BODY)).andExpect(status().isOk()).andExpect(jsonPath("$.data.decision").value("READY")).andExpect(jsonPath("$.data.eligibleCount").value(1)).andExpect(jsonPath("$.data.excludedCount").value(2));}
 /**
  * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
  */
 @Test void overnightQuietHoursExcludeOtherwiseEligibleContact()throws Exception{mvc.perform(post("/api/advanced/marketing/audience-eligibility").with(httpBasic("operator","operator123")).contentType(MediaType.APPLICATION_JSON).content(BODY.replace("T10:00:00","T22:00:00"))).andExpect(status().isOk()).andExpect(jsonPath("$.data.decision").value("NO_ELIGIBLE_AUDIENCE")).andExpect(jsonPath("$.data.eligibleCount").value(0));}
 /**
  * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
  */
 @Test void requiresAuthentication()throws Exception{mvc.perform(post("/api/advanced/marketing/audience-eligibility").contentType(MediaType.APPLICATION_JSON).content(BODY)).andExpect(status().isUnauthorized());}}
