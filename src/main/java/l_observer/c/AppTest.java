package l_observer.c;

import java.util.ArrayList;
import java.util.List;

/**
 * @description:
 * 需求：针对b 包的问题，直接把Role传给观察者
 * @author: huangguoqiang
 * @create: 2021-08-10 20:21
 **/


class Role{
    private String name;
    private Integer hp;
    private Integer mp;

    private List<MyObserver> myObservers = new ArrayList<>();

    public void addObserver(MyObserver myObserver){
        myObservers.add(myObserver);
    }
    public void notifyAllObserver(){
        for (MyObserver myObserver : myObservers) {
            System.out.println("通知："+ myObserver.getClass().getSimpleName());
            myObserver.update(this);
        }
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getHp() {
        return hp;
    }

    public void setHp(Integer hp) {
        this.hp = hp;

        // 就是在这个地方，这个角色遭到攻击，需要在三个地方修改血量，血条、球、面板
        notifyAllObserver();
        
    }

    public Integer getMp() {
        return mp;
    }

    public void setMp(Integer mp) {
        this.mp = mp;
    }
}

class Monster{
    public void attack(Role r){
        r.setHp(r.getHp()-10);
    }
}

interface MyObserver {
     void update(Role r);
}
class Panel implements MyObserver {


    @Override
    public void update(Role r) {

        System.out.println("左上角面板更新hp的值为："+r.getHp());
    }
}
class HeadPanel implements MyObserver {


    @Override
    public void update(Role r) {
        System.out.println("角色头顶面板更新hp的值为："+r.getHp());
    }
}
class BallPanel implements MyObserver {


    @Override
    public void update(Role r) {
        System.out.println("右下角球型面板更新hp的值为："+r.getHp());
    }
}

public class AppTest {

    public static void main(String[] args) {
        Role r = new Role();

        MyObserver o1 = new Panel();
        MyObserver o2 = new BallPanel();
        MyObserver o3= new HeadPanel();
        r.addObserver(o1);
        r.addObserver(o2);
        r.addObserver(o3);

        r.setName("古天乐");
        r.setHp(100);
        r.setMp(100);

        System.out.println("游戏开始================");

        //3个区域显示hp
/*        System.out.println(o1.getHp());
        System.out.println(o2.getHp());
        System.out.println(o3.getHp());*/

        System.out.println("被攻击了================");

        //被攻击了
        Monster m = new Monster();
        m.attack(r);

        //3个区域显示hp
     /*   System.out.println(o1.getHp());
        System.out.println(o2.getHp());
        System.out.println(o3.getHp());*/
        /**
         *缺点：
         * 1、Observer接口中的update方法出现了具体类，违反了依赖倒置原则。如此Observer只能观察Role类的对象了，那如何解决呢？d包
         *
         *
         */
    }
}
