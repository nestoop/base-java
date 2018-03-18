package com.sohouer.core.kafka;




import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;




/**
 * spring kafka !
 *
 */

@ComponentScan(basePackages = "com.sohouer.*")
@EnableConfigurationProperties
@EnableAutoConfiguration(exclude = HibernateJpaAutoConfiguration.class)
public class App implements CommandLineRunner {
	
	
	
	public static void main(String[] args) {
		SpringApplication application = new SpringApplication(App.class);
		System.out.println("kafka 测试 start!");
		application.run(args);
	}

	@Override
	public void run(String... args) throws Exception {
//		Container.listenerContainer("inspiryTopic");
		
	}

}
