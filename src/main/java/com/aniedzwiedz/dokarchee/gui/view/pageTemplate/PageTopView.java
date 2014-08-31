package com.aniedzwiedz.dokarchee.gui.view.pageTemplate;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import ru.xpoft.vaadin.VaadinView;

import com.aniedzwiedz.dokarchee.data.model.BusinessContext;
import com.aniedzwiedz.dokarchee.gui.view.AbstractViewImpl;
import com.aniedzwiedz.dokarchee.gui.view.archObjects.ArchObjectListViewImpl;
import com.aniedzwiedz.dokarchee.gui.view.photos.PhotoListViewImpl;
import com.aniedzwiedz.dokarchee.gui.view.startPage.StartPageView;
import com.vaadin.server.ClassResource;
import com.vaadin.server.ExternalResource;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Image;
import com.vaadin.ui.Label;
import com.vaadin.ui.Link;
import com.vaadin.ui.VerticalLayout;

@Component(PageTopView.VIEW_NAME)
@Scope("prototype")
@VaadinView(PageTopView.VIEW_NAME)
public class PageTopView extends AbstractViewImpl
{
	public interface BusinessContextChangeButtonListener
	{
		void changeBusinessContext();
	}

	private ArrayList<BusinessContextChangeButtonListener> listeners = new ArrayList<>();

	public void addBusinessContextChangeButtonListener(BusinessContextChangeButtonListener listener)
	{
		listeners.add(listener);
	}

	static final String VIEW_NAME = "PageTopView";

	private static final List<PageLink> links = new ArrayList<>();
	private ChangeContextListener changeContextListener = new ChangeContextListener();

	private HorizontalLayout horizontalLayout;
	private Button changeContextButton;

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

		VerticalLayout verticalLayout = new VerticalLayout();
		Label titleLabel = new Label("DokArch");
		verticalLayout.addComponent(titleLabel);
		verticalLayout.setExpandRatio(titleLabel, 1);

		HorizontalLayout bcHorizontalLayout = new HorizontalLayout();
		BusinessContext currentBusinessContext = VaadinSession.getCurrent().getAttribute(BusinessContext.class);
		String curBC = currentBusinessContext == null ? "(nie wybrano opracowania)" : currentBusinessContext.getCity();
		Label bcLabel = new Label("Opracowanie: " + curBC);
		bcHorizontalLayout.addComponent(bcLabel);
		changeContextButton = new Button("Zmien opracowanie");
		changeContextButton.addClickListener(changeContextListener);
		bcHorizontalLayout.addComponent(changeContextButton);

		verticalLayout.addComponent(bcHorizontalLayout);
		verticalLayout.setExpandRatio(bcHorizontalLayout, 0);
		horizontalLayout.addComponent(verticalLayout);
		horizontalLayout.setExpandRatio(verticalLayout, 1);

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

	private class ChangeContextListener implements Button.ClickListener
	{

		@Override
		public void buttonClick(ClickEvent event)
		{
			if (event.getButton() != changeContextButton)
				return;
			for (BusinessContextChangeButtonListener listener : listeners)
				listener.changeBusinessContext();
		}
	}
}
