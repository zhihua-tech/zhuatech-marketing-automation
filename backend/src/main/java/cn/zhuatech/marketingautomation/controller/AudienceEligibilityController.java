/* Copyright 2026 上海如静知华信息科技有限公司 · https://www.zhuatech.cn/ */
package cn.zhuatech.marketingautomation.controller;import cn.zhuatech.marketingautomation.common.ApiResponse;import cn.zhuatech.marketingautomation.service.AudienceEligibilityService;import jakarta.validation.Valid;import org.springframework.web.bind.annotation.*;
/**
 * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
 */
@RestController @RequestMapping("/api/advanced/marketing") public class AudienceEligibilityController{private final AudienceEligibilityService service;/**
                                                                                                                                                        * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
                                                                                                                                                        */
public AudienceEligibilityController(AudienceEligibilityService service){this.service=service;}/**
                                                                                                                                                                                                                                                       * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
                                                                                                                                                                                                                                                       */
@PostMapping("/audience-eligibility") public ApiResponse<AudienceEligibilityService.EligibilityResult> evaluate(@Valid @RequestBody AudienceEligibilityService.EligibilityRequest request){return ApiResponse.ok(service.evaluate(request));}}
