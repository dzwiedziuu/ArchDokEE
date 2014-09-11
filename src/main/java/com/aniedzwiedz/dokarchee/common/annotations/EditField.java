package com.aniedzwiedz.dokarchee.common.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/*
 * adnotacja uzywana do definiowania wygladu w DefaultForm
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface EditField
{ /*
 * wartosc labelki w formularzu
 */
	String label();

	/*
	 * kolejnosc kolumn w tabeli, jezeli sie powtarza w oberebie klasy,
	 * wyswietlona zostanie tylko jedno pole
	 */
	int order();

	/*
	 * nie uzywany
	 */
	boolean editable() default true;

	/*
	 * nie uzywany
	 */
	boolean autofiled() default true;
}
