package h_decorator.b;

/**
 * @description: 针对a包的类的爆炸
 * 解决方式：直接在父类 Beverage 中添加四个boolean 属性，分别对应四种调料。
 * @author: huangguoqiang
 * @create: 2021-08-07 11:22
 **/
//作者、服务端
abstract class Beverage {
    public Beverage(String description) {
        this.description = description;
    }

    private String description;

    private boolean milk, soy, mocha, bubble;

    public boolean isMilk() {
        return milk;
    }

    public void setMilk(boolean milk) {
        this.milk = milk;
    }

    public boolean isSoy() {
        return soy;
    }

    public void setSoy(boolean soy) {
        this.soy = soy;
    }

    public boolean isMocha() {
        return mocha;
    }

    public void setMocha(boolean mocha) {
        this.mocha = mocha;
    }

    public boolean isBubble() {
        return bubble;
    }

    public void setBubble(boolean bubble) {
        this.bubble = bubble;
    }

    public String getDescription() {
        String str = description;
        if (milk) {
            str = str + " + 牛奶";
        }
        if (soy) {
            str = str + " + 豆浆";
        }
        if (mocha) {
            str = str + " + 摩卡";
        }
        if (bubble) {
            str = str + " + 奶泡";
        }
        return str;
    }

    public double cost() {
        double amount = 0;
        if (milk) {
            amount = amount + 0.3;
        }
        if (soy) {
            amount = amount + 0.2;
        }
        if (mocha) {
            amount = amount + 0.5;
        }
        if (bubble) {
            amount = amount + 0.1;
        }
        return amount;
    }

    ;
}

class Decaf extends Beverage {
    public Decaf() {
        super("无咖啡因咖啡");
    }

    @Override
    public double cost() {
        return 1 + super.cost();
    }
}

class Espresso extends Beverage {
    public Espresso() {
        super("浓缩");
    }

    @Override
    public double cost() {
        return 2 + super.cost();
    }
}

class DarkRoast extends Beverage {
    public DarkRoast() {
        super("焦炒咖啡");
    }

    @Override
    public double cost() {
        return 1.5 + super.cost();
    }
}

class HouseBlend extends Beverage {
    public HouseBlend() {
        super("混合咖啡");
    }

    @Override
    public double cost() {
        return 3 + super.cost();
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
        return 2.8 + super.cost();
    }
}

public class Apptest {
    public static void main(String[] args) {
        Beverage b = new Decaf();
        b.setMilk(true);
        System.out.println(b.getDescription() + ":" + b.cost());

        Beverage b2 = new DarkRoast();
        b2.setBubble(true);
        System.out.println(b2.getDescription() + ":" + b2.cost());

        Beverage b3 = new HouseBlend();
        b3.setMocha(true);
        System.out.println(b3.getDescription() + ":" + b3.cost());

        Beverage b4 = new Espresso();
        b4.setSoy(true);
        System.out.println(b4.getDescription() + ":" + b4.cost());

        Beverage b5 = new Tea();
        System.out.println(b5.getDescription() + ":" + b5.cost());
    }
/**
 * 优点：
 * 1、类没有爆炸，没有出现各种各样的类
 * 2、星巴克的老板，又加入了一种新的饮料，茶，不会带来影响，符合开闭原则
 *
 * 缺点：
 * 1、星巴克又加入了新的调料 枸杞 ，那此时要往 Beverage 类中 cost() 和 getDescription() 加代码，违反了开闭原则。那如何解决？
 * c 包 使用装饰器模式来解决。
 *
 *
 */
}
