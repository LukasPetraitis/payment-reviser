package it.systems.paymentreviser.repository;

import it.systems.paymentreviser.dto.UnresolvedCasesDataDTO;
import it.systems.paymentreviser.entity.Case;

import java.util.List;
import java.util.Optional;

public interface CaseRepository {
	
	Case save(Case entity);
	Case update(Case entity);
	Optional<Case> findById(Integer id);
	boolean exists(Integer id);
	List<Case> findAll();
	long count();
	UnresolvedCasesDataDTO getUnresolvedCasesData();
}
