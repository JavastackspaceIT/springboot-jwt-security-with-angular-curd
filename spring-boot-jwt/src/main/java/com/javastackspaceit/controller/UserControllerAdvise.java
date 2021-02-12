package com.javastackspaceit.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.javastackspaceit.dto.ErrorMessage;
import com.javastackspaceit.exception.UserNotCreatedException;

@RestControllerAdvice
public class UserControllerAdvise {

	@ExceptionHandler(UsernameNotFoundException.class)
	public ResponseEntity<ErrorMessage> userNotFoundException(UsernameNotFoundException usernameNotFoundException) {

		ErrorMessage errorMessage = new ErrorMessage();
		errorMessage.setCode(HttpStatus.NOT_FOUND.value());
		errorMessage.setMessage(usernameNotFoundException.getMessage());
		return new ResponseEntity<>(errorMessage, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(UserNotCreatedException.class)
	public ResponseEntity<ErrorMessage> userNotCreated(UserNotCreatedException userNotCreatedException){
		ErrorMessage errorMessage=new ErrorMessage();
		errorMessage.setCode(HttpStatus.NOT_IMPLEMENTED.value());
		errorMessage.setMessage("User Not create" );
		return new ResponseEntity<ErrorMessage>(errorMessage, HttpStatus.NOT_IMPLEMENTED);
	}
}
