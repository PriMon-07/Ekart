package com.infosys.ekart.wishlist.utility;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import com.infosys.ekart.wishlist.exception.AccountServiceException;

@Component
@Aspect
public class LoggingAspect {

	@AfterThrowing(pointcut = "execution(* com.infosys.ekart.wishlist.repository.*.*(..))", throwing = "exception")
	public void logExceptionFromRepository(Exception exception) throws Exception {
		log(exception);
	}

	@AfterThrowing(pointcut = "execution(* com.infosys.ekart.wishlist.service.*.*(..))", throwing = "exception")
	public void logExceptionFromService(Exception exception) throws Exception {
		if (exception instanceof AccountServiceException) {
			log(exception);
		}
	}

	private void log(Exception exception) {
		Logger logger = LogManager.getLogger(this.getClass());
		logger.error(exception.getMessage(), exception);
	}
}
