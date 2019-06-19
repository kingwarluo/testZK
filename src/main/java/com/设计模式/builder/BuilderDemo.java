package com.设计模式.builder;

/**
 * @author kingwarluo
 * 运行建造者模式
 * @date 2019/1/14 10:38
 */
public class BuilderDemo {

    public static void main(String[] args) {
        BroadcastMessageBuilder builder = BroadcastMessageBuilder.newBuilder(new BroadcastMessage());
        builder.addCityId(11L);
        builder.addCityId(22L);
        builder.addCityId(33L);
        BroadcastMessage bm = (BroadcastMessage) builder.build();
        System.out.println(bm);

    }

}
