package com.arteco.eadp.java.collection;


import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Created by rarnau on 22/08/2019.
 * Arteco Consulting SL.
 * info@arteco-consulting.com
 */
public class JavaCollectionMain {
    public static void main(String[] args) throws Exception {

        InvocationHandler handler = new MyHandler();
        MyInterface proxy = (MyInterface) Proxy.newProxyInstance(
                MyInterface.class.getClassLoader(),
                new Class[]{MyInterface.class},
                handler);

       System.out.println(proxy.sayHello("José"));

    }


    private interface MyInterface {
        String sayHello(String name);
    }


    private static class MyHandler implements InvocationHandler {
        @Override
        public Object invoke(Object proxy, Method method, Object[] args) {
            return "Hello " + args[0];
        }
    }
}
