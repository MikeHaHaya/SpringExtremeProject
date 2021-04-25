package app.core.services;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import app.core.exceptions.ServiceException;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import app.core.entities.Company;
import app.core.entities.Customer;
import app.core.exceptions.CouponSystemException;

@Service("adminService")
@Transactional
@Scope("singleton")
public class AdminService extends ClientService {

    /**
     * @param email    = hard coded admin@admin.com
     * @param password = hard coded admin
     * @return true if the email and password correct, false if not
     */


    /**
     * Logs in for an admin user.
     */
    @Override
    public boolean login(String email, String password) {
        return email.equalsIgnoreCase("admin@admin.com") && password.equalsIgnoreCase("admin");
    }

    /**
     * @param company (new company)
     * @throws CouponSystemException in case of company already exist
     */
    public Company addNewCompany(Company company) throws ServiceException {

        if (isCompanyNullCheck(company))
            throw new ServiceException("Some details are missing, please try again. ");
        if (comRep.existsCompanyByName(company.getName()))
            throw new ServiceException("A company with that name already exists. ");
        if (comRep.existsCompanyByEmail(company.getEmail()))
            throw new ServiceException("A company with that email already exists. ");
        if (company.getId() != 0)
            throw new ServiceException("Company id must be left empty. ");

        return comRep.save(company);
    }

    /**
     * @param id  - the id of the company you are updating
     * @param company - the new things you want to update
     * @throws CouponSystemException
     */
    public void updateCompany(int id, Company company) throws ServiceException {

		if (isCompanyNullCheck(company))
			throw new ServiceException("Some details are missing, please try again. ");

        Optional<Company> opt = comRep.findById(id);
        if (opt.isEmpty())
            throw new ServiceException("A company with this id does not exist. ");

        Company temp = opt.get();

        // throw an exception in case of changes that aren't allowed
        if (!temp.getName().equalsIgnoreCase(company.getName()))
            throw new ServiceException("The company's name cannot be changed. ");

        temp.setEmail(company.getEmail());
        temp.setPassword(company.getPassword());

        comRep.save(temp);
    }

    /**
     * Completely del company with all it's history. note: usage of this method is
     * illegal in Israel.
     *
     * @param companyId to del
     * @throws CouponSystemException
     */
    public void deleteCompany(int companyId) throws ServiceException {

        Optional<Company> opt = comRep.findById(companyId);
        if (opt.isEmpty())
            throw new ServiceException("A company with that id does not exists. ");
        Company company = opt.get();
        couRep.deleteByCompany(company);
        comRep.deleteById(companyId);
    }

    /**
     * Gets all companies from the database.
     */
    public List<Company> getAllCompanies() {

        return comRep.findAll();
    }

    /**
     * Gets a single company out of the database.
     */
    public Company getOneCompany(int id) throws ServiceException {

        Optional<Company> opt = comRep.findById(id);
        if (opt.isEmpty())
            throw new ServiceException("no comapny with this id");

        Company company = opt.get();
        return company;
    }

    /**
     * Adds a single customer to the database.
     */
    public Customer addNewCustomer(Customer customer) throws ServiceException {

        if (isCustomerNullCheck(customer))
            throw new ServiceException("Some details are missing, please try again. ");
        if (custRep.existsCustomerByEmail(customer.getEmail()))
            throw new ServiceException("A customer with that email already exists. ");
        if (customer.getId() != 0)
            throw new ServiceException("Customer id must be left empty");

        return custRep.save(customer);
    }

    /**
     * update a customer in the given id
     *
     * @param id       = the id that will be updated
     * @param customer = the updated version
     * @throws CouponSystemException
     */
    public void updateCustomer(int id, Customer customer) throws ServiceException {

        // throw exception in case of nulls where you can't put one
        if (isCustomerNullCheck(customer))
            throw new ServiceException("Some details are missing, please try again. ");

        Optional<Customer> opt = custRep.findById(id);
        if (opt.isEmpty()) {
            throw new ServiceException("A customer with this id does not exist. ");
        }

        Customer temp = opt.get();
        temp.setFirstName(customer.getFirstName());
        temp.setLastName(customer.getLastName());
        temp.setEmail(customer.getEmail());
        temp.setPassword(customer.getPassword());

        custRep.save(temp);
    }

    /**
     * Deletes a customer from the database.
     */
    public void deleteCustomer(int id) throws ServiceException {
        custRep.deleteById(id);
    }

    /**
     * Gets all customers from the database.
     */
    public List<Customer> getAllCustomers() throws ServiceException {
        return custRep.findAll();
    }

    /**
     * Gets a single customer from the database.
     */
    public Customer getOneCustomer(int id) throws ServiceException {
        Optional<Customer> opt = custRep.findById(id);
        if (opt.isEmpty())
            throw new ServiceException("A customer with this id does not exist. ");
        Customer customer = opt.get();
        return customer;
    }

    /**
     * Checks if a company is null or contains null objects (name, email, password).
     * Returns true if null or contains null, returns false if no null was found. */
    public static boolean isCompanyNullCheck(Company company) {

        if (company == null)
            return true;
        if (company.getName() == null || company.getEmail() == null || company.getPassword() == null)
            return true;

        return false;
    }

    /**
     * Checks if a customer is null or contains null objects (firstName, lastName, email, password).
     * Returns true if null or contains null, returns false if no null was found. */
    public static boolean isCustomerNullCheck(Customer customer) {

        if (customer == null)
            return true;
        if (customer.getFirstName() == null || customer.getLastName() == null || customer.getEmail() == null || customer.getPassword() == null)
            return true;

        return false;
    }
}
