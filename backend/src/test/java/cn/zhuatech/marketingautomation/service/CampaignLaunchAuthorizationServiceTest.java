/* Copyright 2026 上海如静知华信息科技有限公司 · https://www.zhuatech.cn/ */
package cn.zhuatech.marketingautomation.service;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;

class CampaignLaunchAuthorizationServiceTest {
    private final CampaignLaunchAuthorizationService service = new CampaignLaunchAuthorizationService();

    @Test void launchesCompliantCampaign() {
        var result = service.assess(new CampaignLaunchAuthorizationService.Request("CMP-100", true, true, true,
                true, true, true, true, true, true, true, true));
        assertThat(result.decision()).isEqualTo(CampaignLaunchAuthorizationService.Decision.LAUNCH);
    }

    @Test void routesOperationalReadinessToReview() {
        var result = service.assess(new CampaignLaunchAuthorizationService.Request("CMP-101", true, true, true,
                false, true, true, false, false, false, true, true));
        assertThat(result.actions()).hasSize(4);
        assertThat(result.decision()).isEqualTo(CampaignLaunchAuthorizationService.Decision.REVIEW);
    }

    @Test void blocksNonCompliantCampaign() {
        var result = service.assess(new CampaignLaunchAuthorizationService.Request("", false, false, false,
                false, false, false, false, false, false, false, false));
        assertThat(result.blockers()).hasSize(8);
        assertThat(result.decision()).isEqualTo(CampaignLaunchAuthorizationService.Decision.BLOCKED);
    }
}
