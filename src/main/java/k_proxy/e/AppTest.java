package k_proxy.e;


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


class MyInvocationHandler implements InvocationHandler {
    //关联关系
    private Object target;

    private MyInterceptor interceptor;

    public MyInvocationHandler(Object target, MyInterceptor interceptor) {
        this.target = target;
        this.interceptor = interceptor;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        //前后打日志
        //前置通知 交给用户去实现
        interceptor.before(proxy, method, args);
        //利用反射，调用目标对象的方法，
        Object result = method.invoke(target, args);

        //后置通知 交给用户去实现
        interceptor.after(proxy, method, args,result);

        //这里的返回值，是返回代理对象的调用处。
        return result;
    }
}

interface MyInterceptor {
    void before(Object proxy, Method method, Object[] args);

    void after(Object proxy, Method method, Object[] args, Object result);
}


class ProxyUtils {
    public static <T> T getProxy(T target, MyInterceptor interceptor) {

        try {
            //获取目标类所实现的接口
            Class<?>[] interfaces = target.getClass().getInterfaces();

            Object proxy = Proxy.newProxyInstance(ProxyUtils.class.getClassLoader(), interfaces, new MyInvocationHandler(target, interceptor));
            return (T) proxy;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;


    }
}

//=========================================
//客户端代码
class LogInterceptor implements MyInterceptor {

    @Override
    public void before(Object proxy, Method method, Object[] args) {
        System.out.println(method.getName() + " 方法，入参：" + Arrays.toString(args));
    }

    @Override
    public void after(Object proxy, Method method, Object[] args, Object result) {
        System.out.println(method.getName() + " 方法，结果：" + result);
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
        ICalculate clac = ProxyUtils.getProxy(new CalculateImpl(), new LogInterceptor());
        System.out.println(clac.add(1, 2));
        System.out.println(clac.sub(1, 2));
        System.out.println(clac.mul(1, 2));
        System.out.println(clac.div(1, 2));

        System.out.println("==========================");

        A b = ProxyUtils.getProxy(new B(), new LogInterceptor());
        b.f1();

        /**
         * 目前来看，可以解决，客户加日志、加缓存、加一些扩展功能的需求。
         *
         * 但是，变化来了：客户想要在add()方法前后加入中文日志，在sub()方法前后加英文日志，在mul()方法前后不加日志，在div()方法前后加入日文日志。
         * 你怎么处理这种需求？首先考虑到 在 LogInterceptor 的 before() 、after() 中 加入判断，如果是add() .....，
         * 这样虽然能完成需求但是，一堆if() else()，感觉不够友好，还是不能应对变化。因为违反了单一职责原则，本来是只有一个日志功能，结果是细化了四个小功能
         */

    }
}
