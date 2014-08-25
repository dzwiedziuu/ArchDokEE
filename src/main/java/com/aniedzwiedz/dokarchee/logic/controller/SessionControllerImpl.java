package com.aniedzwiedz.dokarchee.logic.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import ru.xpoft.vaadin.SpringApplicationContext;

import com.aniedzwiedz.dokarchee.logic.action.Action;
import com.aniedzwiedz.dokarchee.logic.presenter.AbstractPresenter;

@Component
@Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class SessionControllerImpl implements SessionController
{
	private Map<String, List<AbstractPresenter>> presenters = new HashMap<>();

	public void processAction(Action action)
	{
		List<AbstractPresenter> presenterList = presenters.get(action.getSenderViewName());
		if (presenterList != null)
			for (AbstractPresenter presenter : presenterList)
			{
				action.setCurrentPresenter(presenter);
				presenter.takeAction(action);
			}
	}

	@PostConstruct
	public void initSessionControllerImpl()
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
		String viewName = abstractPresenter.getAbstractView().getViewName();
		List<AbstractPresenter> list = presenters.get(viewName);
		if (list == null)
		{
			list = new ArrayList<>();
			presenters.put(viewName, list);
		}
		list.add(abstractPresenter);
	}
}