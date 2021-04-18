package app.core.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import app.core.entities.Company;

public interface CompanyRepository extends JpaRepository<Company, Integer> {

	boolean existsComapnyByEmailAndPassword(String email, String password);

	boolean existsComapnyByName(String name);

}
