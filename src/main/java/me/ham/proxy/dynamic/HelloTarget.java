package me.ham.proxy.dynamic;

public class HelloTarget implements Hello{

    @Override
    public String sayHello(String name) {
        return name+", Hello~!";
    }
}
