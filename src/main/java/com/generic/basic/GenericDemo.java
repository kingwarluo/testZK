package com.generic.basic;

/**
 * 泛型 demo
 * 参考：https://www.cnblogs.com/coprince/p/8603492.html
 *
 * 泛型即“参数化类型”，一提到参数，最熟悉的是定义方法时有形参，调用方法时传入实参，那参数化类型怎么理解？
 * 顾名思义，就是将原来的具体的类型参数化，类似于方法中的变量参数，此时类型也定义成参数形式（可称之为类型形参）
 * 然后在调用时传入具体的类型（类型实参）
 * 泛型的本质是为了参数化类型（在不创建新的类型的情况下，通过泛型指定不同类型来空值形参具体限制的类型）。
 * 也就是说在泛型使用过程中，操作的数据类型被指定为一个参数，这种参数类型可以用在类、接口和方法中，分别被称为：泛型类、泛型接口、泛型方法。
 *
 * 类型擦除：
 *
 * @author jianhua.luo
 * @date 2020/10/29
 */
public class GenericDemo {

    public static void main(String[] args) {
//        // 一个被举了无数次的例子：
//        List arrayList = new ArrayList();
//        arrayList.add("aaaa");
//        arrayList.add(100);
//
//        for(int i = 0; i< arrayList.size();i++){
//            // 由于上面未指定泛型，List添加了String又添加int类型数据，在此用String方式获取时，就报错了。
//            // 如果指定泛型，在编译时就可以解决此类问题
//            String item = (String)arrayList.get(i);
//            System.out.println("泛型测试，item = " + item);
//        }

//        // 泛型只在编译阶段有效，泛型信息不会进入到运行时阶段，看如下代码
//        // 对此总结成一句话：泛型类型在逻辑上看以看成是多个不同的类型，实际上都是相同的基本类型。
//        List<String> stringArrayList = new ArrayList<String>();
//        List<Integer> integerArrayList = new ArrayList<Integer>();
//
//        Class classStringArrayList = stringArrayList.getClass();
//        Class classIntegerArrayList = integerArrayList.getClass();
//
//        if(classStringArrayList.equals(classIntegerArrayList)){
//            System.out.println("泛型测试，类型相同");
//        }

//        //泛型的类型参数只能是类类型（包括自定义类），不能是简单类型
//        //传入的实参类型需与泛型的类型参数类型相同，即为Integer.
//        Generic<Integer> genericInteger = new Generic<Integer>(123456);
//
//        //传入的实参类型需与泛型的类型参数类型相同，即为String.
//        Generic<String> genericString = new Generic<String>("key_vlaue");
//        System.out.println("泛型测试，key is " + genericInteger.getType());
//        System.out.println("泛型测试，key is " + genericString.getType());

//        // 泛型通配符
//        Generic<Integer> genericNumber = new Generic<Integer>(123456);
//        showKeyValue1(genericNumber); // 编译器报错，虽然Integer是Number的子类
    }

    // 泛型通配符
    // 类型通配符一般使用？代替具体的类型实参（注意：此处？是类型实参，不是类型形参）
    // 此处的？和Integer、Number、String一样都是一种实际的类型，可以把？看成所有类型的父类
    // 可以解决当具体类型不确定的时候，这个通配符就是 ?  ；当操作类型时，不需要使用类型的具体功能时，只使用Object类中的功能。那么可以用 ? 通配符来表未知类型。
    public static void showKeyValue1(Generic<?> obj){
        System.out.println("泛型测试, key value is " + obj.getType());
    }

}
