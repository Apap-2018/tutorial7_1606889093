package com.apap.tutorial7.service;

import java.util.List;
import java.util.Optional;

import com.apap.tutorial7.model.CarModel;
import com.apap.tutorial7.repository.CarDb;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CarServiceImpl implements CarService {
	@Autowired
	private CarDb carDb;
	
	@Override
	public CarModel addCar(CarModel car) {
		// TODO Auto-generated method stub
		return carDb.save(car);
		
	}
	
	@Override
	public void deleteCar(CarModel car) {
		carDb.delete(car);
	}
	
	@Override
	public CarModel findCarById(long id) {
		return carDb.findById(id).get();
		
	}
	
	@Override
	public void carUpdate(CarModel updateCar, Long carId) {
		CarModel dataLama = carDb.findById(carId).get();
		dataLama.setBrand(updateCar.getBrand());
		dataLama.setType(updateCar.getType());
		dataLama.setAmount(updateCar.getAmount());
		dataLama.setPrice(updateCar.getPrice());
		carDb.save(dataLama);
	}
	
	@Override
	public List<CarModel> sortDrHarga(Long dealerId){
		return carDb.findByDealerIdOrderByPriceDesc(dealerId);
	}
	
	@Override
	public List<CarModel> viewAllCar(){
		return carDb.findAll();
	}
	
	@Override
	public Optional<CarModel> getCarDetailById(long id) {
		return carDb.findById(id);
	}
}
