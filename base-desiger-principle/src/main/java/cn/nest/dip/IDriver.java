package cn.nest.dip;

/**
 * Created by botter-common
 * on 17-4-23.
 */
public interface IDriver {

    void driver(ICar car);

    /**
     * setter jndi method
     */
    void driver();
    void setCar(ICar car);//setter jndi
}
