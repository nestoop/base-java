package cn.java.design.brige;

/**
 * Created by botter-common
 * on 17-4-23.
 */
public class CurrentDriver1 implements IDriver {

    @Override
    public void isConnected() {
        System.out.println("CurrentDriver1 is connected");
    }

    @Override
    public void afterConnected() {
        System.out.println("CurrentDriver1 afterConnected");
    }

    @Override
    public Object getConnection() {
        return null;
    }

    @Override
    public void close() {
        System.out.println("CurrentDriver1 close");
    }
}
