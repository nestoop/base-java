package cn.nest.netty;

/**
 * Created by botter-common
 * on 17-4-6.
 */
public class ConnectInfo {

    private Long startTime;

    private Long endTime;

    private int size;

    private String name;

    private Object object = new Object();

    public ConnectInfo(){}

    public ConnectInfo(int size) {
        setStartTime();
        this.size = size;
    }

    public ConnectInfo(String name) {
        this.name = name;
    }

    public void setStartTime() {
       synchronized (object){
           startTime = System.currentTimeMillis();
       }
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getSize () {
        return size;
    }

    public Long getStartTime() {
        return startTime;
    }

    public void setEndTime() {
        synchronized (object){
            endTime = System.currentTimeMillis();
        }
    }

    public Long getEndTime () {
        return endTime;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
