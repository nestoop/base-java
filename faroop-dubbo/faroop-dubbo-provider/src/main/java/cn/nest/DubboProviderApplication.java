package cn.nest;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

import java.io.IOException;

/**
 * DubboProviderApplication
 */
@SpringBootApplication
public class DubboProviderApplication {

    public static void main(String[] args) throws IOException {
        new SpringApplicationBuilder().sources(DubboProviderApplication.class).web(false).run(args);
        while(true) {
            System.in.read();
        }
    }
}