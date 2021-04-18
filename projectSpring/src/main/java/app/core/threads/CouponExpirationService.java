package app.core.threads;

import app.core.entities.Coupon;
import app.core.repositories.CouponRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class CouponExpirationService {

    @Autowired
    private CouponRepository couponRep;

    public List<Coupon> getAllCoupons() {
        return couponRep.findAll();
    }

    public void deleteCouponById(int id) {
        couponRep.deleteById(id);
    }
}
