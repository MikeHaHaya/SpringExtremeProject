package app.core;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;

import app.core.exceptions.CouponSystemException;
import app.core.services.AdminService;
import app.core.services.ClientService;
import app.core.services.CustomerService;

@ComponentScan("app.core")
@SpringBootApplication
public class ProjectSpringApplication {

//	done with Daniel Har-Oz
	public static void main(String[] args) {
		ConfigurableApplicationContext ctx = SpringApplication.run(ProjectSpringApplication.class, args);
		
//		CustomerService customer = ctx.getBean(CustomerService.class);
		
	}

}
