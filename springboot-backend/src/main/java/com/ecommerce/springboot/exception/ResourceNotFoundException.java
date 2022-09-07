package com.ecommerce.springboot.exception;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)

@Getter
@Setter

public class ResourceNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	String resourceName;
	String fieldName;
	long fieldValue;

	String username;

	public ResourceNotFoundException(String message) {
		super(message);
	}

	public ResourceNotFoundException(String resourceName,String fieldName,long fieldValue)
	{
		super(String.format("%s  not found with  %s : %s",resourceName,fieldName,fieldValue));
		this.resourceName=resourceName;
		this.fieldName=fieldName;
		this.fieldValue=fieldValue;
	}
	public ResourceNotFoundException(String resourceName,String fieldName,String username)
	{
		super(String.format("%s  not found with  %s : %s",resourceName,fieldName,username));
		this.resourceName=resourceName;
		this.fieldName=fieldName;
		this.fieldValue=fieldValue;
	}
}




