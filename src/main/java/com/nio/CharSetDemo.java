package com.nio;

import java.nio.charset.Charset;
import java.util.Iterator;
import java.util.Map;
import java.util.SortedMap;

public class CharSetDemo {

    public static void main(String[] args) {
        SortedMap<String, Charset> charMap = null;
        charMap = Charset.availableCharsets();
        Iterator<Map.Entry<String, Charset>> it = charMap.entrySet().iterator();
        while(it.hasNext()){
            Map.Entry<String, Charset> me = it.next();
            System.out.println(me.getKey() + "," + me.getValue());
        }
    }

}
