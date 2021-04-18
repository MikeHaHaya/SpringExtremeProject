package app.core.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "companies")
public class Company {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String name, email, password;
	
//	TODO
	@OneToMany(cascade = CascadeType.PERSIST, mappedBy = "company")
	private List<Coupon> coupons;
	
	//CTOR
	/**
	 * @param idCompany
	 * @param name
	 * @param email
	 * @param password
	 * @param coupons
	 */
	public Company(int idCompany, String name, String email, String password, ArrayList<Coupon> coupons) {
		this.id = idCompany;
		this.name = name;
		this.email = email;
		this.password = password;
		this.coupons = coupons;
	}
	public Company(int idCompany) {
		this.id = idCompany;
	}
	
	public Company() {}

	
	//getters and setters
	
	public int getId() {
		return id;
	}
	public void setIdCompany(int idCompany) {
		this.id = idCompany;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public void setCoupons(ArrayList<Coupon> coupons) {
		this.coupons = coupons;
	}
	public void setiDCompany(int idCompany) {
		this.id = idCompany;
	}
	public String getName() {
		return name;
	}
	public String getPassword() {
		return this.password;
	}
	public String getEmail() {
		return email;
	}
	public List<Coupon> getCoupons() {
		return coupons;
	}

	public void addCoupon(Coupon coupon) {
		if(this.coupons == null) { 
			this.coupons = new ArrayList<Coupon>();
		}
		this.coupons.add(coupon);
	}

	@Override
	public String toString() {
		return "Company [ID=" + id + ", name=" + name + ", email=" + email + "]";
	}
	
	
}
