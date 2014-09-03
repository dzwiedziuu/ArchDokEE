package com.aniedzwiedz.dokarchee.gui.view.pageTemplate;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import ru.xpoft.vaadin.VaadinView;

import com.aniedzwiedz.dokarchee.gui.view.AbstractViewImpl;
import com.aniedzwiedz.dokarchee.gui.view.objects.ArchObjectListViewImpl;
import com.aniedzwiedz.dokarchee.gui.view.photos.PhotoListViewImpl;
import com.vaadin.server.ExternalResource;
import com.vaadin.ui.AbstractComponent;
import com.vaadin.ui.Label;
import com.vaadin.ui.Link;
import com.vaadin.ui.VerticalLayout;

@Component(Dictionaries.VIEW_NAME)
@Scope("prototype")
@VaadinView(Dictionaries.VIEW_NAME)
public class Dictionaries extends AbstractViewImpl
{
	private static final List<PageLink> links = new ArrayList<>();

	static
	{
		links.add(new PageLink("START", StartPageView.VIEW_NAME));
		links.add(new PageLink("Obiekty", ArchObjectListViewImpl.VIEW_NAME));
		links.add(new PageLink("Zdjecia", PhotoListViewImpl.VIEW_NAME));
	}

	public Dictionaries()
	{
		VerticalLayout layout = new VerticalLayout();
		layout.setStyleName("page-content");
		setSizeFull();
		layout.addComponent(new Label("Slowniki:"));
		for (PageLink link : links)
			layout.addComponent(generateLink(link));
		setContent(layout);
	}

	private AbstractComponent generateLink(PageLink pageLink)
	{
		return new Link(pageLink.getLabel(), new ExternalResource("#!" + pageLink.getLinkContent()));
	}

	public static final String VIEW_NAME = "Dictionaries";

	@Override
	public String getViewName()
	{
		return VIEW_NAME;
	}
}
