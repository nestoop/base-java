package cn.nest.facde;



/**
 * Created by botter
 * on 17-1-22.
 */
@SuppressWarnings("unused")
public class HelloServiceMock implements HelloService {
    public HelloServiceMock(){}

    @Override
    public String sayHello(String content) {
        System.out.println("dubbo mock sample....");
       return "say hello fail";
    }
}
