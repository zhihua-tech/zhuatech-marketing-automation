/* Copyright 2026 上海如静知华信息科技有限公司 · https://www.zhuatech.cn/ */
package cn.zhuatech.marketingautomation.service;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;

/**
 * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
 */
class CampaignLaunchAuthorizationServiceTest {
    private final CampaignLaunchAuthorizationService service = new CampaignLaunchAuthorizationService();

    /**
     * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
     */
    @Test void launchesCompliantCampaign() {
        var result = service.assess(new CampaignLaunchAuthorizationService.Request("CMP-100", true, true, true,
                true, true, true, true, true, true, true, true));
        assertThat(result.decision()).isEqualTo(CampaignLaunchAuthorizationService.Decision.LAUNCH);
    }

    /**
     * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
     */
    @Test void routesOperationalReadinessToReview() {
        var result = service.assess(new CampaignLaunchAuthorizationService.Request("CMP-101", true, true, true,
                false, true, true, false, false, false, true, true));
        assertThat(result.actions()).hasSize(4);
        assertThat(result.decision()).isEqualTo(CampaignLaunchAuthorizationService.Decision.REVIEW);
    }

    /**
     * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
     */
    @Test void blocksNonCompliantCampaign() {
        var result = service.assess(new CampaignLaunchAuthorizationService.Request("", false, false, false,
                false, false, false, false, false, false, false, false));
        assertThat(result.blockers()).hasSize(8);
        assertThat(result.decision()).isEqualTo(CampaignLaunchAuthorizationService.Decision.BLOCKED);
    }
}
