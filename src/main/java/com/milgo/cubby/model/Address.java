package com.milgo.cubby.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;


@Embeddable
public class Address {

	@NotNull(message="Podaj nazwe miasta!")
	@Size(min=1, message="Podaj nazwe miasta!")
	@Column(name="CITY")
	public String city;
	
	@NotNull(message="Podaj kod pocztowy!")
	@Size(min=1, message="Podaj kod pocztowy!")
	@Column(name="ZIPCODE")
	@Pattern(regexp="^\\d{2}(?:[-\\s]\\d{3})?$", message="Zly format kodu pocztowego!")
	public String zipCode;
	
	@NotNull(message="Podaj nazwe ulicy!")
	@Size(min=1, message="Podaj nazwe ulicy!")
	@Column(name="STREET")
	public String street;

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		if(city != null){			
			if(city.length() > 20){
				this.city = city.substring(0, 20);
				return;
			}
			this.city = city;
		}
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		if(zipCode != null){
			if(zipCode.length() > 6){
				this.zipCode = zipCode.substring(0, 6);
				return;
			}
			this.zipCode = zipCode;
		}
	}
	
	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		if(street != null){
			if(street.length() > 40){
				this.street = street.substring(0, 40);
				return;
			}
			this.street = street;
		}
	}
	
}
