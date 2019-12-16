package me.ham.proxy;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
public class ProxyServiceLogic implements ProxyService {
    @Override
    public String forTest() {
        return "ProxyServiceLogin::여기에 메인로직이 담겨 있습니다.";
    }
}
