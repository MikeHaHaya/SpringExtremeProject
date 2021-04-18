package app.core.services;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

import app.core.entities.Coupon;
import app.core.entities.Customer;
import app.core.exceptions.CouponSystemException;

@ComponentScan("app.core")
@Service
@Transactional
public class CustomerService extends ClientService {
	
	@Autowired
	private Customer customer;

	public CustomerService(Customer customer) {
		this.customer = customer;
	}

	public boolean login(String email, String password) {
		if (custRep.existsCustomerByEmailAndPassword(email, password))
			return true;

		return false;
	}

	public void purchaseCoupon(int couponId) throws CouponSystemException {

		Optional<Coupon> opt = couRep.findById(couponId);

		if (opt.isEmpty())
			throw new CouponSystemException("this coupon doesn't exist");
		Coupon coupon = opt.get();

		List<Integer> customerCoupons = couRep.FindAllByCustomers_id(customer.getId());

		if (coupon.getAmount() < 1)
			throw new CouponSystemException("no coupons left (coupons amount == 0)");

		if (coupon.getEndDate().isBefore(LocalDateTime.now()))
			throw new CouponSystemException("coupon date expired");

		if (customerCoupons.contains(couponId))
			throw new CouponSystemException("you already have this coupon");

		coupon.setAmount(coupon.getAmount() - 1);

		customer.addCoupon(coupon);
		custRep.flush();
	}

	/**
	 * @return
	 */
	public ArrayList<Coupon> getCoupons() {
		ArrayList<Integer> coupnonsID = new ArrayList<Integer>(couRep.FindAllByCustomers_id(customer.getId()));
		ArrayList<Coupon> coupons = new ArrayList<Coupon>();

		for (Integer integer : coupnonsID) {

			Coupon temp = couRep.getOne(integer);
			coupons.add(temp);
		}

		return coupons;
	}

}
