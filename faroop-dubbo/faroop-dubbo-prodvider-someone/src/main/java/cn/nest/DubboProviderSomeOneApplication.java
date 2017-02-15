package cn.nest;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.ImportResource;

import java.io.IOException;

/**
 * DubboProviderApplication
 */
@SpringBootApplication
@ImportResource(value = "classpath:spring-dubbo-provider-someone.xml")
public class DubboProviderSomeOneApplication {

    public static void main(String[] args) throws IOException {
        new SpringApplicationBuilder().sources(DubboProviderSomeOneApplication.class).web(false).run(args);
        while(true) {
            System.in.read();
        }
    }
}