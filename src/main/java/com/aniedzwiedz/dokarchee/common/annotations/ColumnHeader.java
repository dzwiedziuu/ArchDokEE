package com.aniedzwiedz.dokarchee.common.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface ColumnHeader
{
	String value();

	int order();

	Class<?> genericType() default Object.class;
}
