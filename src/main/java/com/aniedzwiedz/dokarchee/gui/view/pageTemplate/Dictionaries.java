package com.aniedzwiedz.dokarchee.gui.view.pageTemplate;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import ru.xpoft.vaadin.VaadinView;

import com.aniedzwiedz.dokarchee.gui.view.AbstractViewImpl;
import com.aniedzwiedz.dokarchee.gui.view.figures.subjects.FigureSubjectListViewImpl;
import com.aniedzwiedz.dokarchee.gui.view.misc.ares.ArListViewImpl;
import com.aniedzwiedz.dokarchee.gui.view.misc.chronologies.ChronologyListViewImpl;
import com.aniedzwiedz.dokarchee.gui.view.misc.users.UsersListViewImpl;
import com.aniedzwiedz.dokarchee.gui.view.objects.cultures.CultureListViewImpl;
import com.aniedzwiedz.dokarchee.gui.view.objects.functions.FunctionListViewImpl;
import com.aniedzwiedz.dokarchee.gui.view.objects.profileLayers.ProfileLayerListViewImpl;
import com.aniedzwiedz.dokarchee.gui.view.objects.profileShapes.ProfileShapeListViewImpl;
import com.aniedzwiedz.dokarchee.gui.view.objects.projectionLayers.ProjectionLayerListViewImpl;
import com.aniedzwiedz.dokarchee.gui.view.objects.projectionShapes.ProjectionShapeListViewImpl;
import com.aniedzwiedz.dokarchee.gui.view.objects.soils.SoilListViewImpl;
import com.aniedzwiedz.dokarchee.gui.view.photos.subject.PhotoSubjectListViewImpl;
import com.aniedzwiedz.dokarchee.gui.view.relics.group.RelicGroupListViewImpl;
import com.aniedzwiedz.dokarchee.gui.view.relics.layer.RelicLayerListViewImpl;
import com.aniedzwiedz.dokarchee.gui.view.relics.type.RelicTypeListViewImpl;
import com.vaadin.server.ExternalResource;
import com.vaadin.ui.AbstractComponent;
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
		links.add(new PageLink("Ar", ArListViewImpl.VIEW_NAME));
		links.add(new PageLink("Autor", UsersListViewImpl.VIEW_NAME));
		links.add(new PageLink("Chronologia", ChronologyListViewImpl.VIEW_NAME));
		links.add(new PageLink("Zarys rzutu", ProjectionShapeListViewImpl.VIEW_NAME));
		links.add(new PageLink("Kszta³t profilu", ProfileShapeListViewImpl.VIEW_NAME));
		links.add(new PageLink("Funkcja", FunctionListViewImpl.VIEW_NAME));
		links.add(new PageLink("Kultura", CultureListViewImpl.VIEW_NAME));
		links.add(new PageLink("Calec", SoilListViewImpl.VIEW_NAME));
		links.add(new PageLink("Warstwa rzutu", ProjectionLayerListViewImpl.VIEW_NAME));
		links.add(new PageLink("Warstwa profilu", ProfileLayerListViewImpl.VIEW_NAME));
		links.add(new PageLink("Temat fotografii", PhotoSubjectListViewImpl.VIEW_NAME));
		links.add(new PageLink("Temat rysunku", FigureSubjectListViewImpl.VIEW_NAME));
		links.add(new PageLink("Warstwa zabytku", RelicLayerListViewImpl.VIEW_NAME));
		links.add(new PageLink("Rodzaj zabytku", RelicTypeListViewImpl.VIEW_NAME));
		links.add(new PageLink("Grupa zabytku", RelicGroupListViewImpl.VIEW_NAME));
	}

	public Dictionaries()
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

	public static final String VIEW_NAME = "Dictionaries";

	@Override
	public String getViewName()
	{
		return VIEW_NAME;
	}

	@Override
	public String getTitle()
	{
		return "S³owniki";
	}
}
