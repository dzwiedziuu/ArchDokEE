package com.aniedzwiedz.dokarchee;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import ru.xpoft.vaadin.DiscoveryNavigator;

import com.aniedzwiedz.dokarchee.gui.view.photos.PhotoListViewImpl;
import com.aniedzwiedz.dokarchee.logic.controller.ApplicationController;
import com.vaadin.navigator.View;
import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.UI;

@Component
@Scope("prototype")
@SuppressWarnings("serial")
public class ApplicationUI extends UI
{
	@Autowired
	private ApplicationController applicationController;

	@Autowired
	private PhotoListViewImpl userList;

	@Override
	protected void init(VaadinRequest request)
	{
		setSizeFull();
		new MyNavigator(this, userList);
	}

	// class necessary to avoid entering the initial view twice
	private class MyNavigator extends DiscoveryNavigator
	{
		// startView has to be stateless
		public MyNavigator(UI ui, View startView)
		{
			super(ui, ui);
			super.addView("", startView);
		}
	}
}
