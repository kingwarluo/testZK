package com.mmc.sic.service.modules.domain;
import lombok.Data;
import java.util.Date;

/**
 *
 * 该文件无需手动修改，若表变更，运行一次Generator即可，会自动刷新
 *
 */
@Data
public class SicExpire {

    /**
     * id
     */
    private Long id;

    /**
     * 配置项
     */
    private String configurationItem;

    /**
     * 失效时间(小时)
     */
    private Integer expireHour;

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