/**
 * 
 */
package com.example.cards.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.cards.model.Cards;

/**
 * @author User
 *
 */
@Repository
public interface CardsRepository extends CrudRepository<Cards, Long>{

	List<Cards> findBycustomerId(int customerId);
}
