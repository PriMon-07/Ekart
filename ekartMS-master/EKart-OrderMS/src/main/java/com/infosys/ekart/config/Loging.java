package com.infosys.ekart.config;

import java.util.Arrays;

//import org.apache.logging.log4j.LogManager;
//import org.apache.logging.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;



@Configuration
@Aspect
public class Loging {




	@Before("execution(* com.infosys.ekart.controller.*.*(..)) || execution(* com.infosys.ekart.service.*.*(..))")
	public void before(JoinPoint joinPoint) {
		
		Logger logger = LoggerFactory.getLogger(joinPoint.getTarget().getClass());
		logger.debug("Before Executing {}", joinPoint.getSignature().toShortString());
	}

	@After(value = "execution(* com.infosys.ekart.controller.*.*(..)) || execution(* com.infosys.ekart.service.*.*(..))")
	public void after(JoinPoint joinPoint) {
		Logger logger = LoggerFactory.getLogger(joinPoint.getTarget().getClass());
		logger.debug("After Executing {}", joinPoint.getSignature().toShortString());
	}

	@AfterThrowing(pointcut = "execution(* com.infosys.ekart.service.*.*(..))", throwing = "e")
	public void myAfterThrowing(JoinPoint joinPoint, Throwable e) {
		Logger logger = LoggerFactory.getLogger(joinPoint.getTarget().getClass());
		logger.error("Error occured in {} with argumrnts {} and the exception is {} with stack trace \n",
				new Object[] { joinPoint.getSignature().toShortString(), Arrays.toString(joinPoint.getArgs()),
						e.getMessage(), e });


	}


}
