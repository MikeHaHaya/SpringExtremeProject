package app.core;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import app.core.exceptions.CouponSystemException;
import app.core.services.AdminService;

@SpringBootApplication
public class ProjectSpringApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext ctx = SpringApplication.run(ProjectSpringApplication.class, args);
		
//		AdminService admin = ctx.getBean(AdminService.class);
//		
//		
//		try {
//			
//			System.out.println(admin.getOneCustomer(1));
//		} catch (CouponSystemException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
	}

}
