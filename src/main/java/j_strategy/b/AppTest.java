package j_strategy.b;

/**
 * @description:
 * 我们希望那些不会飞的方法就没有fly()方法，不会叫的没有 quack()方法，
 * 把经常变化的接口从父类提取出来，定义成两个接口
 * @author: huangguoqiang
 * @create: 2021-08-09 15:12
 **/
abstract class Duck {
    //鸭子呱呱叫
    public void swing() {
        System.out.println("鸭子游泳");
    }
    // 鸭子外观  ， 红头鸭子， 白头鸭子
    public abstract void dispaly();
}
interface Flyable{
     void fly();
}
interface Quackable{
    void quack();
}

class MallarDuck extends Duck implements Flyable  {
    @Override
    public void dispaly() {
        System.out.println("外观是野鸭子");
    }

    @Override
    public void fly() {
        System.out.println("野鸭子时会飞的");
    }
}

class RedHeadDuck extends Duck implements Flyable,Quackable {
    @Override
    public void dispaly() {
        System.out.println("红头鸭");
    }

    @Override
    public void fly() {
        System.out.println("红头鸭会飞的");
    }

    @Override
    public void quack() {
        System.out.println("红头鸭嘎嘎叫");
    }
}

class RubberDuck extends Duck implements Quackable{
    @Override
    public void dispaly() {
        System.out.println("外观是橡皮鸭");
    }

    @Override
    public void quack() {
        // 橡皮鸭不会叫，模拟一下玩具鸭的叫声
        System.out.println("吱吱叫");
    }
}

public class AppTest {
    public static void main(String[] args) {

        //使用鸭子
        RedHeadDuck d = new RedHeadDuck();
        d.dispaly();
        d.quack();
        d.swing();
        d.fly();
        System.out.println("-----------------------");

        RubberDuck d2 = new RubberDuck();
        d2.dispaly();
        d2.quack();
        d2.swing();


        /**
         * 思考：这样解决问题了吗？没有
         * 以前是，每增加一个鸭子角色时候，就要判断这鸭子是否会飞，是否会叫，不会飞的重写 fly()方法，不会叫的重写quack()方法
         * 现在是，每增加一个鸭子角色时候，就要判断这鸭子是否会飞，是否会叫，会飞的实现 Flyable 接口 , 会叫的实现 Quackable。
         * 另一个问题就是，会飞的那些鸭子都得实现Flyable，重写 fly(),这样 大量重复的代码，造成 fly() 没有重用性。比如40只鸭子，38只会飞，
         * 要在38个类中重复代码 fly() ，没有重用性。
         *
         * 杠点：jdk1.8 的接口中可以定义默认方法。那这样是不是就可以重用了。
         * interface Flyable{
         *     default void fly(){
         *         System.out.println("鸭子会飞的");
         *     }
         * }
         * 解释：对于38中会飞的鸭子，有12中飞行方法，你怎么处理？还是没有重用性。
         * c包策略模式登场。
         *
         */



    }
}
