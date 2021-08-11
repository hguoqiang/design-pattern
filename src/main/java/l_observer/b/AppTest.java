package l_observer.b;

import java.util.ArrayList;
import java.util.List;

/**
 * @description:
 * 需求：在游戏中有一些角色，该角色有hp（血量）、mp（魔法值），在游戏的面板中，有个区域展示这些剩余的量。
 * 解决a包的问题：观察者模式
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
    public void notifyAllObserver(Integer hp){
        for (MyObserver myObserver : myObservers) {
            System.out.println("通知："+ myObserver.getClass().getSimpleName());
            myObserver.setHp(hp);
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
        notifyAllObserver(hp);
        
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
     void setHp(Integer hp);
}
class Panel implements MyObserver {


    @Override
    public void setHp(Integer hp) {

        System.out.println("左上角面板更新hp的值为："+hp);
    }
}
class HeadPanel implements MyObserver {


    @Override
    public void setHp(Integer hp) {
        System.out.println("角色头顶面板更新hp的值为："+hp);
    }
}
class BallPanel implements MyObserver {


    @Override
    public void setHp(Integer hp) {
        System.out.println("右下角球型面板更新hp的值为："+hp);
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
         * 1、目前这个主体只把自己的hp广播给观察者，如果游戏公司老板又要加入 愤怒值，又要加入翅膀。。。一系列的，那应该怎么办？
         * Role这个类的属性越来越多，怎么解决？ c 包
         *
         */
    }
}
