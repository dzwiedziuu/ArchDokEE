package com.aniedzwiedz.dokarchee.common.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/*
 * definiuje tresc wyswietlana w polach slownikowych (foreignField) i w wyswietlana w komorce tabeli (CRUDTable)
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface ForeignFieldLabel
{
	String pattern();
}
