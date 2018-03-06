package org.capgemini.carmen.demo.webservice.model;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class User {
	@Id
	@GeneratedValue(strategy= GenerationType.AUTO)
	private Long id;

	private String name;
	private String email;

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getEmail() {
		return email;
	}


	public void setId(Long id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public boolean isUnique(String email) {
		return false;
	}

	@Override
	public String toString() {
		return "User {" +
				"id=" + id +
				", name='" + name + '\'' +
				", email='" + email + '\'' +
				" }";
	}
}