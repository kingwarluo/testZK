package com.bean.copy;

import com.alibaba.fastjson.JSON;
import com.alibaba.testable.core.tool.OmniConstructor;
import net.sf.cglib.beans.BeanCopier;
import org.apache.commons.lang3.time.StopWatch;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 三种bean copy 效率对比 cglib、spring beanUtil、apache beanutils
 *
 * @author kingwarluo
 * @date 2022/3/30 10:22
 */
public class BeanCopy {

    public static void main(String[] args) {
        Shop[] arr = OmniConstructor.newArray(Shop.class, 100);
        List<Shop> shopList = Arrays.asList(arr);

        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        // json copy
        List<ShopInExportVo> jsonCopy = new ArrayList<>(shopList.size());
        for (Shop shop : shopList) {
            ShopInExportVo shopInExportVo = new ShopInExportVo();
            beanCopy(shopInExportVo, shop);
            jsonCopy.add(shopInExportVo);
        }
        stopWatch.split();
        System.out.println("json copy耗时：" + stopWatch.getSplitTime());
        stopWatch.reset();
        stopWatch.start();

        // Apache copy
        List<ShopInExportVo> apacheCopy = new ArrayList<>(shopList.size());
        for (Shop shop : shopList) {
            ShopInExportVo shopInExportVo = new ShopInExportVo();
            beanCopy(shopInExportVo, shop);
            apacheCopy.add(shopInExportVo);
        }
        stopWatch.split();
        System.out.println("Apache copy耗时：" + stopWatch.getSplitTime());
        stopWatch.reset();
        stopWatch.start();

        // Spring copy
        List<ShopInExportVo> springCopy = new ArrayList<>(shopList.size());
        for (Shop shop : shopList) {
            ShopInExportVo shopInExportVo = new ShopInExportVo();
            beanCopy(shopInExportVo, shop);
            springCopy.add(shopInExportVo);
        }
        stopWatch.split();
        System.out.println("Spring copy耗时：" + stopWatch.getSplitTime());
        stopWatch.reset();
        stopWatch.start();

        // cglib copy
        List<ShopInExportVo> cglibCopy = new ArrayList<>(shopList.size());
        for (Shop shop : shopList) {
            ShopInExportVo shopInExportVo = new ShopInExportVo();
            beanCopy(shopInExportVo, shop);
            cglibCopy.add(shopInExportVo);
        }
        stopWatch.split();
        System.out.println("cglib copy耗时：" + stopWatch.getSplitTime());

        stopWatch.stop();
    }

    //Apache copy
    public static <T> T beanCopy(T t, Object orig) {
        try {
            BeanUtils.copyProperties(t, orig);
        } catch (Exception e) {
            throw new RuntimeException("Apache BeanUtils复制对象属性出错", e);
        }
        return t;
    }

    //Spring copy
    public static <T> T copybean(T t, Object orig) {
        try {
            org.springframework.beans.BeanUtils.copyProperties(orig, t);
        } catch (Exception e) {
            throw new RuntimeException("Spring BeanUtils复制对象属性出错", e);
        }
        return t;
    }

    //cglib copy
    public static <T> T cglibcopy(T t, Object orig, Class clazz) {
        try {
            final BeanCopier beanCopier = BeanCopier.create(clazz, clazz, false);
            beanCopier.copy(orig, t, null);

        } catch (Exception e) {
            throw new RuntimeException("cglib BeanCopier复制对象属性出错", e);
        }
        return t;
    }

    //json copy
    public static Object jsonCopy(Object orgi, Class clazz) {
        try {
            String json = JSON.toJSONString(orgi);
            return JSON.parseObject(json, clazz);

        } catch (Exception e) {
            throw new RuntimeException("json parse复制对象属性出错", e);
        }
    }

}
