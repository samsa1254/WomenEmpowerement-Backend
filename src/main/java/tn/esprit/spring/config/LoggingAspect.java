package tn.esprit.spring.config;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;
import tn.esprit.spring.security.UserSecurity;
@Component
@Aspect
@Slf4j
public class LoggingAspect {

	

	@AfterReturning("execution(* tn.esprit.spring.service.*.*(..))")
	public void logMethodExit1(JoinPoint joinPoint) {
		String name = joinPoint.getSignature().getName();
		log.info("Out of method without errors : " + name );
	}



	/*@After("execution(* tn.esprit.spring.service.*.*(..))")
	public void logMethodExit(JoinPoint joinPoint) {
		String name = joinPoint.getSignature().getName();
		log.info("Out of method : " + name );*/
		
	}
	/*private long t1, t2;
	@Before("execution(* tn.esprit.spring.service.*.*(..))") //PointCut
	public void avant(JoinPoint thisJoinPoint) {
		t1 = System.currentTimeMillis();
		log.info("In the method"+ thisJoinPoint.getSignature().getName());
	}

	@After("execution(* tn.esprit.spring.service.*.*(..))")
	public void apres(JoinPoint thisJoinPoint) {
		t2 = System.currentTimeMillis();
		log.info("Exuction Time of methode " + thisJoinPoint.getSignature() + " is  " + (t2 - t1) + " ms");
		log.info("Out of the method (After)"+ thisJoinPoint.getSignature().getName());
}*/
