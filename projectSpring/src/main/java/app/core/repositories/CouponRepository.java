package app.core.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import app.core.entities.Coupon;

public interface CouponRepository extends JpaRepository<Coupon, Integer>{

 	boolean existsCouponById(int id);
 	boolean existsCouponByTitle(String name);
 	long deleteById(int idCompany);

	@Query(value = "select * from customers_coupons where customer_id = :id", nativeQuery = true)
	List<Integer> FindAllByCustomers_id(int id);
}
