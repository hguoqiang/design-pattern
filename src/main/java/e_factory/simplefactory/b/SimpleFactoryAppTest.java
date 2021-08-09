package e_factory.simplefactory.b;

/**
 * @description: 针对a包的问题，服务端代码修改，客户端代码也修改。
 * 使用简单工厂设计模式解决。（简单工厂是作者做的）
 * @author: huangguoqiang
 * @create: 2021-08-04 11:07
 **/
//作者
interface Food {
    //抽象产品
    void eat();
}

class FoodFactory {
    public static Food getFood(int n) {
        Food f = null;
        switch (n) {
            case 1:
                f = new Hamburger();
                break;
            case 2:
                f = new MiXian();
                break;
        }
        return f;
    }
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

//=====================================
//用户
public class SimpleFactoryAppTest {
    public static void main(String[] args) {
        Food food = FoodFactory.getFood(2);
        food.eat();
    }
}

class LP implements Food {
    //具体产品
    @Override
    public void eat() {
        System.out.println("吃凉皮吗");
    }
}


/**
 * 优点：
 * 1.把具体产品的类名从客户端代码中解耦出来。
 * 2.服务端修改了类名，客户端并不知道。
 * 如果作者把具体产品的类名修改了，那么作者也会在工厂内进行修改，客户端不知道具体产品的变化，客户端只需要知道工厂就可以了，知道工厂的参数1就是Hamburger。
 * 只要是下层给上层暴露出去的都叫做接口，一个类，一个方法都是接口，Food是接口，FoodFactory.getFood(2)也是接口。客户端只按接口编程。 低耦合了。
 * <p>
 * 缺点：
 * 1、客户端必须记住常量与具体产品的映射关系。
 * 2、如果具体产品特别多，那简单工厂就会很臃肿，比如有100个具体产品，那就需要在简单工厂 FoodFactory.java 中增加100个case
 * 3、变化来了：客户端需要扩展新的具体产品，增加凉皮，那客户端就要在FoodFactory.java 增加代码，那就违反了开闭原则。
 * <p>
 * 解决方式： 使用工厂方法设计模式
 */
