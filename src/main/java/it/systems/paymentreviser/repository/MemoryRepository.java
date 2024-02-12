package it.systems.paymentreviser.repository;

import it.systems.paymentreviser.entity.Case;

import java.util.UUID;

public interface MemoryRepository {
	
	Case save(Case aCase);
	Case findOne(Integer id);
	boolean exists(Integer id);
	Iterable<Case> findAll();
	Iterable<Case> findAll(Iterable<Integer> ids);
	long count();
}
