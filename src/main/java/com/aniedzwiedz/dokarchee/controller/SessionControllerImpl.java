package com.aniedzwiedz.dokarchee.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import ru.xpoft.vaadin.SpringApplicationContext;

import com.aniedzwiedz.dokarchee.action.Action;
import com.aniedzwiedz.dokarchee.presenter.AbstractPresenter;

@Component
@Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class SessionControllerImpl implements SessionController
{
	private Map<String, List<AbstractPresenter>> presenters = new HashMap<>();

	public void processAction(Action action)
	{
		List<AbstractPresenter> presenterList = presenters.get(action.getSender());
		if (presenterList != null)
			for (AbstractPresenter presenter : presenterList)
			{
				action.setAbstractPresenter(presenter);
				presenter.takeAction(action);
			}
	}

	public SessionControllerImpl()
	{
		// add AbstractPresenters to ApplicationController
		ApplicationContext ac = SpringApplicationContext.getApplicationContext();
		for (String beanName : ac.getBeanDefinitionNames())
		{
			Class<?> classObj = ac.getType(beanName);
			for (Class<?> c = classObj; c != null; c = c.getSuperclass())
				if (c == AbstractPresenter.class)
				{
					AbstractPresenter abstractPresenter = (AbstractPresenter) ac.getBean(classObj);
					registerPresenter(abstractPresenter);
				}
		}
	}

	private void registerPresenter(AbstractPresenter abstractPresenter)
	{
		String viewName = abstractPresenter.getNamedView().getViewName();
		List<AbstractPresenter> list = presenters.get(viewName);
		if (list == null)
		{
			list = new ArrayList<>();
			presenters.put(viewName, list);
		}
		list.add(abstractPresenter);
	}
}
