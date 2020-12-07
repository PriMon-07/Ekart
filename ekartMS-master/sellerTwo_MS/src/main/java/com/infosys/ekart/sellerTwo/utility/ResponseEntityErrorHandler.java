package com.infosys.ekart.sellerTwo.utility;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.infosys.ekart.sellerTwo.dto.ErrorInfo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
@RestController
public class ResponseEntityErrorHandler extends ResponseEntityExceptionHandler {
	
	@Autowired
	Environment environment;
	
	@ExceptionHandler(Exception.class)
	public final ResponseEntity<ErrorInfo> handerSserviceException(Exception ex, WebRequest request) {
		ErrorInfo eInfo = new ErrorInfo();
		eInfo.setMessage(environment.getProperty(ex.getMessage()));
		return new ResponseEntity<>(eInfo, HttpStatus.BAD_REQUEST);
	}

}
