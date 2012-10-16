package com.hashedin.carresearch;

public class Model {

	private String make;
	private String year;
	private String modelName;
	private double maxPrice;
	private double minPrice;
	
	@Override
	public String toString() {
		return "Model [make=" + make + ", year=" + year + ", modelName="
				+ modelName + ", maxPrice=" + maxPrice + ", minPrice="
				+ minPrice + "]";
	}
	
	public String getMake() {
		return make;
	}
	public void setMake(String make) {
		this.make = make;
	}
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	public String getModelName() {
		return modelName;
	}
	public void setModelName(String modelName) {
		this.modelName = modelName;
	}
	public double getMaxPrice() {
		return maxPrice;
	}
	public void setMaxPrice(double maxPrice) {
		this.maxPrice = maxPrice;
	}
	public double getMinPrice() {
		return minPrice;
	}
	public void setMinPrice(double minPrice) {
		this.minPrice = minPrice;
	}	
}
