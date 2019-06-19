package com.logger;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author KingWarLuo
 * @description: ${description}
 * @date 2018-08-14 10:48
 */
public class LogAspectTest {

    public static void main(String[] args) {
        OperateLogService service = new OperateLogService();
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        service.getValue("1", "2");
        System.out.println("1111");
    }

}
