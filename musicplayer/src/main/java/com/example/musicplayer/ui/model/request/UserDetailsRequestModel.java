package com.example.musicplayer.ui.model.request;

public class UserDetailsRequestModel {
	private String firstName;
	private String lastName;
	private String email;
	private String password;
	//confirm password string for validation checls
	private String confirmPassword;

	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	//get confirm password
	public String getConfirmPassword(){
		return confirmPassword;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	
}
