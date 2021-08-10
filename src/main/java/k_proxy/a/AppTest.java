package k_proxy.a;

/**
 * @description: 需求：制作一个计算机，实现两个数的四则运算
 * @author: huangguoqiang
 * @create: 2021-08-09 19:35
 **/

//服务端代码
interface ICalculate {
    int add(int a, int b);

    int sub(int a, int b);

    int mul(int a, int b);

    int div(int a, int b);

}

class CalculateImpl implements ICalculate {
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

//=========================================
//客户端代码

public class AppTest {
    public static void main(String[] args) {
        //使用计算器
        ICalculate calc= new CalculateImpl();
//        System.out.println(calc.add(1, 2));
//        System.out.println(calc.sub(1, 2));
//        System.out.println(calc.mul(1, 2));
//        System.out.println(calc.div(1, 2));
        /**
         * 变化来了，客户说要在每个方法调用前和调用后加入日志，程序员的做法 就在 CalculateImpl的每个方法里面加入了日志。
         */
        calc.add(1, 2);
        calc.sub(1, 2);
        calc.mul(1, 2);
        calc.div(1, 2);
        /**
         * 这么来看，确实能实现这个需求，但是缺点有很多：
         * 1、工作量大，为每个方法加入日志，加入有100个方法
         * 2、如果ICalculate和CalculateImpl都不是自己创建的类，手上没有源代码，你怎么加？
         * 3、需求再次变化呢？客户要把中文的日志改成英文的，过了一段时间，又要把英文的改成中文的
         *
         * 为了解决这个问题，b包
         */
    }
}
