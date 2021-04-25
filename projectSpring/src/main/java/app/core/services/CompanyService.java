package app.core.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import app.core.exceptions.ServiceException;
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


    /**
     * Init Constructor
     */
    public CompanyService(int id) {
        this.id = id;
    }

    /**
     * An empty constructor
     */
    public CompanyService() {
    }

    /**
     * Sets the company's id.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Logs in a company user.
     */
    @Override
    public boolean login(String email, String password) {
        return comRep.existsCompanyByEmailAndPassword(email, password);
    }

    /**
     * Adds a new company to the database.
     */
    public void addNewCoupon(Coupon coupon) throws ServiceException {

        if (isCouponNullCheck(coupon))
            throw new ServiceException("Some details are missing, please try again. ");

        Company company = getCompany();

        if (couRep.existsCouponByTitle(coupon.getTitle()) && id == coupon.getCompany().getId())
            throw new ServiceException("A different coupon created by this company already has this title. ");

        coupon.setCompany(company);
        company.addCoupon(coupon);

    }

    // TODO check if working
    /**
     * Updates the coupon from the database.
     */
    public void updateCoupon(Coupon coupon, int id) throws ServiceException {

        if (isCouponNullCheck(coupon))
            throw new ServiceException("Some details are missing, please try again. ");

        Optional<Coupon> opt = couRep.findById(id);
        if (opt.isEmpty()) {
            throw new ServiceException("A coupon with this id does not exist. ");
        }
        Coupon temp = opt.get();

        //make sure to avoid nullPointer in case of tempering with the DB
        if (coupon.getCompany() == null || coupon.getCompany().getId() != this.id)
            throw new ServiceException("The coupon's registered company must be yours. ");

        temp.setTitle(coupon.getTitle());
        temp.setDescription(coupon.getDescription());
        temp.setCategory(coupon.getCategory());
        temp.setStartDate(coupon.getStartDate());
        temp.setEndDate(coupon.getEndDate());
        temp.setAmount(coupon.getAmount());
        temp.setPrice(coupon.getPrice());
        temp.setImage(coupon.getImage());
        temp.setCategory(coupon.getCategory());

        couRep.save(temp);
    }

    /**
     * Deletes a coupon from the database.
     */
    public void deleteCoupon(int couponId) throws ServiceException {

        Optional<Coupon> opt = couRep.findById(couponId);
        if (opt.isEmpty())
            throw new ServiceException("A company with this id does not exist. ");
        Coupon coupon = opt.get();
        // to avoid nullPointer in case of tempering with the DB
        if ((coupon.getCompany() == null || coupon.getCompany().getId() != this.id))
            throw new ServiceException("you can't delete coupon that not yours");

        couRep.deleteById(couponId);

    }

    /**
     * Gets all coupons of this company from the database.
     */
    public List<Coupon> getCoupons() {
        return couRep.findAllByCompanyId(this.id);
    }

    // TODO -- CHECK IF WORKING
    /**
     * Gets all coupons by this company with a category filter from the database.
     */
    public List<Coupon> getCouponsByCategory(Coupon.Category category) {
        return couRep.findAllByCompanyAndCategoryId(this.id, category);
    }

    // TODO -- CHECK IF WORKING
    /**
     * Gets all coupons by this company with a maxPrice filter from the database.
     */
    public List<Coupon> getCouponsByMaxPrice(double maxPrice) {
        return couRep.findAllByCompanyIdAndMaxPrice(this.id, maxPrice);
    }

    /**
     * Gets all details about this company from the database.
     */
    public String getCompanyDetails() throws ServiceException {

        Optional<Company> opt = comRep.findById(id);
        if (opt.isEmpty())
            throw new ServiceException("A company with this id does not exist. ");
        Company company = opt.get();

        String details = "ID: " + this.id + "\n"
                + "Name: " + company.getName() + "\n"
                + "Email: " + company.getEmail() + "\n"
                + "Coupons owned: ";

        ArrayList<Coupon> coupons = (ArrayList<Coupon>) getCoupons();
        details += coupons.size();

        return details;
    }

    /**
     * @return the company as an object using it's id.
     */
    private Company getCompany() throws ServiceException {

        Optional<Company> opt = comRep.findById(id);
        if (opt.isEmpty())
            throw new ServiceException("A company with this id does not exist. ");
        return opt.get();
    }

    /**
     * Checks if a coupon is null or contains null objects (title, description, image, startDate, endDate, category).
     * @return true if null or contains null, returns false if no null was found.
     */
    private static boolean isCouponNullCheck(Coupon coupon) {

        if (coupon == null)
            return true;
        if (coupon.getTitle() == null || coupon.getDescription() == null || coupon.getImage() == null ||
                coupon.getStartDate() == null || coupon.getEndDate() == null || coupon.getCategory() == null)
            return true;

        return false;
    }
}
