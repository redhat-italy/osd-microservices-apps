package it.redhat.osd.model;

import java.io.Serializable;

public class User implements Serializable{
	
	private String id;
	private String firstname;
	private String lastname;
	private String mail;
	
	
	
	public User() {
		super();
	}



	public User(String id, String firstname, String lastname, String mail) {
		super();
		this.id = id;
		this.firstname = firstname;
		this.lastname = lastname;
		this.mail = mail;
	}



	public String getId() {
		return id;
	}



	public void setId(String id) {
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



	public String getMail() {
		return mail;
	}



	public void setMail(String mail) {
		this.mail = mail;
	}



	@Override
	public String toString() {
		return "User [id=" + id + ", firstname=" + firstname + ", lastname=" + lastname + ", mail=" + mail + "]";
	}
	
	
	

}
