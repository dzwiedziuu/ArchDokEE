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

import com.aniedzwiedz.dokarchee.gui.view.AbstractView;
import com.aniedzwiedz.dokarchee.logic.presenter.AbstractPresenter;

@Component
@Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class SessionController
{
	private Map<String, List<AbstractPresenter>> presenters = new HashMap<>();

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

	public void registerView(AbstractView abstractView)
	{
		String viewName = abstractView.getViewName();
		List<AbstractPresenter> list = presenters.get(viewName);
		if (list == null)
			return;
		for (AbstractPresenter abstractPresenter : list)
			abstractPresenter.setView(abstractView);
	}
}
