package com.apap.tutorial7.service;

import java.util.List;
import java.util.Optional;

import com.apap.tutorial7.model.DealerModel;
import com.apap.tutorial7.repository.DealerDb;

public interface DealerService {
	Optional<DealerModel> getDealerDetailByID(Long id);
	
	DealerModel addDealer(DealerModel dealer);
	void deleteDealer(DealerModel dealer);
	DealerDb searchAll();

	void dealerUpdate(DealerModel dealer, Long dealerId);

	List<DealerModel> viewAllDealer();

	
	
}
