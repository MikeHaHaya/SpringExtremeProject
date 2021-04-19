package app.core.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import app.core.entities.Company;
import app.core.entities.Customer;
import app.core.exceptions.CouponSystemException;
import app.core.repositories.CompanyRepository;
import app.core.repositories.CouponRepository;
import app.core.repositories.CustomerRepository;

@Service
@Transactional
@Scope("singleton")
public class AdminService extends ClientService{

	/**
	 * @param email    = hard coded admin@admin.com
	 * @param password = hard coded admin
	 * @return true if the email and password correct, false if not
	 */

	@Override
	public boolean login(String email, String password) {
		if (email.equalsIgnoreCase("admin@admin.com") && password.equalsIgnoreCase("admin"))
			return true;

		return false;
	}
	
	/**
	 * @param com (new company)
	 * @throws CouponSystemException in case of company already exist
	 */
	public Company addNewCompany(Company com) throws CouponSystemException {
		if (!comRep.existsComapnyByName(com.getName()))
			return comRep.save(com);
		
			throw new CouponSystemException("a company with that name already exist");

	}
	/**
	 * @param id  - the id of the company you are updating
	 * @param com - the new things you want to update
	 * @throws CouponSystemException
	 */
	public void updateComapny(int id, Company com) throws CouponSystemException {
		Optional<Company> opt = comRep.findById(id);
		if (opt.isEmpty())
			throw new CouponSystemException("no comapny with this id");
		
		Company temp = opt.get();

		// throw an exception in case of changes that aren't allowed 
		if (!temp.getName().equalsIgnoreCase(com.getName()))
			throw new CouponSystemException("the field name can't be changed.");

		temp.setName(com.getName());
		temp.setEmail(com.getEmail());
		temp.setPassword(com.getPassword());
		
		comRep.save(temp);
	}
	
	/**
	 * Completely del company with all it's history. note: usage of this method is
	 * illegal in Israel.
	 * 
	 * @param company to del
	 * @throws CouponSystemException
	 */
	public void deleteCompany(int companyId) throws CouponSystemException {

		comRep.deleteById(companyId);
	}
	
	public List<Company> getAllCompanies(){

		return comRep.findAll();
	}

	public Company getOneCompany(int id) throws CouponSystemException {
		Optional<Company> opt = comRep.findById(id);
		if (opt.isEmpty())
			throw new CouponSystemException("no comapny with this id");
		
		Company company = opt.get();
		return company;
	}
	
	public Customer addNewCustomer(Customer customer){
		return custRep.save(customer);
	}
	
	/**
	 * update a customer in the given id
	 * 
	 * @param id   = the id that will be updated
	 * @param customer = the updated version
	 * @throws CouponSystemException
	 */
	public void updateCustomer(int id, Customer customer) throws CouponSystemException {

		// throw exception in case of nulls where you can't put one
		if (customer.getEmail() == null || customer.getPassword() == null)
			throw new CouponSystemException("the fields password and email must have value.");
		
		Optional<Customer> opt = custRep.findById(id);
		if (opt.isEmpty()) {
			throw new CouponSystemException("customer with this id does not exist");
		}

		Customer temp = opt.get();
		temp.setFirstName(customer.getFirstName());
		temp.setLastName(customer.getLastName());
		temp.setEmail(customer.getEmail());
		temp.setPassword(customer.getPassword());

		custRep.save(temp);
	}
	
	public void deleteCustomer(int id) throws CouponSystemException {
		custRep.deleteById(id);
	}
	
	public List<Customer> getAllCustomers() throws CouponSystemException {
		return custRep.findAll();
	}
	
	public Customer getOneCustomer(int id) throws CouponSystemException {
		Optional<Customer> opt = custRep.findById(id);
		if (opt.isEmpty())
			throw new CouponSystemException("no customer with this id");
		Customer customer = opt.get();
		return customer;
	}

}
