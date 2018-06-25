package cn.nest;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author botter
 */
@SpringBootApplication
public class MQTTServerApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(MQTTServerApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println("mqtt server application...");
	}
}
