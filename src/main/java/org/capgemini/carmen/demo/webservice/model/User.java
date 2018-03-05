package org.capgemini.carmen.demo.webservice.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class User {
	@Id
	@GeneratedValue(strategy= GenerationType.AUTO)
	private Integer id;

	private String name;
	private String email;

	public Integer getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getEmail() {
		return email;
	}


	public void setId(Integer id) {
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