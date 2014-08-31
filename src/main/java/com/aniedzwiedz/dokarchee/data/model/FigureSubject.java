package com.aniedzwiedz.dokarchee.data.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.aniedzwiedz.dokarchee.common.annotations.ColumnHeader;
import com.aniedzwiedz.dokarchee.common.annotations.EditField;

@Entity
@Table(name = "figure_subjects")
public class FigureSubject
{
	@Id
	@GeneratedValue
	@Column(name = "figure_subject_id")
	private Long id;

	@Column(name = "figure_subject_name")
	@ColumnHeader(value = "Temat rysunku", order = 2)
	@EditField(label = "Temat rysunku", order = 2)
	@NotNull(message = "Temat rysunku nie moze byc pusty")
	private String photoSubject;

	// @OneToMany(mappedBy = "figureSubject")
	// private Collection<Photo> photos;

	public Long getId()
	{
		return id;
	}

	public void setId(Long id)
	{
		this.id = id;
	}

	public String getPhotoSubject()
	{
		return photoSubject;
	}

	public void setPhotoSubject(String photoSubject)
	{
		this.photoSubject = photoSubject;
	}

	// public Collection<Photo> getPhotos()
	// {
	// return photos;
	// }
	//
	// public void setPhotos(Collection<Photo> photos)
	// {
	// this.photos = photos;
	// }
}
