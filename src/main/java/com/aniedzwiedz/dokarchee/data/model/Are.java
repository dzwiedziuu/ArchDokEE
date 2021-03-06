package com.aniedzwiedz.dokarchee.data.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.aniedzwiedz.dokarchee.common.annotations.ColumnHeader;
import com.aniedzwiedz.dokarchee.common.annotations.EditField;
import com.aniedzwiedz.dokarchee.common.annotations.ForeignFieldLabel;

@Entity
@Table(name = "ares")
@ForeignFieldLabel(pattern = "arNumber")
public class Are
{
	@Id
	@GeneratedValue
	@Column(name = "ar_id")
	private Long id;

	@Column(name = "ar_number")
	@ColumnHeader(value = "Numer ara", order = 1)
	@EditField(label = "Numer ara", order = 1, editable = false)
	@NotNull(message = "Numer ara nie moze byc pusty")
	private String arNumber;

	// @ManyToMany(mappedBy = "ars")
	// // , fetch = FetchType.EAGER)
	// private Set<Photo> photos = new HashSet<Photo>();

	@JoinColumn(name = "business_context_id")
	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	private BusinessContext businessContext;

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

	public String getArNumber()
	{
		return arNumber;
	}

	public void setArNumber(String arNumber)
	{
		this.arNumber = arNumber;
	}

	// public Set<Photo> getPhotos()
	// {
	// return photos;
	// }
	//
	// public void setPhotos(Set<Photo> photos)
	// {
	// this.photos = photos;
	// }
}
