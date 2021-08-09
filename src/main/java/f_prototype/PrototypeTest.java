package f_prototype;


import lombok.Getter;
import lombok.Setter;

import java.io.*;
import java.util.Date;

/**
 * @description: 原型模式，进行深拷贝（属性是一个类的，也要拷贝一份，而不是引用同一个内存地址），
 * @author: huangguoqiang
 * @create: 2021-08-06 16:10
 **/
@Setter
@Getter
class WeekReport implements Cloneable, Serializable {
    /**
     * 写周报：
     * 姓名
     * 本周工作总结：
     * 下周工作计划：
     * 问题反馈：
     * 需要帮助：
     * 日期：
     */


    private String name;
    private String summary;
    private String plan;
    private String problem;
    private String help;
    private Date date;

    @Override
    public String toString() {
        return "WeekReport{" +
                "name='" + name + '\'' +
                ", summary='" + summary + '\'' +
                ", plan='" + plan + '\'' +
                ", problem='" + problem + '\'' +
                ", help='" + help + '\'' +
                ", date=" + date +
                '}';
    }

    @Override
    public Object clone() throws CloneNotSupportedException {

        try {
            //字节输出流
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(baos);

            //将当前对象写入内存
            oos.writeObject(this);

            //从内存取数据
            byte[] datas = baos.toByteArray();

            //从内存中把对象读出来
            ByteArrayInputStream bais = new ByteArrayInputStream(datas);
            ObjectInputStream ois = new ObjectInputStream(bais);
            Object obj = ois.readObject();

            return obj;

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }

    }
}

public class PrototypeTest {
    public static void main(String[] args) throws CloneNotSupportedException {
        WeekReport wr = new WeekReport();
        wr.setName("zhangsan");
        wr.setSummary("开发了支付");
        wr.setPlan("开发信贷");
        wr.setHelp("无");
        wr.setProblem("无");
        wr.setDate(new Date());

        System.out.println("原型："+wr);

        WeekReport wr2 = (WeekReport)wr.clone();
        wr2.setSummary("啥也没干");
        wr2.setPlan("不相干");
        wr2.getDate().setTime(0L);
        System.out.println("克隆："+wr2);
        System.out.println("原型："+ wr);
        System.out.println(wr2==wr);

    }
}
