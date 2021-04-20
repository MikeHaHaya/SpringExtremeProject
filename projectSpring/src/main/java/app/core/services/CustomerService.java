package app.core.services;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;

import app.core.exceptions.ServiceException;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import app.core.entities.Coupon;
import app.core.entities.Customer;
import app.core.exceptions.CouponSystemException;

@Service
@Transactional
@Scope("prototype")
public class CustomerService extends ClientService {

    private int id;

    public CustomerService() {
    }

    public CustomerService(int id) {
        this.id = id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public boolean login(String email, String password) {
        if (custRep.existsCustomerByEmailAndPassword(email, password))
            return true;

        return false;
    }

    public Customer getCustomer() throws ServiceException {
        Optional<Customer> opt = custRep.findById(this.id);
        if (opt.isEmpty())
            throw new ServiceException("customer does not exist in the DB");
        return opt.get();
    }

    public void purchaseCoupon(int couponId) throws CouponSystemException {

        Optional<Coupon> optCoupon = couRep.findById(couponId);

        if (optCoupon.isEmpty())
            throw new CouponSystemException("this coupon doesn't exist");
        Coupon coupon = optCoupon.get();
        List<Integer> customerCoupons = couRep.findAllByCustomersId(id);


        if (coupon.getAmount() < 1) {
            throw new CouponSystemException("no coupons left (coupons amount == 0)");
        }

        if (coupon.getEndDate().isBefore(LocalDateTime.now())) {
            throw new CouponSystemException("coupon date expired");
        }

        if (customerCoupons.contains(couponId)) {
            throw new CouponSystemException("you already have this coupon");
        }

        Optional<Customer> opt = custRep.findById(id);
        if (opt.isEmpty()) {
            throw new CouponSystemException("customer doesn't exist in the DB");
        }

        Customer customer = opt.get();

        coupon.setAmount(coupon.getAmount() - 1);
        customer.addCoupon(coupon);

        custRep.flush();
    }

    /**
     * @return the coupons of the customer. will return empty list in case of no coupons.
     */
    public ArrayList<Coupon> getCoupons() {
        ArrayList<Coupon> coupons = getCouponsArrayList();

        return coupons;
    }

    /**
     * @return the coupons of the customer that match the given category. will return empty list in case of no coupons.
     */
    public ArrayList<Coupon> getCouponsByCategory(Coupon.Category category) {
        ArrayList<Coupon> coupons = getCouponsArrayList();

        for (Coupon coupon : coupons) {
            if (coupon.getCategory() != category)
                coupons.remove(coupon);
        }

        return coupons;
    }

    /**
     * @return the coupons of the customer that under maxPrice. will return empty list in case of no coupons.
     */
    public ArrayList<Coupon> getCouponsByMaxPrice(double maxPrice) {
        ArrayList<Coupon> coupons = getCouponsArrayList();

        for (Coupon coupon : coupons) {

            if (coupon.getPrice() < maxPrice) ;
            coupons.remove(coupon);
        }

        return coupons;
    }

    public ArrayList<Coupon> getCouponsArrayList() {
        ArrayList<Integer> couponsID = new ArrayList<Integer>(couRep.findAllByCustomersId(id));
        ArrayList<Coupon> coupons = new ArrayList<Coupon>();

        for (Integer integer : couponsID) {

            Optional<Coupon> opt = couRep.findById(id);
            Coupon coupon = opt.get();
            coupons.add(coupon);
        }
        return coupons;
    }
}
