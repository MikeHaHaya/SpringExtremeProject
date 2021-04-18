package app.core.services;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

import app.core.entities.Coupon;
import app.core.entities.Customer;
import app.core.exceptions.CouponSystemException;

@ComponentScan("app.core")
@Service
@Transactional
public class CustomerService extends ClientService {

	private int id;
	
	public CustomerService() {
	}

	public CustomerService(int id) {
		this.id = id;
	}

	public boolean login(String email, String password) {
		if (custRep.existsCustomerByEmailAndPassword(email, password))
			return true;

		return false;
	}

	public void purchaseCoupon(int couponId) throws CouponSystemException {

		Optional<Coupon> optCoupon = couRep.findById(couponId);

		if (optCoupon.isEmpty())
			throw new CouponSystemException("this coupon doesn't exist");
		Coupon coupon = optCoupon.get();

		List<Integer> customerCoupons = couRep.FindAllByCustomers_id(id);

		if (coupon.getAmount() < 1)
			throw new CouponSystemException("no coupons left (coupons amount == 0)");

		if (coupon.getEndDate().isBefore(LocalDateTime.now()))
			throw new CouponSystemException("coupon date expired");

		if (customerCoupons.contains(couponId))
			throw new CouponSystemException("you already have this coupon");

		coupon.setAmount(coupon.getAmount() - 1);

		Optional<Customer> opt = custRep.findById(id);
		if(opt.isEmpty())
			throw new CouponSystemException("customer doesn't exist in the DB");
		Customer customer = opt.get();

		customer.addCoupon(coupon);
		
		custRep.flush();
	}

	/**
	 * @return
	 */
	public ArrayList<Coupon> getCoupons() {
		ArrayList<Integer> coupnonsID = new ArrayList<Integer>(couRep.FindAllByCustomers_id(id));
		ArrayList<Coupon> coupons = new ArrayList<Coupon>();

		for (Integer integer : coupnonsID) {

			Coupon temp = couRep.getOne(integer);
			coupons.add(temp);
		}

		return coupons;
	}

}
