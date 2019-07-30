package com.设计模式.biz.excel;

import com.google.common.collect.Lists;
import com.设计模式.biz.excel.bo.ImportBo;
import com.设计模式.biz.excel.handler.ExcelCheckDataHandler;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.apache.poi.ss.usermodel.Row;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * @description:excel导入实际业务场景
 *
 * @author jianhua.luo
 * @date 2019/7/29
 */
@Slf4j
public class ExcelImportService {

    static ExecutorService executor = new ThreadPoolExecutor(10, 10, 3000, TimeUnit.SECONDS, new LinkedBlockingQueue<>());

    public static void main(String[] args) {
        ExcelImportUtil excelImport = new ExcelImportUtil();
        URL url = excelImport.getClass().getClassLoader().getResource("");
        File file = new File(url.getPath() + "导入测试(2000条).xlsx");
        excelImport.setExcel(file);

        excelImport.checkSuffix();
        if(!excelImport.isSuccess()) {
            System.out.println(excelImport.getErrorMsg());
        }
        excelImport.checkHeader();
        if(!excelImport.isSuccess()) {
            System.out.println(excelImport.getErrorMsg());
        }
        log.info("校验中。。。");
        long start = System.currentTimeMillis();
        checkExcelData(excelImport);
        log.info("总耗时：{}ms", System.currentTimeMillis() - start);
        if(!excelImport.isSuccess()) {
            log.info("错误条数：{}条", excelImport.getFails().size());
            for (ImportBo fail : excelImport.getFails()) {
                System.out.println(fail.getRowNum() + ":" + fail.getReason());
            }
        }
        //记得关闭线程池，这是一次性执行。如果不手动关闭，则线程池会一直占用资源
        executor.shutdown();
    }

    public static void checkExcelData(ExcelImportUtil excelImport) {
        excelImport.initCheckStep();
        List<Row> allRows = excelImport.getAllRows();
        log.info("总共要校验{}条信息。", allRows.size());
        List<List<Row>> rowGroup = Lists.partition(allRows, 500);
        List<Future> res = new ArrayList<Future>();
        for (List rows : rowGroup) {
            if(CollectionUtils.isNotEmpty(rows) && rows.get(0) != null) {
                ExcelCheckDataHandler handler = new ExcelCheckDataHandler();
                handler.setExcelImport(excelImport);
                handler.setRows(rows);
                Future ch = executor.submit(handler);
                res.add(ch);
            }
        }
        for (Future future : res) {
            try {
                future.get();
            } catch (Exception e) {
                log.info("数据合法性校验失败", e);
            }
        }
    }

}
