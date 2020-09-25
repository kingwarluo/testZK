package com.http;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 远程服务
 *
 * @author jianhua.luo
 * @date 2020/8/29
 */
public class QueryServiceRemoteCall {

    public static List<User> queryUserByIdBatch(List<Long> ids) {
        if(ids.size() == 0) {
            return new ArrayList<>();
        }
        List<User> list = new ArrayList<>(ids.size());
        for (Long id : ids) {
            //这段代码会异常退出，也没有错误提示
            list.add(new User(id, id.toString(), id.intValue()));
        }
        System.out.println("ids:" + ids.size() + ", list" + list.size());
        return list;
    }

}
