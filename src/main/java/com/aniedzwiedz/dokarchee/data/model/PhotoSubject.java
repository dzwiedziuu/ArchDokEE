package com.aniedzwiedz.dokarchee.data.model;

import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.aniedzwiedz.dokarchee.gui.annotations.ColumnHeader;
import com.aniedzwiedz.dokarchee.gui.annotations.EditField;

@Entity
@Table(name = "photo_subject")
public class PhotoSubject
{
	@Id
	@Column(name = "idphoto_subject")
	@ColumnHeader(value = "ID Tematu zdjecia", order = 0)
	@GeneratedValue
	private Long id;

	@Column(name = "content")
	@ColumnHeader(value = "Temat zdjecia", order = 2)
	@EditField(label = "Temat zdjecia", order = 2)
	@NotNull(message = "Temat zdjecia nie moze byc pusty")
	private String photoSubject;

	@OneToMany(mappedBy = "photoSubject")
	private Collection<Photo> photos;

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

	@Override
	public String toString()
	{
		return photoSubject;
	}
}
