package e_factory.simplefactory.a;

/**
 * @description:
 * @author: huangguoqiang
 * @create: 2021-08-03 19:51
 **/
//作者
interface Food {
    //抽象产品
    void eat();
}

class Hamburger2 implements Food {
    //具体产品
    @Override
    public void eat() {
        System.out.println("吃汉堡包");
    }
}

//=====================================
//用户
public class AppTest {
    public static void main(String[] args) {
        //向上转型时，调的方法只与new的对象有关
        Food f = new Hamburger2();
        f.eat();
    }
}
/**
 * 缺点：
 * 如果作者把具体产品的名称修改了（Hamburger.java 类名改成 Hamburger2.java） ,那么客户端就得跟着改，这样服务端和客户端耦合了。
 * 我们期望无论服务端代码如何修改，客户端不用任何改变。不管服务端的类名如何变化，客户端应该是不知道的，最少知道原则。
 */
