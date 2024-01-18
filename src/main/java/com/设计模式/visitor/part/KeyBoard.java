package com.设计模式.visitor.part;

import com.设计模式.visitor.ComputerPartVisitor;

/**
 * @author kingwarluo
 * @{description}
 * @date 2024/1/18 11:51
 */
public class KeyBoard implements ComputerPart {
    @Override
    public void accept(ComputerPartVisitor visitor) {
        visitor.visit(this);
    }
}
