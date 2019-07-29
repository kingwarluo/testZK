package com.设计模式.biz.excel.constant;

public class ExcelConstants {

    /**
     * excel文件后缀（2003）
     */
    public final static String EXCEL_2003_SUFFIX = ".xls";
    /**
     * excel文件后缀(2007)
     */
    public final static String EXCEL_2007_SUFFIX = ".xlsx";
    /**
     * excel文件头
     */
    public final static String[] EXCEL_HEADRE_ARR = new String[]{"会员手机号", "客户姓名", "客户性别",
            "意向购车城市", "留咨渠道名称", "客户联系电话"};

    /*********************** 导入结果 ****************************/
    /**
     * 导入成功
     */
    public final static int EXCEL_IMPORT_SUC = 1;
    /**
     * 导入失败
     */
    public final static int EXCEL_IMPORT_FAIL = -1;
    /**
     * 最大错误条数
     */
    public final static int MAX_FAIL_NUM = 50;


}
