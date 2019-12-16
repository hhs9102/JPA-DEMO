package me.ham.proxy.factory;

import me.ham.proxy.dynamic.UppercaseHandler;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.cglib.proxy.Proxy;
import org.springframework.stereotype.Component;

@Component
public class ProxyFactory implements FactoryBean<Object> {

    Object target;
    Class<?> serviceInterface;

    public void setTarget(Object target) {
        this.target = target;
    }

    public void setServiceInterface(Class<?> serviceInterface) {
        this.serviceInterface = serviceInterface;
    }

    @Override
    public Object getObject() throws Exception {
        UppercaseHandler uppercaseHandler = new UppercaseHandler();
        uppercaseHandler.setTarget(target);
        return Proxy.newProxyInstance(getClass().getClassLoader(), new Class[]{serviceInterface}, uppercaseHandler);
    }

    @Override
    public Class<?> getObjectType() {
        return serviceInterface;
    }

    @Override
    public boolean isSingleton() {
        return false;
    }
}
