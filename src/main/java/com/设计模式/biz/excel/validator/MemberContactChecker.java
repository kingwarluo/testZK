package com.设计模式.biz.excel.validator;

import com.设计模式.biz.excel.constant.ExcelConstants;
import org.apache.commons.lang.StringUtils;

import java.util.regex.Pattern;

public class MemberContactChecker extends AbstractCellChecker {

    @Override
    public String check(String value) {
        if(!Pattern.matches(ExcelConstants.REGEX_MOBILE, value)) {
            return "电话号码格式不正确";
        }
        return StringUtils.EMPTY;
    }

}
