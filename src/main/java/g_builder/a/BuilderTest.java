package g_builder.a;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @description:
 * @author: huangguoqiang
 * @create: 2021-08-06 16:59
 **/
@Getter
@Setter
@ToString

class Computer {
    private String cpu;
    private String gpu;
    private String memory;
    private String hd;
}

public class BuilderTest {
    public static void main(String[] args) {
        Computer c = new Computer();
        c.setCpu("i7");
        c.setGpu("GT1550");
        c.setMemory("16G");
        c.setHd("500G");
        System.out.println(c);
    }
}
/**
 * 缺点：
 * 1、客户端程序员在实例化产品的对象后，要给对象的每一个属性赋值，这样对于客户端程序太麻烦了
 * 2、违反了迪米特原则
 * 相当于你去配电脑，商家把零件给你了，你自己去组装
 * <p>
 * 建造者模式和工厂模式的区别：
 * 工厂模式：都是直接实例化出一个对象即可。
 * 建造者模式：在实例化对象之后，还要给该对象的属性赋值。
 */
