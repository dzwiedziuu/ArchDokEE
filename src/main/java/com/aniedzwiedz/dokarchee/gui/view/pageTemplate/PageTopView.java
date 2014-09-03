package com.aniedzwiedz.dokarchee.gui.view.pageTemplate;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import ru.xpoft.vaadin.VaadinView;

import com.aniedzwiedz.dokarchee.common.utils.EntityLabelUtils;
import com.aniedzwiedz.dokarchee.data.model.BusinessContext;
import com.aniedzwiedz.dokarchee.gui.view.AbstractViewImpl;
import com.aniedzwiedz.dokarchee.gui.view.figures.FigureListViewImpl;
import com.aniedzwiedz.dokarchee.gui.view.objects.ArchObjectListViewImpl;
import com.aniedzwiedz.dokarchee.gui.view.photos.PhotoListViewImpl;
import com.vaadin.server.ClassResource;
import com.vaadin.server.ExternalResource;
import com.vaadin.server.Page;
import com.vaadin.server.VaadinService;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Image;
import com.vaadin.ui.Label;
import com.vaadin.ui.Link;
import com.vaadin.ui.UI;
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
		links.add(new PageLink("Rysunki", FigureListViewImpl.VIEW_NAME));
		links.add(new PageLink("Slowniki", Dictionaries.VIEW_NAME));
	}

	public PageTopView()
	{
		setSizeFull();
		horizontalLayout = new HorizontalLayout();
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
		titleLabel.setStyleName("main-label");
		verticalLayout.addComponent(titleLabel);
		verticalLayout.setExpandRatio(titleLabel, 1);
		Label titleLabel2 = new Label("Dokumentacja archeologiczna 2.0");
		verticalLayout.addComponent(titleLabel2);
		titleLabel2.setStyleName("title2-label");

		HorizontalLayout bcHorizontalLayout = new HorizontalLayout();
		BusinessContext currentBusinessContext = VaadinSession.getCurrent().getAttribute(BusinessContext.class);
		String curBC = currentBusinessContext == null ? "(nie wybrano opracowania)" : EntityLabelUtils
				.getObjectLabel(currentBusinessContext);
		Label bcLabel = new Label("Opracowanie: " + curBC);
		bcHorizontalLayout.addComponent(bcLabel);
		bcHorizontalLayout.setComponentAlignment(bcLabel, Alignment.MIDDLE_LEFT);
		changeContextButton = new Button("Zmien");
		changeContextButton.addClickListener(changeContextListener);
		bcHorizontalLayout.addComponent(changeContextButton);

		verticalLayout.addComponent(bcHorizontalLayout);
		verticalLayout.setExpandRatio(bcHorizontalLayout, 0);
		horizontalLayout.addComponent(verticalLayout);
		// horizontalLayout.setExpandRatio(verticalLayout, 1);

		VerticalLayout vLayout = new VerticalLayout();
		HorizontalLayout userLoggerLayout = new HorizontalLayout();
		Label loginInfo = new Label("Jesteœ zalogowany jako " + getLoggedUserName());
		loginInfo.setStyleName("login-info");
		userLoggerLayout.addComponent(loginInfo);
		userLoggerLayout.addComponent(new Button("Wyloguj", new LogoutListener()));
		vLayout.addComponent(userLoggerLayout);
		vLayout.setComponentAlignment(userLoggerLayout, Alignment.TOP_RIGHT);
		GridLayout linkGrid = new GridLayout(links.size(), 2);
		Image arrow = new Image(null, new ClassResource("/images/green-arrow3.png"));
		int currentLinkIdx = 0;
		for (int i = 0; i < links.size(); i++)
			if (links.get(i).getLinkContent().equals(uriFragment))
				currentLinkIdx = i;
		linkGrid.addComponent(arrow, currentLinkIdx, 0);

		for (int i = 0; i < links.size(); i++)
		{
			Link link = new Link(links.get(i).getLabel(), new ExternalResource("#!" + links.get(i).getLinkContent()));
			link.setSizeFull();
			linkGrid.addComponent(link, i, 1);
			// linkGrid.setComponentAlignment(link, Alignment.MIDDLE_RIGHT);
		}
		vLayout.addComponent(linkGrid);
		vLayout.setComponentAlignment(linkGrid, Alignment.BOTTOM_RIGHT);
		// vLayout.setSizeFull();
		horizontalLayout.addComponent(vLayout);
		// horizontalLayout.setExpandRatio(vLayout, 0);
		horizontalLayout.setSizeFull();
	}

	private String getLoggedUserName()
	{
		SecurityContext sc = SecurityContextHolder.getContext();
		Authentication at = sc.getAuthentication();
		Object principal = at.getPrincipal();
		UserDetails userDetails = null;
		if (principal instanceof UserDetails)
		{
			userDetails = (UserDetails) principal;
		}
		String userName = userDetails.getUsername();
		return userName;
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

	private class LogoutListener implements Button.ClickListener
	{
		@Override
		public void buttonClick(ClickEvent event)
		{
			SecurityContextHolder.clearContext();
			UI.getCurrent().close();
			VaadinSession.getCurrent().close();
			String ctx = VaadinService.getCurrentRequest().getContextPath();
			Page.getCurrent().setLocation(ctx + "/logout");
		}
	}
}
