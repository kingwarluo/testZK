package com.设计模式.builder;

/**
 * @author kingwarluo
 * 可重新构建接口
 * @date 2019/1/11 17:14
 */
public interface Rebuildable<T extends Builder> {

    /**
     * 重新构建回builder
     * @return
     */
    T rebuild();

}
