/**
 * 
 */
package com.example.cards.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.cards.model.Cards;
import com.example.cards.model.Customer;
import com.example.cards.repository.CardsRepository;

/**
 * @author User
 *
 */
@RestController
public class CardsController {
	
	@Autowired
	private CardsRepository cardsRepository;
	
	@PostMapping("/myCards")
	public List<Cards> getCardDetails(@RequestBody Customer customer){
		
		List<Cards> cards = cardsRepository.findBycustomerId(customer.getCustomerId());
		if(cards != null) {
			return cards;
		}
		
		return null;
	}

}
