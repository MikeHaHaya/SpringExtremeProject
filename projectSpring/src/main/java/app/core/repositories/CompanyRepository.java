package app.core.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import app.core.entities.Company;

public interface CompanyRepository extends JpaRepository<Company, Integer> {

	boolean existsCompanyByEmailAndPassword(String email, String password);

	boolean existsCompanyByEmail(String email);

	boolean existsCompanyByName(String name);

}
