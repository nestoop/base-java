package cn.nest;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class NettyApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(NettyApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		for ( long i = 0 ; i < Long.MAX_VALUE; i++) {
		    new Thread(new Runnable() {
                @Override
                public void run() {
                    new NettyClient().client();
                }
            }).start();
        }
	}
}
