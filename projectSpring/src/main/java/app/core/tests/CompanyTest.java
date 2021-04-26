package app.core.tests;

import app.core.entities.Company;
import app.core.entities.Coupon;
import app.core.exceptions.CouponSystemException;
import app.core.services.AdminService;
import app.core.services.CompanyService;

import java.sql.Date;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Scanner;

public class CompanyTest {

    private static final Scanner scan = new Scanner(System.in);
    private static Company company;
    private static CompanyService service;

    /**
     * Adds coupons from CompanyService.
     */
    public static void addCoupons() {

        MainTest.sleep();
        System.out.println("Auto-adding 3 coupons - Should run smoothly (press enter to continue): ");
        scan.nextLine();
        System.out.println();

        // Trying to add coupon1
        Calendar startCal1 = Calendar.getInstance();
        Calendar endCal1 = Calendar.getInstance();
        startCal1.set(2021, 01, 14);
        endCal1.set(2022, 02, 14);

        Coupon coupon1 = new Coupon();
        coupon1.setCompany(company);
        coupon1.setCategory(Coupon.Category.ELECTRICITY);
        coupon1.setTitle("Coupon1");
        coupon1.setDescription("Description1");
        coupon1.setImage("Image1");
        coupon1.setStartDate(startCal1.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime());
        coupon1.setEndDate(endCal1.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime());
        coupon1.setAmount(10);
        coupon1.setPrice(60.0);

        System.out.println("Trying to add Coupon1: ");
        System.out.println(coupon1);

        try {
            service.addNewCoupon(coupon1);
            System.out.println("Coupon1 was added successfully");
        } catch (CouponSystemException e) {
            e.printStackTrace();
        }

        MainTest.sleep();
        System.out.println();


        // Trying to add coupon2
        Calendar startCal2 = Calendar.getInstance();
        Calendar endCal2 = Calendar.getInstance();
        startCal2.set(2021, 00, 01);
        endCal2.set(2023, 02, 14);

        Coupon coupon2 = new Coupon();
        coupon2.setCompany(company);
        coupon2.setCategory(Coupon.Category.FOOD);
        coupon2.setTitle("Coupon2");
        coupon2.setDescription("Description2");
        coupon2.setImage("Image2");
        coupon2.setStartDate(startCal2.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime());
        coupon2.setEndDate(endCal2.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime());
        coupon2.setAmount(15);
        coupon2.setPrice(85.0);

        System.out.println("Trying to add Coupon2: ");
        System.out.println(coupon2);

        try {
            service.addNewCoupon(coupon2);
            System.out.println("Coupon2 was added successfully");
        } catch (CouponSystemException e) {
            e.printStackTrace();
        }

        MainTest.sleep();
        System.out.println();

        // Trying to add coupon3
        Calendar startCal3 = Calendar.getInstance();
        Calendar endCal3 = Calendar.getInstance();
        startCal3.set(2020, 01, 12);
        endCal3.set(2022, 11, 14);

        Coupon coupon3 = new Coupon();
        coupon3.setCompany(company);
        coupon3.setCategory(Coupon.Category.RESTAURANT);
        coupon3.setTitle("Coupon3");
        coupon3.setDescription("Description3");
        coupon3.setImage("Image3");
        coupon3.setStartDate(startCal3.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime());
        coupon3.setEndDate(endCal3.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime());
        coupon3.setAmount(10);
        coupon3.setPrice(30.0);

        System.out.println("Trying to add Coupon3: ");
        System.out.println(coupon3);

        try {
            service.addNewCoupon(coupon3);
            System.out.println("Coupon3 was added successfully");
        } catch (CouponSystemException e) {
            e.printStackTrace();
        }

        MainTest.sleep();
        System.out.println();
        System.out.println("Auto-adding a single coupon with an id duplicate - Should throw an exception (press enter to continue):");
        scan.nextLine();
        System.out.println();


        // Trying to add coupon3
        Calendar startCal4 = Calendar.getInstance();
        Calendar endCal4 = Calendar.getInstance();
        startCal4.set(2020, 01, 12);
        endCal4.set(2022, 11, 14);

        Coupon coupon4 = new Coupon();
        coupon4.setiD(coupon1.getId());
        coupon4.setCompany(company);
        coupon4.setCategory(Coupon.Category.VACATION);
        coupon4.setTitle("Coupon4");
        coupon4.setDescription("Description4");
        coupon4.setImage("Image4");
        coupon4.setStartDate(startCal4.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime());
        coupon4.setEndDate(endCal4.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime());
        coupon4.setAmount(10);
        coupon4.setPrice(40.0);

        System.out.println("Trying to add Coupon4: ");
        System.out.println(coupon4);

        try {
            service.addNewCoupon(coupon4);
            System.out.println("Coupon4 was added successfully");
        } catch (CouponSystemException e) {
            e.printStackTrace();
        }

        MainTest.space();
    }

    /**
     * Updates coupons from CompanyService.
     */
    public static void updateCoupons() {

        MainTest.sleep();
        System.out.println("Auto-updating a single coupon with some title and description changes - Should run smoothly (press enter to continue): ");
        scan.nextLine();
        System.out.println();


        // Trying to update coupon1
        Coupon coupon1 = service.getCoupons().get(0);

        coupon1.setTitle("UpdatedCoupon1");
        coupon1.setDescription("UpdatedDescription1");
        System.out.println("Trying to update Coupon1");
        System.out.println(coupon1);

        try {
            service.updateCoupon(coupon1, coupon1.getId());
            System.out.println("Coupon1 was updated successfully");
        } catch (CouponSystemException e) {
            e.printStackTrace();
        }

        MainTest.sleep();
        System.out.println();
        System.out.println("Auto-updating a single coupon with id changes - Should throw an exception (press enter to continue): ");
        scan.nextLine();
        System.out.println();

        // Trying to update coupon2
        Coupon coupon2 = service.getCoupons().get(1);

        coupon2.setiD(9853);
        System.out.println("Trying to update Coupon2");
        System.out.println(coupon2);

        try {
            service.updateCoupon(coupon2, coupon2.getId());
            System.out.println("Coupon2 was updated successfully");
        } catch (CouponSystemException e) {
            e.printStackTrace();
        }

        MainTest.space();
    }

    /**
     * Deletes coupons from CompanyService.
     */
    public static void deleteCoupons() {

        MainTest.sleep();
        System.out.println("Auto-deleting a single company - Should run smoothly (press enter to continue): ");
        scan.nextLine();
        System.out.println();

        // Trying to delete coupon2
        Coupon coupon = null;

        try {
            coupon = service.getCoupons().get(2);
            System.out.println("Trying to delete Coupon3");
            service.deleteCoupon(coupon.getId());
            System.out.println("Coupon3 was deleted successfully");
        } catch (CouponSystemException e) {
            e.printStackTrace();
        }

        MainTest.space();
    }

    /**
     * Gets all coupons by this company from CompanyService.
     */
    public static void getAllCoupons() {

        MainTest.sleep();
        System.out.println("Auto-getting all coupons by this company - Should run smoothly (press enter to continue): ");
        scan.nextLine();
        System.out.println();

        ArrayList<Coupon> coupons = (ArrayList<Coupon>) service.getCoupons();
        System.out.println("Coupons received successfully");
        for (Coupon coupon : coupons) {
            System.out.println(coupon);
        }

        MainTest.space();
    }

    /**
     * Gets all coupons by this company in the electricity category CompanyService.
     */
    public static void getAllCouponsCat() {

        MainTest.sleep();
        System.out.println("Auto-getting all coupons by this company in the electricity category - Should run smoothly (press enter to continue): ");
        scan.nextLine();
        System.out.println();

        ArrayList<Coupon> coupons = (ArrayList<Coupon>) service.getCouponsByCategory(Coupon.Category.ELECTRICITY);
        System.out.println("Coupons received successfully");
        for (Coupon coupon : coupons) {
            System.out.println(coupon);
        }

        MainTest.space();
    }

    /**
     * Gets all coupons by this company with a max price of 100 from CompanyService.
     */
    public static void getAllCouponsMax() {

        MainTest.sleep();
        System.out.println("Auto-getting all coupons by this company with a max price of 100 - Should run smoothly (press enter to continue): ");
        scan.nextLine();
        System.out.println();

        ArrayList<Coupon> coupons = (ArrayList<Coupon>) service.getCouponsByMaxPrice(100);
        System.out.println("Coupons received successfully");
        for (Coupon coupon : coupons) {
            System.out.println(coupon);
        }

        MainTest.space();
    }

    /**
     * Gets more details about this company.
     */
    public static void getCompanyDetails() {

        MainTest.sleep();
        System.out.println("Auto-getting details about this company - Should run smoothly (press enter to continue): ");
        scan.nextLine();
        System.out.println();

        try {
            String details = service.getCompanyDetails();
            System.out.println("Details received successfully");
            System.out.println(details);
        } catch (CouponSystemException e) {
            e.printStackTrace();
        }

        MainTest.space();
    }

    /**
     * Initializes a company for future use.
     */
    public static void initCompany(AdminService adminService, CompanyService companyService) {
        company = adminService.getAllCompanies().get(0);
        service = companyService;
    }
}
