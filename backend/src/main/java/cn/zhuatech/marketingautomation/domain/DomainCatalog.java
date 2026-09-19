/* Copyright 2026 上海如静知华信息科技有限公司 · https://www.zhuatech.cn/ */
package cn.zhuatech.marketingautomation.domain;
import org.springframework.stereotype.Component;
import java.util.*;
/**
 * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
 */
@Component
public class DomainCatalog {
    private final Map<String, WorkflowAction> actions = new LinkedHashMap<>();
    /**
     * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
     */
    public DomainCatalog() {
        actions.put("LAUNCH", new WorkflowAction("LAUNCH", "提交活动上线", List.of("草稿"), "运行中", "ADMIN"));
        actions.put("PAUSE", new WorkflowAction("PAUSE", "暂停活动", List.of("运行中"), "已暂停", "OPERATOR"));
        actions.put("CLOSE", new WorkflowAction("CLOSE", "结束活动", List.of("已暂停"), "已结束", "ADMIN"));
    }
    /**
     * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
     */
    public String systemName() { return "知华科技营销自动化平台"; }
    /**
     * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
     */
    public String scene() { return "受众、活动、内容、自动旅程、线索培育、评分、同意、归因和经营分析"; }
    /**
     * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
     */
    public String initialStatus() { return "草稿"; }
    /**
     * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
     */
    public String partyLabel() { return "客户群/营销活动"; }
    /**
     * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
     */
    public String amountLabel() { return "活动预算"; }
    /**
     * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
     */
    public String quantityLabel() { return "触达人数"; }
    /**
     * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
     */
    public String dueLabel() { return "活动期限"; }
    /**
     * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
     */
    public List<ModuleDefinition> modules() { return List.of(
            new ModuleDefinition("AUDIENCE", "客户分群", "基于标签、行为和价值构建动态人群"),
            new ModuleDefinition("CAMPAIGN", "营销活动", "管理目标、预算、渠道、排期和负责人"),
            new ModuleDefinition("CONTENT", "营销内容", "管理模板、素材、版本和审批"),
            new ModuleDefinition("JOURNEY", "自动旅程", "通过触发器、条件和动作编排培育流程"),
            new ModuleDefinition("LEAD_SCORING", "线索评分", "按画像、意向和互动计算线索分值"),
            new ModuleDefinition("CONSENT", "营销同意", "管理订阅偏好、退订和触达合规"),
            new ModuleDefinition("EXECUTION", "触达执行", "连接邮件、短信、企微和广告渠道"),
            new ModuleDefinition("ATTRIBUTION", "转化归因", "分析触点、商机、订单和收入贡献"),
            new ModuleDefinition("ANALYTICS", "营销分析", "跟踪漏斗、ROI、获客成本和留存")
        ); }
    /**
     * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
     */
    public Map<String, WorkflowAction> actions() { return Collections.unmodifiableMap(actions); }
    /**
     * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
     */
    public record ModuleDefinition(String code,String name,String description) {}
    /**
     * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
     */
    public record WorkflowAction(String code,String label,List<String> from,String to,String requiredRole) {}
}
