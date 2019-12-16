package me.ham.proxy.dynamic;

import me.ham.proxy.dynamic.Hello;
import me.ham.proxy.dynamic.UppercaseHandler;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cglib.proxy.InvocationHandler;
import org.springframework.cglib.proxy.Proxy;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class HelloTest {

    @Test
    public void dynamicProxyTest(){
        UppercaseHandler uppercaseHandler = new UppercaseHandler();
        uppercaseHandler.setTarget(new HelloTarget());
        Hello proxiedHello = (Hello) Proxy.newProxyInstance(getClass().getClassLoader()
                , new Class[]{Hello.class}
                , uppercaseHandler);
        
        String result = proxiedHello.sayHello("hamhosik");
        assertEquals(true, result.startsWith("HAMHOSIK"));
    }
}