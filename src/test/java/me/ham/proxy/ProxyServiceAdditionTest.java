package me.ham.proxy;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ProxyServiceAdditionTest {

    @Autowired
    ProxyService proxyService;

    @Test
    public void proxyTest(){
        String serviceImplStr = proxyService.forTest();
        System.out.println(serviceImplStr);
    }
}