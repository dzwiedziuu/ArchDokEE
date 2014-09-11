package com.aniedzwiedz.dokarchee.data.service;

import java.util.List;

import org.hibernate.criterion.Criterion;

/*
 * klasa uzywana przez prezenterow do pobierania danych i utrwalania danych w bazie danych
 */
public interface PojoService<T>
{
	/*
	 * zapisuje obiekt w bazie danych
	 */
	void add(T t);

	/*
	 * aktualizuje obiekt w bazie danych
	 */
	void update(T t);

	/*
	 * usuwa obiekt z bazy danych
	 */
	void remove(T t);

	/*
	 * zwraca obiekt w bazie danych po id obiektu w argumencie
	 */
	T find(T t);

	/*
	 * zwraca liste obiektow przy uzyciu kryterium w argumencie
	 */
	List<T> getAll(Class<T> clazz, Criterion criterion);
}
