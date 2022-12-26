/**
 * 
 */
package com.example.loans.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.loans.model.Loans;

/**
 * @author User
 *
 */
@Repository
public interface LoansRepository extends CrudRepository<Loans, Long>{

 	List<Loans> findByCustomerIdOrderByStartDtDesc( int customerId);
}
