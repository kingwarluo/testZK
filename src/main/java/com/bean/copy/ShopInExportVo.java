package com.bean.copy;

import lombok.Data;

import java.util.Date;

@Data
public class ShopInExportVo {


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


    private String martName;

    private Date bankSubmitTime;

    private Date bankAuditTime;

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

    private String inviter;

    private String inviterMobile;

    private String inviteCode;

    private Integer productNum;

    private Integer vipNum;

    private Integer orderNum;

    private Integer deliveryNum;

    private String remark;



}
