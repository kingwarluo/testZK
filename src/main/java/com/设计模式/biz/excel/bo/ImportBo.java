package com.设计模式.biz.excel.bo;

import lombok.Data;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * @description:导入业务数据
 *
 * @author jianhua.luo
 * @date 2019/7/30
 */
@Data
public class ImportBo implements Comparable<ImportBo> {

    private int rowNum;

    private String reason;

    private Queue<String> queue = new ConcurrentLinkedQueue<String>();

    @Override
    public int compareTo(ImportBo o) {
        return this.rowNum - o.getRowNum();
    }
}
