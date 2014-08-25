package com.aniedzwiedz.dokarchee.data.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.aniedzwiedz.dokarchee.gui.annotations.ColumnHeader;
import com.aniedzwiedz.dokarchee.gui.annotations.EditField;

@Entity
@Table(name = "USERS")
public class User
{
	@Id
	@Column(name = "IDUSERS")
	@ColumnHeader(value = "ID", order = 0)
	@GeneratedValue
	private Long id;

	@Column(name = "FIRSTNAME")
	@ColumnHeader(value = "Imie", order = 1)
	@EditField(label = "Imie", order = 1)
	private String firstName;

	@Column(name = "LASTNAME")
	@ColumnHeader(value = "Nazwisko", order = 2)
	@EditField(label = "Nazwisko", order = 2)
	@NotNull(message = "Nazwisko nie moze byc puste")
	@Size(min = 5, max = 10, message = "Dlugosc nazwiska musi zawierac sie w przedziale <5, 10> znakow")
	private String lastName;

	@Column(name = "EMAIL")
	@EditField(label = "Email", order = 3)
	private String email;

	public User()
	{
	}

	public String getEmail()
	{
		return email;
	}

	public void setEmail(String email)
	{
		this.email = email;
	}

	public String getFirstName()
	{
		return firstName;
	}

	public void setFirstName(String firstName)
	{
		this.firstName = firstName;
	}

	public String getLastName()
	{
		return lastName;
	}

	public void setLastName(String lastName)
	{
		this.lastName = lastName;
	}

	public Long getId()
	{
		return id;
	}

	public void setId(Long id)
	{
		this.id = id;
	}

	// @Override
	// public String toString()
	// {
	// return "" + id + ") " + firstName + " " + lastName;
	// }
}
