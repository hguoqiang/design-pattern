package i_adaptor.a;

import java.util.Arrays;

/**
 * @description:
 * @author: huangguoqiang
 * @create: 2021-08-07 18:17
 **/

//作者写的代码
interface Processor {
    String name();

    Object process(Object input);
}

abstract class StringProcessor implements Processor {
    @Override
    public String name() {
        return this.getClass().getSimpleName();
    }

    @Override
    public Object process(Object input) {
        return input;
    }
}

class UpCase extends StringProcessor {
    @Override
    public String process(Object input) {
        return ((String) input).toUpperCase();
    }
}

class LowCase extends StringProcessor {
    @Override
    public String process(Object input) {
        return ((String) input).toLowerCase();
    }
}

class Splitter extends StringProcessor {
    @Override
    public String process(Object input) {
        return Arrays.toString(((String) input).split(" "));
    }
}

class Apply {

    public static void process(Processor p, Object input) {
        System.out.println(p.name() + " :" + p.process(input));
    }
}

//作者写了适配器去扩展使用 Filter
class FilterAdaptor implements Processor {
    //关联关系
    private Filter f;

    public FilterAdaptor(Filter f) {
        this.f = f;
    }

    @Override
    public String name() {
        return f.name();
    }

    @Override
    public Object process(Object input) {
        return f.process((Wavefrom) input);
    }
}

//===========================================
//作者发现别人写的代码
class Wavefrom {
    private static long counter;
    private final long id = counter++;

    @Override
    public String toString() {
        return "Wavefrom{" +
                "id=" + id +
                '}';
    }
}

class Filter {
    public String name() {
        return this.getClass().getSimpleName();
    }

    public Wavefrom process(Wavefrom input) {
        return input;
    }
}

class LowPass extends Filter {

    double cutoff;

    public LowPass(double cutoff) {
        this.cutoff = cutoff;
    }

    @Override
    public Wavefrom process(Wavefrom input) {
        // dummy processing 假装处理过
        return input;
    }
}

class HighPass extends Filter {

    double cutoff;

    public HighPass(double cutoff) {
        this.cutoff = cutoff;
    }

    @Override
    public Wavefrom process(Wavefrom input) {
        // dummy processing 假装处理过
        return input;
    }
}

class BandPass extends Filter {

    double lowCutoff, highCutOff;

    public BandPass(double lowCutoff, double highCutOff) {
        this.lowCutoff = lowCutoff;
        this.highCutOff = highCutOff;
    }

    @Override
    public Wavefrom process(Wavefrom input) {
        // dummy processing 假装处理过
        return input;
    }
}


public class AppTest {
    public static void main(String[] args) {

        //作者使用自己的代码（调用三个类）
        String s = "How Are You";
        Processor p = new UpCase();
        System.out.println(p.name() + " :" + p.process(s));

        Processor p2 = new LowCase();
        System.out.println(p2.name() + " :" + p2.process(s));


        Processor p3 = new Splitter();
        System.out.println(p3.name() + " :" + p3.process(s));

        System.out.println("==============================");
        //作者发现这个使用代码都是重复的 所以出现了 Apply类
        Apply.process(new UpCase(), s);
        Apply.process(new LowCase(), s);
        Apply.process(new Splitter(), s);

        System.out.println("==============================");
        //突然有一天，作者发现了一个类 Wavefrom 和 Filter ，使用他们。
        Wavefrom wf = new Wavefrom();
        Filter f = new HighPass(3);
        System.out.println(f.name() + " : " + f.process(wf));

        Wavefrom wf2 = new Wavefrom();
        Filter f2 = new LowPass(-1);
        System.out.println(f2.name() + " : " + f2.process(wf2));

        Wavefrom wf3 = new Wavefrom();
        Filter f3 = new BandPass(-1, 19);
        System.out.println(f3.name() + " : " + f3.process(wf3));

        System.out.println("==============================");
        // Filter类和作者写的 Processor 类的功能很相似，那么该如何复用 Apply呢？ 如何适配呢？
        Apply.process(new FilterAdaptor(new HighPass(3)), new Wavefrom());
        Apply.process(new FilterAdaptor(new LowPass(-3)), new Wavefrom());
        Apply.process(new FilterAdaptor(new BandPass(-1, 3)), new Wavefrom());

    }
}
