package demo.dp.ch05_dp_structural._01_proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class UserControllerDynamicProxy {
   
    public Object createProxy(Object proxiedObject) {
        Class<?>[] interfaces = proxiedObject.getClass().getInterfaces();
        DynamicProxyHandler proxyHandler = new DynamicProxyHandler(proxiedObject);
        return Proxy.newProxyInstance(proxiedObject.getClass().getClassLoader(), interfaces, proxyHandler);
    }


    private class DynamicProxyHandler implements InvocationHandler {
        private Object proxiedObject;
        
        public DynamicProxyHandler(Object proxiedObject) {
            this.proxiedObject = proxiedObject;
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            // proxy : jdk.proxy1.$Proxy0
            // System.out.println("proxy : " + proxy.getClass().getName());
            long start = System.currentTimeMillis();

            Object result = method.invoke(proxiedObject, args);

            long end = System.currentTimeMillis();
            long responeTime = end - start;

            String name = proxiedObject.getClass().getSimpleName() + ":" + method.getName();

            System.out.println(name + " execute " + responeTime + " ms.");

            return result;
        }
        
    }

    public static void main(String[] args) {
        UserControllerDynamicProxy proxy = new UserControllerDynamicProxy();
        UserController userController = new UserController();
        IUserController proxiedUser = (IUserController) proxy.createProxy(userController);
        proxiedUser.login("zhangsan", null);
    }
}
