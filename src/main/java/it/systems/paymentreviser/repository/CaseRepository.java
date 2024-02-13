package it.systems.paymentreviser.repository;

import it.systems.paymentreviser.entity.Case;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class CaseRepository implements MemoryRepository {
	
	private final Map<Integer, Case> caseMap = new HashMap<Integer, Case>();
	
	@Override
	public Integer save(Case entity) {
		Integer id = entity.getId();
		caseMap.put(id, entity);
		return id;
	}
	
	@Override
	public Case findOne(Integer id) {
		return caseMap.get(id);
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
}
