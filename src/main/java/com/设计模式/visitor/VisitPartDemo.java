package com.设计模式.visitor;

import com.设计模式.visitor.part.Computer;
import com.设计模式.visitor.part.ComputerPart;

/**
 * @author kingwarluo
 * @{description}
 * @date 2024/1/18 11:57
 */
public class VisitPartDemo {

    public static void main(String[] args) {
        ComputerPart computer = new Computer();
        computer.accept(new ComputerPartDisplayVisitor());
    }

}
