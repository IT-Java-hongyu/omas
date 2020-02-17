package com.fosustu.omas.pojo;

import java.util.List;

public class ApartmentList {
	private List<Apartment> apartmentList;

	public List<Apartment> getApartmentList() {
		return apartmentList;
	}

	public void setApartmentList(List<Apartment> apartmentList) {
		this.apartmentList = apartmentList;
	}

	@Override
	public String toString() {
		return "ApartmentList [apartmentList=" + apartmentList + "]";
	}
}