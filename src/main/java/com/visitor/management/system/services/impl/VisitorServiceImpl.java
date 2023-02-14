package com.visitor.management.system.services.impl;

import java.util.List;

import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.visitor.management.system.entities.Visitor;
import com.visitor.management.system.payloads.VisitorDto;
import com.visitor.management.system.payloads.VisitorResponse;
import com.visitor.management.system.repositories.VisitorRepo;
import com.visitor.management.system.services.VisitorService;
import com.visitor.management.system.exceptions.*;


@Service
public class VisitorServiceImpl implements VisitorService {
	
	@Autowired
	private VisitorRepo visitorRepo;

	@Override
	public VisitorDto createVisitor(VisitorDto visitorDto) {
		Visitor visitor = this.dtoToVisitor(visitorDto);
		
		Visitor savedVisitor = this.visitorRepo.save(visitor);
		return this.visitorToDto(savedVisitor);
	}

	@Override
	public VisitorDto updateVisitor(VisitorDto visitorDto, Long visitorId) {
		
		Visitor visitor=this.visitorRepo.findById(visitorId).
				orElseThrow(()-> new ResourceNotFoundException("Visitor"," Id ", "visitorId"));
		visitor.setFullName(visitorDto.getFullName());
		visitor.setEmailId(visitorDto.getEmailId());
		visitor.setAddress(visitorDto.getAddress());
		visitor.setGender(visitorDto.getGender());
		visitor.setMobileNo(visitorDto.getMobileNo());
		visitor.setPurposeToVisit(visitorDto.getPurposeToVisit());
		visitor.setVisitingDate(visitorDto.getVisitingDate());
		
		Visitor updatedVisitor = this.visitorRepo.save(visitor);
		VisitorDto visitorDto1 = this.visitorToDto(updatedVisitor);
		return visitorDto1;
	}

	@Override
	public VisitorDto getVisitorById(Long visitorId) {
		
		Visitor visitor = this.visitorRepo.findById(visitorId)
				.orElseThrow(()->new ResourceNotFoundException("Visitor","Id","visitorId"));
		
		return this.visitorToDto(visitor);
	}

	@Override
	public List<VisitorDto> getAllVisitor() {
		List<Visitor> visitors = this.visitorRepo.findAll();
		List<VisitorDto> visitorDtos = visitors.stream()
				.map(visitor->this.visitorToDto(visitor)).collect(Collectors.toList());
		return visitorDtos;
	}

	@Override
	public void deleteVisitor(Long visitorId) {
		Visitor visitor = this.visitorRepo.findById(visitorId)
		    .orElseThrow(()->new ResourceNotFoundException("Visitor","Id","visitorId"));
		this.visitorRepo.delete(visitor);

	}
	
	private Visitor dtoToVisitor(VisitorDto visitorDto) {
		Visitor visitor=new Visitor();
//		visitor.setId(visitorDto.getId());
		visitor.setFullName(visitorDto.getFullName());
		visitor.setEmailId(visitorDto.getEmailId());
		visitor.setGender(visitorDto.getGender());
		visitor.setMobileNo(visitorDto.getMobileNo());
		visitor.setAddress(visitorDto.getAddress());
		visitor.setPurposeToVisit(visitorDto.getPurposeToVisit());
		visitor.setVisitingDate(visitorDto.getVisitingDate());
		
		return visitor;
	}
	
	public VisitorDto visitorToDto(Visitor visitor)
	{
		VisitorDto visitorDto = new VisitorDto();
		visitorDto.setId(visitor.getId());
		visitorDto.setFullName(visitor.getFullName());
		visitorDto.setEmailId(visitor.getEmailId());
	    visitorDto.setGender(visitor.getGender());
	    visitorDto.setMobileNo(visitor.getMobileNo());
	    visitorDto.setAddress(visitor.getAddress());
	    visitorDto.setPurposeToVisit(visitor.getPurposeToVisit());
	    visitorDto.setVisitingDate(visitor.getVisitingDate());
	    
	    return visitorDto;
	}

	@Override
	public VisitorResponse getAllVisitorPagination(Integer pageNumber, Integer pageSize) {
		
		Pageable p = PageRequest.of(pageNumber, pageSize);
		Page<Visitor> pageVisitor = this.visitorRepo.findAll(p);
		List<Visitor> allVisitors = pageVisitor.getContent();
		List<VisitorDto> visitorDtos = allVisitors.stream().map(visitor->this.visitorToDto(visitor))
				.collect(Collectors.toList());
		
		VisitorResponse visitorResponse = new VisitorResponse();
		
		visitorResponse.setContent(visitorDtos);
		visitorResponse.setPageNumber(pageVisitor.getNumber());
		visitorResponse.setPageSize(pageVisitor.getSize());
		visitorResponse.setTotalElements(pageVisitor.getTotalElements());
		visitorResponse.setTotalPages(pageVisitor.getTotalPages());
		visitorResponse.setLastPage(pageVisitor.isLast());
		
		
		return visitorResponse;
		
		
	}
	
	
	
	
	
	//searchVisitorBy Name and address

	@Override
	public List<Visitor> searchVisitors(String query) {
		
		List<Visitor> visitors = visitorRepo.searchVisitors(query);
		
		return visitors;
	}

	
	//Sorting Visitor
	
	@Override
	public List<Visitor> sortingVisitor(String field) {
		
		return this.visitorRepo.findAll(Sort.by(Sort.Direction.ASC,field));
	}

	
	//Search Date By visitor
	@Override
	public List<Visitor> searchByDateRange(String startingDate, String endingDate) {
		return this.visitorRepo.searchByDateRange(startingDate, endingDate);
	}

	//advance Search
	@Override
	public List<Visitor> advanceSearch(String full_name, String email_id, String purpose, String visiting_date) {
		
		return this.visitorRepo.advanceSearch(full_name, email_id, purpose, visiting_date);
	}
	
	

	

}
