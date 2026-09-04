# 企业营销活动发布授权

`POST /api/enterprise/marketing-automation/campaign-launch-authorization` 在活动上线前检查营销同意、退订抑制、内容审批、预算、渠道政策、品牌、归因、频控、落地页与职责分离。

- `LAUNCH`：合规与运营准备完整，可以启动活动。
- `REVIEW`：不存在硬性阻断，但仍需完善品牌、跟踪、频控或落地页。
- `BLOCKED`：营销同意、抑制名单、内容、预算、渠道、职责分离或审计控制失败。

授权结果可作为邮件、短信、企微和广告渠道适配器前的统一发送门禁。
