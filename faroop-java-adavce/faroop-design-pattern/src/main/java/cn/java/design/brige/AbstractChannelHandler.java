package cn.java.design.brige;

/**
 * Created by botter-common
 * on 17-4-23.
 */
public abstract class AbstractChannelHandler {

    private IDriver driver;

    public AbstractChannelHandler(IDriver driver) {
        this.driver = driver;
    }

    public void channelActive() {
        this.driver.isConnected();
    }

    public void channelInActive() {
        this.driver.close();
    }

    public void handleMessage() {
        this.driver.afterConnected();
    }

    public IDriver getImplementor() {
        return this.driver;
    }
}
