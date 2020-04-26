package com.ssafy.hashtag.db.dto;

public class UserDto {
	private int user_pk;
	private String sex;
	private String name;
	private String email;
	private String nickname;
	private String phone_number;
	private String password;
	private String location;
	private int grade;
	private String profile_image;
	private int together;
    private String description;
	private String created_at;
	private String updated_at;
	
	public UserDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public UserDto(int user_pk, String sex, String name, String email, String nickname, String phone_number,
			String password, String location, int grade, String profile_image, int together, String description,
			String created_at, String updated_at) {
		super();
		this.user_pk = user_pk;
		this.sex = sex;
		this.name = name;
		this.email = email;
		this.nickname = nickname;
		this.phone_number = phone_number;
		this.password = password;
		this.location = location;
		this.grade = grade;
		this.profile_image = profile_image;
		this.together = together;
		this.description = description;
		this.created_at = created_at;
		this.updated_at = updated_at;
	}

	public int getUser_pk() {
		return user_pk;
	}

	public void setUser_pk(int user_pk) {
		this.user_pk = user_pk;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getPhone_number() {
		return phone_number;
	}

	public void setPhone_number(String phone_number) {
		this.phone_number = phone_number;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public int getGrade() {
		return grade;
	}

	public void setGrade(int grade) {
		this.grade = grade;
	}

	public String getProfile_image() {
		return profile_image;
	}

	public void setProfile_image(String profile_image) {
		this.profile_image = profile_image;
	}

	public int getTogether() {
		return together;
	}

	public void setTogether(int together) {
		this.together = together;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getCreated_at() {
		return created_at;
	}

	public void setCreated_at(String created_at) {
		this.created_at = created_at;
	}

	public String getUpdated_at() {
		return updated_at;
	}

	public void setUpdated_at(String updated_at) {
		this.updated_at = updated_at;
	}

	@Override
	public String toString() {
		return "UserDto [user_pk=" + user_pk + ", sex=" + sex + ", name=" + name + ", email=" + email + ", nickname="
				+ nickname + ", phone_number=" + phone_number + ", password=" + password + ", location=" + location
				+ ", grade=" + grade + ", profile_image=" + profile_image + ", together=" + together + ", description="
				+ description + ", created_at=" + created_at + ", updated_at=" + updated_at + "]";
	}
	
}
