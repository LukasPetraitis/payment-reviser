package it.systems.paymentreviser.repository;

import it.systems.paymentreviser.dto.UnresolvedCasesDataDTO;
import it.systems.paymentreviser.entity.Case;
import it.systems.paymentreviser.enums.ResolutionStatus;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
public class CaseMemoryRepository implements CaseRepository {
	private static AtomicInteger ID_GENERATOR = new AtomicInteger(1000);
	private final Map<Integer, Case> caseMap = new HashMap<>();
	
	@Override
	public Case save(Case entity) {
		Integer id = ID_GENERATOR.getAndIncrement();
		entity.setId(id);
		return caseMap.put(id, entity);
	}
	
	@Override
	public Case update(Case entity) {
		return caseMap.put(entity.getId(), entity);
	}
	
	@Override
	public Optional<Case> findById(Integer id) {
		return Optional.ofNullable(caseMap.get(id));
	}
	
	@Override
	public boolean exists(Integer id) {
		return caseMap.containsKey(id);
	}
	
	@Override
	public List<Case> findAll() {
		return caseMap.values().stream().toList();
	}
	
	@Override
	public long count() {
		return caseMap.size();
	}
	
	@Override
	public UnresolvedCasesDataDTO getUnresolvedCasesData() {
		List<Case> unresolvedCasesList = caseMap.values().stream()
				.filter(aCase -> ResolutionStatus.UNRESOLVED.equals(aCase.getResolution()))
				.toList();
		
		BigDecimal totalAmountUnresolved = unresolvedCasesList.stream().map(Case::getPaymentAmount).reduce(BigDecimal.ZERO, BigDecimal::add);
		
		return new UnresolvedCasesDataDTO(unresolvedCasesList.size(), totalAmountUnresolved);
	}
}
