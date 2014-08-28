package com.aniedzwiedz.dokarchee.data.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.aniedzwiedz.dokarchee.gui.annotations.ColumnHeader;
import com.aniedzwiedz.dokarchee.gui.annotations.EditField;

@Entity
@Table(name = "Ar")
public class Ar
{
	@Id
	@Column(name = "idar")
	@ColumnHeader(value = "ID Ara", order = 0)
	@GeneratedValue
	private Long id;

	@Column(name = "ar_number")
	@ColumnHeader(value = "Numer ara", order = 2)
	@EditField(label = "Numer ara", order = 2)
	@NotNull(message = "Numer ara nie moze byc pusty")
	private String photoSubject;

	@ManyToMany(mappedBy = "ars")
	private Set<Photo> photos = new HashSet<Photo>();
}
