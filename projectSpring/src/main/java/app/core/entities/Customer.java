package app.core.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "customers")
public class Customer {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String firstName, lastName, email, password;

	@ManyToMany
	@JoinTable(name = "customers_coupons", joinColumns = @JoinColumn(name = "customer_id"), inverseJoinColumns = @JoinColumn(name = "coupon_id"))
	private List<Coupon> coupons;

	// CTORS
	/**
	 * @param iDCustomer
	 * @param firstName
	 * @param lastName
	 * @param email
	 * @param password
	 * @param coupons
	 */
	public Customer(int iDCustomer, String firstName, String lastName, String email, String password,
			ArrayList<Coupon> coupons) {
		this.id = iDCustomer;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
		this.coupons = coupons;
	}

	public Customer(int idCustomer) {
	}

	public Customer() {
	}

	// getters and setters
	public int getId() {
		return id;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public String getEmail() {
		return email;
	}

	public List<Coupon> getCoupons() {
		return coupons;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setiDCustomer(int iDCustomer) {
		this.id = iDCustomer;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setCoupons(ArrayList<Coupon> coupons) {
		this.coupons = coupons;
	}

	public void addCoupon(Coupon coupon) {
		if(this.coupons == null) { 
			this.coupons = new ArrayList<Coupon>();
		}
		this.coupons.add(coupon);
	}

	@Override
	public String toString() {
		return "Customer [iDCustomer=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", email=" + email
				+ ", coupons=" + coupons + "]";
	}

}
