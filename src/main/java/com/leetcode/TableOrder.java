package com.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 点餐订单
 *
 * @author jianhua.luo
 * @date 2021/7/6
 */
public class TableOrder {

    /**
     * 输入：orders = [["David","3","Ceviche"],["Corina","10","Beef Burrito"],["David","3","Fried Chicken"],["Carla","5","Water"],["Carla","5","Ceviche"],["Rous","3","Ceviche"]]
     * 输出：[["Table","Beef Burrito","Ceviche","Fried Chicken","Water"],["3","0","2","1","0"],["5","0","1","0","1"],["10","1","0","0","0"]]
     * 解释：
     * 点菜展示表如下所示：
     * Table,Beef Burrito,Ceviche,Fried Chicken,Water
     * 3    ,0           ,2      ,1            ,0
     * 5    ,0           ,1      ,0            ,1
     * 10   ,1           ,0      ,0            ,0
     *
     * 提示：
     * 1 <= orders.length <= 5 * 10^4
     * orders[i].length == 3
     * 1 <= customerNamei.length, foodItemi.length <= 20
     * customerNamei 和 foodItemi 由大小写英文字母及空格字符 ' ' 组成。
     * tableNumberi 是 1 到 500 范围内的整数。
     *
     * @param args
     */
    public static void main(String[] args) {
        String param = args[0].substring(2, args[0].length() - 2);
        String[] orderArr = param.split("],\\[");

        List<List<String>> orders = new ArrayList<List<String>>();
        for (String order : orderArr) {
            String[] o = order.split(",");
            orders.add(Arrays.asList(o));
        }
        System.out.println(orders);
        displayTable(orders);
    }

    /**
     * * 点菜展示表如下所示：
     *      * Table,Beef Burrito,Ceviche,Fried Chicken,Water
     *      * 3    ,0           ,2      ,1            ,0
     *      * 5    ,0           ,1      ,0            ,1
     *      * 10   ,1           ,0      ,0            ,0
     *
     *  注意：菜品名称按升序排列，表中的数据行应该按餐桌桌号升序排列
     * @param orders
     * @return
     */
    public static List<List<String>> displayTable(List<List<String>> orders) {
        Set<String> nameSet = new HashSet<String>();
        Map<Integer, Map<String, Integer>> mapResult = new HashMap<>();
        for (List<String> order : orders) {
            nameSet.add(order.get(2));
            Integer id = Integer.valueOf(order.get(1));
            Map<String, Integer> table = mapResult.getOrDefault(id, new HashMap<String, Integer>());
            table.put(order.get(2), table.getOrDefault(order.get(2), 0) + 1);
            mapResult.put(id, table);
        }

        List<String> nameList = new ArrayList<>(nameSet);
        Collections.sort(nameList);
        List<String> lineOne = new ArrayList<>();
        lineOne.add("Table");
        lineOne.addAll(nameList);

        List<Integer> keySet = new ArrayList<>(mapResult.keySet());
        Collections.sort(keySet);

        List<List<String>> result = new ArrayList<>();
        // 拼接结果
        result.add(lineOne);
        for (Integer tableNo : keySet) {
            if(mapResult.get(tableNo) != null) {
                Map<String, Integer> table = mapResult.get(tableNo);
                List<String> countLine = new ArrayList<>();
                countLine.add(String.valueOf(tableNo));
                for (int i = 1; i < lineOne.size(); i++) {
                    countLine.add(Integer.toString(table.getOrDefault(lineOne.get(i), 0)));
                }
                result.add(countLine);
            }
        }
        return result;
    }

}
