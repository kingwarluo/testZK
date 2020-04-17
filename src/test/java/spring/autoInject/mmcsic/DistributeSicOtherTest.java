package spring.autoInject.mmcsic;

import com.spring.autoInject.mmcsic.DistributeSicHandlerOtherContext;
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
public class DistributeSicOtherTest {

    @Autowired
    private DistributeSicHandlerOtherContext otherContext;

    @Test
    public void otherInvokeHandler() {
        AbstractDistributeSicHandler handler = otherContext.getInstance("2");
        handler.sayHello();
    }

}
