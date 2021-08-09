package j_strategy.a;

/**
 * @description: 有一家游戏公司，制作了一款鸭子游戏，在这个游戏中，所有角色都是鸭子，不同的鸭子之间有共性，
 * 所有为了提高代码的重用性，开发人员制作了一个父类：Duck，把这些共性封装进来。
 * @author: huangguoqiang
 * @create: 2021-08-09 15:12
 **/
abstract class Duck {
    //鸭子呱呱叫
    public void quack() {
        System.out.println("鸭子呱呱叫");
    }

    public void swing() {
        System.out.println("鸭子游泳");
    }
    public void fly() {
        System.out.println("鸭子会飞了");
    }

    // 鸭子外观  ， 红头鸭子， 白头鸭子
    public abstract void dispaly();
}

class MallarDuck extends Duck {
    @Override
    public void dispaly() {
        System.out.println("外观是野鸭子");
    }
}

class RedHeadDuck extends Duck {
    @Override
    public void dispaly() {
        System.out.println("红头鸭");
    }
}

class RubberDuck extends Duck{
    @Override
    public void quack() {
        // 橡皮鸭不会叫，模拟一下玩具鸭的叫声
        System.out.println("吱吱叫");
    }

    @Override
    public void dispaly() {
        System.out.println("外观是橡皮鸭");
    }

    @Override
    public void fly() {
        System.out.println("橡皮鸭不能飞");
    }
}

public class AppTest {
    public static void main(String[] args) {

        //使用鸭子
        Duck d = new RedHeadDuck();
        d.dispaly();
        d.quack();
        d.swing();

        /**
         * 变化来了，游戏公司老板，提供公司游戏竞争力的方案，要求让游戏中的鸭子飞起来。
         * 程序员想法是，只需要在父类duck中加入一个方法 fly()
         */
        d.fly();
        System.out.println("-----------------------");
        /**
         * 此时，问题似乎解决了，但是所有Duck的子类都有这个fly()的方法，
         * 问题是有些鸭子不能飞、比如橡皮鸭，橡皮鸭实际是没有生命的，所以不能飞，可以让橡皮鸭重写fly方法，让他不能飞。
         */
        Duck d2 = new RubberDuck();
        d2.dispaly();
        d2.quack();
        d2.swing();
        d2.fly();
        /*
         * 但实际并没有解决问题，一会增加木头鸭、一会增加怪鸭伯爵，就是增加各种鸭子，
         * 那这样的变化，程序员每次在增加新的角色的鸭子时，都要判断会不会叫，会不会飞，这样工作量很大，很麻烦。
         * 我们希望那些不会飞的方法就没有fly()方法，不会叫的没有 quack()方法，把经常变化的接口从父类提取出来，定义成两个接口，见b包代码
         *
         */




    }
}
