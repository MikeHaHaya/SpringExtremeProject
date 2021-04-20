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
public class CompanyService extends ClientService {

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

    @Override
    public boolean login(String email, String password) {
        if (comRep.existsCompanyByEmailAndPassword(email, password))
            return true;

        return false;
    }

    public void addNewCoupon(Coupon coupon) throws CouponSystemException {

        CouponMustHaveValues(coupon);

        Optional<Company> opt = comRep.findById(id);
        if (opt.isEmpty())
            throw new CouponSystemException("company doesn't exist in the DB");
        Company company = opt.get();

        if (couRep.existsCouponByTitle(coupon.getTitle()) && id == coupon.getCompany().getId())
            throw new CouponSystemException("coupon title for that company already exist");

        coupon.setCompany(company);
        company.addCoupon(coupon);


    }

    //	TODO check if working
    public void updateCoupon(Coupon coupon, int id) throws CouponSystemException {

        CouponMustHaveValues(coupon);

        Optional<Coupon> opt = couRep.findById(id);
        if(opt.isEmpty()){
            throw new CouponSystemException("no such coupon exist in the DB");
        }
        Coupon temp = opt.get();

        //make sure to avoid nullPointer in case of tempering with the DB
        if (coupon.getCompany() == null || coupon.getCompany().getId() != this.id )
            throw new CouponSystemException("you can't update coupons of others");

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

    public void deleteCoupon(int couponId) throws CouponSystemException {

        Optional<Coupon> opt = couRep.findById(couponId);
        if(opt.isEmpty())
            throw new CouponSystemException("no such coupon exist");
        Coupon coupon = opt.get();
        // to avoid nullPointer in case of tempering with the DB
        if ((coupon.getCompany() == null || coupon.getCompany().getId() != this.id))
            throw new CouponSystemException("you can't delete coupon that not yours");

        couRep.deleteById(couponId);

    }

    public List<Coupon> getAllCoupons() throws CouponSystemException {

       List<Coupon> coupons = couRep.findAllByCompanyId(this.id);
        return coupons;
    }

    /*
     * throw exception in case of nulls where you can't put one
     */
    public static void CouponMustHaveValues(Coupon coupon) throws CouponSystemException {
        if (coupon.getCategory() == null || coupon.getTitle() == null
                || coupon.getStartDate() == null || coupon.getEndDate() == null)
            throw new CouponSystemException("You have empty fields that need to contain value.");

    }
}
