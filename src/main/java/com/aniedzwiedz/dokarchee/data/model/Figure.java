package com.aniedzwiedz.dokarchee.data.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;

import org.hibernate.validator.constraints.NotEmpty;

import com.aniedzwiedz.dokarchee.common.annotations.ColumnHeader;
import com.aniedzwiedz.dokarchee.common.annotations.EditField;

@Entity
@Table(name = "figures")
public class Figure
{
	@Id
	@GeneratedValue
	@Column(name = "figure_id")
	private Long id;

	@Column(name = "figure_nr")
	@ColumnHeader(value = "Nr rysunku", order = 1)
	@EditField(label = "Nr rysunku", order = 1)
	@NotEmpty
	private String figureNr;

	@Column(name = "figure_date")
	@ColumnHeader(value = "Data eksploracji", order = 2)
	@EditField(label = "Data eksploracji", order = 2)
	@NotNull(message = "Data eksploracji nie moze byc pusta")
	@Past
	private Date explorationDate;

	@JoinColumn(name = "figure_subject_id")
	@ColumnHeader(value = "Temat rysunku", order = 3)
	@EditField(label = "Temat rysunku", order = 3)
	@NotNull(message = "Temat rysunku nie moze byc pusty")
	@ManyToOne(optional = false)
	private FigureSubject figureSubject;

	@JoinColumn(name = "user_id")
	@ColumnHeader(value = "Autor", order = 4)
	@EditField(label = "Autor", order = 4)
	@NotNull(message = "Autor nie moze byc pusty")
	@ManyToOne(optional = false)
	private User author;

	@JoinColumn(name = "business_context_id")
	@ManyToOne(optional = false)
	private BusinessContext businessContext;

	@ColumnHeader(value = "Specyfikacja rysunku", order = 6, genericType = FigureSpecification.class)
	@EditField(label = "Specyfikacja rysunku:", order = 6)
	@OneToMany(mappedBy = "figure", fetch = FetchType.EAGER, orphanRemoval = true)
	@org.hibernate.annotations.Cascade({ org.hibernate.annotations.CascadeType.ALL })
	private Set<FigureSpecification> figureSpecifications = new HashSet<FigureSpecification>();

	public Long getId()
	{
		return id;
	}

	public void setId(Long id)
	{
		this.id = id;
	}

	public String getFigureNr()
	{
		return figureNr;
	}

	public void setFigureNr(String figureNr)
	{
		this.figureNr = figureNr;
	}

	public Date getExplorationDate()
	{
		return explorationDate;
	}

	public void setExplorationDate(Date explorationDate)
	{
		this.explorationDate = explorationDate;
	}

	public FigureSubject getFigureSubject()
	{
		return figureSubject;
	}

	public void setFigureSubject(FigureSubject figureSubject)
	{
		this.figureSubject = figureSubject;
	}

	public User getAuthor()
	{
		return author;
	}

	public void setAuthor(User author)
	{
		this.author = author;
	}

	public BusinessContext getBusinessContext()
	{
		return businessContext;
	}

	public void setBusinessContext(BusinessContext businessContext)
	{
		this.businessContext = businessContext;
	}

	public Set<FigureSpecification> getFigureSpecifications()
	{
		return figureSpecifications;
	}

	public void setFigureSpecifications(Set<FigureSpecification> figureSpecifications)
	{
		this.figureSpecifications = figureSpecifications;
	}
}
