package com.mmc.sic.service.modules.domain;
import lombok.Data;
import java.util.Date;

/**
 *
 * 该文件无需手动修改，若表变更，运行一次Generator即可，会自动刷新
 *
 */
@Data
public class SicHistoryRecord {

    /**
     * id
     */
    private Long id;

    /**
     * 留咨id
     */
    private Long sicInfoId;

    /**
     * 留咨单状态(1:已新建 2:已关联销售 3:已成交 4:已失效 5:已放弃)
     */
    private Integer sicStatus;

    /**
     * 操作人类型
     */
    private String operatorType;

    /**
     * 操作人id
     */
    private Long operatorEmpId;

    /**
     * 操作来源(1:后台 2:车码头-加盟商 3:车码头-自营 4:小程序)
     */
    private Integer operatorSource;

    /**
     * 操作时间
     */
    private Date operatorDate;

    /**
     * 操作备注
     */
    private String operatorRemark;

    /**
     * 操作人部门名称
     */
    private String operatorDeptName;

}