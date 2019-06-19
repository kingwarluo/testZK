package com.mmc.sic.service.modules.domain;
import lombok.Data;
import java.util.Date;

/**
 *
 * 该文件无需手动修改，若表变更，运行一次Generator即可，会自动刷新
 *
 */
@Data
public class SicFollowRecord {

    /**
     * id
     */
    private Long id;

    /**
     * 留咨id
     */
    private Long sicInfoId;

    /**
     * 成交意向id
     */
    private Long turnoverIntentionId;

    /**
     * 沟通细节
     */
    private String communicateDetail;

    /**
     * 操作人
     */
    private Long operatorEmpId;

    /**
     * 操作来源(1:后台 2:车码头-加盟商 3:车码头-自营 4:小程序)
     */
    private Integer operatorSource;

    /**
     * 跟进时间
     */
    private Date followDate;

    /**
     * 操作人部门名称
     */
    private String operatorDeptName;

    /**
     * 操作人账号ID
     */
    private Long operatorAccountId;

}