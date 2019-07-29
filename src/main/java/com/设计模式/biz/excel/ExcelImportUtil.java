package com.设计模式.biz.excel;

import com.设计模式.biz.excel.bo.ImportBo;
import com.设计模式.biz.excel.validator.*;
import com.设计模式.biz.excel.constant.ExcelConstants;
import lombok.Data;
import lombok.extern.log4j.Log4j;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @description:导入excel类
 * 注：如果需要引入其他服务校验，可以添加一个spring注解，以便引入spring中的service，指定prototype
 *  @Component
 *  @Scope("prototype")  spring中，每次getBean()新生成一个实例
 * @author jianhua.luo
 * @date 2019/7/29
 */
@Data
@Log4j
public class ExcelImportUtil {

    /**
     * excel文件
     */
    private File excel;
    /**
     * excel对象
     */
    private Workbook workbook;
    /**
     * 异常消息
     */
    private String errorMsg;
    /**
     * 导入状态标识
     */
    private int code = ExcelConstants.EXCEL_IMPORT_SUC;
    /**
     * excel有效数据总行数
     */
    private int totalNum;
    /**
     * 初始校验单元格
     */
    private AbstractCellChecker firstChecker;
    /**
     * 错误数据列表(不能重复，所以用set保证不重复)
     */
    private volatile Set<ImportBo> fails = new TreeSet<ImportBo>();
    /**
     * 待插入数据
     */
    private List<ImportBo> readyForInsert = new CopyOnWriteArrayList<ImportBo>();
    /**
     * 错误行号，描述
     */
    private Map<Integer, String> failMap = new ConcurrentHashMap<Integer, String>();

    /**
     * @description:设置失败
     *
     * @param reason    失败原因
     * @author jianhua.luo
     * @date 2019/7/29
     */
    public void setFail(String reason) {
        this.code = ExcelConstants.EXCEL_IMPORT_FAIL;
        this.errorMsg = reason;
    }

    /**
     * @description:是否导入成功
     *
     * @author jianhua.luo
     * @date 2019/7/29
     */
    public boolean isSuccess() {
        return ExcelConstants.EXCEL_IMPORT_SUC == this.code;
    }

    //1、先校验文件格式是否正确
    public void checkSuffix() {
        String fileName = excel.getName();
        String suffix = fileName.substring(fileName.lastIndexOf("."), fileName.length());
        System.out.println(fileName);

        try {
            if(ExcelConstants.EXCEL_2003_SUFFIX.equalsIgnoreCase(suffix)) {
                this.workbook = new HSSFWorkbook(new FileInputStream(excel));
            } else if(ExcelConstants.EXCEL_2007_SUFFIX.equalsIgnoreCase(suffix)) {
                this.workbook = new XSSFWorkbook(new FileInputStream(excel));
            } else {
                setFail("请导入excel类型的文件");
            }
        } catch (IOException e) {
            log.error("请导入excel类型的文件", e);
            setFail("请导入excel类型的文件");
        }
    }

    //2、校验文件头是否正确
    public void checkHeader() {
        Sheet sheet = this.workbook.getSheetAt(0);
        Row row2 = sheet.getRow(1);
        for (int i = 0; i < ExcelConstants.EXCEL_HEADRE_ARR.length; i++) {
            if(!ExcelConstants.EXCEL_HEADRE_ARR[i].equals(getCellValue2String(row2.getCell(i)))) {
                setFail("文件头格式不正确");
                break;
            }
        }
        System.out.println(sheet.getLastRowNum());
    }

    //3、校验文件每一行内容是否符合格式
    public void checkData(List<Row> rows) {
        if(fails.size() < ExcelConstants.MAX_FAIL_NUM) {
            for (Row row : rows) {
//                firstChecker.checkData(row);
            }
        }
    }

    /**
     * @description:初始内容校验步骤
     *
     * @author jianhua.luo
     * @date 2019/7/29
     */
    public void initCheckStep() {
        AbstractCellChecker mobileChecker = new MemberMobileChecker();
        AbstractCellChecker nameChecker = new MemberNameChecker();
        AbstractCellChecker sexChecker = new MemberSexChecker();
        AbstractCellChecker cityChecker = new CityChecker();
        AbstractCellChecker sicChannelChecker = new SicChannelChecker();
        AbstractCellChecker contactChecker = new MemberContactChecker();
        mobileChecker.setNext(nameChecker).setNext(sexChecker).setNext(cityChecker)
            .setNext(sicChannelChecker).setNext(contactChecker);
        mobileChecker.setExcelImport(this);
        nameChecker.setExcelImport(this);
        sexChecker.setExcelImport(this);
        cityChecker.setExcelImport(this);
        sicChannelChecker.setExcelImport(this);
        contactChecker.setExcelImport(this);
        this.firstChecker = mobileChecker;
    }

    //4、执行导入过程，插入数据（批量插入）
    //5、直接插入有可能插入相同数据，做一次补偿。保留重复数据最早的一次数据。
    //6、异步调用会员系统关联会员信息


    /**
     * @description:获取所有有效的数据行
     *
     * @author jianhua.luo
     * @date 2019/7/29
     */
    public List<Row> getAllRows() {
        Sheet sheet = this.workbook.getSheetAt(0);
        List<Row> rows = new ArrayList<Row>();
        if(sheet == null) {
            return rows;
        }
        int dataStartRow = 2;
        this.totalNum = sheet.getLastRowNum() - dataStartRow;
        for (int i = dataStartRow; i < this.totalNum; i++) {
            rows.add(sheet.getRow(i));
        }
        return rows;
    }

    /**
     * @description:获取单元格值的过程
     *
     * @author jianhua.luo
     * @date 2019/7/29
     */
    public static String getCellValue2String(Cell cell) {
        String str = "";
        if(cell == null) {
            return str;
        }

        switch(cell.getCellType()) {
            case Cell.CELL_TYPE_NUMERIC :
                double d = cell.getNumericCellValue();
                str = String.valueOf(d);
                break;
            case Cell.CELL_TYPE_STRING :
                str = cell.getStringCellValue().trim();
                break;
            default:
        }
        return str;
    }

}
