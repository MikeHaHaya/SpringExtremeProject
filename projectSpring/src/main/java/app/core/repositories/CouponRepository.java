package app.core.repositories;

import java.util.List;

import app.core.entities.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import app.core.entities.Coupon;
import org.springframework.stereotype.Repository;

@Repository("couponRepository")
public interface CouponRepository extends JpaRepository<Coupon, Integer> {

    boolean existsCouponById(int id);

    boolean existsCouponByTitle(String name);

    void deleteByCompany(Company company);

    @Query(value = "SELECT `coupon_id` FROM `customers_coupons` WHERE `customer_id` = :customerId", nativeQuery = true)
    List<Integer> findAllByCustomers(int customerId);

    // Native query due to problems with IntelliJ and Eclipse connection to mySQL
    @Query(value = "SELECT * FROM `coupons` WHERE `company_id` = :id", nativeQuery = true)
    List<Coupon> findAllByCompanyId(int id);

    @Query(value = "SELECT * FROM `coupons` WHERE `company_id` = :id AND `category` = :category", nativeQuery = true)
    List<Coupon> findAllByCompanyAndCategoryId(int id, Coupon.Category category);

    @Query(value = "SELECT * FROM `coupons` WHERE `company_id` = :id AND `price` <= :maxPrice", nativeQuery = true)
    List<Coupon> findAllByCompanyIdAndMaxPrice(int id, double maxPrice);
}
