package cn.java.design.proxy.staticproxy;

/**
 * Created by botter-common on 17-2-21.
 */
@SuppressWarnings("unused")
public class MineInterfaceImpl implements SampleInterface {

    public void add(String code) {
        System.out.println("add code :" + code);
    }

    public void delete(String code) {
        System.out.println("delete code :" + code);
    }

    public void update(String code) {
        System.out.println("update code :" + code);
    }

    public String query(String code) {
        return "query" + code;
    }
}
