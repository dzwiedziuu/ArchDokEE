package com.aniedzwiedz.dokarchee.common.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface EditField
{
	String label();

	int order();

	boolean editable() default true;

	boolean autofiled() default true;
}
