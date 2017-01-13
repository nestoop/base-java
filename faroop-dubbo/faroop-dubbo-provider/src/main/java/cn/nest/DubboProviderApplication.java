package cn.nest;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

import java.io.IOException;

/**
 * DubboProviderApplication
 *
 */
@SpringBootApplication
public class DubboProviderApplication {

    public static void main(String[] args) {
        new SpringApplicationBuilder().main(DubboProviderApplication.class).web(false).run(args);
        while (true) {
            try {
                System.in.read();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}