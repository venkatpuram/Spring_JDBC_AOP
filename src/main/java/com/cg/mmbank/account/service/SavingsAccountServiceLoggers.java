package com.cg.mmbank.account.service;

import java.util.logging.Logger;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;



@Aspect
@Component
public class SavingsAccountServiceLoggers {

	Logger logger = Logger.getLogger(SavingsAccountServiceLoggers.class.getName());
	
	/*
	 * @Before("execution(* com.cg.mmbank.account.service.SavingsAccountServiceImpl.withdraw(..))"
	 * ) public void log1() { logger.info("Before - Doing withdraw"); }
	 * 
	 * @After("execution(* com.cg.mmbank.account.service.SavingsAccountServiceImpl.withdraw(..))"
	 * ) public void log2() { logger.info("After - withdraw successful"); }
	 * 
	 * @AfterThrowing(pointcut=("execution(* com.cg.mmbank.account.service.*.*(..))"
	 * ),throwing="exe") public void log3(Exception exe) {
	 * logger.info("Exception is "+exe.toString()); }
	 */
	/*
	 * @Before("execution(* com.cg.mmbank.account.service.SavingsAccountServiceImpl.deposit(..))"
	 * ) public void log4() { logger.info("Before - Doing deposit"); }
	 * 
	 * @After("execution(* com.cg.mmbank.account.service.SavingsAccountServiceImpl.deposit(..))"
	 * ) public void log5() { logger.info("After - deposit successful"); }
	 * 
	 * @AfterThrowing(pointcut=("execution(* com.cg.mmbank.account.service.*.*(..))"
	 * ),throwing="exe") public void log6(Exception exe) {
	 * logger.info("Exception is "+exe.toString()); }
	 */
}
