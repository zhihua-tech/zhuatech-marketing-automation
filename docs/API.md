# 营销自动化平台 API

所有业务接口默认位于 `/api`，除 `/public/**` 和健康检查外均需要 HTTP Basic 身份认证。生产环境应接入企业 IAM 或统一身份平台。

| 方法 | 路径 | 说明 |
| --- | --- | --- |
| GET | `/public/about` | 产品、公司、官网和许可元数据 |
| GET | `/catalog` | 业务模块、字段标签和状态动作 |
| GET | `/dashboard` | 业务规模、金额、状态和模块统计 |
| GET/POST | `/records` | 业务台账查询与创建 |
| GET/PUT/DELETE | `/records/{id}` | 详情、草稿修改与删除 |
| POST | `/records/{id}/actions` | 执行服务端状态迁移 |
| POST | `/records/{id}/comments` | 增加协作记录 |
| GET | `/records/{id}/timeline` | 查询完整操作时间线 |
| GET | `/records/search` | 组合检索、分页和逾期筛选 |
| GET | `/records/export.csv` | 导出 UTF-8 CSV |
| GET | `/sla-summary` | SLA、逾期、风险和人员工作量 |
| POST | `/domain/decision` | 执行营销自动化平台专属领域规则 |
| POST | `/advanced/marketing/audience-eligibility` | 发送前执行同意、抑制、退订、频控、静默时段和去重检查 |
| GET/POST | `/enterprise/controls` | 企业控制项查询与幂等创建 |
| POST | `/enterprise/controls/{id}/submit` | 提交复核 |
| POST | `/admin/enterprise/controls/{id}/review` | 管理员审批或驳回 |
| POST | `/enterprise/controls/{id}/documents` | 登记附件哈希及存储元数据 |
| POST | `/enterprise/controls/{id}/complete` | 凭证完整后办结 |
| POST | `/admin/enterprise/controls/{id}/sync` | 登记外部系统回执 |

## 领域决策字段

| 字段 | 类型 | 含义 |
| --- | --- | --- |
| `campaignNo` | String | 活动编号 |
| `budget` | double | 预算 |
| `spend` | double | 已花费 |
| `leads` | int | 线索数 |
| `opportunities` | int | 商机数 |
| `revenue` | double | 归因收入 |
| `consentRate` | double | 有效同意率(%) |

接口统一返回 `ApiResponse`；业务冲突使用 HTTP 409，参数错误使用 400，未认证使用 401，无权限使用 403。

## 专业营销增长接口

| 方法 | 路径 | 说明 |
| --- | --- | --- |
| GET | `/api/marketing-ops/dashboard` | 活动、同意和触点总览 |
| POST | `/api/marketing-ops/audiences` | 创建动态受众 |
| POST | `/api/marketing-ops/campaigns` | 创建营销活动 |
| POST | `/api/marketing-ops/campaigns/{id}/journeys` | 配置自动旅程 |
| POST | `/api/admin/marketing-ops/campaigns/{id}/approve` | 批准活动 |
| POST | `/api/marketing-ops/campaigns/{id}/launch` | 启动活动 |
| POST | `/api/marketing-ops/consents` | 记录渠道营销同意 |
| POST | `/api/marketing-ops/consents/{id}/revoke` | 撤回同意 |
| POST | `/api/marketing-ops/campaigns/{id}/touch-events` | 记录合规触点和旅程评分 |
