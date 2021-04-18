package app.core.services;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import app.core.entities.Company;
import app.core.entities.Coupon;
import app.core.exceptions.CouponSystemException;

@Service
@Transactional
@Scope("prototype")
public class CompanyService extends ClientService{
	
	private int id;

	//CTOR
	public CompanyService(int id) {
		this.id = id;
	}

	public CompanyService() {
	}
	
	//Setter
	public void setId(int id) {
		this.id = id;
	}

	public boolean login(String email, String password) {
		if (comRep.existsComapnyByEmailAndPassword(email, password))
			return true;

		return false;
	}

	public void addNewCoupon(Coupon coupon) throws CouponSystemException {

		
		Optional<Company> opt = comRep.findById(id);
		if(opt.isEmpty())
			throw new CouponSystemException("company doesn't exist in the DB");
		Company company = opt.get();

		if (couRep.existsCouponByTitle(coupon.getTitle()) && id == coupon.getCompany().getId())
			throw new CouponSystemException("coupon title for that company already exist");

		company.addCoupon(coupon);
		
		
	}

//	TODO check if working
	public void updateCoupon(Coupon coupon) throws CouponSystemException {

		// throw exception in case of nulls where you can't put one
		if (coupon.getCategory() == null || coupon.getTitle() == null
				|| coupon.getStartDate() == null || coupon.getEndDate() == null)
			throw new CouponSystemException("You have empty fields that need to contain value.");
		
		if (coupon.getId() == id)
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
	
	public List<Coupon> getAllCoupons() throws CouponSystemException{

		Optional<Company> opt = comRep.findById(id);
		if(opt.isEmpty())
			throw new CouponSystemException("company doesn't exist in the DB");
		Company company = opt.get();
		return company.getCoupons();
	}

	
}
