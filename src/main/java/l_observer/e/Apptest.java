package l_observer.e;

import java.util.ArrayList;
import java.util.List;

/**
 * @description: 需求：气象站 检测到温度、湿度、气压三项值发送给需要的人
 * @author: huangguoqiang
 * @create: 2021-08-11 11:17
 **/
//主题 有一组观察者和对观察者的操作，那就再次抽象
interface Subject {
    void addObserver(Observer observer);

    void removeObserver(Observer observer);

    void notifyObservers();
}

class WeatherStation implements Subject {
    private Integer temperature;
    private Integer humidity;
    private Integer pressure;

    private List<Observer> observers = new ArrayList<>();

    @Override
    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers() {
        for (Observer observer : observers) {
            observer.update();
        }
    }


    //有个传感器，检测到三项值会调用这个方法传过来
    public void setData(Integer temperature, Integer humidity, Integer pressure) {
        this.temperature = temperature;
        this.humidity = humidity;
        this.pressure = pressure;

        //通知所有的观察者
        notifyObservers();

    }

    @Override
    public String toString() {
        return "WeatherStation{" +
                "temperature=" + temperature +
                ", humidity=" + humidity +
                ", pressure=" + pressure +
                '}';
    }
}

interface Observer {
    void update();
}

class Phone implements Observer {
    private WeatherStation ws;

    public Phone(WeatherStation ws) {
        this.ws = ws;
        ws.addObserver(this);
    }

    @Override
    public void update() {

        System.out.println("手机收到气象站的天气数据：" + ws);
    }
}


class Window implements Observer {
    private WeatherStation ws;

    public Window(WeatherStation ws) {
        this.ws = ws;
        ws.addObserver(this);
    }

    @Override
    public void update() {
        System.out.println("窗户上收到气象站的天气数据：" + ws);
    }
}

public class Apptest {
    public static void main(String[] args) throws InterruptedException {

        //模拟传感器
        WeatherStation ws = new WeatherStation();

        Phone phone = new Phone(ws);
        Window window = new Window(ws);


        ws.setData(32, 109, 306);


        Thread.sleep(1000L);
        System.out.println("15分钟后天气更新=======================");

        ws.setData(19, 132, 343);


    }
}
