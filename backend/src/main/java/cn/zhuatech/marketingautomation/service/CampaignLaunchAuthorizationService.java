/* Copyright 2026 上海如静知华信息科技有限公司 · https://www.zhuatech.cn/ */
package cn.zhuatech.marketingautomation.service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class CampaignLaunchAuthorizationService {
    public Result assess(Request request) {
        var blockers = new ArrayList<String>();
        var actions = new ArrayList<String>();
        if (request.campaignId() == null || request.campaignId().isBlank()) blockers.add("营销活动编号不能为空");
        if (!request.audienceConsentVerified()) blockers.add("受众营销同意未核验");
        if (!request.suppressionListApplied()) blockers.add("退订与抑制名单未应用");
        if (!request.contentApproved()) blockers.add("营销内容未审批");
        if (!request.budgetApproved()) blockers.add("活动预算未批准");
        if (!request.channelPolicyCompliant()) blockers.add("渠道发送政策不合规");
        if (!request.ownerSeparated()) blockers.add("活动配置与发布未职责分离");
        if (!request.auditReady()) blockers.add("活动发布审计证据不完整");
        if (!request.brandApproved()) actions.add("完成品牌规范审核");
        if (!request.trackingConfigured()) actions.add("配置归因与转化跟踪");
        if (!request.frequencyCapConfigured()) actions.add("配置触达频次上限");
        if (!request.landingPageReady()) actions.add("完成落地页验收");
        var decision = !blockers.isEmpty() ? Decision.BLOCKED : actions.isEmpty() ? Decision.LAUNCH : Decision.REVIEW;
        return new Result(decision, List.copyOf(blockers), List.copyOf(actions));
    }

    public enum Decision { LAUNCH, REVIEW, BLOCKED }
    public record Request(String campaignId, boolean audienceConsentVerified, boolean suppressionListApplied,
                          boolean contentApproved, boolean brandApproved, boolean budgetApproved,
                          boolean channelPolicyCompliant, boolean trackingConfigured,
                          boolean frequencyCapConfigured, boolean landingPageReady,
                          boolean ownerSeparated, boolean auditReady) {}
    public record Result(Decision decision, List<String> blockers, List<String> actions) {}
}
