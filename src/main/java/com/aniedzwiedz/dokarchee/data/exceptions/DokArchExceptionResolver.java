package com.aniedzwiedz.dokarchee.data.exceptions;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/*
 * klasa wylapowujaca wyjatki i 
 */
@Aspect
@Component
public class DokArchExceptionResolver
{
	private static final Logger logger = LoggerFactory.getLogger(DokArchExceptionResolver.class);

	@AfterThrowing(pointcut = "execution(public * com.aniedzwiedz.dokarchee.data.service.AbstractService+.*(..))", throwing = "e")
	public void errorInterceptorAbstract(JoinPoint jp, Throwable e) throws Throwable
	{
		errorInterceptor(jp, e);
	}

	@AfterThrowing(pointcut = "execution(public * com.aniedzwiedz.dokarchee.data.service.impl.*.*(..))", throwing = "e")
	public void errorInterceptorAbstract2(JoinPoint jp, Throwable e) throws Throwable
	{
		errorInterceptor(jp, e);
	}

	private void errorInterceptor(JoinPoint jp, Throwable e) throws Throwable
	{
		logger.info("", e);
	}
}
