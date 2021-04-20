package app.core.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import app.core.entities.Coupon;


public interface CouponRepository extends JpaRepository<Coupon, Integer> {

    boolean existsCouponById(int id);

    boolean existsCouponByTitle(String name);

    @Query(value = "select * from coupons where company_id = :id", nativeQuery = true)
    List<Coupon> findAllByCompanyId(int id);

    @Query(value = "select * from customers_coupons where customer_id = :id", nativeQuery = true)
    List<Integer> FindAllByCustomersId(int id);
}
