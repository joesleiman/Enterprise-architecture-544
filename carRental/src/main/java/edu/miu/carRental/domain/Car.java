package edu.miu.carRental.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "cars")
public class Car {
	
	@Id
	@Column(name = "car_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long carId;
	
	@Column(name = "car_vin_number")
	@NotNull(message = "*Please provide vehicle number")
	private String carVinNumber;
	
	@Column(name = "plate_number")
	@NotNull(message = "*Please provide plate number")
    private String plateNumber;
	
	@Column(name = "make")
	@NotNull(message = "*Please provide make")
    private String make;
	
	@Column(name = "model")
	@NotNull(message = "*Please provide model")
    private String model;
	
	@Column(name = "year")
	@NotNull(message = "*Please provide year")
    private Integer year;
	
	@Column(name = "category_name")
	@NotNull(message = "*Please provide category name")   
    private String categoryName;
	
	@Column(name = "car_status")
	@NotNull(message = "*Please provide car status")   
    private String carStatus;
	
	@Column(name = "price_per_day")
	@NotNull(message = "*Please provide car price per day")   
    private Double pricePerDay;
	
	public Car() {
		
	}
	
	public String getCarStatus() {
		return carStatus;
	}

	public void setCarStatus(String carStatus) {
		this.carStatus = carStatus;
	}

	public Double getPricePerDay() {
		return pricePerDay;
	}

	public void setPricePerDay(Double pricePerDay) {
		this.pricePerDay = pricePerDay;
	}

	public Long getCarId() {
		return carId;
	}
	public void setCarId(Long carId) {
		this.carId = carId;
	}
	public String getCarVinNumber() {
		return carVinNumber;
	}
	public void setCarVinNumber(String carVinNumber) {
		this.carVinNumber = carVinNumber;
	}
	public String getPlateNumber() {
		return plateNumber;
	}
	public void setPlateNumber(String plateNumber) {
		this.plateNumber = plateNumber;
	}
	public String getMake() {
		return make;
	}
	public void setMake(String make) {
		this.make = make;
	}
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	public Integer getYear() {
		return year;
	}
	public void setYear(Integer year) {
		this.year = year;
	}
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
}
