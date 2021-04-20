package app.core.repositories;



import java.util.List;

import app.core.entities.Coupon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import app.core.entities.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {
	
	boolean existsCustomerByEmailAndPassword(String email, String password);

	boolean existsCustomerByEmail(String email);

	
}
