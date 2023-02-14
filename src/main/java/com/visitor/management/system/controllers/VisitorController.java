package com.visitor.management.system.controllers;

import java.io.IOException;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.visitor.management.system.entities.Visitor;
import com.visitor.management.system.payloads.ApiResponse;
import com.visitor.management.system.payloads.VisitorDto;
import com.visitor.management.system.payloads.VisitorResponse;
import com.visitor.management.system.services.FileService;
import com.visitor.management.system.services.VisitorService;

import jakarta.persistence.criteria.Path;
import jakarta.validation.Valid;



@RestController
@RequestMapping("/api/visitors")
@CrossOrigin
public class VisitorController {
	
	@Autowired
	private VisitorService visitorService;
	
	@Autowired
	private FileService fileService;
	
	@Value("${Project.image}")
	private String path;
	
	//post Image uploading 
	@PostMapping("/image/{id}")
	public ResponseEntity<VisitorDto> uploadPostImage(@RequestParam("image") MultipartFile image ,
			@PathVariable Long id) throws IOException{
		
		VisitorDto visitorDto = this.visitorService.getVisitorById(id);
		String fileName = this.fileService.uploadImage(path, image);
		visitorDto.setImageName(fileName);
		VisitorDto updateVisitor = this.visitorService.updateVisitor(visitorDto, id);
		return new ResponseEntity<VisitorDto>(updateVisitor , HttpStatus.OK);
	}
	
	//Post - create Visitor
    @PostMapping("/")
	public ResponseEntity<VisitorDto> createVisitor(@Valid @RequestBody VisitorDto visitorDto)
	{
	
	   VisitorDto createVisitorDto =	this.visitorService.createVisitor(visitorDto);
	   return new ResponseEntity<>(createVisitorDto,HttpStatus.CREATED);
		
	}
    
    //Put - update Visitor
    @PutMapping("/{id}")
    public ResponseEntity<VisitorDto> updateVisitor(@Valid @RequestBody VisitorDto visitorDto,@PathVariable("id") Long vid)
    {
    	 VisitorDto updatedVisitor = this.visitorService.updateVisitor(visitorDto, vid);
    	 return ResponseEntity.ok(updatedVisitor);
    }
    
    //Delete -delete Visitor
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteVisitor(@PathVariable("id") Long vid)
    {
    	this.visitorService.deleteVisitor(vid);
    	return new ResponseEntity<ApiResponse>(new ApiResponse("Visitor deleted successfully",true),HttpStatus.OK);
    }
    
//    //GET - user get
//    @GetMapping("/")
//    public ResponseEntity<List<VisitorDto>> getAllVisitors(){
//    	return ResponseEntity.ok(this.visitorService.getAllVisitor());
//    }
    
  //GET - single visitor get
    @GetMapping("/{id}")
    public ResponseEntity<VisitorDto> getSingleVisitor(@PathVariable Long id){
    	return ResponseEntity.ok(this.visitorService.getVisitorById(id));
    }
    
//  GET - visitor get
    @GetMapping("/")
    public ResponseEntity<List<VisitorDto>> getAllVisitors(){
    	return ResponseEntity.ok(this.visitorService.getAllVisitor());
    }
    
    //pagination
    
    @GetMapping("/pagination")
    public ResponseEntity<VisitorResponse> getAllVisitorsPagination(
    		@RequestParam(value = "pageNumber",defaultValue = "0",required = false) Integer pageNumber,
    		@RequestParam(value = "pageSize",defaultValue = "10",required = false)Integer pageSize
    		){
    	VisitorResponse visitorResponse = this.visitorService.getAllVisitorPagination(pageNumber, pageSize);
    	return new ResponseEntity<VisitorResponse>(visitorResponse,HttpStatus.OK);
    }
    
    
    
    //searching
    
    @GetMapping("/search")
    public ResponseEntity<List<Visitor>> searchVisitors(@RequestParam(value = "query") String query){
    	return ResponseEntity.ok(visitorService.searchVisitors(query));
    }
    
    
    //sorting Data
    
    public List<Visitor> sortingVisitor(@PathVariable("field") String field){
    	List<Visitor> allProducts = this.visitorService.sortingVisitor(field);
    	return allProducts;
    }
    
    //search By Date Range
    @GetMapping("/p/{startingDate}/{endingDate}")
    public List<Visitor> searchByDateRange(@PathVariable("startingDate") String sartingDate,@PathVariable("endingDate") String endingDate){
    	List<Visitor> list = this.visitorService.searchByDateRange(sartingDate, endingDate);
    	//System.out.println("List"+list);
    	return list;
    }
    
    //Advance Search
    @GetMapping("/advanceSearch")
    public ResponseEntity<List<Visitor>> advanceSearch(
    		@RequestParam(value = "full_name",defaultValue = "")String full_name,
    		@RequestParam(value = "email_id",defaultValue = "")String email_id,
    		@RequestParam(value = "purpose",defaultValue = "")String purpose,
    		@RequestParam(value = "visiting_date",defaultValue = "")String visiting_date
    		){
    	List<Visitor> list = this.visitorService.advanceSearch(full_name, email_id, purpose, visiting_date);
    	return ResponseEntity.ok(list);
    }
    
}
