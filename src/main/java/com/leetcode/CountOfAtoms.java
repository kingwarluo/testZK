package com.leetcode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * 原子的数量
 *
 * @author jianhua.luo
 * @date 2021/7/8
 */
public class CountOfAtoms {

    public static void main(String[] args) {
//        formula = "K4(OaN(SO3)2)2(OA)32Kt5";
        formula = "H20";
        String sortStr = countOfAtoms(formula);
        System.out.println(sortStr);
    }


    static int i, n;
    static String formula;

    /**
     * 遇到）括号就用栈，遇到数字就用map存储
     * @param formula
     * @return
     */
    public static String countOfAtoms(String formula) {
        i = 0;
        n = formula.length();
        Deque<Map<String, Integer>> stack = new LinkedList<>();
        stack.push(new HashMap<String, Integer>());

        while(i < n) {
            char c = formula.charAt(i);
            if(c == '(') {
                i++;
                stack.push(new HashMap<String, Integer>()); // 将一个空的哈希表压入栈中，准备统计括号内的原子数量
            } else if(c == ')') {
                i++;
                int num = parseNum(); // 括号右侧的数字
                Map<String, Integer> popMap = stack.pop(); // 弹出括号内的原子数量，压入下一个栈
                Map<String, Integer> topMap = stack.peek();
                for (Map.Entry<String, Integer> entry : popMap.entrySet()) {
                    String atom = entry.getKey();
                    int v = entry.getValue();
                    topMap.put(atom, topMap.getOrDefault(atom, 0) + v * num); // 将括号内的原子数量乘上 num，加到上一层的原子数量中
                }
            } else {
                // 收集普通原子
                String atom = parseAtom();
                int num = parseNum();
                Map<String, Integer> topMap = stack.peek();
                topMap.put(atom, topMap.getOrDefault(atom, 0) + num);
            }
        }

        Map<String, Integer> map = stack.peek();
        List<String> keyList = new ArrayList<>(map.keySet());
        Collections.sort(keyList, (a, b) -> a.compareTo(b));
        StringBuilder sb = new StringBuilder();
        for (String key : keyList) {
            sb.append(key).append(map.get(key));
        }
        return sb.toString();
    }

    public static String parseAtom() {
        StringBuilder sb = new StringBuilder();
        char c = formula.charAt(i++);
        sb.append(c);
        while(i < n && Character.isLowerCase(formula.charAt(i))) {
            sb.append(formula.charAt(i++));
        }
        return sb.toString();
    }

    public static int parseNum() {
        if(i == n || !Character.isDigit(formula.charAt(i))) {
            return 1; // 不是数字，视作 1
        }
        StringBuilder sb = new StringBuilder();
        char c;
        while(i < n && Character.isDigit(c = formula.charAt(i))) {
            sb.append(c);
            i++;
        }
        return Integer.parseInt(sb.toString());
    }

}
