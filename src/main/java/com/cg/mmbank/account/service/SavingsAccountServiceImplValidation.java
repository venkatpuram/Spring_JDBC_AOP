package com.cg.mmbank.account.service;

import java.util.logging.Logger;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import com.cg.mmbank.account.SavingsAccount;
import com.cg.mmbank.exception.InsufficientFundsException;
import com.cg.mmbank.exception.InvalidInputException;

@Aspect
@Component
public class SavingsAccountServiceImplValidation {

	Logger logger = Logger.getLogger(SavingsAccountServiceImplValidation.class.getName());
	@Around("execution(* com.cg.mmbank.account.service.SavingsAccountServiceImpl.withdraw(..))")
	public void withdrawValidation(ProceedingJoinPoint pjp) throws Throwable {
		Object[] param = pjp.getArgs();
		double amount = (double) param[1];
		SavingsAccount balance = (SavingsAccount) param[0];
		double currentBalance = balance.getBankAccount().getAccountBalance();
		if (amount > 0 && amount <= currentBalance) {
			logger.info("before - doing withdraw");
			pjp.proceed();
			logger.info("after - withdraw successful");
		} else

			throw new InsufficientFundsException("insufficient funds");

	}

	
	
	@Around("execution(* com.cg.mmbank.account.service.SavingsAccountServiceImpl.deposit(..))")
	public void depositValidation(ProceedingJoinPoint pjp) throws Throwable {
		Object[] param = pjp.getArgs();
		double amount = (double) param[1];
		if (amount > 0) {
			logger.info("Before - Doing deposit");
			pjp.proceed();
			logger.info("After - deposit successful");
		} else
			throw new InvalidInputException("Invalid Input");
	}
	
	@AfterThrowing(pointcut=("execution(* com.cg.mmbank.account.service.*.*(..))"),throwing="exe")
	public void log(Exception exe)
	{
		logger.info("Exception is "+exe.toString());
	}
}
