package com.aniedzwiedz.dokarchee.logic.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import com.aniedzwiedz.dokarchee.gui.view.AbstractView;
import com.aniedzwiedz.dokarchee.logic.presenter.AbstractPresenter;

/*
 * klasa bêd¹ca kontrolerem sesji, paruje widoki z prezenterami
 */
@Component
@Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class SessionController
{
	private Map<String, List<Class<? extends AbstractPresenter>>> presenters = new HashMap<>();

	@Autowired
	private ApplicationContext applicationContext;

	@PostConstruct
	public void initSessionControllerImpl()
	{
		// register AbstractPresenters to ApplicationController
		for (String beanName : applicationContext.getBeanDefinitionNames())
		{
			Class<?> classObj = applicationContext.getType(beanName);
			for (Class<?> c = classObj; c != null; c = c.getSuperclass())
				if (c == AbstractPresenter.class)
				{
					AbstractPresenter abstractPresenter = (AbstractPresenter) applicationContext.getBean(classObj);
					registerPresenter(abstractPresenter);
				}
		}
	}

	private void registerPresenter(AbstractPresenter abstractPresenter)
	{
		String viewName = abstractPresenter.getAbstractView().getViewName();
		List<Class<? extends AbstractPresenter>> list = presenters.get(viewName);
		if (list == null)
		{
			list = new ArrayList<>();
			presenters.put(viewName, list);
		}
		list.add(abstractPresenter.getClass());
	}

	/*
	 * paruje widok z prezenterem
	 */
	public void registerView(AbstractView abstractView)
	{
		String viewName = abstractView.getViewName();
		List<Class<? extends AbstractPresenter>> list = presenters.get(viewName);
		if (list == null)
			return;
		for (Class<? extends AbstractPresenter> abstractPresenterClass : list)
		{
			AbstractPresenter abstractPresenter = applicationContext.getBean(abstractPresenterClass);
			abstractPresenter.setView(abstractView);
		}

	}
}
