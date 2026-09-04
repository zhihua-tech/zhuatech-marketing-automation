/* Copyright 2026 上海如静知华信息科技有限公司 · https://www.zhuatech.cn/ */
package cn.zhuatech.marketingautomation.controller;

import cn.zhuatech.marketingautomation.common.ApiResponse;
import cn.zhuatech.marketingautomation.service.CampaignLaunchAuthorizationService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/enterprise/marketing-automation")
public class CampaignLaunchAuthorizationController {
    private final CampaignLaunchAuthorizationService service;
    public CampaignLaunchAuthorizationController(CampaignLaunchAuthorizationService service) { this.service = service; }

    @PostMapping("/campaign-launch-authorization")
    public ApiResponse<?> assess(@RequestBody CampaignLaunchAuthorizationService.Request request) {
        return ApiResponse.ok(service.assess(request));
    }
}
