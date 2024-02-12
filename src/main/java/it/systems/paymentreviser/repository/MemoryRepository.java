package it.systems.paymentreviser.repository;

import it.systems.paymentreviser.entity.Case;

import java.util.UUID;

public interface MemoryRepository {
	
	boolean save(Case aCase);
	Case findOne(UUID id);
	boolean exists(UUID id);
	Iterable<Case> findAll();
	Iterable<Case> findAll(Iterable<UUID> ids);
	long count();
}
