package com.infosys.ekart.account.utility;

import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.springframework.stereotype.Component;

import com.infosys.ekart.account.exception.EmailAreadyUsedException;

@Component
@Aspect
public class LoggingAspect {

	@AfterThrowing(pointcut = "execution(* com.infosys.ekart.account.repository.*.*(..))", throwing = "exception")
	public void logExceptionFromRepository(Exception exception) throws Exception {
		log(exception);
	}

	@AfterThrowing(pointcut = "execution(* com.infosys.ekart.account.serviceImpl.*.*(..))", throwing = "exception")
	public void logExceptionFromService(Exception exception) throws Exception {
		if (exception instanceof EmailAreadyUsedException) {
			log(exception);
		}
	}

	private void log(Exception exception) {
		Logger logger = LogManager.getLogger(this.getClass());
		logger.error(exception.getMessage(), exception);
	}
}