package com.设计模式.biz.excel.validator;

import com.设计模式.biz.excel.ExcelImportUtil;
import com.设计模式.biz.excel.bo.BizBo;
import com.设计模式.biz.excel.bo.ImportBo;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

import java.util.Queue;

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

    public void checkData(Row row, int index) {
        checkData(row, new ImportBo(), index);
    }

    /**
     * @description:校验数据
     *
     * @author jianhua.luo
     * @date 2019/7/29
     */
    private void checkData(Row row, ImportBo bo, int index) {
        int rowNum = row.getRowNum() + 1;
        Cell cell = row.getCell(index);
        String value = ExcelImportUtil.getCellValue2String(cell);
        String err = check(value);
        if(StringUtils.isNotEmpty(err)) {
            StringBuilder sb = excelImport.getFailMap().get(rowNum);
            if(sb == null) {
                sb = new StringBuilder(err);
                excelImport.getFailMap().put(rowNum, sb);
            } else {
                if(sb.length() > 0) {
                    sb.append(",");
                }
                sb.append(err);
                excelImport.getFailMap().put(rowNum, sb);
            }
        } else {
            bo.getQueue().offer(value);
        }
        if(next != null) {
            next.checkData(row, bo, ++index);
        } else {
            Queue<String> queue = bo.getQueue();
            String mobile = queue.poll();
            String name = queue.poll();
            String sex = queue.poll();
            String city = queue.poll();
            String sicChannel = queue.poll();
            String contact = queue.poll();
            if(excelImport.getFailMap().get(rowNum) != null) {
                bo.setRowNum(rowNum);
                bo.setReason(excelImport.getFailMap().get(rowNum).toString());
                excelImport.getFails().add(bo);
            } else {
                //判断手机号重复，在导入时就过滤掉重复的手机号
                if(StringUtils.isNotEmpty(mobile) && !excelImport.getExistMobiles().contains(mobile)) {
                    System.out.println("rowNum:" + rowNum + "校验完毕");
                    BizBo bizBo = new BizBo();
                    bizBo.setMobile(mobile);
                    bizBo.setName(name);
                    bizBo.setSex(sex);
                    bizBo.setCity(city);
                    bizBo.setSicChannel(sicChannel);
                    bizBo.setContact(contact);
                    excelImport.getReadyForInsert().add(bizBo);
                    excelImport.getExistMobiles().add(mobile);
                }
            }
        }
    }

    /**
     * @description:链路式编程，set方法返回当前对象实例
     *
     * @author jianhua.luo
     * @date 2019/7/30
     */
    public AbstractCellChecker setNext(AbstractCellChecker checker) {
        this.next = checker;
        return checker;
    }

    /**
     * 抽象方法，由各子类自己实现
     *
     * @param value
     * @return
     * @author jianhua.luo
     * @date 2019/7/30
     */
    public abstract String check(String value);

}
