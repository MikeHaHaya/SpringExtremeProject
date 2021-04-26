package app.core.services;

import java.lang.reflect.InvocationTargetException;
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

@Service("customerService")
@Transactional
@Scope("prototype")
public class CustomerService extends ClientService {

    private int id;

    /**
     * An empty constructor.
     */
    public CustomerService() {
    }

    /**
     * Init Constructor
     */
    public CustomerService(int id) {
        this.id = id;
    }

    /**
     * Sets the company's id.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Logs in a customer user.
     */
    @Override
    public boolean login(String email, String password) {

        if (custRep.existsCustomerByEmailAndPassword(email, password)) {
            this.id = custRep.findCustomerByEmailAndPassword(email, password).getId();
            return true;
        } else
            return false;
    }

    /**
     * Adds a new purchase to the database
     */
    public void purchaseCoupon(int couponId) throws ServiceException {

        Optional<Coupon> optCoupon = couRep.findById(couponId);

        if (optCoupon.isEmpty())
            throw new ServiceException("A coupon with this id does not exist. ");
        Coupon coupon = optCoupon.get();
        List<Integer> couponsId = couRep.findAllByCustomers(id);

        if (coupon.getAmount() < 1) {
            throw new ServiceException("The coupon you are trying to buy has run out of stock. ");
        }

        if (coupon.getEndDate().isBefore(LocalDateTime.now())) {
            throw new ServiceException("The coupon you are trying to buy has expired. ");
        }

        if (couponsId.contains(couponId)) {
            throw new ServiceException("You already owns this coupon. ");
        }

        Customer customer = getCustomer();

        coupon.setAmount(coupon.getAmount() - 1);
        customer.addCoupon(coupon);

        custRep.flush();
    }

    /**
     * @return the coupons of the customer. will return empty list in case of no coupons.
     */
    public ArrayList<Coupon> getCoupons() {

        ArrayList<Integer> couponsID = new ArrayList<Integer>(couRep.findAllByCustomers(id));
        ArrayList<Coupon> coupons = new ArrayList<Coupon>();

        for (Integer couponId : couponsID) {

            Optional<Coupon> opt = couRep.findById(couponId);
            Coupon coupon = opt.get();
            coupons.add(coupon);
        }
        return coupons;
    }

    /**
     * @return the coupons of the customer that match the given category. will return empty list in case of no coupons.
     */
    public ArrayList<Coupon> getCouponsByCategory(Coupon.Category category) {

        ArrayList<Coupon> coupons = getCoupons();

        for (int i = 0; i < coupons.size(); i++) {
            if (coupons.get(i).getCategory() != category) {
                coupons.remove(i);
            }
        }

        return coupons;
    }

    /**
     * @return the coupons of the customer that under maxPrice. will return empty list in case of no coupons.
     */
    public ArrayList<Coupon> getCouponsByMaxPrice(double maxPrice) {

        ArrayList<Coupon> coupons = getCoupons();

        for (Coupon coupon : coupons) {

            if (coupon.getPrice() <= maxPrice) ;
            coupons.remove(coupon);
        }

        return coupons;
    }

    /**
     * @return all details about this customer from the database.
     */
    public String getCustomerDetails() throws ServiceException {

        Customer customer = getCustomer();

        String details = "ID: " + id + "\n"
                + "Full Name: " + customer.getFirstName() + " " + customer.getLastName() + "\n"
                + "Email: " + customer.getEmail() + "\n"
                + "Coupons purchased: ";

        ArrayList<Coupon> coupons = getCoupons();
        details += coupons.size();

        return details;
    }

    /**
     * @return the customer as an object using it's id.
     */
    private Customer getCustomer() throws ServiceException {

        Optional<Customer> opt = custRep.findById(this.id);
        if (opt.isEmpty())
            throw new ServiceException("A customer with this id does not exist. ");
        return opt.get();
    }

}
