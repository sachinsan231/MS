/**
 * 
 */
package com.example.accounts.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.accounts.model.Accounts;
import com.example.accounts.model.Customer;
import com.example.accounts.repository.AccountsRepository;

/**
 * @author User
 *
 */
@RestController
public class AccountsController {
	
	@Autowired
	AccountsRepository accountsRepository;

	@PostMapping("/myAccount")
	public Accounts getAccountDetails(@RequestBody Customer customer) {
		
		Accounts account = accountsRepository.findByCustomerId(customer.getCustomerId());
		
		if(account != null) {
			return account;
		}
		
		return null;
	}
}
