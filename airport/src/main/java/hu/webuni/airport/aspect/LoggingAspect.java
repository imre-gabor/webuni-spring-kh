package hu.webuni.airport.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {

//	@Before("execution(* hu.webuni.airport.repository.*.*(..))")
//	public void logBefore(JoinPoint joinPoint) {
//		//System.out.println(String.format("Method %s called in class %s", joinPoint.getSignature(), joinPoint.getTarget().getClass().getName()));
////a generált proxy típusát írja ki 
//		System.out.println(String.format("Method %s called in type %s", 
//				joinPoint.getSignature(), 
//				joinPoint.getTarget().getClass().getInterfaces()[0]));
//	}

	
	@Pointcut("@annotation(hu.webuni.airport.aspect.LogCall) "
			+ "|| @within(hu.webuni.airport.aspect.LogCall)")
	public void annotationLogCall(){}
		
	@Before("hu.webuni.airport.aspect.LoggingAspect.annotationLogCall()")
	public void logBefore(JoinPoint joinPoint) {
		Class<? extends Object> clazz = joinPoint.getTarget().getClass();
		Class<?>[] interfaces = clazz.getInterfaces();
		String type = interfaces.length == 0 ? clazz.getName() : interfaces[0].toString(); 
		System.out.println(String.format("Method %s called in type %s", joinPoint.getSignature(), type));

	}

}
