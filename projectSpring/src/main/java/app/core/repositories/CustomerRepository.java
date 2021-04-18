package app.core.repositories;



import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import app.core.entities.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {
	
	boolean existsCustomerByEmailAndPassword(String email, String password);
	
	//TODO
//	@Query(value = "from customers_coupons where customers_id = :id")
//	List<Integer> FindByCoupons(int id);
//	List<Job> GetJobsByEmployee(long id);



}
