package cn.java.design.brige;

/**
 * Created by botter-common
 * on 17-4-23.
 */
public interface IDriver {

    void isConnected();

    void afterConnected();

    Object getConnection();

    void close();
}
