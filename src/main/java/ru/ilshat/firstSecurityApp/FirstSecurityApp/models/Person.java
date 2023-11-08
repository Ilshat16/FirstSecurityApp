package ru.ilshat.firstSecurityApp.FirstSecurityApp.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "Person")
public class Person {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name = "username")
	@NotEmpty(message = "Имя не должно быть пустым")
	@Size(min = 2, max = 100, message = "Имя должно быть от 2 до 100 символов")
	private String username;
	
	@Column(name = "year_of_birth")
	@NotNull(message = "Год рождения не должно быть пустым")
	@Min(value = 1900, message = "Год рождения должен быть больше чем 1900")
	private int yearOfBirth;
	
	@Column(name = "password")
	@NotEmpty(message = "Пароль не должен быть пустым")
	private String password;
	
	public Person() {}

	public Person(int id, String username, int yearOfBirth, String password) {
		super();
		this.id = id;
		this.username = username;
		this.yearOfBirth = yearOfBirth;
		this.password = password;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public int getYearOfBirth() {
		return yearOfBirth;
	}

	public void setYearOfBirth(int yearOfBirth) {
		this.yearOfBirth = yearOfBirth;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "Person [id=" + id + ", username=" + username + ", yearOfBirth=" + yearOfBirth + ", password=" + password
				+ "]";
	}
	
}
