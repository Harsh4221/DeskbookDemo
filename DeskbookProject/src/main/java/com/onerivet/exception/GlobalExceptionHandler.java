package com.onerivet.exception;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.onerivet.model.response.Error_Response;

@RestControllerAdvice
public class GlobalExceptionHandler extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<Error_Response> resourceNotFoundExceptionHandler(ResourceNotFoundException exception) {
		Error_Response response = new Error_Response(LocalDateTime.now(), exception.getMessage());

		return new ResponseEntity<Error_Response>(response, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Error_Response> methodArgumentNotValidException(MethodArgumentNotValidException exception) {
		Map<String, String> errors = new HashMap<>();

		exception.getBindingResult().getAllErrors().forEach((error) -> {
			String errorField = ((FieldError) error).getField();
			String message = error.getDefaultMessage();
			errors.put(errorField, message);
		});

		Error_Response response = new Error_Response(null, errors);

		return new ResponseEntity<Error_Response>(response, HttpStatus.BAD_REQUEST);
	}

}
