package cn.nest;

/**
 * Hello world!
 */

import cn.nest.facde.HelloService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;

@SpringBootApplication
public class DubboConsumerApplication implements CommandLineRunner {

    ApplicationContext factory = new ClassPathXmlApplicationContext("classpath:spring-dubbo-consumer.xml");

    public static void main(String[] args) {
        new SpringApplicationBuilder().sources(DubboConsumerApplication.class).web(false).run(args);

        while (true) {
            try {
                System.in.read();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void run(String... strings) throws Exception {
        HelloService iHelloService = (HelloService) factory.getBean("helloService");
        System.out.println(iHelloService.sayHello("botter"));
    }
}