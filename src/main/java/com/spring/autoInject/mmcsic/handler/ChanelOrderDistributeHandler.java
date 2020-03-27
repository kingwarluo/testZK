package com.spring.autoInject.mmcsic.handler;

import com.spring.autoInject.mmcsic.DistributeSicHandlerType;
import org.springframework.stereotype.Component;

@Component
@DistributeSicHandlerType(value = "1", description = "按渠道顺序分单")
public class ChanelOrderDistributeHandler extends AbstractDistributeSicHandler {
    @Override
    public void sayHello() {
        System.out.println("按渠道顺序分单");
    }
}
