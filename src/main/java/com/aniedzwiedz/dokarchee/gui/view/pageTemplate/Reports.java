package com.aniedzwiedz.dokarchee.gui.view.pageTemplate;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import ru.xpoft.vaadin.VaadinView;

import com.aniedzwiedz.dokarchee.gui.view.AbstractViewImpl;
import com.aniedzwiedz.dokarchee.gui.view.reports.PhotoListReportView;
import com.vaadin.server.ExternalResource;
import com.vaadin.ui.AbstractComponent;
import com.vaadin.ui.Link;
import com.vaadin.ui.VerticalLayout;

@Component(Reports.VIEW_NAME)
@Scope("prototype")
@VaadinView(Reports.VIEW_NAME)
public class Reports extends AbstractViewImpl
{
	public static final String VIEW_NAME = "Reports";

	private static final List<PageLink> links = new ArrayList<>();

	static
	{
		links.add(new PageLink("Inwentarz zabytków", PhotoListReportView.VIEW_NAME));
	}

	public Reports()
	{
		VerticalLayout layout = new VerticalLayout();
		layout.setStyleName("page-content");
		setSizeFull();
		for (PageLink link : links)
			layout.addComponent(generateLink(link));
		setContent(layout);
	}

	private AbstractComponent generateLink(PageLink pageLink)
	{
		return new Link(pageLink.getLabel(), new ExternalResource("#!" + pageLink.getLinkContent()));
	}

	@Override
	public String getViewName()
	{
		return VIEW_NAME;
	}

	@Override
	public String getTitle()
	{
		return "Raporty";
	}
}
