package app.core.services;

import javax.transaction.Transactional;

import app.core.exceptions.CouponSystemException;
import app.core.exceptions.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Service;

import app.core.repositories.CompanyRepository;
import app.core.repositories.CouponRepository;
import app.core.repositories.CustomerRepository;

@Service("customerService")
@DependsOn({"companyRepository", "couponRepository", "customerRepository"})
@Transactional
public abstract class ClientService {

	@Autowired
	protected CompanyRepository comRep;
	@Autowired
	protected CouponRepository couRep;
	@Autowired
	protected CustomerRepository custRep;

	public abstract boolean login(String email, String password);
}
