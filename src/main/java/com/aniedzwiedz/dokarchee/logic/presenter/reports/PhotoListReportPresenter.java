package com.aniedzwiedz.dokarchee.logic.presenter.reports;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.aniedzwiedz.dokarchee.common.utils.EntityLabelUtils;
import com.aniedzwiedz.dokarchee.data.model.Are;
import com.aniedzwiedz.dokarchee.data.model.Photo;
import com.aniedzwiedz.dokarchee.data.service.PhotoService;
import com.aniedzwiedz.dokarchee.gui.view.AbstractView;
import com.aniedzwiedz.dokarchee.gui.view.reports.PhotoListReportView;
import com.aniedzwiedz.dokarchee.logic.session.SessionUtils;

@Component
@Scope("session")
public class PhotoListReportPresenter extends AbstractJasperReportsPresenter
{
	@Autowired
	private PhotoListReportView photoListReportView;

	@Override
	public AbstractView getAbstractView()
	{
		return (AbstractView) photoListReportView;
	}

	@Autowired
	private PhotoService photoService;

	protected List<?> getReportData()
	{
		List<Photo> listOfPhoto = photoService.getAll(Photo.class, SessionUtils.getBusinessContextCriterion("businessContext"));
		List<ReportObject> result = new ArrayList<>();
		List<Long> usedId = new ArrayList<>();
		for (Photo photo : listOfPhoto)
		{
			Long currId = photo.getId();
			if (usedId.contains(currId))
				continue;
			else
				usedId.add(currId);
			ReportObject newReportObject = new ReportObject();
			newReportObject.setAutor(EntityLabelUtils.getObjectLabel(photo.getAuthor()));
			newReportObject.setExplorationDate(new java.sql.Date(photo.getExplorationDate().getTime()));
			newReportObject.setObjectNr(photo.getObject().getObjectNumber());
			newReportObject.setPhotoNr(photo.getPhotoNumber().intValue());
			newReportObject.setSubject(photo.getPhotoSubject().getPhotoSubject());
			newReportObject.setAres(generateAres(photo.getArs()));
			result.add(newReportObject);
		}
		return result;
	}

	private String generateAres(Set<Are> ars)
	{
		StringBuffer sb = new StringBuffer();
		for (Are are : ars)
			sb.append(are.getArNumber()).append(", ");
		if (ars.size() > 0)
			sb.setLength(sb.length() - 2);
		return sb.toString();
	}

	@Override
	protected String getReportPath()
	{
		return "reports/inwentarzZdjec.jrxml";
	}

	public class ReportObject
	{
		private Integer photoNr;
		private Date explorationDate;
		private String autor;
		private String subject;
		private String ares;
		private String objectNr;

		public Integer getPhotoNr()
		{
			return photoNr;
		}

		public void setPhotoNr(Integer photoNr)
		{
			this.photoNr = photoNr;
		}

		public Date getExplorationDate()
		{
			return explorationDate;
		}

		public void setExplorationDate(Date explorationDate)
		{
			this.explorationDate = explorationDate;
		}

		public String getAutor()
		{
			return autor;
		}

		public void setAutor(String autor)
		{
			this.autor = autor;
		}

		public String getSubject()
		{
			return subject;
		}

		public void setSubject(String subject)
		{
			this.subject = subject;
		}

		public String getAres()
		{
			return ares;
		}

		public void setAres(String ares)
		{
			this.ares = ares;
		}

		public String getObjectNr()
		{
			return objectNr;
		}

		public void setObjectNr(String objectNr)
		{
			this.objectNr = objectNr;
		}
	}
}
