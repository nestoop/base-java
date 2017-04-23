package cn.nest.dip;

/**
 * Created by botter-common
 * on 17-4-23.
 */
public class Car implements ICar{

    @Override
    public void run() {
        System.out.println("car run....");
    }
}
