package app.core.tests;

import app.core.entities.Coupon;
import app.core.entities.Customer;
import app.core.exceptions.CouponSystemException;
import app.core.services.AdminService;
import app.core.services.CompanyService;
import app.core.services.CustomerService;

import java.util.ArrayList;
import java.util.Scanner;

public class CustomerTest {

    private static final Scanner scan = new Scanner(System.in);
    private static CustomerService service;
    private static Customer customer;
    private static CompanyService compService;

    /**
     * Purchases coupons from CustomerService.
     */
    public static void purchaseCoupons() {

        MainTest.sleep();
        System.out.println("Auto-adding 2 purchases - Should run smoothly (press enter to continue): ");
        scan.nextLine();
        System.out.println();


        // Trying to buy coupon1
        Coupon coupon1 = compService.getCoupons().get(0);
        System.out.println("Trying to buy Coupon1: ");
        System.out.println(coupon1);

        try {
            service.purchaseCoupon(coupon1.getId());
            System.out.println("Coupon1 was purchased successfully");
        } catch (CouponSystemException e) {
            e.printStackTrace();
        }

        MainTest.sleep();
        System.out.println();


        // Trying to buy coupon1
        Coupon coupon2 = compService.getCoupons().get(1);
        System.out.println("Trying to buy Coupon2: ");
        System.out.println(coupon2);

        try {
            service.purchaseCoupon(coupon2.getId());
            System.out.println("Coupon2 was purchased successfully");
        } catch (CouponSystemException e) {
            e.printStackTrace();
        }

        MainTest.sleep();
        System.out.println();
        System.out.println("Auto-adding a coupon that was already bought - Should throw an exception (press enter to continue):");
        scan.nextLine();
        System.out.println();


        // Trying to buy coupon1 again
        System.out.println("Trying to buy Coupon1 again: ");
        System.out.println(coupon1);

        try {
            service.purchaseCoupon(coupon1.getId());
            System.out.println("Coupon1 was purchased successfully");
        } catch (CouponSystemException e) {
            e.printStackTrace();
        }

        MainTest.space();
    }

    /**
     * Gets all coupons bought by this customer from CustomerService.
     */
    public static void getCustomerCoupons() {

        MainTest.sleep();
        System.out.println("Auto-getting all coupons bought by this customer - Should run smoothly (press enter to continue): ");
        scan.nextLine();
        System.out.println();

        ArrayList<Coupon> coupons = new ArrayList<Coupon>();
        coupons = service.getCoupons();
        System.out.println("Coupons received successfully");
        for (Coupon coupon : coupons) {
            System.out.println(coupon);
        }

        MainTest.space();
    }

    /**
     * Gets all coupons bought by this customer in the electricity category from CustomerService.
     */
    public static void getCustomerCouponsCat() {

        MainTest.sleep();
        System.out.println("Auto-getting all coupons bought by this customer in the electricity category - Should run smoothly (press enter to continue): ");
        scan.nextLine();
        System.out.println();

        ArrayList<Coupon> coupons = new ArrayList<Coupon>();
        coupons = service.getCouponsByCategory(Coupon.Category.ELECTRICITY);
        System.out.println("Coupons received successfully");
        for (Coupon coupon : coupons) {
            System.out.println(coupon);
        }

        MainTest.space();
    }

    /**
     * Gets all coupons bought by this customer with a max price of 100 from CustomerService.
     */
    public static void getCustomerCouponsMax() {

        MainTest.sleep();
        System.out.println("Auto-getting all coupons bought by this customer with a max price of 100 - Should run smoothly (press enter to continue): ");
        scan.nextLine();
        System.out.println();

        ArrayList<Coupon> coupons = new ArrayList<Coupon>();
        coupons = service.getCouponsByMaxPrice(100);
        System.out.println("Coupons received successfully");
        for (Coupon coupon : coupons) {
            System.out.println(coupon);
        }

        MainTest.space();
    }

    /**
     * Gets more details about this customer. */
    public static void getCustomerDetails() {

        MainTest.sleep();
        System.out.println("Auto-getting details about this customer - Should run smoothly (press enter to continue): ");
        scan.nextLine();
        System.out.println();

        try {
            String details = service.getCustomerDetails();
            System.out.println("Details received successfully");
            System.out.println(details);
        } catch (CouponSystemException e) {
            e.printStackTrace();
        }

        MainTest.space();
    }

    /**
     * Initializes a customer for future use.
     */
    public static void initCustomer(AdminService adminService, CompanyService companyService, CustomerService customerService) {
        compService = companyService;
        service = customerService;
        customer = adminService.getAllCustomers().get(0);
    }
}
