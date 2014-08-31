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
	public void errorInterceptorAbstract(JoinPoint jp, Exception e) throws Throwable
	{
		errorInterceptor(jp, e);
	}

	private void errorInterceptor(JoinPoint jp, Exception e) throws Throwable
	{
		e.printStackTrace();
		throw new Error("Blad bazy danych!");
	}
}
