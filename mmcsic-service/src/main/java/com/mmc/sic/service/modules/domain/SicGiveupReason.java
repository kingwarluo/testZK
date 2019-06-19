package com.mmc.sic.service.modules.domain;
import lombok.Data;
import java.util.Date;

/**
 *
 * 该文件无需手动修改，若表变更，运行一次Generator即可，会自动刷新
 *
 */
@Data
public class SicGiveupReason {

    /**
     * id
     */
    private Long id;

    /**
     * 放弃原因
     */
    private String giveupReason;

    /**
     * 状态(1:有效 2:失效)
     */
    private Integer status;

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

}