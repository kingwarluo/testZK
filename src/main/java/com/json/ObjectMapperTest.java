package com.json;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.io.IOException;

/**
 * @description:ObjectMapper类测试
 *
 * @author jianhua.luo
 * @date 2019/7/16
 */
public class ObjectMapperTest {

    public static void main(String[] args) {
        User user = new User(1L, "huage", 12);

        try {
            objToJsonAndByteArr(user);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void objToJsonAndByteArr(User user) throws IOException {
        ObjectMapper mapper = getObjectMapper();

        /**
         * 写是write，读是read
         */
        String jsonStr = mapper.writeValueAsString(user);
        System.out.println("对象转为json字符串：" + jsonStr);

        byte[] byteArr = mapper.writeValueAsBytes(user);
        System.out.println("对象转为byte数组：" + byteArr);

        User user1 = mapper.readValue(jsonStr, User.class);
        System.out.println("json字符串转对象：" + user1);

        User user2 = mapper.readValue(byteArr, User.class);
        System.out.println("byte数组转对象：" + user2);
    }

    public static ObjectMapper getObjectMapper() {
        ObjectMapper mapper = new ObjectMapper();
        //反序列化时，如果json中有字段在实体类中不存在，不报错
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        //序列化时，可以写入空map
        mapper.configure(SerializationFeature.WRITE_NULL_MAP_VALUES, false);
        //序列化时，不包装一层根节点
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        return mapper;
    }

}
