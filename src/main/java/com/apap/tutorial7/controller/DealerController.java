package com.apap.tutorial7.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.apap.tutorial7.model.DealerModel;
import com.apap.tutorial7.rest.DealerDetail;
import com.apap.tutorial7.rest.Setting;
import com.apap.tutorial7.service.CarService;
import com.apap.tutorial7.service.DealerService;

@RestController
@RequestMapping("/dealer")
public class DealerController {
	@Autowired
	private DealerService dealerService;
	
	
	@Autowired
	RestTemplate restTemplate;
	
	@Bean
	public RestTemplate rest() {
		return new RestTemplate();
	}
	
	@GetMapping(value = "/status/{dealerId}")
	private String getStatus(@PathVariable ("dealerId") long dealerId) throws Exception{
		String path = Setting.dealerUrl + "/dealer?id=" + dealerId;
		return restTemplate.getForEntity(path, String.class).getBody();	
	}
	
	@GetMapping(value = "/full/{dealerId}")
	private DealerDetail postStatus(@PathVariable ("dealerId") long dealerId) throws Exception{
		String path = Setting.dealerUrl + "/dealer";
		DealerModel dealer = dealerService.getDealerDetailByID(dealerId).get();
		DealerDetail detail = restTemplate.postForObject(path, dealer, DealerDetail.class);
		return detail;
	}
	
	
	@PostMapping(value = "/add")
	private DealerModel addDealerSubmit(@RequestBody DealerModel dealer) {
		return dealerService.addDealer(dealer);
	}
	
	@GetMapping(value = "/{dealerId}")
	private DealerModel viewDealer(@PathVariable ("dealerId") long dealerId, Model model) {
		return dealerService.getDealerDetailByID(dealerId).get();
	}
	
	@DeleteMapping(value = "/delete")
	private String deleteDealer(@RequestParam("dealerId") long id, Model model) {
		DealerModel dealer = dealerService.getDealerDetailByID(id).get();
		dealerService.deleteDealer(dealer);
		return "Success";
	}
	
	@PutMapping(value = "/{id}")
	private String updateDealerSubmit(
		@PathVariable(value = "id") long id,
		@RequestParam("alamat") String alamat,
		@RequestParam("telp") String telp){
			DealerModel dealer = (DealerModel) dealerService.getDealerDetailByID(id).get();
			if(dealer.equals(null)) {
				return "Couldn't find your dealer";
			}
			dealer.setAlamat(alamat);
			dealer.setNoTelp(telp);
			dealerService.dealerUpdate(dealer, id);
			return "update success";
		}
	
	@GetMapping()
	private List<DealerModel> viewAllDealer(Model model){
		return dealerService.viewAllDealer();
	}
	
	
//	@RequestMapping("/")
//	private String home() {
//		return "home";
//	}
	
	
//	@RequestMapping(value = "/dealer/add", method = RequestMethod.GET)
//	private String add (Model model) {
//		model.addAttribute("dealer", new DealerModel());
//		return "addDealer";
//	}
	
//	@RequestMapping(value = "/dealer/add", method = RequestMethod.POST)
//	private String addDealerSubmit(@ModelAttribute DealerModel dealer) {
//		dealerService.addDealer(dealer);
//		return "add";
//	}
	
//	@RequestMapping(value = "/dealer/view", method = RequestMethod.GET)
//	public String viewDealer(String dealerId, Model model) {
//		
//		Long id = Long.parseLong(dealerId);
//		DealerModel archive = dealerService.getDealerDetailByID(id).get();
//		List<CarModel>carList = carService.sortDrHarga(id);
//		
//		model.addAttribute("list", carList);
//		model.addAttribute("dealer", archive);
//		return "view-dealer";
//	}
	
//	@RequestMapping(value = "/dealer/delete/{dealerId}", method = RequestMethod.GET)
//	public String delete(@PathVariable(value = "dealerId") Long dealerId) {
//		dealerService.deleteDealer(dealerId);
//		return "delete";
//	 }
	
//	@RequestMapping(value = "/dealer/viewall", method = RequestMethod.GET)
//	public String viewAll(Model model) {
//		List<DealerModel>dealerList = dealerService.searchAll().findAll();
//		
//		model.addAttribute("list", dealerList);
//		return "view-all";
//	}
	
//	@RequestMapping(value = "/dealer/update/{dealerId}", method = RequestMethod.GET)
//	private String update(@PathVariable(value = "dealerId") Long dealerId, Model model) {
//		DealerModel archive = dealerService.getDealerDetailByID(dealerId).get();
//		
//		model.addAttribute("dealer", archive);
//		return "update-dealer";
//	}
		
		
//	@RequestMapping(value = "/dealer/update/{dealerId}", method = RequestMethod.POST)
//	private String updateDealerSubmit(@PathVariable(value = "dealerId") Long dealerId, @ModelAttribute DealerModel dealer) {
//		dealerService.dealerUpdate(dealer, dealerId);
//		return "update";
//		
//	}
}
