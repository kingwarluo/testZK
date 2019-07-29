package com.设计模式.biz.excel.validator;

import com.设计模式.biz.excel.ExcelImportUtil;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

/**
 * @description:excel校验父类
 *
 * @author jianhua.luo
 * @date 2019/7/29
 */
@Data
@Slf4j
public abstract class AbstractCellChecker {
    /**
     * 引用对象
     */
    private ExcelImportUtil excelImport;
    /**
     * 下一个校验对象
     */
    private AbstractCellChecker next;

    /**
     * @description:校验数据
     *
     * @author jianhua.luo
     * @date 2019/7/29
     */
    public void checkData() {

    }

    public AbstractCellChecker setNext(AbstractCellChecker checker) {
        this.next = checker;
        return this;
    }

}
