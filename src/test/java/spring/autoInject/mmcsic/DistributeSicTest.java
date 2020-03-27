package spring.autoInject.mmcsic;

import com.spring.autoInject.mmcsic.DistributeSicHandlerContext;
import com.spring.autoInject.mmcsic.handler.AbstractDistributeSicHandler;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * 策略模式自动注入spring测试
 *
 * @author jianhua.luo
 * @date 2020/3/27
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:frameworkContext.xml"})
public class DistributeSicTest {

    @Autowired
    private DistributeSicHandlerContext context;

    @Test
    public void invokeHandler() {
        AbstractDistributeSicHandler handler = context.getInstance("1");
        handler.sayHello();
    }

}
