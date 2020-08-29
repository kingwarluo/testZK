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

    public static List<Map<Long, Object>> queryUserByIdBatch(List<Long> ids) {
        List<Map<Long, Object>> list = new ArrayList<>();
        for (Long id : ids) {
            Map<Long, Object> map = new HashMap<Long, Object>();
            map.put(id, new Object());
            list.add(map);
        }
        return list;
    }

}
