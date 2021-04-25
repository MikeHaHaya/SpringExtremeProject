package app.core.tests;

import app.core.entities.Company;
import app.core.entities.Customer;
import app.core.exceptions.CouponSystemException;
import app.core.services.AdminService;

import java.util.ArrayList;
import java.util.Scanner;

public class AdminTest {

    private static final Scanner scan = new Scanner(System.in);

    /**
     * Adds companies from AdminService.
     */
    public static void addCompanies(AdminService service) {

        MainTest.sleep();
        System.out.println("Auto-adding 3 companies - Should run smoothly (press enter to continue): ");
        scan.nextLine();
        System.out.println();

        // Trying to add company1
        Company company1 = new Company();
        company1.setName("Company1");
        company1.setEmail("company1@auto-company.com");
        company1.setPassword("1234!");
        System.out.println("Trying to add Company1: ");
        System.out.println(company1);

        try {
            service.addNewCompany(company1);
            System.out.println("Company1 was added successfully");
        } catch (CouponSystemException e) {
            e.printStackTrace();
        }

        MainTest.sleep();
        System.out.println();

        // Trying to add company2
        Company company2 = new Company();
        company2.setName("Company2");
        company2.setEmail("company2@auto-company.com");
        company2.setPassword("1234!");
        System.out.println("Trying to add Company2: ");
        System.out.println(company2);

        try {
            service.addNewCompany(company2);
            System.out.println("Company2 was added successfully");
        } catch (CouponSystemException e) {
            e.printStackTrace();
        }

        MainTest.sleep();
        System.out.println();

        // Trying to add company3
        Company company3 = new Company();
        company3.setName("Company3");
        company3.setEmail("company3@auto-company.com");
        company3.setPassword("1234!");
        System.out.println("Trying to add Company3: ");
        System.out.println(company3);

        try {
            service.addNewCompany(company3);
            System.out.println("Company3 was added successfully");
        } catch (CouponSystemException e) {
            e.printStackTrace();
        }

        MainTest.sleep();
        System.out.println();
        System.out.println("Auto-adding 2 companies with some name and email duplicates - Should throw an exception (press enter to continue):");
        scan.nextLine();
        System.out.println();

        // Trying to add company4
        Company company4 = new Company();
        company4.setName("Company3");
        company4.setEmail("company4@auto-company.com");
        company4.setPassword("1234!");
        System.out.println("Trying to add Company4: ");
        System.out.println(company4);

        try {
            service.addNewCompany(company4);
            System.out.println("Company4 was added successfully");
        } catch (CouponSystemException e) {
            e.printStackTrace();
        }

        MainTest.sleep();
        System.out.println();

        // Trying to add company5
        Company company5 = new Company();
        company5.setName("Company5");
        company5.setEmail("company2@auto-company.com");
        company5.setPassword("1234!");
        System.out.println("Trying to add Company5: ");
        System.out.println(company5);

        try {
            service.addNewCompany(company5);
            System.out.println("Company5 was added successfully");
        } catch (CouponSystemException e) {
            e.printStackTrace();
        }

        MainTest.space();
    }

    /**
     * Updates companies from AdminService.
     */
    public static void updateCompanies(AdminService service) {

        MainTest.sleep();
        System.out.println("Auto-updating 2 companies with some email and password changes - Should run smoothly (press enter to continue): ");
        scan.nextLine();
        System.out.println();

        // Trying to update company1
        Company company1 = null;
        try {
            Company company = service.getAllCompanies().get(0);
            company1 = service.getOneCompany(company.getId());
        } catch (CouponSystemException e) {
            e.printStackTrace();
        }

        company1.setEmail("company1@updated-company.com");
        company1.setPassword("0000");
        System.out.println("Trying to update Company1: ");
        System.out.println(company1);

        try {
            service.updateCompany(company1.getId(), company1);
            System.out.println("Company1 was updated successfully");
        } catch (CouponSystemException e) {
            e.printStackTrace();
        }

        MainTest.sleep();
        System.out.println();

        // Trying to update company2
        Company company2 = null;
        try {
            Company company = service.getAllCompanies().get(1);
            company2 = service.getOneCompany(company.getId());
        } catch (CouponSystemException e) {
            e.printStackTrace();
        }

        company2.setEmail("company2@updated-company.com");
        company2.setPassword("9876");
        System.out.println("Trying to update Company2: ");
        System.out.println(company2);

        try {
            service.updateCompany(company2.getId(), company2);
            System.out.println("Company2 was updated successfully");
        } catch (CouponSystemException e) {
            e.printStackTrace();
        }

        MainTest.sleep();
        System.out.println();
        System.out.println("Auto-updating 2 companies with some id and name changes - Should throw an exception (press enter to continue): ");
        scan.nextLine();
        System.out.println();

        // Trying to update company3
        Company company3 = null;
        try {
            Company company = service.getAllCompanies().get(2);
            company3 = service.getOneCompany(company.getId());
        } catch (CouponSystemException e) {
            e.printStackTrace();
        }

        company3.setIdCompany(4464156);
        System.out.println("Trying to update Company3: ");
        System.out.println(company3);

        try {
            service.updateCompany(company3.getId(), company3);
            System.out.println("Company3 was updated successfully");
        } catch (CouponSystemException e) {
            e.printStackTrace();
        }

        MainTest.sleep();
        System.out.println();

        // Trying to update company1 again
        try {
            Company company = service.getAllCompanies().get(0);
            company1 = service.getOneCompany(company.getId());
        } catch (CouponSystemException e) {
            e.printStackTrace();
        }

        company1.setName("Comp1");
        System.out.println("Trying to update Company1 again: ");
        System.out.println(company1);

        try {
            service.updateCompany(company1.getId(), company1);
            System.out.println("Company1 was updated successfully");
        } catch (CouponSystemException e) {
            e.printStackTrace();
        }

        MainTest.space();
    }

    /**
     * Deletes companies from AdminFacade.
     */
    public static void deleteCompanies(AdminService service) {

        MainTest.sleep();
        System.out.println("Auto-deleting a single company - Should run smoothly (press enter to continue): ");
        scan.nextLine();
        System.out.println();

        // Trying to delete company3
        Company company3 = null;
        try {
            Company company = service.getAllCompanies().get(2);
            company3 = service.getOneCompany(company.getId());
        } catch (CouponSystemException e) {
            e.printStackTrace();
        }

        System.out.println("Trying to delete Company3: ");
        System.out.println(company3);

        try {
            service.deleteCompany(company3.getId());
            System.out.println("Company 3 was deleted successfully");
        } catch (CouponSystemException e) {
            e.printStackTrace();
        }

        MainTest.space();

    }

    /**
     * Gets all companies from AdminFacade.
     */
    public static void getAllCompanies(AdminService service) {

        MainTest.sleep();
        System.out.println("Auto-getting all companies - Should run smoothly (press enter to continue): ");
        scan.nextLine();
        System.out.println();

        // Trying to get all companies
        ArrayList<Company> companies = (ArrayList<Company>) service.getAllCompanies();
        System.out.println("Companies received successfully");
        for (Company company : companies) {
            System.out.println(company);
        }

        MainTest.space();
    }

    /**
     * Gets a single company from AdminFacade. */
    public static void getOneCompany(AdminService service) {

        MainTest.sleep();
        System.out.println("Auto-getting a single company - Should run smoothly (press enter to continue): ");
        scan.nextLine();
        System.out.println();

        try {
            Company companyI = service.getAllCompanies().get(0);
            Company company = service.getOneCompany(companyI.getId());
            System.out.println("Company received successfully");
            System.out.println(company);
        } catch (CouponSystemException e) {
            e.printStackTrace();
        }

        MainTest.space();
    }

    /**
     * Adds customers from AdminFacade. */
    public static void addCustomers(AdminService service) {

        MainTest.sleep();
        System.out.println("Auto-adding 3 customers - Should run smoothly (press enter to continue): ");
        scan.nextLine();
        System.out.println();


        // Trying to add customer1
        Customer customer1 = new Customer();
        customer1.setFirstName("First1");
        customer1.setLastName("Last1");
        customer1.setEmail("customer1@auto-customer.com");
        customer1.setPassword("1234!");
        System.out.println("Trying to add Customer1: ");
        System.out.println(customer1);

        try {
            service.addNewCustomer(customer1);
            System.out.println("Customer1 was added successfully");
        } catch (CouponSystemException e) {
            e.printStackTrace();
        }

        MainTest.sleep();
        System.out.println();


        // Trying to add customer2
        Customer customer2 = new Customer();
        customer2.setFirstName("First2");
        customer2.setLastName("Last2");
        customer2.setEmail("customer2@auto-customer.com");
        customer2.setPassword("1234!");
        System.out.println("Trying to add Customer2: ");
        System.out.println(customer2);

        try {
            service.addNewCustomer(customer2);
            System.out.println("Customer2 was added successfully");
        } catch (CouponSystemException e) {
            e.printStackTrace();
        }

        MainTest.sleep();
        System.out.println();


        // Trying to add customer3
        Customer customer3 = new Customer();
        customer3.setFirstName("First3");
        customer3.setLastName("Last3");
        customer3.setEmail("customer3@auto-customer.com");
        customer3.setPassword("1234!");
        System.out.println("Trying to add Customer3: ");
        System.out.println(customer3);

        try {
            service.addNewCustomer(customer3);
            System.out.println("Customer3 was added successfully");
        } catch (CouponSystemException e) {
            e.printStackTrace();
        }

        MainTest.sleep();
        System.out.println();
        System.out.println("Auto-adding a customer with email duplicate - Should throw an exception (press enter to continue):");
        scan.nextLine();
        System.out.println();

        // Trying to add customer1
        Customer customer4 = new Customer();
        customer4.setFirstName("First4");
        customer4.setLastName("Last4");
        customer4.setEmail("customer3@auto-customer.com");
        customer4.setPassword("1234!");
        System.out.println("Trying to add Customer4: ");
        System.out.println(customer4);

        try {
            service.addNewCustomer(customer4);
            System.out.println("Customer4 was added successfully");
        } catch (CouponSystemException e) {
            e.printStackTrace();
        }

        MainTest.space();

    }

    /**
     * Updates customers from AdminFacade. */
    public static void updateCustomers(AdminService service) {

        MainTest.sleep();
        System.out.println("Auto-updating 2 customers with some email and password changes - Should run smoothly (press enter to continue): ");
        scan.nextLine();
        System.out.println();

        // Trying to update customer1
        Customer customer1 = null;
        try {
            Customer customer = service.getAllCustomers().get(0);
            customer1 = service.getOneCustomer(customer.getId());
            System.out.println();
        } catch (CouponSystemException e) {
            e.printStackTrace();
        }


        customer1.setEmail("customer1@updated-customer.com");
        customer1.setPassword("0000");
        System.out.println("Trying to update Customer1: ");
        System.out.println(customer1);

        try {
            service.updateCustomer(customer1.getId(), customer1);
            System.out.println("Customer1 was updated successfully");
        } catch (CouponSystemException e) {
            e.printStackTrace();
        }

        MainTest.sleep();
        System.out.println();


        // Trying to update customer2
        Customer customer2 = null;
        try {
            Customer customer = service.getAllCustomers().get(1);
            customer2 = service.getOneCustomer(customer.getId());
            System.out.println();
        } catch (CouponSystemException e) {
            e.printStackTrace();
        }


        customer2.setEmail("customer2@updated-customer.com");
        customer2.setPassword("9876");
        System.out.println("Trying to update Customer2: ");
        System.out.println(customer2);

        try {
            service.updateCustomer(customer2.getId(), customer2);
            System.out.println("Customer2 was updated successfully");
        } catch (CouponSystemException e) {
            e.printStackTrace();
        }

        MainTest.sleep();
        System.out.println();
        System.out.println("Auto-updating a single customer with id changes - Should throw an exception (press enter to continue): ");
        scan.nextLine();
        System.out.println();


        // Trying to update customer3
        Customer customer3 = null;
        try {
            Customer customer = service.getAllCustomers().get(2);
            customer3 = service.getOneCustomer(customer.getId());
            System.out.println();
        } catch (CouponSystemException e) {
            e.printStackTrace();
        }


        customer3.setiDCustomer(4564);
        System.out.println("Trying to update Customer3: ");
        System.out.println(customer3);

        try {
            service.updateCustomer(customer3.getId(), customer3);
            System.out.println("Customer3 was updated successfully");
        } catch (CouponSystemException e) {
            e.printStackTrace();
        }

        MainTest.space();
    }

    /**
     * Deletes customers from AdminFacade. */
    public static void deleteCustomers(AdminService service) {

        MainTest.sleep();
        System.out.println("Auto-deleting a single customer - Should run smoothly (press enter to continue): ");
        scan.nextLine();
        System.out.println();

        Customer customer3 = null;
        try {
            Customer customer = service.getAllCustomers().get(2);
            customer3 = service.getOneCustomer(customer.getId());
        } catch (CouponSystemException e) {
            e.printStackTrace();
        }

        System.out.println("Trying to delete Customer3: ");
        System.out.println(customer3);

        try {
            service.deleteCustomer(customer3.getId());
            System.out.println("Customer3 was deleted successfully");
        } catch (CouponSystemException e) {
            e.printStackTrace();
        }

        MainTest.space();
    }

    /**
     * Gets all companies from AdminFacade. */
    public static void getAllCustomers(AdminService service) {

        MainTest.sleep();
        System.out.println("Auto-getting all customers - Should run smoothly (press enter to continue): ");
        scan.nextLine();
        System.out.println();

        // Trying to get all companies
        try {
            ArrayList<Customer> customers = (ArrayList<Customer>) service.getAllCustomers();
            System.out.println("Customers received successfully");
            for (Customer customer : customers) {
                System.out.println(customer);
            }
        } catch (CouponSystemException e) {
            e.printStackTrace();
        }

        MainTest.space();
    }

    /**
     * Gets a single customer from AdminFacade. */
    public static void getOneCustomer(AdminService service) {

        MainTest.sleep();
        System.out.println("Auto-getting a single customer - Should run smoothly (press enter to continue): ");
        scan.nextLine();
        System.out.println();

        try {
            Customer customerI = service.getAllCustomers().get(0);
            Customer customer = service.getOneCustomer(customerI.getId());
            System.out.println("Customer received successfully");
            System.out.println(customer);
        } catch (CouponSystemException e) {
            e.printStackTrace();
        }

        MainTest.space();
    }
}
