package cn.nest;

import cn.nest.dip.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PrincipleApplication implements CommandLineRunner{

	public static void main(String[] args) {
		SpringApplication.run(PrincipleApplication.class, args);
	}

	@Override
	public void run(String... strings) throws Exception {
		ICar car  = new Car();
		IDriver Zhangsan = new Driver(car);
		Zhangsan.driver(car);

		IDriver lisi = new Driver();
		lisi.setCar(car);
		lisi.driver();

		ICar iCar = new Benz();
		IDriver wangwu = new Driver();
		wangwu.setCar(iCar);
		wangwu.driver();


	}
}
