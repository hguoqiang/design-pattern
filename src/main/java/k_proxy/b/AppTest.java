package k_proxy.b;

/**
 * @description:
 * 解决a包的问题，我们自己创建的个类 MyCalculate，符合开闭原则
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
        return i ;
    }

    @Override
    public int sub(int a, int b) {
        int i = a - b;
        return i ;
    }

    @Override
    public int mul(int a, int b) {
        int i = a * b;
        return i ;
    }

    @Override
    public int div(int a, int b) {
        int i = a / b;
        return i ;
    }
}

//=========================================
//客户端代码

class  MyCalculate extends CalculateImpl {
    @Override
    public int add(int a, int b) {
        System.out.println("add 方法，入参："+a +"," +b);
        int i = a + b;
        System.out.println("add 方法，结果："+i);
        return i ;
    }

    @Override
    public int sub(int a, int b) {
        System.out.println("sub 方法，入参："+a +"," +b);
        int i = a - b;
        System.out.println("sub 方法，结果："+i);
        return i ;
    }

    @Override
    public int mul(int a, int b) {
        System.out.println("mul 方法，入参："+a +"," +b);
        int i = a * b;
        System.out.println("mul 方法，结果："+i);
        return i ;
    }

    @Override
    public int div(int a, int b) {
        System.out.println("div 方法，入参："+a +"," +b);
        int i = a / b;
        System.out.println("div 方法，结果："+i);
        return i ;
    }
}

public class AppTest {
    public static void main(String[] args) {
        //使用自己的MyCalculate
        ICalculate calc= new MyCalculate();

        calc.add(1, 2);
        calc.sub(1, 2);
        calc.mul(1, 2);
        calc.div(1, 2);
        /**
         * 这样还是有缺点：
         * 1、工作量大，为每个方法加入日志，加入有100个方法
         * 2、需求再次变化呢？客户要把中文的日志改成英文的，过了一段时间，又要把英文的改成中文的
         *
         * 为了解决这个问题，我们c包
         */
    }
}
