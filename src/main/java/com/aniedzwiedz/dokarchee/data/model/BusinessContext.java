package com.aniedzwiedz.dokarchee.data.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotEmpty;

import com.aniedzwiedz.dokarchee.common.annotations.ColumnHeader;
import com.aniedzwiedz.dokarchee.common.annotations.EditField;
import com.aniedzwiedz.dokarchee.common.annotations.ForeignFieldLabel;

@Entity
@Table(name = "business_contexts")
@ForeignFieldLabel(pattern = "$business_context_city$/$business_context_voivodeship$/$business_context_site_nr$")
public class BusinessContext
{
	@Id
	@GeneratedValue
	@Column(name = "business_context_id")
	private Long id;

	@Column(name = "business_context_city")
	@ColumnHeader(value = "Miejscowosc", order = 1)
	@EditField(label = "Miejscowosc", order = 1)
	@NotEmpty(message = "Miejscowosc nie moze byc pusta")
	private String city;

	@Column(name = "business_context_municipality")
	@ColumnHeader(value = "Gmina", order = 2)
	@EditField(label = "Gmina", order = 2)
	@NotEmpty(message = "Gmina nie moze byc pusta")
	private String municipality;

	@Column(name = "business_context_voivodeship")
	@ColumnHeader(value = "Wojewodztwo", order = 3)
	@EditField(label = "Wojewodztwo", order = 3)
	@NotEmpty(message = "Wojewodztwo nie moze byc puste")
	private String voivodeship;

	@Column(name = "business_context_azp_area_nr")
	@ColumnHeader(value = "Nr obszaru AZP", order = 4)
	@EditField(label = "Nr obszaru AZP", order = 4)
	@NotEmpty(message = "Nr obszaru AZP nie moze byc pusty")
	private String azpAreaNr;

	@Column(name = "business_context_nr_in_city")
	@ColumnHeader(value = "Numer obszaru w miejscowosci", order = 5)
	@EditField(label = "Numer obszaru w miejscowosci", order = 5)
	@NotEmpty(message = "Numer obszaru w miejscowosci nie moze byc pusty")
	private String nrInCity;

	@Column(name = "business_context_site_nr")
	@ColumnHeader(value = "Numer stanowiska", order = 6)
	@EditField(label = "Numer stanowiska", order = 6)
	@NotEmpty(message = "Numer stanowiska nie moze byc pusty")
	private String siteNr;

	public Long getId()
	{
		return id;
	}

	public void setId(Long id)
	{
		this.id = id;
	}

	public String getCity()
	{
		return city;
	}

	public void setCity(String city)
	{
		this.city = city;
	}

	public String getMunicipality()
	{
		return municipality;
	}

	public void setMunicipality(String municipality)
	{
		this.municipality = municipality;
	}

	public String getVoivodeship()
	{
		return voivodeship;
	}

	public void setVoivodeship(String voivodeship)
	{
		this.voivodeship = voivodeship;
	}

	public String getAzpAreaNr()
	{
		return azpAreaNr;
	}

	public void setAzpAreaNr(String azpAreaNr)
	{
		this.azpAreaNr = azpAreaNr;
	}

	public String getNrInCity()
	{
		return nrInCity;
	}

	public void setNrInCity(String nrInCity)
	{
		this.nrInCity = nrInCity;
	}

	public String getSiteNr()
	{
		return siteNr;
	}

	public void setSiteNr(String siteNr)
	{
		this.siteNr = siteNr;
	}
}
