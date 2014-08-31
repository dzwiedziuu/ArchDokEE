package com.aniedzwiedz.dokarchee.logic.session;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;

import com.aniedzwiedz.dokarchee.data.model.BusinessContext;
import com.vaadin.server.VaadinSession;

public class SessionUtils
{
	public static BusinessContext getCurrentBusinessContext()
	{
		return VaadinSession.getCurrent().getAttribute(BusinessContext.class);
	}

	public static Criterion getBusinessContextCriterion(String propertyName)
	{
		BusinessContext bc = getCurrentBusinessContext();
		return Restrictions.eq(propertyName, bc);
	}
}
