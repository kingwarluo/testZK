package com.设计模式.biz.excel.validator;

import com.设计模式.biz.excel.constant.ExcelConstants;
import org.apache.commons.lang.StringUtils;

import java.util.regex.Pattern;

/**
 * @description:校验电话格式
 *
 * @author jianhua.luo
 * @date 2019/7/30
 */
public class MemberMobileChecker extends AbstractCellChecker {

    @Override
    public String check(String value) {
        if(StringUtils.isEmpty(value)) {
            return "电话号码不能为空";
        }
        if(!Pattern.matches(ExcelConstants.REGEX_MOBILE, value)) {
            return "电话号码格式不正确";
        }
        return StringUtils.EMPTY;
    }

}
