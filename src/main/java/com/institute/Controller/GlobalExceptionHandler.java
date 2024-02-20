package com.institute.Controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

	private static final Logger logger = LoggerFactory.getLogger(MainInstituteController.class);

	@ExceptionHandler(DataAccessException.class)
	public ResponseEntity<?> handleDataAccessException(DataAccessException ex) {
		logger.error("Error occured while saving", ex);
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error occured while saving");

	}

}
