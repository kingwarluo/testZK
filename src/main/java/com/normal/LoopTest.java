package com.normal;

import java.util.ArrayList;
import java.util.List;

/**
 * @author KingWarLuo
 * @description: ${description}
 * @date 2018-08-16 09:31
 */
public class LoopTest {

    public static void main(String[] args) {

        List<String> list = new ArrayList<String>();
        for (int i = 0; i < 1570; i++) {
            list.add("222");
        }

        int currentPage = 1;
        int pageSize = 500;
        int currentCount;
        int totalCount;
        totalCount = list.size();
        do {
            int toIndex = (currentPage * pageSize > totalCount)?totalCount:currentPage * pageSize;
            List<String> strs = list.subList((currentPage - 1) * pageSize, toIndex);
            System.out.println(strs.size());
            currentCount = pageSize * currentPage;
            System.out.println(currentCount);
            currentPage++;
        } while (currentCount < totalCount);
    }

}
