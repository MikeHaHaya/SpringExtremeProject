package app.core.tests;

import app.core.entities.Company;
import app.core.entities.Customer;
import app.core.exceptions.CouponSystemException;
import app.core.exceptions.ServiceException;
import app.core.login.ClientType;
import app.core.login.LoginManager;
import app.core.services.AdminService;
import app.core.services.CompanyService;
import app.core.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Scanner;

@Component
@DependsOn({"couponExpirationThread", "loginManager"})
@Scope("singleton") // Will run automatically on application startup
public class MainTest {

    @Autowired
    private LoginManager manager;
    private static final Scanner scan = new Scanner(System.in);

    /**
     * An empty constructor
     */
    public MainTest() {
    }

    /**
     * Gives a developer test to test the entire system functioning.
     */
    @PostConstruct // To ensure that LoginManager is injected prior to this method.
    public void testAll() {

        mainMenu(manager);

    }

    /**
     * Interact with the user to get the ClientType.
     */
    public static void mainMenu(LoginManager manager) {

        startMenu();
        boolean quit = false;

        while (!quit) {

            System.out.println();
            System.out.println("To start AdminService test....................................enter 1");
            System.out.println("To start CompanyService test..................................enter 2");
            System.out.println("To start CustomerService test.................................enter 3");
            System.out.println("To clear test and reset database..............................enter 4");
            System.out.println("To quit.......................................................enter 5");

            String key = scan.nextLine();

            switch (key) {

                case "1":
                    space();
                    adminMenu(manager);
                    break;
                case "2":
                    space();
                    companyMenu(manager);
                    break;
                case "3":
                    space();
                    customerMenu(manager);
                    break;
                case "4":
                    space();
                    clearAll(manager);
                    break;
                case "5":
                    quit = true;
                    break;
                default:
                    System.out.println("Please enter one of the following keys: ");
            }

        }
    }

    /**
     * The start menu that interacts with the developer.
     */
    public static void startMenu() {

        System.out.println("Welcome to CouponsSystem!");
        System.out.println("================================");
        System.out.println();
        System.out.println("This is a test made for developers.");
        System.out.println("The test will run over every aspect in the program automatically,");
        System.out.println("the only request from the developer is to press enter to continue.");
        System.out.println("There are small sleeps of half a second for interface purposes only (more readable and doesn't look like random text).");
        System.out.println();
        System.out.println("At the beginning of the test, you will be shown a menu.");
        System.out.println("It is important to test everything by order (AdminService, CompanyService, CustomerService),");
        System.out.println("otherwise unwanted exceptions will be thrown and the test will be damaged.");
        System.out.println();
        System.out.println("When you are ready, press enter to start the test: ");
        scan.nextLine();
        space();

    }

    /**
     * Interacts with an administrator type user.
     */
    public static void adminMenu(LoginManager manager) {

        System.out.println("Logs in as an admin");
        System.out.println("Email: admin@admin.com");
        System.out.println("Password: admin");

        AdminService service = logInAdmin(manager);
        System.out.println("Logged in successfully");

        space();

        AdminTest.initAdmin(logInAdmin(manager));
        AdminTest.addCompanies(service);
        AdminTest.updateCompanies(service);
        AdminTest.deleteCompanies(service);
        AdminTest.getAllCompanies(service);
        AdminTest.getOneCompany(service);
        AdminTest.addCustomers(service);
        AdminTest.updateCustomers(service);
        AdminTest.deleteCustomers(service);
        AdminTest.getAllCustomers(service);
        AdminTest.getOneCustomer(service);

        System.out.println("AdminService test is over, returning to main menu. ");

        space();
    }

    /**
     * Interacts with a company type user. */
    public static void companyMenu(LoginManager manager) {

        System.out.println("Logs in as Company1");
        System.out.println("Email: company1@updated-company.com");
        System.out.println("Password: 0000");

        CompanyService service = logInCompany(manager);
        System.out.println("Logged in successfully");

        space();

        CompanyTest.initCompany(logInAdmin(manager), logInCompany(manager));
        CompanyTest.addCoupons();
        CompanyTest.updateCoupons();
        CompanyTest.deleteCoupons();
        CompanyTest.getAllCoupons();
        CompanyTest.getAllCouponsCat();
        CompanyTest.getAllCouponsMax();
        CompanyTest.getCompanyDetails();

        System.out.println("CompanyService test is over, returning to main menu. ");

        space();

    }

    /**
     * Interacts with a customer type user. */
    public static void customerMenu(LoginManager manager) {

        CustomerService service = null;
        System.out.println("Logs in as Customer1");
        System.out.println("Email: customer1@updated-customer.com");
        System.out.println("Password: 0000");

        try {
            service = (CustomerService) manager.login("customer1@updated-customer.com", "0000", ClientType.Customer);
            System.out.println("Logged in successfully");
        } catch (CouponSystemException e) {
            e.printStackTrace();
        }

        space();

        CustomerTest.initCustomer(logInAdmin(manager),logInCompany(manager), service);
        CustomerTest.purchaseCoupons();
        CustomerTest.getCustomerCoupons();
        CustomerTest.getCustomerCouponsCat();
        CustomerTest.getCustomerCouponsMax();
        CustomerTest.getCustomerDetails();

        System.out.println("CustomerService test is over, returning to main menu. ");

        space();
    }

    /**
     * Space and sleep.
     */
    public static void space() {

        sleep();
        System.out.println();
        System.out.println("===================================================");
    }

    /**
     * Makes the program sleep for half a second.
     */
    public static void sleep() {

        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * Clears the entire database.
     */
    public static void clearAll(LoginManager manager) {

        AdminService service = null;

        try {
            service = (AdminService) manager.login("admin@admin.com", "admin", ClientType.Administrator);
        } catch (CouponSystemException e) {
            e.printStackTrace();
        }

        // Deletes all companies
        try {
            ArrayList<Company> companies = (ArrayList<Company>) service.getAllCompanies();
            for (Company company : companies) {
                service.deleteCompany(company.getId());
            }

            ArrayList<Customer> customers = (ArrayList<Customer>) service.getAllCustomers();
            for (Customer customer : customers) {
                service.deleteCustomer(customer.getId());
            }

            System.out.println("Database cleared successfully");


        } catch (CouponSystemException e) {
            e.printStackTrace();
        }

        space();
    }

    /**
     * Logs in an admin.
     */
    public static AdminService logInAdmin(LoginManager manager) {

        AdminService service = null;

        try {
            service = (AdminService) manager.login("admin@admin.com", "admin", ClientType.Administrator);
        } catch (CouponSystemException e) {
            e.printStackTrace();
        }

        return service;
    }

    /**
     * Logs in a company.
     */
    public static CompanyService logInCompany(LoginManager manager) {

        CompanyService service = null;

        try {
            service = (CompanyService) manager.login("company1@updated-company.com", "0000", ClientType.Company);
        } catch (CouponSystemException e) {
            e.printStackTrace();
        }

        return service;
    }
}
