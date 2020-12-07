package com.infosys.ekart.sellerTwo.utility;

import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
@Component
@Aspect
public class LoggingAspect {

	@AfterThrowing(pointcut = "execution(* com.infosys.ekart.cart.sellerTwo.*.*(..))", throwing = "exception")
	public void logExceptionFromRepository(Exception exception) throws Exception {
		log(exception);
	}

	@AfterThrowing(pointcut = "execution(* com.infosys.ekart.cart.sellerTwo.*.*(..))", throwing = "exception")
	public void logExceptionFromService(Exception exception) throws Exception {
		//if (exception instanceof ServiceException) {
			log(exception);
		//}
	}

	private void log(Exception exception) {
		Logger logger = LogManager.getLogger(this.getClass());
		logger.error(exception.getMessage(), exception);
	}
}
