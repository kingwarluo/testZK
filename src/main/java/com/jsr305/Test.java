package com.jsr305;

import lombok.Data;

import javax.annotation.Nonnull;

/**
 * @description:测试jsr305包 {com.google.code.findbugs}
 *
 * @author jianhua.luo
 * @date 2019/5/16
 */
public class Test {

    public static void main(String[] args) {
        User user = null;
        setUserInfo(user);
    }

    public static void setUserInfo(@Nonnull User user){
        user.setName("");
        user.setPassword("");
    }

    @Data
    public class User {

        private String name;
        private String password;

    }

}
