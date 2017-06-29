package com.java8.demo03;

import org.junit.Test;

/**
 * 1.一个所谓的函数式接口必须要有且仅有一个抽象方法声明
 * 2.由于默认方法不是抽象的，因此你可以在你的函数式接口里任意添加默认方法
 * 3.如果你的接口中定义了第二个抽象方法的话，编译器会抛出异常。
 * 4.不写@FunctionalInterface 标注，程序也是正确
 * Created by liuburu on 2017/6/8.
 */
@FunctionalInterface
interface MyConverter<S, I> {
    I convert(S from);

    //如果你的接口中定义了第二个抽象方法的话，编译器会抛出异常
   // void fun();
    default double sqrt(int a) {
        return Math.sqrt(a);
    }
}
class Something {
    String startsWith(String s) {
        return String.valueOf(s.charAt(0));
    }
}

class Person {
    String firstName;
    String lastName;

    Person() {}

    Person(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    @Override
    public String toString() {
        return "Person{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }
}
interface PersonFactory<P extends Person> {
    P create(String firstName, String lastName);
}

public class TestFunctionalInterface {

    @Test
    public void test(){
        /*
        * 1.使用lambda
        * */
//        MyConverter<String, Integer> converter = (from) -> Integer.valueOf(from);
        /**
         * 2.通过静态方法引用，使之更加简洁
         */
//        MyConverter<String, Integer> converter = Integer::valueOf;
//        Integer converted = converter.convert("123");
//        System.out.println(converted);    // 123
//        System.out.println(converter.sqrt(81));

        /**
         * 3.还可以对一个对象的方法进行引用
         *允许你通过::关键字获取方法或者构造函数的的引用
         */
        Something something = new Something();
        MyConverter<String, String> converter = something::startsWith;
        String converted = converter.convert("Java");
        System.out.println(converted);    // 123
        System.out.println(converter.sqrt(81));// 9


        /**
         * 4.使用::关键字引用构造函数
         */
        PersonFactory<Person> personFactory = Person::new;
        Person person = personFactory.create("Peter", "Parker");
        System.out.println(person);
    }
}
