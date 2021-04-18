package app.core.services;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import app.core.entities.Company;
import app.core.entities.Coupon;
import app.core.exceptions.CouponSystemException;

@Service
@Transactional
public class ComanyService extends ClientService {

	private Company company;

	public ComanyService(Company company) {
		this.company = company;
	}

	public ComanyService() {
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	public boolean login(String email, String password) {
		if (comRep.existsComapnyByEmailAndPassword(email, password))
			return true;

		return false;
	}

	public void addNewCoupon(Coupon coupon) throws CouponSystemException {

		if (couRep.existsCouponByTitle(coupon.getTitle()) && company.getId() == coupon.getCompany().getId())
			throw new CouponSystemException("coupon title for that company already exist");
			
		company.addCoupon(coupon);
		comRep.flush();
	}

//	TODO check if working
	public void updateCoupon(Coupon coupon) throws CouponSystemException {

		// throw exception in case of nulls where you can't put one
		if (coupon.getCategory() == null || coupon.getTitle() == null
				|| coupon.getStartDate() == null || coupon.getEndDate() == null)
			throw new CouponSystemException("You have empty fields that need to contain value.");
		
		if (coupon.getId() == company.getId())
			throw new CouponSystemException("you can't update coupons of others");

		Coupon temp = new Coupon();
		temp.setTitle(coupon.getTitle());
		temp.setDescription(coupon.getDescription());
		temp.setCategory(coupon.getCategory());
		temp.setStartDate(coupon.getStartDate());
		temp.setEndDate(coupon.getEndDate());
		temp.setAmount(coupon.getAmount());
		temp.setPrice(coupon.getPrice());
		temp.setImage(coupon.getImage());

		couRep.save(temp);
	}
	public void deleteCoupon(int couponId) {
		couRep.deleteById(couponId);
	}
}
