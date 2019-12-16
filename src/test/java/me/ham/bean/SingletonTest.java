package me.ham.bean;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@ContextConfiguration("file:/Users/hamhosik/IdeaProjects/jpa-demo/src/main/resources/bean-source.xml")
class SingletonTest {

    @Autowired
    ApplicationContext applicationContext;

    @Test
    public void singletonTest(){
        Singleton singleton1 = (Singleton) applicationContext.getBean("singleton1");
        Singleton singleton2 = (Singleton) applicationContext.getBean("singleton2");
        assertEquals(false, singleton1==singleton2);
        System.out.println(singleton1.getName());
        System.out.println(singleton1.toString());
        System.out.println(singleton2.getName());
        System.out.println(singleton2.toString());

        System.out.println("=============");
        Singleton singleton1_1 = (Singleton) applicationContext.getBean("singleton1");
        assertEquals(true, singleton1==singleton1_1);
        System.out.println(singleton1_1.getName());
        System.out.println(singleton1_1.toString());
    }
}