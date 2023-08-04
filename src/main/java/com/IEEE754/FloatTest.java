package com.IEEE754;

import java.text.DecimalFormat;

/**
 * @author kingwarluo
 * @{description}
 * @date 2023/8/4 15:53
 */
public class FloatTest {

    public static void main(String[] args) {
        // 10.625
        int i = 0b01000001001010100000000000000000;
        float v = Float.intBitsToFloat(i);
        System.out.println(v);

        float f = 0xa.ap0f;
        System.out.println(f);

        float f2 = 0x1.54p3f;
        System.out.println(f2);

        float n1 = 10.4f;
        float n2 = 10.40000000000000001f;
        System.out.println(n1 == n2);
    }

}
