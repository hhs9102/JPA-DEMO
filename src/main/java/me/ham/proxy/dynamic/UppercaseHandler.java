package me.ham.proxy.dynamic;

import org.springframework.cglib.proxy.InvocationHandler;

import java.lang.reflect.Method;

public class UppercaseHandler implements InvocationHandler {
    Object target;

    public void setTarget(Object target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        String result = (String) method.invoke(target, args);
        return result.toUpperCase();
    }
}
