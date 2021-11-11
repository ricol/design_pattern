package com.rmit.design_patterns.observerpattern;

import java.util.*;

public class SignalLightEvent
{
    public static void main(String[] args)
    {
        SignalLight light = new SignalLight();//交通信号灯（事件源）
        light.addVehicleListener(new Car());  //注册监听器（轿车）
        light.addVehicleListener(new Buses());//注册监听器（公交车）
        light.changeColor("红色");
        System.out.println("------------");
        light.changeColor("绿色");
    }
}

//信号灯颜色
class SignalColor extends EventObject
{
    private String color; //"红色"和"绿色"

    public SignalColor(Object source, String color)
    {
        super(source);
        this.color = color;
    }

    public void setColor(String color)
    {
        this.color = color;
    }

    public String getColor()
    {
        return this.color;
    }
}

//目标类：事件源，交通信号灯
class SignalLight
{
    private List<Vehicle> listener; //监听器容器

    public SignalLight()
    {
        listener = new ArrayList<Vehicle>();
    }

    //给事件源绑定监听器
    public void addVehicleListener(Vehicle vehicle)
    {
        listener.add(vehicle);
    }

    //事件触发器：信号灯改变颜色。
    public void changeColor(String color)
    {
        System.out.println(color + "信号灯亮...");
        SignalColor event = new SignalColor(this, color);
        notifies(event);    //通知注册在该事件源上的所有监听器
    }

    //当事件发生时,通知绑定在该事件源上的所有监听器做出反应（调用事件处理方法）
    protected void notifies(SignalColor e)
    {
        Vehicle vehicle = null;
        Iterator<Vehicle> iterator = listener.iterator();
        while (iterator.hasNext())
        {
            vehicle = iterator.next();
            vehicle.see(e);
        }
    }
}

//抽象观察者类：车
interface Vehicle extends EventListener
{
    //事件处理方法，看见
    public void see(SignalColor e);
}

//具体观察者类：轿车
class Car implements Vehicle
{
    public void see(SignalColor e)
    {
        if ("红色".equals(e.getColor()))
        {
            System.out.println("红灯亮，轿车停！");
        } else
        {
            System.out.println("绿灯亮，轿车行！");
        }
    }
}

//具体观察者类： 公交车
class Buses implements Vehicle
{
    public void see(SignalColor e)
    {
        if ("红色".equals(e.getColor()))
        {
            System.out.println("红灯亮，公交车停！");
        } else
        {
            System.out.println("绿灯亮，公交车行！");
        }
    }
}
