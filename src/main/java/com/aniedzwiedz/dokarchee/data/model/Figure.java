package com.aniedzwiedz.dokarchee.data.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

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
	@ColumnHeader(value = "Profil", order = 3)
	@EditField(label = "Profil", order = 3)
	private String figureNr;
}
