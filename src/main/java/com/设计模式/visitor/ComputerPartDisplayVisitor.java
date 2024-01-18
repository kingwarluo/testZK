package com.设计模式.visitor;

import com.设计模式.visitor.part.Computer;
import com.设计模式.visitor.part.KeyBoard;
import com.设计模式.visitor.part.Monitor;
import com.设计模式.visitor.part.Mouse;

/**
 * @author kingwarluo
 * @{description}
 * @date 2024/1/18 11:56
 */
public class ComputerPartDisplayVisitor implements ComputerPartVisitor {
    @Override
    public void visit(Mouse mouse) {
        System.out.println("mouse");
    }

    @Override
    public void visit(Monitor monitor) {
        System.out.println("monitor");
    }

    @Override
    public void visit(KeyBoard keyBoard) {
        System.out.println("keyboard");
    }

    @Override
    public void visit(Computer computer) {
        System.out.println("computer");
    }
}
