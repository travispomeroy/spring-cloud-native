package com.polarbookshop.catalogservice.web;

import com.polarbookshop.catalogservice.domain.BookService;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class BookControllerAdvice {

	@ExceptionHandler(BookService.BookNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	String bookNotFoundHandler(BookService.BookNotFoundException ex) {
		return ex.getMessage();
	}

	@ExceptionHandler(BookService.BookAlreadyExistsException.class)
	@ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
	String bookAlreadyExistsHandler(BookService.BookAlreadyExistsException ex) {
		return ex.getMessage();
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	Map<String, String> handleMethodArgumentNotValid(MethodArgumentNotValidException ex) {
		var errors = new HashMap<String, String>();
		ex.getBindingResult().getAllErrors().forEach(error -> {
			var fieldName = ((FieldError) error).getField();
			var errorMessage = error.getDefaultMessage();
			errors.put(fieldName, errorMessage);
		});
		return errors;
	}

}
