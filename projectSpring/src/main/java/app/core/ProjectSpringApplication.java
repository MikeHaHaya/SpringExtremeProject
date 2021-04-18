package app.core;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;

import app.core.entities.Company;
import app.core.entities.Customer;
import app.core.exceptions.CouponSystemException;
import app.core.services.AdminService;
import app.core.services.ClientService;
import app.core.services.CustomerService;

@SpringBootApplication
public class ProjectSpringApplication {

//	done with Daniel Har-Oz
	public static void main(String[] args) {
		try {

			ConfigurableApplicationContext ctx = SpringApplication.run(ProjectSpringApplication.class, args);
		
		AdminService admin = ctx.getBean(AdminService.class);
		
		Customer customer = new Customer(0, "a", "a", "a@a", "128376gh", null);
		Customer customer1 = new Customer(0, "b", "b", "b@b", "128376gh", null);
		Customer customer2 = new Customer(0, "c", "c", "c@c", "128376gh", null);
		Customer customer3 = new Customer(0, "d", "d", "d@d", "128376gh", null);
		Customer customer4 = new Customer(0, "e", "e", "e@e", "128376gh", null);
		admin.addNewCustomer(customer);
		admin.addNewCustomer(customer1);
		admin.addNewCustomer(customer2);
		admin.addNewCustomer(customer3);
		admin.addNewCustomer(customer4);
		
		Company company = new Company(0, "com1", "com1@a", "13fd897h", null);
		Company company1 = new Company(0, "com2", "com2@a", "13fd897h", null);
		Company company2 = new Company(0, "com3", "com3@a", "13fd897h", null);
		Company company3 = new Company(0, "com4", "com4@a", "13fd897h", null);
		Company company4 = new Company(0, "com5", "com5@a", "13fd897h", null);
		admin.addNewCompany(company);
		admin.addNewCompany(company1);
		admin.addNewCompany(company2);
		admin.addNewCompany(company3);
		admin.addNewCompany(company4);
//			Customer customerFromDB = admin.getOneCustomer(1);
//			System.out.println(customerFromDB);
		} catch (CouponSystemException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
