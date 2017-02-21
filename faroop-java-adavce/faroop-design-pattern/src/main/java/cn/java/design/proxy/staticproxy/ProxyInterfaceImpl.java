package cn.java.design.proxy.staticproxy;

/**
 * Created by botter-common on 17-2-21.
 */
@SuppressWarnings("unused")
public class ProxyInterfaceImpl implements SampleInterface {

    private SampleInterface mineInterface;

    public ProxyInterfaceImpl(MineInterfaceImpl mineInterfaceImpl) {
        this.mineInterface =  mineInterfaceImpl;
    }

    public void add(String code) {
        mineInterface.add(code);
    }

    public void delete(String code) {
        mineInterface.delete(code);
    }

    public void update(String code) {
        mineInterface.update(code);
    }

    public String query(String code) {
        return "proxy code " + code;
    }
}
