package d_liskov;

/**
 * @description: 里氏替换原则，正方形和长方形案例
 * @author: huangguoqiang
 * @create: 2021-08-03 16:22
 **/

class Utils {
    //使长方形的宽度大于其长度
    public static void transfer(Rectangle r) {
        while (r.getWidth() <= r.getLength()) {
            r.setWidth(r.getWidth() + 1);
            System.out.println(r);
        }
    }
}

/**
 * 长方形
 */
class Rectangle {
    private double length;
    private double width;

    public double getLength() {
        return length;
    }

    public void setLength(double length) {
        this.length = length;
    }

    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    @Override
    public String toString() {
        return "Rectangle{" +
                "length=" + length +
                ", width=" + width +
                '}';
    }
}

//正方形 使其继承长方形
class Square extends Rectangle{

    //正方形边长 （长度和宽度是一个）
    private double sideLength;

    @Override
    public double getLength() {
        return sideLength;
    }

    @Override
    public void setLength(double length) {
       this.sideLength = length;
    }

    @Override
    public double getWidth() {
        return sideLength;
    }

    @Override
    public void setWidth(double width) {
        this.sideLength = width;
    }

    @Override
    public String toString() {
        return "Square{" +
                "sideLength=" + sideLength +
                '}';
    }
}

public class AppTest {
    public static void main(String[] args) {
        Rectangle r = new Square();
        r.setWidth(6);
        r.setLength(10);
        Utils.transfer(r);
    }
}

