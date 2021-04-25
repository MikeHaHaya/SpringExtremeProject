package app.core.repositories;

import app.core.entities.Coupon;
import org.springframework.data.jpa.repository.JpaRepository;

import app.core.entities.Company;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("companyRepository")
public interface CompanyRepository extends JpaRepository<Company, Integer> {

	boolean existsCompanyByEmailAndPassword(String email, String password);

	boolean existsCompanyByEmail(String email);

	boolean existsCompanyByName(String name);

}
