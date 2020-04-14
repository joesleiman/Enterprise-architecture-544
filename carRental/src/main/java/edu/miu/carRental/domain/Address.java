package edu.miu.carRental.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Range;

@Entity
@Table(name = "addresses")
public class Address {
	
	@Id
	@Column(name = "address_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long addressId;
	
	@Column(name = "street_line")
	@NotEmpty
    private String streetLine;
	
	@Column(name = "city")
	@NotEmpty
    private String city;
    
	@Column(name = "state")
	@NotEmpty
    private String state;
    
	@Column(name = "zip_code")
	@NotEmpty
	@Range(min=5, max=9, message= "{Range.zipcode}")
    private Integer zipCode;
    
	@Column(name = "country")
	@NotEmpty
    private String country;
	
	public Address(){
		
	}
	public Long getAddressId() {
		return addressId;
	}

	public void setAddressId(Long addressId) {
		this.addressId = addressId;
	}

	public String getStreetLine() {
		return streetLine;
	}

	public void setStreetLine(String streetLine) {
		this.streetLine = streetLine;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public Integer getZipCode() {
		return zipCode;
	}

	public void setZipCode(Integer zipCode) {
		this.zipCode = zipCode;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}
	

}
