package com.kingwarluo.agent;

import java.lang.instrument.Instrumentation;

public class MyAgent {

    public static void premain(String agentOpts, Instrumentation inst) {
        System.out.println("============premain方法执行==========");
        System.out.println(agentOpts);
        inst.addTransformer(new MyTransformer());
    }

}
