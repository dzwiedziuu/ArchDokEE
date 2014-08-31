package com.aniedzwiedz.dokarchee.data.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.aniedzwiedz.dokarchee.common.annotations.ColumnHeader;
import com.aniedzwiedz.dokarchee.common.annotations.EditField;
import com.aniedzwiedz.dokarchee.common.annotations.ForeignFieldLabel;

@Entity
@Table(name = "users")
@ForeignFieldLabel(pattern = "$firstName$ $lastName$")
public class User
{
	@Id
	@GeneratedValue
	@Column(name = "user_id")
	private Long id;

	@Column(name = "user_name")
	@ColumnHeader(value = "Imie", order = 1)
	@EditField(label = "Imie", order = 1)
	private String firstName;

	@Column(name = "user_lastname")
	@ColumnHeader(value = "Nazwisko", order = 2)
	@EditField(label = "Nazwisko", order = 2)
	@NotNull(message = "Nazwisko nie moze byc puste")
	private String lastName;

	@Column(name = "user_login")
	@ColumnHeader(value = "Login", order = 2)
	@EditField(label = "Login", order = 3)
	private String login;

	public String getLogin()
	{
		return login;
	}

	public void setLogin(String login)
	{
		this.login = login;
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
