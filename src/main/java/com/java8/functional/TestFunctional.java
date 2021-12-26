package com.java8.functional;

import java.math.BigDecimal;

/**
 * @author kingwarluo
 * @{description}
 * @date 2021/12/26 13:44
 */
public class TestFunctional {

    public static void main(String[] args) {
        NormalInterface<Integer> normalInterface = str -> str.hashCode();
        System.out.println(normalInterface.getStr("33333"));

        System.out.println(calcDiscountPrice(370L, 125L));
    }

    public static BigDecimal calcDiscountPrice(Long retailPrice, Long discount) {
        BigDecimal retailPriceBig = new BigDecimal(retailPrice);
        BigDecimal discountBig = new BigDecimal(discount);
        BigDecimal result = discountBig.divide(new BigDecimal(1000));
        return retailPriceBig.multiply(result);
    }
}
