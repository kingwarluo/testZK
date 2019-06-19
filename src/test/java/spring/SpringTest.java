package spring;

import com.spring.IDemo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

/**
 * @author kingwarluo
 * Spring初始化顺序测试
 * @date 2019/1/23 16:11
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:frameworkContext.xml"})
public class SpringTest {

//    @Autowired
//    private ApplicationContext context;

    @Resource(name = "initPostTest")
    private IDemo initPostBean;

    @Test
    public void test2(){
//        initPostBean.sayHello();
    }

    @Test
    public void test(){
//        Object obj = context.getBean("initPostTest");
//        IDemo proxy = (IDemo) obj;
        initPostBean.getName();
        initPostBean.getValue();
//        Object obj = context.getBean("&initPostTest");
//        SpringInitialzingPostProcessor initPostBean = (SpringInitialzingPostProcessor) obj;
//        initPostBean.sayHello();
    }

}
