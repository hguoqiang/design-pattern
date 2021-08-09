package g_builder.b;

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

class  ComputerBuilder {
    Computer c = new Computer();

    public ComputerBuilder setCpu(String cpu) {
        c.setCpu(cpu);
        return this;
    }

    public ComputerBuilder setGpu(String gpu) {
        c.setGpu(gpu);
        return this;
    }

    public ComputerBuilder setMemory(String memory) {
        c.setMemory(memory);
        return this;
    }

    public ComputerBuilder setHd(String hd) {
        c.setHd(hd);
        return this;
    }

    public Computer build() {
        return c;
    }
}


public class BuilderTest {
    public static void main(String[] args) {
        ComputerBuilder builder = new ComputerBuilder();
        Computer c = builder.setCpu("i9").setGpu("GTX1009").setHd("800G").build();
        System.out.println(c);
    }
}

