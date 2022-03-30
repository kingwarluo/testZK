package com.bean.copy;

import lombok.Data;
import lombok.ToString;

import java.util.Date;
import java.util.List;

/**
 * 店铺对象 tb_shop
 *
 * @author chenxl
 * @date 2020-11-11
 */
@Data
@ToString
public class Shop {
    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    private Long id;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 入驻提交时间
     */
    private Date settleSubmitTime;

    private String provinceName;

    private String cityName;

    private String shopNo;

    /**
     * 店铺名称(数字、中文，英文(可混合，不可有特殊字符)，可修改)、不唯一
     */
    private String shopName;

    /**
     * 简称
     */
    private String shopShort;

    private String shopHead;

    private String headMobile;

    /**
     * 门店面积（平方米）
     */
    private Integer shopAcreage;

    /**
     * 店铺详细地址
     */
    private String shopAddress;

    /**
     * 门店审核状态（1：提交审核，2：审核通过，3：审核驳回）tb_shop_audit_deputy
     */
    private Integer auditStatus;

    private Date auditTime;

    /**
     * 审核人
     */
    private String auditUser;

    /**
     * 备注信息（审核备注：驳回）
     */
    private String remark;

    private Date bankSubmitTime;

    private Date bankAuditTime;

    /**
     * 导出时需要
     */
    private Date abcBankSubmitTime;

    private Date abcBankAuditTime;

    /**
     * 是否签约（0：未签约，1：已签约）
     */
    private Integer isSigning;

    /**
     * 门店审核状态（1：提交审核，2：审核通过，3：审核驳回）
     */
    private Integer cooperation;

    /**
     * 店铺状态(-1:未开通 0: 停业中 1:营业中)，可修改
     */
    private Integer shopStatus;

    private Integer serviceStatus;
    private Integer signboardStatus;

    /**
     * 收件人名车
     */
    private String receiveName;

    /**
     * 收件人联系方式
     */
    private String receiveTel;

    /**
     * 收件-详细地址
     */
    private String receiveAddress;

    /**
     * 客服备注
     */
    private String sysRemark;

    /**
     * 店铺类型（1：加盟  2:直营）
     */
    private Byte shopType;

    /**
     * 店铺简介(可修改)
     */
    private String shopIntro;


    /**
     * 门店等级（1 :A ,2: B）
     */
    private String shopLevel;

    /**
     * 技术服务费（10000,表示百分位）
     */
    private Integer technicalRate;


    /**
     * 门店服务范围（米）
     */
    private Integer serviceRange;

    /**
     * 店长（账户）
     */
    private String ownerAccount;

    /**
     * 店铺绑定的手机(门店申请人账号）用户账号
     */
    private String mobile;

    /**
     * 用户账号密码
     */
    private String password;

    /**
     * 店铺联系电话
     */
    private String shopTel;

    /**
     * 店铺所在纬度(可修改)
     */
    private Double shopLat;

    /**
     * 店铺所在经度(可修改)
     */
    private Double shopLng;

    private Integer noSince;

    /**
     * 店铺所在省份（描述）
     */
    private String provinceId;

    /**
     * 店铺所在城市（描述）
     */
    private String cityId;

    /**
     * 店铺所在区域（描述）
     */
    private String areaId;

    /**
     * 店铺logo(可修改)
     */
    private String shopLogo;

    /**
     * 每天营业时间段 开始
     */
    private Date openStartTime;

    /**
     * 每天营业时间段 结束
     */
    private Date openEndTime;

    /**
     * 门店审核状态副审核流程状态（4：服务认证-提交,5：服务认证-驳回,6：服务认证-通过,7：规范认证-提交,8：规范认证-驳回,9：规范认证-通过）',
     */
    private Integer auditDeputy;

    /**
     * 0:商家承担运费; 1:买家承担运费
     */
    private Byte transportType;

    /**
     * 收件-省编号
     */
    private String receiveProvince;

    /**
     * 收件-市编号
     */
    private String receiveCity;

    /**
     * 收件-区编号
     */
    private String receiveArea;

    /**
     * 创建人
     */
    private Long createUid;

    /**
     * 修改人
     */
    private Long updateUid;

    private String updateUser;
    private String areaAddress;
    private String receiveRegion;
    private Integer haveRead;//是否已读协议（默认已读0，未读1）

    //发放金额 单位：分
    private Long issueAccount;
    //发放状态(0未发放 1 待发放 2 已发放)
    private Integer issueStatus;
    private Date issueSubmitTime;
    private Long issueUid;
    private Date issuedTime;

    /**
     * 修改时间
     */
    private Date updateTime;

    private Integer vipNum;

    private Integer orderNum;

    private Long orderMoney;

    private List<String> ids;

    private String beginCreateTime;
    private String startCreateTime;
    private String endCreateTime;
    private List createTimes;

    private String rejectType;

    /**
     * 费率申请状态( 1申请中)
     */
    private Integer rateStatus;
    /**
     * 营业申请状态(1申请中)
     */
    private Integer businessStatus;
    /**
     * 合作申请状态(1申请中)
     */
    private Integer cooperationStatus;

    private Integer shopCertificate;

    private Integer defaultBank;

    private String businessNumber;

    private String identityNumber;

    private Integer errorFlag;

    private Date submitTime;

    /**
     * 待删除状态
     */
    private Integer waitDelFlag;

    /**
     * 查询审核tab类型
     * 0:待入驻审核
     * 1：银行入驻审核
     * 2：服务认证
     * 3：店招认证
     * 4：全部
     */
    private Integer tabType;
    /**
     * 理由
     */
    private String reason;

    /**
     * 服务认证提交时间
     */
    private Date serviceSubmitTime;
    /**
     * 店招认证提交时间
     */
    private Date signboardSubmitTime;
    /**
     * 移动异常提交时间
     */
    private Date errorSubmitTime;
    /**
     * 移动异常原因
     */
    private String removeReason;

    /**
     * 查询辅助字段
     */
    private String keyWords;

    private Boolean isAllCleck;

    /**
     * 入驻类型
     */
    private Integer auditType;
    /**
     * 审核状态
     */
    private Integer settleStatus;

    private String shipmentNumber;

    /**
     * app码审核状态：0 待审核；1 审核驳回；2 审核通过
     */
    private Integer appCodeAudit;

    //搜索条件需要
    private String shopNameMobile;

    private Long industryId;

    private String inviter;

    private String inviterMobile;

    private String inviteCode;

    private Integer productNum;

    private String martName;


    private Integer inviteChannelType;

    /**
     * 农行通道状态
     */
    private Integer abcStatus;
    /**
     * 工行状态
     */
    private Integer icbcStatus;

    /**
     * 地址id
     */
    private Long addressId;

    /**
     * 地址等级
     */
    private Integer addressLevel;

    private Integer status;
}
