package app.core.services;

import javax.transaction.Transactional;

import app.core.exceptions.CouponSystemException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.core.repositories.CompanyRepository;
import app.core.repositories.CouponRepository;
import app.core.repositories.CustomerRepository;

@Service
@Transactional
public abstract class ClientService {

	@Autowired
	protected CompanyRepository comRep;
	@Autowired
	protected CouponRepository couRep;
	@Autowired
	protected CustomerRepository custRep;

	public abstract boolean login(String email, String pasword);
}
