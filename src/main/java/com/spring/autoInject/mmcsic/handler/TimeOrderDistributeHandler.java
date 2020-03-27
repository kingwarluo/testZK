package com.spring.autoInject.mmcsic.handler;

import com.spring.autoInject.mmcsic.DistributeSicHandlerType;
import org.springframework.stereotype.Component;

@Component
@DistributeSicHandlerType(value = "2", description = "按时间顺序分单")
public class TimeOrderDistributeHandler extends AbstractDistributeSicHandler {
    @Override
    public void sayHello() {
        System.out.println("按时间顺序分单");
    }
}
