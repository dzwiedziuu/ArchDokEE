package com.aniedzwiedz.dokarchee.logic.presenter.reports;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;

import com.aniedzwiedz.dokarchee.gui.view.AbstractView;
import com.aniedzwiedz.dokarchee.gui.view.AbstractView.ViewEvent;
import com.aniedzwiedz.dokarchee.gui.view.reports.AbstractJasperReportView;
import com.aniedzwiedz.dokarchee.gui.view.reports.AbstractJasperReportView.ReportViewListener;
import com.aniedzwiedz.dokarchee.logic.presenter.AbstractPresenter;

public abstract class AbstractJasperReportsPresenter extends AbstractPresenter implements ReportViewListener
{
	@Override
	public void refreshView(AbstractView abstractView)
	{
	}

	@Override
	public void focusAfterClosedWindow(ViewEvent viewEvent)
	{
	}

	@Override
	public void setView(AbstractView abstractView)
	{
		AbstractJasperReportView ajrv = (AbstractJasperReportView) abstractView;
		ajrv.addReportViewListener((ReportViewListener) this);
	}

	@Override
	public InputStream generateReport()
	{
		InputStream is = null;
		try
		{
			InputStream inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream(getReportPath());
			JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(getReportData());
			Map parameters = getParams();
			JasperDesign jasperDesign = JRXmlLoader.load(inputStream);
			JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);
			JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, beanColDataSource);
			ByteArrayOutputStream os = new ByteArrayOutputStream();
			JasperExportManager.exportReportToPdfStream(jasperPrint, os);
			is = new ByteArrayInputStream(os.toByteArray());
		} catch (JRException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return is;
	}

	protected abstract List<?> getReportData();

	protected abstract String getReportPath();

	protected Map getParams()
	{
		return new HashMap();
	}
}
