package customer.aspect;

import java.util.logging.Logger;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class CRMLoggingAspect {

	// set logger for matching prints
	private Logger logger = Logger.getLogger(getClass().getName());
	
	// set pointcuts for packages
	
	@Pointcut("execution(* customer.controller.*.*(..))")
	private void forControllerPackage() {}
	
	@Pointcut("execution(* customer.service.*.*(..))")
	private void forServicePackage() {}
	
	@Pointcut("execution(* customer.dao.*.*(..))")
	private void forDaoPackage() {}
	
	@Pointcut("forControllerPackage() || forServicePackage() || forDaoPackage()")
	private void forAppFlow() {}
	
	// add @Before advice
	@Before("forAppFlow()")
	public void before(JoinPoint theJoinPoint) {
		
		String method = theJoinPoint.getSignature().toShortString();
		logger.info("@Before: calling method: " + method);
		
		
		Object[] args = theJoinPoint.getArgs();
		for (Object arg : args) {
			logger.info("@Before: Argument: " + arg);
		}
	}
	
	
	// add @AfterReturning advice
	@AfterReturning(
			pointcut="forAppFlow()",
			returning="result"
			)
	public void afterReturning(JoinPoint theJoinPoint, Object result) {
		String method = theJoinPoint.getSignature().toShortString();
		logger.info("@AfterReturning: Method: " + method);
				
		logger.info("@AfterReturning: Result: " + result);
	
	}
}
