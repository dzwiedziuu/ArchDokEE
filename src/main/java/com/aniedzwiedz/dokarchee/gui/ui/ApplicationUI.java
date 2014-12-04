package com.aniedzwiedz.dokarchee.gui.ui;

import java.util.LinkedList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import ru.xpoft.vaadin.DiscoveryNavigator;

import com.aniedzwiedz.dokarchee.gui.ui.errors.ArchDokErrorHandler;
import com.aniedzwiedz.dokarchee.gui.view.AbstractView;
import com.aniedzwiedz.dokarchee.gui.view.AbstractViewImpl;
import com.aniedzwiedz.dokarchee.gui.view.pageTemplate.PageTopView;
import com.aniedzwiedz.dokarchee.gui.view.pageTemplate.PageView;
import com.aniedzwiedz.dokarchee.gui.view.pageTemplate.StartPageView;
import com.aniedzwiedz.dokarchee.gui.window.AbstractWindow;
import com.aniedzwiedz.dokarchee.gui.window.SubWindow;
import com.aniedzwiedz.dokarchee.logic.controller.SessionController;
import com.vaadin.annotations.Theme;
import com.vaadin.navigator.View;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.UI;
import com.vaadin.ui.Window;
import com.vaadin.ui.Window.CloseEvent;

@Theme("archdokee")
@Component
@Scope("prototype")
public class ApplicationUI extends UI implements GuiController
{
	// class necessary to avoid entering the initial view twice
	private class MyNavigator extends DiscoveryNavigator
	{
		// startView has to be stateless
		public MyNavigator(UI ui, View startView)
		{
			super(ui, ui);
			super.addView("", startView);
		}

		protected void navigateTo(View view, String viewName, String parameters)
		{
			AbstractView abstractView = (AbstractView) view;
			lastView = currentView;
			currentView = abstractView;
			sessionController.registerView(abstractView);
			abstractView.setGuiController(ApplicationUI.this);
			abstractView.refresh();
			PageTopView pageTopView = new PageTopView();
			sessionController.registerView(pageTopView);
			pageTopView.setGuiController(ApplicationUI.this);
			pageTopView.refresh();
			PageView pageView = new PageView((AbstractViewImpl) abstractView, pageTopView);
			super.navigateTo(pageView, viewName, parameters);
		}
	}

	@Autowired
	private SessionController sessionController;

	@Autowired
	private StartPageView startView;

	private AbstractView lastView;
	private AbstractView currentView;

	private static final ArchDokErrorHandler archDokErrorHandler = new ArchDokErrorHandler();

	@Override
	protected void init(VaadinRequest request)
	{
		setErrorHandler(archDokErrorHandler);
		VaadinSession.getCurrent().setErrorHandler(archDokErrorHandler);
		setSizeFull();
		currentView = startView;
		startView.setGuiController(this);
		new MyNavigator(this, startView);
	}

	@Override
	public void switchViewTo(AbstractView view)
	{
		getNavigator().navigateTo(view.getViewName());
	}

	private LinkedList<SubWindow> openedWindows = new LinkedList<>();

	private CloseWindowListener closeWindowListener = new CloseWindowListener();

	@Autowired
	private ApplicationContext applicationContext;

	@Override
	public void openInNewWindow(AbstractView view)
	{
		// creating new bean - like in navigator
		view = applicationContext.getBean(view.getClass());
		sessionController.registerView(view);
		view.setGuiController(this);
		view.refresh();
		SubWindow subWindow = new SubWindow(view);
		subWindow.setHeight(300, Unit.PIXELS);
		subWindow.setWidth(500, Unit.PIXELS);
		subWindow.addCloseListener(closeWindowListener);
		openedWindows.add(subWindow);
		addWindow(subWindow);
	}

	@Override
	public void closeWindowOrGoPrevView()
	{
		if (openedWindows.peekLast() == null)
		{
			switchViewTo(lastView);
			return;
		}
		AbstractWindow lastOpenedWindow = openedWindows.pollLast();
		if (lastOpenedWindow != null)
			removeWindow((Window) lastOpenedWindow);
		refreshLastFreezedView();

	}

	private void refreshLastFreezedView()
	{
		SubWindow lastWindow = openedWindows.peekLast();
		if (lastWindow == null)
			currentView.refresh();
		else
			lastWindow.getView().refresh();
	}

	private class CloseWindowListener implements Window.CloseListener
	{
		@Override
		public void windowClose(CloseEvent e)
		{
			SubWindow lastWindow = openedWindows.peekLast();
			if (lastWindow != e.getWindow())
				return;
			openedWindows.pollLast();
			lastWindow = openedWindows.peekLast();
			AbstractView newTopView = null;
			if (lastWindow != null)
				newTopView = lastWindow.getView();
			else
				newTopView = currentView;
			AbstractView closedView = ((SubWindow) e.getWindow()).getView();
			newTopView.takeFocusAfterClosedWindow(closedView);
			refreshLastFreezedView();
		}
	}
}
