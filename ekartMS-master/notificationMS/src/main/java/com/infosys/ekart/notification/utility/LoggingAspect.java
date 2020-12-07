package com.infosys.ekart.notification.utility;

import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.springframework.stereotype.Component;

import com.infosys.ekart.notification.exception.ErrorOnServerException;

@Component
@Aspect
public class LoggingAspect {

	@AfterThrowing(pointcut = "execution(* com.infosys.ekart.notification.repository.*.*(..))", throwing = "exception")
	public void logExceptionFromRepository(Exception exception) throws Exception {
		log(exception);
	}

	@AfterThrowing(pointcut = "execution(* com.infosys.ekart.notification.serviceimpl.*.*(..))", throwing = "exception")
	public void logExceptionFromService(Exception exception) throws Exception {
		if (!(exception instanceof ErrorOnServerException)) {
			log(exception);
		}
	}

	private void log(Exception exception) {
		Logger logger = LogManager.getLogger(this.getClass());
		logger.error(exception.getMessage(), exception);
	}
}