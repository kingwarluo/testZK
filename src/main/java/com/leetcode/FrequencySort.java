package com.leetcode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FrequencySort {

    public static void main(String[] args) {
        String s = "sdfasghhrdwwwaadfa";
        s = frequencySort(s);
        System.out.println(s);
    }

    public static String frequencySort(String s) {
        Map<Character, Integer> map = new HashMap<>();
        char[] arr = s.toCharArray();
        for (char c : arr) {
            Integer count = map.getOrDefault(c, 0);
            map.put(c, ++count);
        }
        List<Character> list = new ArrayList<>(map.keySet());
        Collections.sort(list, (a, b) -> map.get(b) - map.get(a));

        StringBuilder sb = new StringBuilder();
        for (Character c : list) {
            Integer times = map.get(c);
            for (int i = 0; i < times; i++) {
                sb.append(c);
            }
        }
        return sb.toString();
    }

}
