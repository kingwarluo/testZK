package com.设计模式.builder.enums;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author kingwarluo
 * 目标类型枚举类
 * @date 2019/1/12 11:22
 */
public enum TargetTypeEnum {

    /**
     * 网站会员
     */
    CUSTOMER(1, "会员1");

    /**
     * 代码
     */
    private Integer code;
    /**
     * 描述
     */
    private String desc;

    TargetTypeEnum(Integer code, String desc){
        this.code = code;
        this.desc = desc;
    }

    private static final Map<Integer, String> MAP = new HashMap<>();
    private static final List<Map<String, Object>> LIST = new ArrayList<>();

    /**
     * 为什么要写toList、toMap方法，能在类内部做的，尽量不要在外部写
     */

    /**
     * 将枚举值转换成MAP
     * @return
     */
    public static Map toMap(){
        if(MAP.isEmpty()){
            /**
             * 对类进行加锁，该类所有对象公用一把锁
             */
            synchronized (TargetTypeEnum.class){
                /**
                 * 多做一次非空判断
                 * 因为在外部判断之后，其它线程刚执行完同步块
                 */
                if(!MAP.isEmpty()){
                    return MAP;
                }
                for (TargetTypeEnum targetType : TargetTypeEnum.values()){
                    MAP.put(targetType.code, targetType.desc);
                }
            }
        }
        return MAP;
    }

    /**
     * 将枚举转换成List
     * @return
     */
    public static List toList(){
        if(LIST.isEmpty()){
            /**
             * 对类进行加锁
             */
            synchronized (TargetTypeEnum.class){
                /**
                 * 重复验证LIST非空
                 */
                if (!LIST.isEmpty()) {
                    return LIST;
                }
                Map<String, Object> map;
                for (TargetTypeEnum targetType : TargetTypeEnum.values()){
                    map = new HashMap<>();
                    map.put("code", targetType.code);
                    map.put("desc", targetType.desc);
                    LIST.add(map);
                }
            }
        }
        return LIST;
    }

    /**
     * 根据代码获取描述
     * @param code
     * @return
     */
    public static String getDesc(Integer code){
        TargetTypeEnum[] enums = values();
        for (TargetTypeEnum targetType : enums){
            if(targetType.code.equals(code)){
                return targetType.desc;
            }
        }
        return null;
    }

    /**
     * 根据代码获取枚举类型
     * @param code
     * @return
     */
    public static TargetTypeEnum getEnum(Integer code){
        TargetTypeEnum[] enums = values();
        for (TargetTypeEnum targetType : enums){
            if(targetType.code.equals(code)){
                return targetType;
            }
        }
        return null;
    }

    public String getDesc(){
        return desc;
    }

    public Integer getCode(){
        return code;
    }

    public static void main(String[] args) {
        String desc = TargetTypeEnum.getDesc(1);
        System.out.println(desc);
        System.out.println(TargetTypeEnum.toList());
        System.out.println(TargetTypeEnum.toMap());
    }

}
