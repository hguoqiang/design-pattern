package e_factory.abstractfactory;


/**
 * @description: 抽象工厂，针对工厂方法的问题，当有很多的产品等级时（食物、饮料、甜品。。。），工厂类就会特别多，
 * 使用抽象工厂解决。工厂是抽象的，不是特定某一个产品等级的工厂，而是很多的产品等级的工厂
 * @author: huangguoqiang
 * @create: 2021-08-04 11:07
 **/
//作者
interface Food {
    //抽象产品
    void eat();
}

class Hamburger implements Food {
    //具体产品
    @Override
    public void eat() {
        System.out.println("吃汉堡包");
    }
}

class MiXian implements Food {
    //具体产品
    @Override
    public void eat() {
        System.out.println("西安米线");
    }
}

//抽象产品
interface Drink {
    void drink();
}

class Colo implements Drink {
    @Override
    public void drink() {
        System.out.println("可口可乐");
    }
}

class Tea implements Drink {
    @Override
    public void drink() {
        System.out.println("喝茶");
    }
}

//抽象产品
interface Factory {
    Food getFood();

    Drink getDrink();
}

class KfcFactory implements Factory {
    //具体产品
    @Override
    public Food getFood() {
        return new Hamburger();
    }

    @Override
    public Drink getDrink() {
        return new Colo();
    }
}


class SanQinFactory implements Factory {

    @Override
    public Food getFood() {
        return new MiXian();
    }

    @Override
    public Drink getDrink() {
        return new Tea();
    }
}


class Business {
    public static void taste(Factory ff) {
        Food f1 = ff.getFood();
        Drink d1 = ff.getDrink();
        System.out.println("评委1，品尝:");
        f1.eat();
        d1.drink();

        Food f2 = ff.getFood();
        Drink d2 = ff.getDrink();
        System.out.println("评委2，品尝:");
        f2.eat();
        d2.drink();

        Food f3 = ff.getFood();
        Drink d3 = ff.getDrink();
        System.out.println("评委3，品尝:");
        f3.eat();
        d3.drink();
    }

}

//=====================================
//用户
class LP implements Food {
    //具体产品
    @Override
    public void eat() {
        System.out.println("宝鸡凉皮");
    }
}

class FenDa implements Drink {
    @Override
    public void drink() {
        System.out.println("来杯芬达");

    }
}

class BaoJiFactory implements Factory {

    @Override
    public Food getFood() {
        return new LP();
    }

    @Override
    public Drink getDrink() {
        return new FenDa();
    }
}


public class AbstractFactoryTest {
    public static void main(String[] args) {
        Business.taste(new SanQinFactory());
        Business.taste(new BaoJiFactory());
    }
}


/**
 * 优点：
 * 1、仍然有简单工厂和工厂方法的优点、具体产品的类名解耦了，工厂方法的扩展性好
 * 2、更重要的是，工厂类的数量减少了，无论有多少产品，工厂代码只要一套
 * 3、
 *
 * <p>
 * 杠点：
 * 1、为什么三秦工厂中就必须是米线搭配茶呢？为什么不能是米线搭配可乐？
 * 解释：抽象工厂中，可以生产多个产品，这多个产品必须是要有内在联系，
 * 同一个工厂中的产品都属于同一个产品簇，不能把不同产品簇中的产品混合到一个抽象工厂的实现类中。
 * <p>
 *
 *
 * 缺点：
 * 当产品等级发生变化，有可能增加产品等级，有可能删除产品等级，都要引起以前代码的修改，违反了开闭原则。
 * 如果是产品簇发生变化，就不用修改源代码。
 * <p>
 * 当产品等级比较固定时，建议使用抽象工厂。产品等级经常变化时，不用抽象工厂。
 */
