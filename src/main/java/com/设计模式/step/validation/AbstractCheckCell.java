package com.设计模式.step.validation;

/**
 * @description:步骤校验
 *
 * @author jianhua.luo
 * @date 2019/6/13
 */
public abstract class AbstractCheckCell {

    private AbstractCheckCell nextCheck;

    public abstract String checkCell();

}
