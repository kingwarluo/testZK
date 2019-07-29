package com.设计模式.biz.excel.handler;

import com.设计模式.biz.excel.ExcelImportUtil;
import lombok.Data;
import org.apache.poi.ss.usermodel.Row;

import java.util.List;
import java.util.concurrent.Callable;

/**
 * @description:校验数据格式是否正确
 *
 * @author jianhua.luo
 * @date 2019/7/29
 */
@Data
public class ExcelCheckDataHandler implements Callable {
    /**
     * 导入对象
     */
    private ExcelImportUtil excelImport;
    /**
     * 校验的数据
     */
    private List<Row> rows;

    @Override
    public Object call() throws Exception {
        excelImport.checkData(rows);
        return this;
    }
}
