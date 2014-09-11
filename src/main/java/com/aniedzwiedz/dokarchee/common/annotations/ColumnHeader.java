package com.aniedzwiedz.dokarchee.common.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/*
 * adnotacja uzywana do definiowania wygladu w CRUDTable
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface ColumnHeader
{
	/*
	 * wartosc labelki w tabeli
	 */
	String value();

	/*
	 * kolejnosc kolumn w tabeli, jezeli sie powtarza w oberebie klasy,
	 * wyswietlona zostanie tylko jedno pole
	 */
	int order();

	/*
	 * typ generyczny obiektu - kolekcji (Set) do ktorego sie odwoluje ta
	 * adnotacja
	 */
	Class<?> genericType() default Object.class;
}
