package com.example.SimpleFirstCrud.dto;

public class StudentDTO {

	public long id;
	public String firstname;
	public String lastname;
	public String email;
	public String branch;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getBranch() {
		return branch;
	}
	public void setBranch(String branch) {
		this.branch = branch;
	}
	@Override
	public String toString() {
		return "StudentDTO [id=" + id + ", firstname=" + firstname + ", lastname=" + lastname + ", email=" + email
				+ ", branch=" + branch + "]";
	}
	public StudentDTO(long id, String firstname, String lastname, String email, String branch) {
		super();
		this.id = id;
		this.firstname = firstname;
		this.lastname = lastname;
		this.email = email;
		this.branch = branch;
	}
	public StudentDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
}
