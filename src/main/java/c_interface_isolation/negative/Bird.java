package c_interface_isolation.negative;

public class Bird implements Animal {
    @Override
    public void eat() {
        System.out.println("吃");
    }

    @Override
    public void fly() {
        System.out.println("飞");
    }

    //======鸟不会游泳，并不需要实现
    @Override
    public void swim() {
        System.out.println("游泳");
    }
}