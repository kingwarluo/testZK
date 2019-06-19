package com.logger;

import com.bean.Person;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author KingWarLuo
 * @description: ${description}
 * @date 2018-08-13 17:47
 */
public class OperateLogService {

    public String getValue(String a, String b){
        return "1";
    }

    public @LogParam("return") Person logParamTest(@LogParam("id") String id){
        return new Person();
    }

}
