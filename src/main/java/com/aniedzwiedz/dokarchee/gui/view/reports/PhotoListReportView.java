package com.aniedzwiedz.dokarchee.gui.view.reports;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import ru.xpoft.vaadin.VaadinView;

@Component(PhotoListReportView.VIEW_NAME)
@Scope("prototype")
@VaadinView(PhotoListReportView.VIEW_NAME)
public class PhotoListReportView extends AbstractJasperReportView
{
	public static final String VIEW_NAME = "PhotoListReport";

	@Override
	public String getViewName()
	{
		return VIEW_NAME;
	}

	@Override
	public String getTitle()
	{
		return "Raport inwentarz zdjêæ";
	}

	@Override
	protected String getDefaultFileName()
	{
		return "inwentarzZabytkow.pdf";
	}
}
