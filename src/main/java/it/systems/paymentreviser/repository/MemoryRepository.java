package it.systems.paymentreviser.repository;

import it.systems.paymentreviser.entity.Case;

import java.util.List;

public interface MemoryRepository {
	
	Integer save(Case aCase);
	Case findOne(Integer id);
	boolean exists(Integer id);
	List<Case> findAll();
	long count();
}
