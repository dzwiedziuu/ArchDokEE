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

import com.aniedzwiedz.dokarchee.common.annotations.ColumnHeader;
import com.aniedzwiedz.dokarchee.common.annotations.EditField;

@Entity
@Table(name = "photos")
public class Photo
{
	@Id
	@GeneratedValue
	@Column(name = "photo_id")
	private Long id;

	@Column(name = "photo_nr")
	@ColumnHeader(value = "Numer zdjecia", order = 1)
	@EditField(label = "Numer zdjecia", order = 1)
	@NotNull(message = "Numer zdjecia nie moze byc pusty")
	private Long photoNumber;

	@JoinColumn(name = "object_id")
	@ColumnHeader(value = "Powiazany obiekt", order = 2)
	@EditField(label = "Powiazany obiekt", order = 2)
	@ManyToOne(optional = true)
	private ArchObject object;

	@JoinColumn(name = "photo_subject_id")
	@ColumnHeader(value = "Temat zdjecia", order = 3)
	@EditField(label = "Temat zdjecia", order = 3)
	@NotNull(message = "Temat zdjecia nie moze byc pusty")
	@ManyToOne(optional = false)
	private PhotoSubject photoSubject;

	@JoinColumn(name = "user_id")
	@ColumnHeader(value = "Autor", order = 4)
	@EditField(label = "Autor", order = 4)
	@NotNull(message = "Autor nie moze byc pusty")
	@ManyToOne(optional = false)
	private User author;

	@Column(name = "photo_exploration_date")
	@ColumnHeader(value = "Data eksploracji", order = 5)
	@EditField(label = "Data eksploracji", order = 5)
	@NotNull(message = "Data eksploracji nie moze byc pusta")
	@Past
	private Date explorationDate;

	@JoinColumn(name = "business_context_id")
	@ManyToOne(optional = false)
	private BusinessContext businessContext;

	@JoinTable(name = "ares_photos", joinColumns = { @JoinColumn(name = "photos_photo_id") }, inverseJoinColumns = { @JoinColumn(name = "ares_ar_id") })
	@ColumnHeader(value = "Ary dotyczace zdjecia", order = 6, genericType = Are.class)
	@EditField(label = "Ary dotyczace zdjecia:", order = 6)
	// without CascadeType.Persist, CascadeType.REMOVE
	@ManyToMany(fetch = FetchType.LAZY, cascade = { CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH })
	private Set<Are> ars = new HashSet<Are>();

	public BusinessContext getBusinessContext()
	{
		return businessContext;
	}

	public void setBusinessContext(BusinessContext businessContext)
	{
		this.businessContext = businessContext;
	}

	public Long getId()
	{
		return id;
	}

	public void setId(Long id)
	{
		this.id = id;
	}

	public PhotoSubject getPhotoSubject()
	{
		return photoSubject;
	}

	public void setPhotoSubject(PhotoSubject photoSubject)
	{
		this.photoSubject = photoSubject;
	}

	public Long getPhotoNumber()
	{
		return photoNumber;
	}

	public void setPhotoNumber(Long photoNumber)
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

	public Set<Are> getArs()
	{
		return ars;
	}

	public void setArs(Set<Are> ars)
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
