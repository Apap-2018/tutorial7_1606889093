package com.apap.tutorial7.service;

import java.util.List;
import java.util.Optional;

import com.apap.tutorial7.model.CarModel;

public interface CarService {
	CarModel addCar(CarModel car);
	CarModel findCarById(long id);
	void carUpdate(CarModel car, Long id);
	List<CarModel> sortDrHarga(Long dealerId);
	List<CarModel> viewAllCar();
	Optional<CarModel> getCarDetailById(long id);
	void deleteCar(CarModel car);
}
