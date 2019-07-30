package com.设计模式.biz.excel.validator;

import org.apache.commons.lang.StringUtils;

public class MemberNameChecker extends AbstractCellChecker {

    @Override
    public String check(String value) {
        return StringUtils.EMPTY;
    }

}
