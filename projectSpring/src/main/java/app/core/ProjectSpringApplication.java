package app.core;

import java.time.LocalDateTime;

import app.core.services.AdminService;
import app.core.services.CompanyService;
import app.core.services.CustomerService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import app.core.entities.Coupon;
import app.core.entities.Coupon.Category;

@SpringBootApplication
public class ProjectSpringApplication {

    /*
    	Made by Eitan Etzion and Daniel Har-Oz
     */
    public static void main(String[] args) {
        try {


            ConfigurableApplicationContext ctx = SpringApplication.run(ProjectSpringApplication.class, args);
//			CouponExpirationDailyJob.startThread(ctx);

//			LoginManagerTest.runTest(ctx);

            AdminService admin = ctx.getBean(AdminService.class);
			CompanyService companyService = ctx.getBean(CompanyService.class);
            CustomerService customerService = ctx.getBean(CustomerService.class);

            //Methods to add to the DB
//			Customer customer = new Customer(0, "a", "a", "a@a", "128376gh", null);
//			Customer customer1 = new Customer(0, "b", "b", "b@b", "128376gh", null);
//			Customer customer2 = new Customer(0, "c", "c", "c@c", "128376gh", null);
//			Customer customer3 = new Customer(0, "d", "d", "d@d", "128376gh", null);
//			Customer customer4 = new Customer(0, "e", "e", "e@e", "128376gh", null);
//			admin.addNewCustomer(customer);
//			admin.addNewCustomer(customer1);
//			admin.addNewCustomer(customer2);
//			admin.addNewCustomer(customer3);
//			admin.addNewCustomer(customer4);
//
//			Company company = new Company(0, "com1", "com12@a", "13fd897h", null);
//			Company company1 = new Company(0, "com2", "com2@a", "13fd897h", null);
//			Company company2 = new Company(0, "com3", "com3@a", "13fd897h", null);
//			Company company3 = new Company(0, "com4", "com4@a", "13fd897h", null);
//			Company company4 = new Company(0, "com5", "com5@a", "13fd897h", null);
//			admin.addNewCompany(company);
//			admin.addNewCompany(company1);
//			admin.addNewCompany(company2);
//			admin.addNewCompany(company3);
//			admin.addNewCompany(company4);

//			Coupon coupon = new Coupon(0, null, 5, "coupon1", "the best coupon", "c://", LocalDateTime.now(),
//					LocalDateTime.now().plusDays(9), 5.5, Category.ELECTRICITY);
//			Coupon coupon1 = new Coupon(0, null, 6, "coupon2", "amazing coupon", "c://", LocalDateTime.now(),
//					LocalDateTime.now().plusDays(2), 20, Category.RESTAURANT);
//			Coupon coupon2 = new Coupon(0, null, 7, "coupon3", "breathtaking coupon", "c://", LocalDateTime.now(),
//					LocalDateTime.now().plusDays(3), 25.5, Category.BOOK);
//			Coupon coupon3 = new Coupon(0, null, 8, "coupon4", "unforgettable coupon", "c://", LocalDateTime.now(),
//					LocalDateTime.now().plusDays(7), 99.9, Category.FOOD);
//
//          Company services:
//			companyService.setId(1);
//			companyService.addNewCoupon(coupon);
//            companyService.deleteCoupon(1);
//			companyService.setId(2);
//			companyService.addNewCoupon(coupon1);
//
//			companyService.setId(3);
//			companyService.addNewCoupon(coupon2);
//
//			companyService.setId(4);
//			companyService.addNewCoupon(coupon3);


//            admin services:
//			admin.deleteCompany(4);
//			admin.updateComapny(1, company);
//			System.out.println(admin.getAllCompanies());
//			Company com = admin.getOneCompany(4);
//          System.out.println(com);
//			Customer customerFromDB = admin.getOneCustomer(1);
//			System.out.println(customerFromDB);
//            admin.deleteCompany(4);

//            customer services:
//         customerService.setId(1);
//		   customerService.purchaseCoupon(2);
//         System.out.println(customerService.getCoupons());
//

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

}
