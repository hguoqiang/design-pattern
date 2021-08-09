package j_strategy.c;


/**
 * @description: fly()方法 和 quack()方法从父类中分离出来
 * @author: huangguoqiang
 * @create: 2021-08-09 15:12
 **/
abstract class Duck {
    protected FlyBehavior fb;
    protected QuackBehavior qb;

    public FlyBehavior getFb() {
        return fb;
    }

    public void setFb(FlyBehavior fb) {
        this.fb = fb;
    }

    public QuackBehavior getQb() {
        return qb;
    }

    public void setQb(QuackBehavior qb) {
        this.qb = qb;
    }

    public void performFly() {
        fb.fly();
    }

    public void performQuack() {
        qb.quack();
    }

    //鸭子呱呱叫
    public void swing() {
        System.out.println("鸭子游泳");
    }

    // 鸭子外观  ， 红头鸭子， 白头鸭子
    public abstract void dispaly();
}

interface FlyBehavior {
    void fly();
}

interface QuackBehavior {
    void quack();
}

class FlyWithWings implements FlyBehavior {
    @Override
    public void fly() {
        System.out.println("用翅膀飞");
    }
}


class FlyWithRocket implements FlyBehavior {
    @Override
    public void fly() {
        System.out.println("背上帮个火箭飞");
    }
}

class FlyWithKick implements FlyBehavior {
    @Override
    public void fly() {
        System.out.println("被人踢飞");
    }
}

class FlyNoWay implements FlyBehavior {
    @Override
    public void fly() {
        System.out.println("不会飞");
    }
}


class Quack implements QuackBehavior {
    @Override
    public void quack() {
        System.out.println("嘎嘎叫");
    }
}

class Squack implements QuackBehavior {
    @Override
    public void quack() {
        System.out.println("吱吱叫");
    }
}

class MuteQuack implements QuackBehavior {
    @Override
    public void quack() {
        System.out.println("不会叫");
    }
}


class MallarDuck extends Duck {

    public MallarDuck() {
        this.fb = new FlyWithWings();
        this.qb = new Quack();
    }

    @Override
    public void dispaly() {
        System.out.println("外观是野鸭子");
    }


}

class RedHeadDuck extends Duck {
    public RedHeadDuck() {
        this.fb = new FlyWithRocket();
        this.qb = new MuteQuack();
    }

    @Override
    public void dispaly() {
        System.out.println("红头鸭");
    }

}

class RubberDuck extends Duck {
    public RubberDuck() {
        this.fb = new FlyNoWay();
        this.qb = new MuteQuack();
    }


    @Override
    public void dispaly() {
        System.out.println("外观是橡皮鸭");
    }

}

public class AppTest {
    public static void main(String[] args) {

        //使用鸭子
        Duck d = new MallarDuck();
        d.dispaly();
        d.performQuack();
        d.swing();
        d.performFly();
        System.out.println("-----------------------");

        Duck d2 = new RedHeadDuck();
        d2.dispaly();
        d2.performQuack();
        d2.swing();
        d2.performFly();
        System.out.println("-----------------------");

        Duck d3 = new RubberDuck();
        d3.dispaly();
        d3.performQuack();
        d3.performFly();

        d3.setFb(new FlyWithKick());
        d3.performFly();

        d3.setFb(new FlyWithRocket());
        d3.performFly();

        /**
         * 此时，针对于38中鸭子，12中飞行方式而言，每种飞行方式写一次。
         *
         */


    }
}
