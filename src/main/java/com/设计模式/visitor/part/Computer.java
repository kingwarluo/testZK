package com.设计模式.visitor.part;

import com.设计模式.visitor.ComputerPartVisitor;

/**
 * @author kingwarluo
 * @{description}
 * @date 2024/1/18 11:52
 */
public class Computer implements ComputerPart {

    ComputerPart[] computerParts;

    public Computer() {
        computerParts = new ComputerPart[]{new KeyBoard(), new Mouse(), new Monitor()};
    }

    @Override
    public void accept(ComputerPartVisitor visitor) {
        for (ComputerPart computerPart : computerParts) {
            computerPart.accept(visitor);
        }
        visitor.visit(this);
    }
}
