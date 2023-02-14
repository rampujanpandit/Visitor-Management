package com.visitor.management.system.services;

import java.util.List;

import com.visitor.management.system.entities.Visitor;
import com.visitor.management.system.payloads.VisitorDto;
import com.visitor.management.system.payloads.VisitorResponse;

public interface VisitorService {
	
	VisitorDto createVisitor(VisitorDto visitor);
	
	VisitorDto updateVisitor(VisitorDto visitor,Long visitorId);
	
	VisitorDto getVisitorById(Long visitorId);	
	
	List<VisitorDto> getAllVisitor();
	
	void deleteVisitor(Long visitorId);
	
	
	VisitorResponse getAllVisitorPagination(Integer pageNumber,Integer pageSize);
	
	List<Visitor> searchVisitors(String query);	
	
	List<Visitor> sortingVisitor(String field);	
	List<Visitor> searchByDateRange(String startingDate,String endingDate);	
	List<Visitor> advanceSearch(String full_name,String email_id,String purpose,String visiting_date);

}

