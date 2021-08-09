package h_decorator.c;

/**
 * @description: 针对b包的扩展问题，使用 装饰器模式。
 * 增加一个 调料 类 Condiment，继承 Beverage
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

    public void setDescription(String description) {
        this.description = description;
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

/**
 * 判断两个类是不是能有继承关系：
 * 1、有is-a 关系
 * 2、符合里氏替换原则
 * 尽管以上只是原则，不是语法强制，也就是说，在特定的情况下，可以不用遵守，在装饰器模式中就是这样。
 * 尽管调料不是饮料，但是调料也必须继承饮料，为了制造装饰器模式
 */
//这是调料
abstract class Condiment extends Beverage {

    //调料关联饮料
    protected Beverage beverage;

    public Condiment(Beverage beverage) {
        super("调料");
        this.beverage = beverage;
    }
}

class Milk extends Condiment {
    public Milk(Beverage beverage) {
        super(beverage);
    }

    @Override
    public double cost() {
        return beverage.cost() + 0.3;
    }

    @Override
    public String getDescription() {
        return beverage.getDescription() + " + 牛奶 ";
    }
}
class Soy extends Condiment {
    public Soy(Beverage beverage) {
        super(beverage);
    }

    @Override
    public double cost() {
        return beverage.cost() + 0.2;
    }

    @Override
    public String getDescription() {
        return beverage.getDescription() + " + 豆浆 ";
    }
}


//=============================================
// 客户端
class Tea extends Beverage {

    public Tea() {
        super("茶");
    }

    @Override
    public double cost() {
        return 2.8;
    }
}

class GouQi extends Condiment{

    public GouQi(Beverage beverage) {
        super(beverage);
    }

    @Override
    public double cost() {
        return beverage.cost()+ 0.8;
    }

    @Override
    public String getDescription() {
        return beverage.getDescription() + " + 枸杞 ";
    }
}

public class Apptest {
    public static void main(String[] args) {
        Beverage b = new Decaf();
        //加了牛奶的 Decaf
        Beverage milk = new Milk(b);
        Beverage soy = new Soy(milk);
        System.out.println(soy.getDescription() + ":" + soy.cost());

        Beverage b2 = new DarkRoast();
        System.out.println(b2.getDescription() + ":" + b2.cost());

        Beverage b3 = new HouseBlend();
        System.out.println(b3.getDescription() + ":" + b3.cost());

        Beverage b4 = new Espresso();
        System.out.println(b4.getDescription() + ":" + b4.cost());

        Beverage b5 = new Tea();
        Beverage gouQi = new GouQi(b5);
        Beverage gouQi2 = new GouQi(gouQi);
        System.out.println(gouQi2.getDescription() + ":" + gouQi2.cost());
    }
/**
 * 优点：
 * 1、用调料装饰真正的饮料，增加新的饮料和新的调料都不用修改源代码，扩展性好，符合开闭原则
 *
 * 缺点：
 * 1、类虽然没有爆炸，也还是挺多的
 *
 */
}
