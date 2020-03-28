package spring.autoInject.beanDefinition;

import com.spring.autoInject.beanDefinition.dao.UserMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:frameworkContext.xml"})
public class BeanDefinitionTest {

    @Autowired
    private ApplicationContext applicationContext;

    @Test
    public void autoInject() {
        UserMapper userMapper = applicationContext.getBean(UserMapper.class);
        userMapper.add("lalaldsf");
    }

}
