package k_proxy.d;


import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;

/**
 * @description: 封装动态代理
 * @author: huangguoqiang
 * @create: 2021-08-09 19:35
 **/

//服务端代码 作者的+
interface ICalculate {
    int add(int a, int b);

    int sub(int a, int b);

    int mul(int a, int b);

    int div(int a, int b);

}

class CalculateImpl implements ICalculate {
    @Override
    public int add(int a, int b) {
        int i = a + b;
        return i;
    }

    @Override
    public int sub(int a, int b) {
        int i = a - b;
        return i;
    }

    @Override
    public int mul(int a, int b) {
        int i = a * b;
        return i;
    }

    @Override
    public int div(int a, int b) {
        int i = a / b;
        return i;
    }
}

//=========================================
//客户端代码

class MyInvocationHandler implements InvocationHandler {
    //关联关系
    private Object target;

    public MyInvocationHandler(Object target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        //前后打日志
        System.out.println(method.getName() + " 方法，入参：" + Arrays.toString(args));
        //利用反射，调用目标对象的方法，
        Object result = method.invoke(target, args);
        System.out.println(method.getName() + " 方法，结果：" + result);

        //这里的返回值，是返回代理对象的调用处。
        return result;
    }
}


class ProxyUtils {
    public static <T> T getProxy(T target) {

        try {
            //获取目标类所实现的接口
            Class<?>[] interfaces = target.getClass().getInterfaces();

            Object proxy = Proxy.newProxyInstance(ProxyUtils.class.getClassLoader(), interfaces, new MyInvocationHandler(target));
            return (T) proxy;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;


    }
}

interface A {
    void f1();
}

class B implements A {

    @Override
    public void f1() {
        System.out.println("f1允许");
    }
}


public class AppTest {
    public static void main(String[] args) {


        //ICalculate clac = new MyProxy<ICalculate>().getProxy(ICalculate.class);
        ICalculate clac = ProxyUtils.getProxy(new CalculateImpl());
        System.out.println(clac.add(1, 2));
        System.out.println(clac.sub(1, 2));
        System.out.println(clac.mul(1, 2));
        System.out.println(clac.div(1, 2));

        System.out.println("==========================");

        A b = ProxyUtils.getProxy(new B());
        b.f1();

        /**
         * 目前这个解决方式仅仅是基于jdk动态代理实现的。
         * 缺点：
         * 1、目前我们创建的代理对象，只能在真实对象的真实方法调用前后加上日志功能，无法加入其它功能。
         * 比如 用户想要加入缓存功能、加入一些其他的功能，那就无法实现。
         * 如何解决? e包
         *
         */
    }
}
