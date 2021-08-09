package h_decorator.a;

/**
 * @description: 业务场景：星巴克卖咖啡，一开始只有四种咖啡：Decaf、Espresso、DarkRoast、HouseBlend，
 * 因为咖啡都用共性，描述和价格，所以他们公同继承一个父类， Beverage
 * @author: huangguoqiang
 * @create: 2021-08-07 11:22
 **/
//作者、服务端
abstract class Beverage {
    public Beverage(String description) {
        this.description = description;
    }

    private String description;

    public String getDescription() {
        return description;
    }

    public abstract double cost();
}

class Decaf extends Beverage {
    public Decaf() {
        super("无咖啡因咖啡");
    }

    @Override
    public double cost() {
        return 1;
    }
}

class Espresso extends Beverage {
    public Espresso() {
        super("浓缩");
    }

    @Override
    public double cost() {
        return 2;
    }
}

class DarkRoast extends Beverage {
    public DarkRoast() {
        super("焦炒咖啡");
    }

    @Override
    public double cost() {
        return 1.5;
    }
}

class HouseBlend extends Beverage {
    public HouseBlend() {
        super("混合咖啡");
    }

    @Override
    public double cost() {
        return 3;
    }
}

//=============================================
// 客户端
public class Apptest {
    public static void main(String[] args) {
        Beverage b = new Decaf();
        System.out.println(b.getDescription() + ":" + b.cost());

        Beverage b2 = new DarkRoast();
        System.out.println(b2.getDescription() + ":" + b2.cost());

        Beverage b3 = new HouseBlend();
        System.out.println(b3.getDescription() + ":" + b3.cost());

        Beverage b4 = new Espresso();
        System.out.println(b4.getDescription() + ":" + b4.cost());
    }
/**
 * 目前，这段代码没问题
 *
 * 变化来了：
 * 咖啡馆为了吸引更多的顾客，需要在订单系统中允许顾客选择加入不同调料的咖啡，
 * 例如：蒸奶（Steamed Milk）、豆浆（Soy）、摩卡（Mocha，也就是巧克力风味）或覆盖奶泡。
 * 星巴兹会根据所加入的调料收取不同的费用。所以订单系统必须考虑到这些调料部分。
 * 为了往咖啡中加入不同的调料。可以创建不同的类：
 *
 * 为加牛奶的decaf创建一个类
 * class DecafWithMilk{}
 *
 * 为加豆浆的decaf创建一个类
 * class DecafWithSoy{}
 *
 * 为加摩卡的decaf创建一个类
 * class DecafWithMocha{}
 *
 * 为加牛奶加豆浆的的decaf创建一个类
 * class DecafWithMilkSoy{}
 * 。。。如果依次这样下去，那么类就爆炸了，所以这种采用继承的方式是不可行的
 *
 * 如何应对这种变化？ b包解决
 */
}
