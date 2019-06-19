package com.zookeeper;

import org.apache.zookeeper.server.auth.DigestAuthenticationProvider;

import java.security.NoSuchAlgorithmException;

/**
 * @author KingWarLuo
 * @description: ${description}
 * @date 2018-08-15 17:14
 */
public class AclUtil {

    public static String getDigestUserPwd(String id) throws NoSuchAlgorithmException {
        return DigestAuthenticationProvider.generateDigest(id);
    }

    public static void main(String[] args) {
        try {
            System.out.println(getDigestUserPwd("super:super"));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }

}
