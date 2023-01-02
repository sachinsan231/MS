/**
 * 
 */
package com.example.cards.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.cards.config.CardsServiceConfig;
import com.example.cards.model.Cards;
import com.example.cards.model.Customer;
import com.example.cards.model.Properties;
import com.example.cards.repository.CardsRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

/**
 * @author User
 *
 */
@RestController
public class CardsController {
	
	private static final Logger logger = LoggerFactory.getLogger(CardsController.class);
	
	@Autowired
	private CardsRepository cardsRepository;
	
	@Autowired
	private CardsServiceConfig loansServiceConfig;
	
	@PostMapping("/myCards")
	public List<Cards> getCardDetails(@RequestBody Customer customer){
		logger.info("getCardDetails() started");
		List<Cards> cards = cardsRepository.findBycustomerId(customer.getCustomerId());
		logger.info("getCardDetails() ended");
		if(cards != null) {
			return cards;
		}
		
		return null;
	}
	
	@GetMapping("/card/properties")
	public String getPropertyDetails() throws JsonProcessingException {
		ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
		Properties properties = new Properties(loansServiceConfig.getMsg(), loansServiceConfig.getBuildVersion(),
				loansServiceConfig.getMailDetails(), loansServiceConfig.getActiveBranches());
		String jsonStr = ow.writeValueAsString(properties);
		return jsonStr;
	}

}
