package cn.nest.facde;

/**
 * Created by botter on 17-1-22.
 */
public class HelloServiceMock implements HelloService {
    @Override
    public String sayHello(String content) {
        System.out.println("dubbo mock sample....");
        throw new RuntimeException("say hello fail....");
    }
}
