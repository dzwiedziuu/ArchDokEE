package com.aniedzwiedz.dokarchee.gui.ui.errors;

import java.util.HashMap;
import java.util.Map;

import org.hibernate.exception.ConstraintViolationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DBExceptionResolver
{
	private static Logger logger = LoggerFactory.getLogger(DBExceptionResolver.class);

	private static final Map<String, String> uniqueContent = new HashMap<>();
	static
	{
		uniqueContent.put("unique_nr_object", "Podany numer obiektu juz istnieje w bazie.");
		uniqueContent.put("unique_nr_ar", "Podany numer aru juz istnieje w bazie.");
		uniqueContent.put("chronology_name_UNIQUE", "Podana nazwa chronologii obiektu juz istnieje w bazie.");
		uniqueContent.put("culture_name_UNIQUE", "Podany kultura juz istnieje w bazie.");
		uniqueContent.put("figure_spec_object_id_unique", "Podany obiekt zostal juz wprowadzony w specyfikacji tego rysunku.");
		uniqueContent.put("picture_subject_name_UNIQUE", "Podany temat rysunku juz istnieje w bazie.");
		uniqueContent.put("unique_picture_nr", "Podany numer rysunku juz istnieje w bazie.");
		uniqueContent.put("function_name_UNIQUE", "Podana funkcja juz istnieje w bazie.");
		uniqueContent.put("unique_nr_mass_relic", "Podany numer zabytku masowego juz istnieje w bazie.");
		uniqueContent.put("photo_subject_name_UNIQUE", "Podany temat fotografii juz istnieje w bazie.");
		uniqueContent.put("UNIQUE_PHOTO_NR", "Podany numer fotografii juz istnieje w bazie.");
		uniqueContent.put("profile_layer_name_UNIQUE", "Podana warstwa profilu juz istnieje w bazie.");
		uniqueContent.put("profile_shape_name_UNIQUE", "Podany ksztalt profilu juz istnieje w bazie.");
		uniqueContent.put("projection_layer_name_UNIQUE", "Podana warstwa rzutu juz istnieje w bazie.");
		uniqueContent.put("projection_shape_name_UNIQUE", "Podany zarys rzutu juz istnieje w bazie.");
		uniqueContent.put("relic_group_name_UNIQUE", "Podana grupa zabytkow juz istnieje w bazie.");
		uniqueContent.put("relic_layer_name_UNIQUE", "Podana warstwa obiektu w ktorej znaleziono zabytek juz istnieje w bazie.");
		uniqueContent.put("relic_type_name_UNIQUE", "Podany rodzaj zabytku juz istnieje w bazie.");
		uniqueContent.put("unique_nr_separated_relics", "Podany numer zabytku wydzielonego juz istnieje w bazie.");
		uniqueContent.put("soil_name_UNIQUE", "Podany calec juz istnieje w bazie.");
		uniqueContent.put("user_login_UNIQUE", "Podany login juz istnieje w bazie.");
	}

	public String getErrorContent(Throwable e)
	{
		logger.warn("", e);
		if (e instanceof ConstraintViolationException)
		{
			ConstraintViolationException ex = (ConstraintViolationException) e;
			String idxName = ex.getSQLException().getMessage().split("'")[3];
			String message = uniqueContent.get(idxName);
			return message == null ? idxName : message;
		}
		return "Wystapil blad w bazie danych. Zamknij okno edycji i sprobuj otworzyc je ponownie.";
	}
}
