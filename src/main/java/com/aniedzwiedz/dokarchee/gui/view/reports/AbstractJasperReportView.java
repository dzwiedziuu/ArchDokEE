package com.aniedzwiedz.dokarchee.gui.view.reports;

import java.io.InputStream;

import com.aniedzwiedz.dokarchee.gui.view.AbstractViewImpl;
import com.vaadin.server.FileDownloader;
import com.vaadin.server.StreamResource;
import com.vaadin.server.StreamResource.StreamSource;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.VerticalLayout;

public abstract class AbstractJasperReportView extends AbstractViewImpl
{
	public interface ReportViewListener
	{
		public InputStream generateReport();
	}

	private ReportViewListener reportListener;

	public void addReportViewListener(ReportViewListener reportViewListener)
	{
		this.reportListener = reportViewListener;
	}

	public AbstractJasperReportView()
	{
		VerticalLayout vl = new VerticalLayout();
		Button button = new Button("Generuj raport");
		StreamSource s = new StreamSource()
		{
			@Override
			public InputStream getStream()
			{
				return reportListener.generateReport();
			}
		};
		FileDownloader downloader = new FileDownloader(new StreamResource(s, getDefaultFileName()));
		downloader.extend(button);
		vl.addComponent(button);
		vl.setComponentAlignment(button, Alignment.MIDDLE_CENTER);
		setContent(vl);
	}

	protected abstract String getDefaultFileName();
}
