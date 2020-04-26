package com.ssafy.hashtag.db.dto;

public class LoginUserDto {

	private int loginuser_pk;
	private int user_id;
	private String location;
	
	public LoginUserDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public LoginUserDto(int loginuser_pk, int user_id, String location) {
		super();
		this.loginuser_pk = loginuser_pk;
		this.user_id = user_id;
		this.location = location;
	}

	public int getLoginuser_pk() {
		return loginuser_pk;
	}

	public void setLoginuser_pk(int loginuser_pk) {
		this.loginuser_pk = loginuser_pk;
	}

	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}


	@Override
	public String toString() {
		return "LoginUserDto [user_id=" + user_id + ", location=" + location + "]";
	}
}
