package app.core.repositories;

import java.util.List;

import app.core.entities.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import app.core.entities.Coupon;


public interface CouponRepository extends JpaRepository<Coupon, Integer> {

    boolean existsCouponById(int id);

    boolean existsCouponByTitle(String name);

    void deleteByCompany(Company company);

    //native query due to problems with the InteliJ and Eclipse to mySQL
    @Query(value = "select * from coupons where company_id = :id", nativeQuery = true)
    List<Coupon> findAllByCompanyId(int id);

    @Query(value = "select * from customers_coupons where customer_id = :id", nativeQuery = true)
    List<Integer> findAllByCustomersId(int id);

    @Query(value = "SELECT * FROM `coupons` WHERE `company_id` = :id AND `category` = :category", nativeQuery = true)
    List<Coupon> findAllByCompanyAndCategoryId(int id, Coupon.Category category);

    @Query(value = "SELECT * FROM `coupons` WHERE `company_id` = :id AND `price` <= :maxPrice", nativeQuery = true)
    List<Coupon> findAllByCompanyIdAndMaxPrice(int id, double maxPrice);
}
