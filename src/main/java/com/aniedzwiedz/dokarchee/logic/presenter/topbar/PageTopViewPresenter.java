package com.aniedzwiedz.dokarchee.logic.presenter.topbar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.aniedzwiedz.dokarchee.data.model.BusinessContext;
import com.aniedzwiedz.dokarchee.gui.view.AbstractView;
import com.aniedzwiedz.dokarchee.gui.view.AbstractView.ViewEvent;
import com.aniedzwiedz.dokarchee.gui.view.pageTemplate.PageTopView;
import com.aniedzwiedz.dokarchee.gui.view.pageTemplate.PageTopView.BusinessContextChangeButtonListener;
import com.aniedzwiedz.dokarchee.logic.presenter.AbstractPresenter;
import com.aniedzwiedz.dokarchee.logic.presenter.SelectListener;
import com.aniedzwiedz.dokarchee.logic.presenter.misc.businessContext.BusinessContextListPresenter;
import com.vaadin.server.Page;
import com.vaadin.server.VaadinSession;

@Component
@Scope("session")
public class PageTopViewPresenter extends AbstractPresenter implements SelectListener, BusinessContextChangeButtonListener
{
	@Autowired
	private PageTopView pageTopBar;

	private BusinessContextListPresenter bussiBusinessContextListPresenter;

	@Autowired
	public void setBussiBusinessContextListPresenter(BusinessContextListPresenter bussiBusinessContextListPresenter)
	{
		this.bussiBusinessContextListPresenter = bussiBusinessContextListPresenter;
		this.bussiBusinessContextListPresenter.setPresentsInWindow(true);
		this.bussiBusinessContextListPresenter.setSelectable(true);
	}

	@Override
	public void refreshView(AbstractView abstractView)
	{
		BusinessContext currentBusinessContext = VaadinSession.getCurrent().getAttribute(BusinessContext.class);
		if (currentBusinessContext == null)
			goToNextView(bussiBusinessContextListPresenter);
		else
			pageTopBar.setCurrentURIFragment(Page.getCurrent().getUriFragment());
	}

	@Override
	public void focusAfterClosedWindow(ViewEvent viewEvent)
	{
	}

	@Override
	public AbstractView getAbstractView()
	{
		return pageTopBar;
	}

	@Override
	public void setView(AbstractView abstractView)
	{
		this.pageTopBar = (PageTopView) abstractView;
		this.pageTopBar.addViewListener(this);
		this.pageTopBar.addBusinessContextChangeButtonListener(this);
	}

	@Override
	public void returnValue(AbstractPresenter abstractPresenter, Object value)
	{
		BusinessContext businessContext = (BusinessContext) value;
		VaadinSession.getCurrent().setAttribute(BusinessContext.class, businessContext);
		pageTopBar.refresh();
	}

	@Override
	public void changeBusinessContext()
	{
		goToNextView(bussiBusinessContextListPresenter);
	}
}
