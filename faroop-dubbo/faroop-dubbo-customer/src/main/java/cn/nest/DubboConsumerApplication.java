package cn.nest;

/**
 * Hello world!
 */

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
        new SpringApplicationBuilder().main(DubboConsumerApplication.class).web(false).run(args);
        while (true) {
            try {
                System.in.read();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void run(String... strings) throws Exception {

    }
}