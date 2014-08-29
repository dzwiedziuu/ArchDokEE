package com.aniedzwiedz.dokarchee.data.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

import com.aniedzwiedz.dokarchee.gui.annotations.ColumnHeader;
import com.aniedzwiedz.dokarchee.gui.annotations.EditField;

@Entity
@Table(name = "PHOTO")
public class Photo
{
	@Id
	@Column(name = "IDPHOTO")
	@ColumnHeader(value = "ID Zdjecia", order = 0)
	@GeneratedValue
	private Long id;

	@Column(name = "PHOTO_NUMBER")
	@ColumnHeader(value = "Numer zdjecia", order = 1)
	@EditField(label = "Numer zdjecia", order = 1)
	@NotNull(message = "Numer zdjecia nie moze byc pusty")
	@Size(min = 1, max = 5, message = "Numer zdjecia musi miec dlugosc w przedziale <1, 5>")
	private String photoNumber;

	@JoinColumn(name = "OBJECT")
	@ColumnHeader(value = "Powiazany obiekt", order = 2)
	@EditField(label = "Powiazany obiekt", order = 2)
	@ManyToOne(optional = true)
	private ArchObject object;

	@JoinColumn(name = "PHOTO_SUBJECT")
	@ColumnHeader(value = "Temat zdjecia", order = 3)
	@EditField(label = "Temat zdjecia", order = 3)
	@NotNull(message = "Temat zdjecia nie moze byc pusty")
	@ManyToOne(optional = false)
	private PhotoSubject photoSubject;

	@JoinColumn(name = "AUTHOR")
	@ColumnHeader(value = "Autor", order = 4)
	@EditField(label = "Autor", order = 4)
	@NotNull(message = "Autor nie moze byc pusty")
	@ManyToOne(optional = false)
	private User author;

	@Column(name = "EXPLORATION_DATE")
	@ColumnHeader(value = "Data eksploracji", order = 5)
	@EditField(label = "Data eksploracji", order = 5)
	@NotNull(message = "Data eksploracji nie moze byc pusta")
	@Past
	private Date explorationDate;

	@JoinTable(name = "photo_ar", joinColumns = { @JoinColumn(name = "id_photo") }, inverseJoinColumns = { @JoinColumn(name = "id_ar") })
	// @ColumnHeader(value = "Ary dotyczace zdjecia", order = 6)
	@EditField(label = "Ary dotyczace zdjecia:", order = 6)
	// without CascadeType.Persist
	@ManyToMany(fetch = FetchType.EAGER, cascade = { CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.REMOVE })
	private Set<Ar> ars = new HashSet<Ar>();

	public PhotoSubject getPhotoSubject()
	{
		return photoSubject;
	}

	public void setPhotoSubject(PhotoSubject photoSubject)
	{
		this.photoSubject = photoSubject;
	}

	public Long getId()
	{
		return id;
	}

	public void setId(Long id)
	{
		this.id = id;
	}

	public String getPhotoNumber()
	{
		return photoNumber;
	}

	public void setPhotoNumber(String photoNumber)
	{
		this.photoNumber = photoNumber;
	}

	public User getAuthor()
	{
		return author;
	}

	public void setAuthor(User author)
	{
		this.author = author;
	}

	public Date getExplorationDate()
	{
		return explorationDate;
	}

	public void setExplorationDate(Date explorationDate)
	{
		this.explorationDate = explorationDate;
	}

	public Set<Ar> getArs()
	{
		return ars;
	}

	public void setArs(Set<Ar> ars)
	{
		this.ars = ars;
	}

	public ArchObject getObject()
	{
		return object;
	}

	public void setObject(ArchObject object)
	{
		this.object = object;
	}
}
