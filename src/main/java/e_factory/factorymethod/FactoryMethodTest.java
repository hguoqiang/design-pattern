package e_factory.factorymethod;


/**
 * @description: 针对简单工厂的问题，使用工厂方法设计模式解决。（工厂方法是作者做的）
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
        System.out.println("吃米线");
    }
}

//抽象产品
interface FoodFactory {
    Food getFood();
}

class HamburgerFactory implements FoodFactory {
    //具体产品
    @Override
    public Food getFood() {
        return new Hamburger();
    }
}

class MiXianFactory implements FoodFactory {

    @Override
    public Food getFood() {
        return new MiXian();
    }
}

class Business{
    public static void taste(FoodFactory ff){
        Food f1 = ff.getFood();
        System.out.println("评委1，品尝:");
        f1.eat();
        Food f2 = ff.getFood();
        System.out.println("评委2，品尝:");
        f2.eat();
        Food f3 = ff.getFood();
        System.out.println("评委3，品尝:");
        f3.eat();
    }

}

//=====================================
//用户
class Lp implements Food {
    //具体产品
    @Override
    public void eat() {
        System.out.println("吃凉皮吗");
    }
}

class LpFactory implements FoodFactory {

    @Override
    public Food getFood() {
        return new Lp();
    }
}

public class FactoryMethodTest {
    public static void main(String[] args) {
        FoodFactory factory = new MiXianFactory();
        Food food = factory.getFood();
        food.eat();

        FoodFactory lpFactory = new LpFactory();
        Food lp = lpFactory.getFood();
        lp.eat();

        Business.taste(new LpFactory());
    }
}


/**
 * 优点：
 * 1、服务器端修改了具体产品的名称时，客户端不知道，不需要修改，有简单工厂的优点。
 * 2、当客户端增加新的具体产品凉皮时，服务端不需要修改代码。客户端只需要扩展 产品和工厂，不需要修改作者的代码，符合开闭原则。
 * 3、
 *
 * <p>
 * 杠点：
 * 1、我们已经知道简单工厂也好，工厂方法也好，都有一个优点，就是服务端的具体产品类名修改后，客户端不知道。但是，现在的代码，仍然依赖于具体产品的工厂的类名，
 * 此时，如果服务端的工厂类名修改后，客户端也需要跟着一起修改。工厂类名和客户端耦合了。感觉折腾了一圈，又回到了原点。
 * 解释：工厂的名字，是视为接口的，作者有责任、有义务保证自己对外暴露的接口是不变的。也就是说，虽然客户端依赖服务器端的工厂的具体类名，可是在IT业内，
 * 所有的工厂的名字都是趋于稳定的（并不是100%不会变）。至少工厂的的名字，要比具体产品类的名字稳定。
 *
 * 2、对于Lp这个产品，既然产品是我们自己客户端扩展出来的，那么为什么不直接实例化呢？为什么还有扩展LpFactory这个工厂呢？
 * 毕竟对于这个Lp，我们自己就是作者，我们想怎么改类名，自己就能把控？为啥需要工厂？
 * 解释：因为作者在开发功能时，不仅仅是开发一些抽象产品、具体产品、对应的工厂，还会搭配一些提前做好的框架，对业务逻辑做一个框架，品尝食物的业务。
 *
 * 3、现在制作的 LpFactory ，为了把 LpFactory 传给 Business.taste方法，所以必须定义 LpFactory，那为什么不从一开始就让 Business.taste方法直接接受Food参数呢？
 * 而不是现在的 FoodFactory参数。如果直接传Food，又回到了一开始的具体产品名称和客户端耦合了。
 *
 * 缺点：
 * 如果有多个产品等级，那么工厂类的数量，就会爆炸式增长。
 * <p>
 * 解决方式：如果有多个产品等级，使用抽象工厂。
 */
