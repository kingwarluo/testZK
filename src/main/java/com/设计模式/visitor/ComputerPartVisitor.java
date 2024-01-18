package com.设计模式.visitor;

import com.设计模式.visitor.part.Computer;
import com.设计模式.visitor.part.KeyBoard;
import com.设计模式.visitor.part.Monitor;
import com.设计模式.visitor.part.Mouse;

/**
 * @author kingwarluo
 * @{description}
 * @date 2024/1/18 11:49
 */
public interface ComputerPartVisitor {
    void visit(Mouse mouse);

    void visit(Monitor monitor);

    void visit(KeyBoard keyBoard);

    void visit(Computer computer);
}
