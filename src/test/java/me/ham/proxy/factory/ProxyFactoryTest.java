package me.ham.proxy.factory;

import me.ham.proxy.dynamic.Hello;
import me.ham.proxy.dynamic.HelloTarget;
import me.ham.proxy.dynamic.UppercaseHandler;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ProxyFactoryTest {

    @Autowired
    ProxyFactory proxyFactory;

    @Test
    public void proxyFactoryTest() throws Exception {
        proxyFactory.setTarget(new HelloTarget());
        proxyFactory.setServiceInterface(Hello.class);
        assertNotNull(proxyFactory.getObject());
        Hello hello = (Hello) proxyFactory.getObject();

        String result = hello.sayHello("hamhosik");
        assertEquals(true, result.startsWith("HAMHOSIK"));
    }

}