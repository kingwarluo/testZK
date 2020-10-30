package com.generic.basic;

/**
 * 未传入泛型实参时，与泛型类的定义相同，在声明类的时候，必须将泛型的声明也一同加到类中
 * 即：public class FruitGenerator<T> implements Generator<T> {}
 *
 * 传入泛型实参时，定义一个生产器实现这个接口,虽然我们只创建了一个泛型接口Generator<T>
 * 但是我们可以为T传入无数个实参，形成无数种类型的Generator接口。
 * 在实现类实现泛型接口时，如已将泛型类型传入实参类型，则所有使用泛型的地方都要替换成传入的实参类型
 * @author jianhua.luo
 * @date 2020/10/29
 */
public class FruitGenerator implements Generator<String> {

    @Override
    public String next() {
        return null;
    }

}
