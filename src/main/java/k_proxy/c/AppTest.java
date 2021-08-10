package k_proxy.c;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;

/**
 * @description: 解决b包的问题，我们使用 jdk 的 Proxy 动态代理
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
    private CalculateImpl target;

    public MyInvocationHandler(CalculateImpl target) {
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

public class AppTest {
    public static void main(String[] args) {
        /**
         * 创建代理对象3个参数：
         * ClassLoader loader : 类加载器，要实例化一个对象，必须要调用构造器，在构造器调用之前，jvm使用类加载器把这个类的字节码加载到内存，jvm自动完成。
         * Class<?>[] interfaces ：字节码数组，指明 类加载器加载哪个类的字节码，确切的说，动态代理是动态生成字节码的吗，那依据是社么，依据就是这个参数。生成一个实现了这个参数的类的字节码
         * InvocationHandler h ：调用处理器，动态代理生成的实现了这个字节码，实现了 ICalculate 接口，那势必要实现 add()、sub() 、mul() 、div()这些方法，
         * 那这些方法都是什么内容呢？这恰恰就是由这个 InvocationHandler 参数决定的。InvocationHandler 的 invoke() 方法就是方法体的内容
         *
         */


        Object proxy = Proxy.newProxyInstance(AppTest.class.getClassLoader(), new Class[]{ICalculate.class}, new MyInvocationHandler(new CalculateImpl()));

        ICalculate clac = (ICalculate) proxy;
        System.out.println(clac.add(1, 2));
        System.out.println(clac.sub(1, 2));
        System.out.println(clac.mul(1, 2));
        System.out.println(clac.div(1, 2));

        /**
         * 我们应该把这个代码封装一下，对使用者更友好。d包
         */
    }
}
