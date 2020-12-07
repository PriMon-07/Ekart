package com.infosys.ekart.utility;

import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.infosys.ekart.exception.ErrorOnServerException;

@Component
@Aspect
public class LoggingAspect {

	@AfterThrowing(pointcut = "execution(* com.infosys.ekart.repository.*.*(..))", throwing = "exception")
	public void logExceptionFromRepository(Exception exception) throws Exception {
		log(exception);
	}

	@AfterThrowing(pointcut = "execution(* com.infosys.ekart.service.*.*(..))", throwing = "exception")
	public void logExceptionFromService(Exception exception) throws Exception {
		if (!(exception instanceof ErrorOnServerException)) {
			log(exception);
		}
	}

	private void log(Exception exception) {
		Logger logger = LoggerFactory.getLogger(this.getClass());
		logger.error(exception.getMessage(), exception);
	}
}