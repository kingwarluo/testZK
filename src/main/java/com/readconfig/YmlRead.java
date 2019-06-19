package com.readconfig;

import com.bean.Person;
import com.bean.User;
import org.apache.commons.collections.map.UnmodifiableMap;
import org.yaml.snakeyaml.Yaml;

import java.io.*;
import java.util.Map;

/**
 * @author KingWarLuo
 * @description: ${description}
 * @date 2018-08-24 09:51
 */
public class YmlRead implements Serializable {

    private static final String LOG_FILE_POSITION = "log.yml";

    private static final String SERIALIZABLE_FILE = "serializable.txt";

    public static void main(String[] args) {
        Map LOG_TEMPLATE_MAP = UnmodifiableMap.decorate((Map) new Yaml()
                .load(YmlRead.class.getClassLoader().getResourceAsStream(LOG_FILE_POSITION)));
        System.out.println(LOG_TEMPLATE_MAP.get("notice"));

//        try {
//            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(SERIALIZABLE_FILE));
//            oos.writeObject(new User(1, "huage", 30));
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(SERIALIZABLE_FILE));
            User user = (User) ois.readObject();
            System.out.println(user);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

}
