package l_observer.a;

/**
 * @description:
 * 需求：在游戏中有一些角色，该角色有hp（血量）、mp（魔法值），在游戏的面板中，有个区域展示这些剩余的量。
 * @author: huangguoqiang
 * @create: 2021-08-10 20:21
 **/


class Role{
    private String name;
    private Integer hp;
    private Integer mp;

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

        //模拟三个地方的处理逻辑
        System.out.println("血条修改为："+hp);
        System.out.println("球修改为："+hp);
        System.out.println("面板修改为："+hp);

        //又增加新的展示位置

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


public class AppTest {

    public static void main(String[] args) {
        Role r = new Role();
        r.setName("古天乐");
        r.setHp(100);
        r.setMp(100);

        //被攻击了
        Monster m = new Monster();
        m.attack(r);

        /**
         * 变化来了：客户想在页面上某个位置再次显示hp的剩余量，程序员很容易想到在Role类的setHp方法增加逻辑，
         * 这样做违反了开闭职责，单一职责原则。
         * 如何解决？b包
         *
         *
         */
    }
}
