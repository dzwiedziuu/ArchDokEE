package com.aniedzwiedz.dokarchee.data.model;

import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.aniedzwiedz.dokarchee.common.annotations.ColumnHeader;
import com.aniedzwiedz.dokarchee.common.annotations.EditField;
import com.aniedzwiedz.dokarchee.common.annotations.ForeignFieldLabel;

@Entity
@Table(name = "photo_subjects")
@ForeignFieldLabel(pattern = "photoSubject")
public class PhotoSubject
{
	@Id
	@GeneratedValue
	@Column(name = "photo_subject_id")
	private Long id;

	@Column(name = "photo_subject_name")
	@ColumnHeader(value = "Temat zdjecia", order = 2)
	@EditField(label = "Temat zdjecia", order = 2)
	@NotNull(message = "Temat zdjecia nie moze byc pusty")
	private String photoSubject;

	@OneToMany(mappedBy = "photoSubject")
	private Collection<Photo> photos;

	public Collection<Photo> getPhotos()
	{
		return photos;
	}

	public void setPhotos(Collection<Photo> photos)
	{
		this.photos = photos;
	}

	public PhotoSubject()
	{
	}

	public Long getId()
	{
		return id;
	}

	public String getPhotoSubject()
	{
		return photoSubject;
	}

	public void setId(Long id)
	{
		this.id = id;
	}

	public void setPhotoSubject(String photoSubject)
	{
		this.photoSubject = photoSubject;
	}
}
