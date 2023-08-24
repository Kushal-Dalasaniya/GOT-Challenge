package com.demandfarm.got.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.demandfarm.got.model.ErrorResponse;
import com.demandfarm.got.util.HeaderMapper;


/*
 * @author Kushal.dalasaniya
 * @Description This is globle exception handler for the rest API calls
 */
@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler{
	
	/*
	 * This is GOT handler
	 */
	@ExceptionHandler(GotException.class)
	public ResponseEntity<ErrorResponse> handleSudokuGameException(GotException ex){
		ErrorResponse err = new ErrorResponse();
		err.setCode(ex.getCode());
		err.setStatus(ex.getStatusCode().value());
		err.setMessage(ex.getMessage());
		
		HttpHeaders header = HeaderMapper.getHttpHeaders(ex.getCorrelationId());
		return ResponseEntity.status(ex.getStatusCode()).headers(header).body(err);
	}
}
