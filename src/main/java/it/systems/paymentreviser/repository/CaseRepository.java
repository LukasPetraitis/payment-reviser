package it.systems.paymentreviser.repository;

import it.systems.paymentreviser.entity.Case;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.function.Function;

@Repository
public class CaseRepository implements MemoryRepository {
	
	private final Map<Integer, Case> caseMap = new HashMap<Integer, Case>();
	private final Function<Case, Integer> idGetter = (Case::getId);
	
	@Override
	public Case save(Case entity) {
		return caseMap.put(idGetter.apply(entity), entity);
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
	public Iterable<Case> findAll() {
		return caseMap.values();
	}
	
	@Override
	public Iterable<Case> findAll(Iterable<Integer> ids) {
		final List<Integer> idList = new ArrayList<>();
		ids.forEach(idList::add);
		return caseMap.values().stream()
				.filter(item -> idList.contains(item.getId()))
				.toList();
	}
	
	@Override
	public long count() {
		return caseMap.size();
	}
}
