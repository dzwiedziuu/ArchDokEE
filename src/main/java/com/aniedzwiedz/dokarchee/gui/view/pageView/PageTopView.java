package com.aniedzwiedz.dokarchee.gui.view.pageView;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import ru.xpoft.vaadin.VaadinView;

import com.aniedzwiedz.dokarchee.gui.view.AbstractViewImpl;
import com.aniedzwiedz.dokarchee.gui.view.archObjects.ArchObjectListViewImpl;
import com.aniedzwiedz.dokarchee.gui.view.photos.PhotoListViewImpl;
import com.vaadin.server.ClassResource;
import com.vaadin.server.ExternalResource;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Image;
import com.vaadin.ui.Label;
import com.vaadin.ui.Link;

@Component(PageTopView.VIEW_NAME)
@Scope("prototype")
@VaadinView(PageTopView.VIEW_NAME)
public class PageTopView extends AbstractViewImpl
{
	static final String VIEW_NAME = "PageTopView";

	private static final List<PageLink> links = new ArrayList<>();

	private HorizontalLayout horizontalLayout;
	static
	{
		links.add(new PageLink("START", StartPageView.VIEW_NAME));
		links.add(new PageLink("Obiekty", ArchObjectListViewImpl.VIEW_NAME));
		links.add(new PageLink("Zdjecia", PhotoListViewImpl.VIEW_NAME));
	}

	public PageTopView()
	{
		setSizeFull();
		horizontalLayout = new HorizontalLayout();
		horizontalLayout.setSizeFull();
		setContent(horizontalLayout);
	}

	@Override
	public String getViewName()
	{
		return PageTopView.VIEW_NAME;
	}

	public void setCurrentURIFragment(String uriFragment)
	{
		if (uriFragment != null)
			uriFragment = uriFragment.substring(1); // uriFragment starts with !
		horizontalLayout.removeAllComponents();
		Label titleLabel = new Label("DokArch");
		horizontalLayout.addComponent(titleLabel);
		horizontalLayout.setExpandRatio(titleLabel, 1);
		GridLayout linkGrid = new GridLayout(links.size(), 2);
		Image arrow = new Image(null, new ClassResource("/images/green-arrow.png"));

		int currentLinkIdx = 0;
		for (int i = 0; i < links.size(); i++)
			if (links.get(i).getLinkContent().equals(uriFragment))
				currentLinkIdx = i;
		linkGrid.addComponent(arrow, currentLinkIdx, 0);

		for (int i = 0; i < links.size(); i++)
			linkGrid.addComponent(new Link(links.get(i).getLabel(), new ExternalResource("#!" + links.get(i).getLinkContent())), i, 1);
		horizontalLayout.addComponent(linkGrid);
		horizontalLayout.setExpandRatio(linkGrid, 0);
	}
}
