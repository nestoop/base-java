package cn.nest.dip;

/**
 * Created by botter
 * on 17-4-23.
 */
public class Driver implements IDriver {

    private ICar car;

    public Driver(ICar car) {//construct jndi abstract class
        this.car = car;
    }

    public Driver(){}

    @Override
    public void driver(ICar car) {
        car.run();
    }

    @Override
    public void driver() {
        car.run();
    }

    @Override
    public void setCar(ICar car) {
        this.car = car;
    }
}
