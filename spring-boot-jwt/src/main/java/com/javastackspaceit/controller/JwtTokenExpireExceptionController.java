package com.javastackspaceit.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.javastackspaceit.dto.ErrorMessage;
import com.javastackspaceit.exception.JwtTokenExpireException;

@RestControllerAdvice
public class JwtTokenExpireExceptionController {
	@ExceptionHandler(JwtTokenExpireException.class)
	public @ResponseBody ResponseEntity<ErrorMessage> jwtTokenExpireException(JwtTokenExpireException exception) {
		System.out.println(exception.getMessage());
		ErrorMessage errorMessage = new ErrorMessage();
		errorMessage.setMessage("Token expire ");
		errorMessage.setCode(400);
		return new ResponseEntity<>(errorMessage, HttpStatus.UNAUTHORIZED);
	}
}
