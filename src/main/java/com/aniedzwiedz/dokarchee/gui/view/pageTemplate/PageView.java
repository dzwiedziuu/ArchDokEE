package com.aniedzwiedz.dokarchee.gui.view.pageTemplate;

import com.aniedzwiedz.dokarchee.gui.view.AbstractViewImpl;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.Page;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

/*
 * klasa opakowuj�ca wy�wietlany widok i g�rn� belke strony
 */
public class PageView extends AbstractViewImpl
{
	private PageTopView pageTopView;

	private AbstractViewImpl contentView;

	public PageView(AbstractViewImpl contentView, PageTopView pageTopView)
	{
		this.contentView = contentView;
		this.pageTopView = pageTopView;
	}

	@Override
	public void enter(ViewChangeEvent event)
	{
		contentView.enter(event);
		pageTopView.setCurrentURIFragment(Page.getCurrent().getUriFragment());
		VerticalLayout verticalLayout = new VerticalLayout();
		verticalLayout.addComponent(pageTopView);
		verticalLayout.setExpandRatio(pageTopView, 0);
		Label titleLabel = new Label(contentView.getTitle());
		titleLabel.setStyleName("titleLabel");
		verticalLayout.addComponent(titleLabel);
		verticalLayout.setExpandRatio(titleLabel, 0);
		verticalLayout.addComponent(contentView);
		verticalLayout.setExpandRatio(contentView, 1);
		// verticalLayout.setSizeFull();
		// setSizeFull();
		setContent(verticalLayout);
	}

	@Override
	public String getViewName()
	{
		return contentView.getViewName();
	}

	@Override
	public String getTitle()
	{
		return "";
	}
}
