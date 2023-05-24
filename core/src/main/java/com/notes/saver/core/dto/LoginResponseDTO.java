package com.notes.saver.core.dto;

public class LoginResponseDTO {
	private int id;
	private String username;
	private String email;

	public LoginResponseDTO() {
	}

	public LoginResponseDTO(int id, String username, String email) {
		super();
		this.id = id;
		this.username = username;
		this.email = email;
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

	public String getemail() {
		return email;
	}

	public void setemail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "LoginResponseDTO [id=" + id + ", username=" + username + ", email=" + email + "]";
	}

}
