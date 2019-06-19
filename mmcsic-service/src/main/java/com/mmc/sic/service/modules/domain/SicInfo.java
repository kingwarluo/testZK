package com.mmc.sic.service.modules.domain;
import lombok.Data;
import java.math.BigDecimal;
import java.util.Date;

/**
 *
 * 该文件无需手动修改，若表变更，运行一次Generator即可，会自动刷新
 *
 */
@Data
public class SicInfo {

    /**
     * id
     */
    private Long id;

    /**
     * 留咨单编号
     */
    private String sicNo;

    /**
     * 手机号
     */
    private String mobile;

    /**
     * 国家代码
     */
    private String countryCode;

    /**
     * 会员id
     */
    private Long ucrmId;

    /**
     * 姓名
     */
    private String name;

    /**
     * 留咨单状态(1:已新建 2:已关联销售 3:已成交 4:已失效 5:已放弃)
     */
    private Integer sicStatus;

    /**
     * 城市
     */
    private Long cityId;

    /**
     * 门店
     */
    private Long storeId;

    /**
     * 联系电话
     */
    private String contactPhone;

    /**
     * 购买方式(1:全款 2:分期)
     */
    private Integer paymentType;

    /**
     * 品牌
     */
    private Long brandId;

    /**
     * 车系
     */
    private Long seriesId;

    /**
     * 车型
     */
    private Long modelId;

    /**
     * 外观颜色
     */
    private Long vehicleColorId;

    /**
     * 内饰颜色
     */
    private Long trimColorId;

    /**
     * 期望首付金额(万元)
     */
    private BigDecimal firstPayment;

    /**
     * 最新成交意向
     */
    private Long turnoverIntentionId;

    /**
     * 最新沟通细节
     */
    private String communicateDetail;

    /**
     * 最后跟进时间
     */
    private Date lastFollowDate;

    /**
     * 最新放弃原因
     */
    private Long giveupReasonId;

    /**
     * 关联销售人员
     */
    private Long salesmanId;

    /**
     * 激活标识:true-已激活 false-未激活
     */
    private Integer activateFlag;

    /**
     * 新建人
     */
    private Long createEmp;

    /**
     * 新建时间
     */
    private Date createTime;

    /**
     * 修改人
     */
    private Long modifyEmp;

    /**
     * 修改时间yyyy-MM-dd HH:mm:ss
     */
    private Date modifyTime;

    /**
     * 关联销售账号id
     */
    private Long salesmanAccountId;

    /**
     * 新建人账号id
     */
    private Long createAccountId;

    /**
     * 销售人员账号类型来源(1:渠道账号 2:员工账号)
     */
    private Integer salesAccountType;

    /**
     * 修改人账号类型来源(1:渠道账号 2:员工账号)
     */
    private Integer modifyAccountType;

    /**
     * 门店类型（1自营 2加盟）
     */
    private Integer storeType;

    /**
     * 销售点id
     */
    private Long salePointId;

    /**
     * 门店部门id
     */
    private String org;

}