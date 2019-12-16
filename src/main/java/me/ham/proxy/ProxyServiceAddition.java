package me.ham.proxy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service(value = "proxyService")
public class ProxyServiceAddition implements ProxyService{

    ProxyService proxyServiceLogic;

    public ProxyServiceAddition(ProxyService proxyService){
        this.proxyServiceLogic = proxyService;
    }

    @Override
    public String forTest() {
        System.out.println("ProxyServiceAddition::여기에 부가기능을 추가합니다.");
        return proxyServiceLogic.forTest();
    }
}
