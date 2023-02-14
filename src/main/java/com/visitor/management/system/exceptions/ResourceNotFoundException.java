package com.visitor.management.system.exceptions;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResourceNotFoundException extends RuntimeException{

	String resourceName;
	String fieldName;
	String fieldValue;
	
	
	
	public ResourceNotFoundException(String resourceName, String fieldName, String visitorId) {
		super(String.format("%s not found with %s :%1", resourceName,fieldName,visitorId));
		this.resourceName = resourceName;
		this.fieldName = fieldName;
		this.fieldValue = visitorId;
	}

	
	
}
