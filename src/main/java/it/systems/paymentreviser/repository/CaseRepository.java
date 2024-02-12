package it.systems.paymentreviser.repository;

import it.systems.paymentreviser.entity.Case;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@Repository
public class CaseRepository implements MemoryRepository {
	
	private final Map<String, Case> caseMap = new HashMap<String, Case>();
	private final Function<Case, String> idGetter = (Case::returnCaseIdCopy);
	
	@Override
	public boolean save(Case entity) {
	
		caseMap.put(idGetter.apply(entity), entity);
		return true;
	}
	
	@Override
	public Case findOne(UUID id) {
		return caseMap.get(id);
	}
	
	@Override
	public boolean exists(UUID id) {
		return caseMap.containsKey(id);
	}
	
	@Override
	public Iterable<Case> findAll() {
		return caseMap.values();
	}
	
	@Override
	public Iterable<Case> findAll(Iterable<UUID> ids) {
		final List<UUID> idList = new ArrayList<>();
		ids.forEach(idList::add);
		return caseMap.values().stream()
				.filter(item -> idList.contains(item.returnCaseIdCopy()))
				.collect(Collectors.toList());
	}
	
	@Override
	public long count() {
		return caseMap.size();
	}
}
