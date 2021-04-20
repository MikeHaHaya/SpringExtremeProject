package app.core.repositories;

import app.core.entities.Coupon;
import org.springframework.data.jpa.repository.JpaRepository;

import app.core.entities.Company;

import java.util.List;

public interface CompanyRepository extends JpaRepository<Company, Integer> {

	boolean existsCompanyByEmailAndPassword(String email, String password);

	boolean existsCompanyByEmail(String email);

	boolean existsCompanyByName(String name);

}
