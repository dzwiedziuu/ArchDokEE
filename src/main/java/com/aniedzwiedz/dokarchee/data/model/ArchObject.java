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
import com.aniedzwiedz.dokarchee.common.annotations.ForeignFieldLabel;

@Entity
@Table(name = "objects")
@ForeignFieldLabel(pattern = "objectNumber")
public class ArchObject
{
	@Id
	@GeneratedValue
	@Column(name = "object_id")
	private Long id;

	@Column(name = "object_nr")
	@ColumnHeader(value = "Numer obiektu", order = 1)
	@EditField(label = "Numer obiektu", order = 1)
	@NotNull(message = "Numer obiektu nie moze byc pusty")
	private String objectNumber;

	@Column(name = "object_exploration_date")
	@ColumnHeader(value = "Data eksploracji", order = 2)
	@EditField(label = "Data eksploracji", order = 2)
	@NotNull(message = "Data eksploracji nie moze byc pusta")
	@Past
	private Date explorationDate;

	@Column(name = "object_fill_date")
	@ColumnHeader(value = "Data wype³nienia dokumentacji", order = 3)
	@EditField(label = "Data wype³nienia dokumentacji", order = 3)
	@NotNull(message = "Data wype³nienia dokumentacji nie moze byc pusta")
	@Past
	private Date fillDate;

	@Column(name = "object_leveling_min")
	@ColumnHeader(value = "Niwelacja min.", order = 4)
	@EditField(label = "Niwelacja min.", order = 4)
	@NotNull(message = "Niwelacja min. nie moze byc pusta")
	private Double levelingDepth;

	@Column(name = "object_elevation_from_surface")
	@ColumnHeader(value = "G³êbokoœæ od powierzchni", order = 5)
	@EditField(label = "G³êbokoœæ od powierzchni", order = 5)
	@NotNull(message = "G³êbokoœæ od powierzchni nie moze byc pusta")
	private Double depthFromSurface;

	@Column(name = "object_projection_x")
	@ColumnHeader(value = "Szerokoœæ (X) rzutu", order = 6)
	@EditField(label = "Szerokoœæ (X) rzutu", order = 6)
	@NotNull(message = "Szerokoœæ (X) rzutu nie moze byc pusta")
	private Double projectionXDim;

	@Column(name = "object_projection_y")
	@ColumnHeader(value = "D³ugoœæ (Y) rzutu", order = 7)
	@EditField(label = "D³ugoœæ (Y) rzutu", order = 7)
	@NotNull(message = "D³ugoœæ (Y) rzutu nie moze byc pusta")
	private Double projectionYDim;

	@Column(name = "object_elevation")
	@ColumnHeader(value = "G³êbokoœæ obiektu", order = 8)
	@EditField(label = "G³êbokoœæ obiektu", order = 8)
	@NotNull(message = "G³êbokoœæ obiektu nie moze byc pusta")
	private Double objectDepth;

	@JoinColumn(name = "user_id")
	@ColumnHeader(value = "Autor opracowania", order = 9)
	@EditField(label = "Autor opracowania", order = 9)
	@NotNull(message = "Autor opracowania nie moze byc pusty")
	@ManyToOne(optional = false)
	private User author;

	@JoinColumn(name = "profile_shape_id")
	@ColumnHeader(value = "Kszta³t profilu", order = 10)
	@EditField(label = "Kszta³t profilu", order = 10)
	@NotNull(message = "Kszta³t profilu nie moze byc pusty")
	@ManyToOne(optional = false)
	private ProfileShape profileShape;

	@JoinColumn(name = "projection_shape_id")
	@ColumnHeader(value = "Zarys rzutu", order = 11)
	@EditField(label = "Zarys rzutu", order = 11)
	@NotNull(message = "Zarys rzutu nie moze byc pusty")
	@ManyToOne(optional = false)
	private ProjectionShape projectionShape;

	@JoinColumn(name = "function_id")
	@ColumnHeader(value = "Funkcja", order = 12)
	@EditField(label = "Funkcja", order = 12)
	@NotNull(message = "Funkcja nie moze byc pusty")
	@ManyToOne(optional = false)
	private Function function;

	@JoinColumn(name = "culture_id")
	@ColumnHeader(value = "Kultura", order = 13)
	@EditField(label = "Kultura", order = 13)
	@NotNull(message = "Kultura nie moze byc pusty")
	@ManyToOne(optional = false)
	private Culture culture;

	@JoinColumn(name = "soil_id")
	@ColumnHeader(value = "Calec", order = 14)
	@EditField(label = "Calec", order = 14)
	@NotNull(message = "Calec nie moze byc pusty")
	@ManyToOne(optional = false)
	private Soil soil;

	@JoinColumn(name = "chronology_id")
	@ColumnHeader(value = "Chronologia", order = 15)
	@EditField(label = "Chronologia", order = 15)
	@NotNull(message = "Chronologia nie moze byc pusty")
	@ManyToOne(optional = false)
	private Chronology chronology;

	@JoinTable(name = "objects_ares", joinColumns = { @JoinColumn(name = "object_id") }, inverseJoinColumns = { @JoinColumn(name = "ar_id") })
	@ColumnHeader(value = "Ary dotyczace obiektu", order = 16, genericType = Are.class)
	@EditField(label = "Ary dotyczace obiektu:", order = 16)
	@ManyToMany(fetch = FetchType.LAZY, cascade = { CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH })
	private Set<Are> ares = new HashSet<Are>();

	@JoinTable(name = "objects_profile_layers", joinColumns = { @JoinColumn(name = "object_id") }, inverseJoinColumns = { @JoinColumn(name = "profile_layer_id") })
	@ColumnHeader(value = "Warstwy profilu", order = 17, genericType = ProfileLayer.class)
	@EditField(label = "Warstwy profilu:", order = 17)
	@ManyToMany(fetch = FetchType.LAZY, cascade = { CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH })
	private Set<ProfileLayer> profileLayers = new HashSet<ProfileLayer>();

	@JoinTable(name = "objects_projection_layers", joinColumns = { @JoinColumn(name = "object_id") }, inverseJoinColumns = { @JoinColumn(name = "projection_layer_id") })
	@ColumnHeader(value = "Warstwy rzutu", order = 18, genericType = ProjectionLayer.class)
	@EditField(label = "Warstwy rzutu:", order = 18)
	@ManyToMany(fetch = FetchType.LAZY, cascade = { CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH })
	private Set<ProjectionLayer> projectionLayers = new HashSet<ProjectionLayer>();

	@JoinColumn(name = "business_context_id")
	@ManyToOne(optional = false)
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

	public String getObjectNumber()
	{
		return objectNumber;
	}

	public void setObjectNumber(String objectNumber)
	{
		this.objectNumber = objectNumber;
	}

	public Date getExplorationDate()
	{
		return explorationDate;
	}

	public void setExplorationDate(Date explorationDate)
	{
		this.explorationDate = explorationDate;
	}

	public Date getFillDate()
	{
		return fillDate;
	}

	public void setFillDate(Date fillDate)
	{
		this.fillDate = fillDate;
	}

	public Double getLevelingDepth()
	{
		return levelingDepth;
	}

	public void setLevelingDepth(Double levelingDepth)
	{
		this.levelingDepth = levelingDepth;
	}

	public Double getDepthFromSurface()
	{
		return depthFromSurface;
	}

	public void setDepthFromSurface(Double depthFromSurface)
	{
		this.depthFromSurface = depthFromSurface;
	}

	public Double getProjectionXDim()
	{
		return projectionXDim;
	}

	public void setProjectionXDim(Double projectionXDim)
	{
		this.projectionXDim = projectionXDim;
	}

	public Double getProjectionYDim()
	{
		return projectionYDim;
	}

	public void setProjectionYDim(Double projectionYDim)
	{
		this.projectionYDim = projectionYDim;
	}

	public Double getObjectDepth()
	{
		return objectDepth;
	}

	public void setObjectDepth(Double objectDepth)
	{
		this.objectDepth = objectDepth;
	}

	public User getAuthor()
	{
		return author;
	}

	public void setAuthor(User author)
	{
		this.author = author;
	}

	public ProfileShape getProfileShape()
	{
		return profileShape;
	}

	public void setProfileShape(ProfileShape profileShape)
	{
		this.profileShape = profileShape;
	}

	public ProjectionShape getProjectionShape()
	{
		return projectionShape;
	}

	public void setProjectionShape(ProjectionShape projectionShape)
	{
		this.projectionShape = projectionShape;
	}

	public Function getFunction()
	{
		return function;
	}

	public void setFunction(Function function)
	{
		this.function = function;
	}

	public Culture getCulture()
	{
		return culture;
	}

	public void setCulture(Culture culture)
	{
		this.culture = culture;
	}

	public Soil getSoil()
	{
		return soil;
	}

	public void setSoil(Soil soil)
	{
		this.soil = soil;
	}

	public Chronology getChronology()
	{
		return chronology;
	}

	public void setChronology(Chronology chronology)
	{
		this.chronology = chronology;
	}

	public Set<Are> getAres()
	{
		return ares;
	}

	public void setAres(Set<Are> ares)
	{
		this.ares = ares;
	}

	public Set<ProfileLayer> getProfileLayers()
	{
		return profileLayers;
	}

	public void setProfileLayers(Set<ProfileLayer> profileLayers)
	{
		this.profileLayers = profileLayers;
	}

	public Set<ProjectionLayer> getProjectionLayers()
	{
		return projectionLayers;
	}

	public void setProjectionLayers(Set<ProjectionLayer> projectionLayers)
	{
		this.projectionLayers = projectionLayers;
	}
}
