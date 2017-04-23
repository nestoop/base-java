package cn.java.design.brige;

/**
 * Created by botter-common
 * on 17-4-23.
 */
public class HandlerTest {

    public static void main(String[] args) {

        IDriver driver = new CurrentDriver1();

        AbstractChannelHandler handler = new RefindedChannelHandler(driver);

        handler.handleMessage();

    }
}
