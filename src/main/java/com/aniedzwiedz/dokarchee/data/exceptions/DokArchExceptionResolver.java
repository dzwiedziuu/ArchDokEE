package com.aniedzwiedz.dokarchee.data.exceptions;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class DokArchExceptionResolver
{
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
		// e.printStackTrace();
	}
}
