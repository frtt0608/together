package com.ssafy.hashtag.db.dto;

public class ChatDto {
	private int chat_pk;
	private int opener_id;
	private String sex;
	private String nickname;
	private String title;
	private String location;
	private String description;
	private int user_cnt;
	private String created_at;
	
	public ChatDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ChatDto(int chat_pk, int opener_id, String sex, String nickname, String title, String location,
			String description, int user_cnt, String created_at) {
		super();
		this.chat_pk = chat_pk;
		this.opener_id = opener_id;
		this.sex = sex;
		this.nickname = nickname;
		this.title = title;
		this.location = location;
		this.description = description;
		this.user_cnt = user_cnt;
		this.created_at = created_at;
	}

	public int getChat_pk() {
		return chat_pk;
	}

	public void setChat_pk(int chat_pk) {
		this.chat_pk = chat_pk;
	}

	public int getOpener_id() {
		return opener_id;
	}

	public void setOpener_id(int opener_id) {
		this.opener_id = opener_id;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getUser_cnt() {
		return user_cnt;
	}

	public void setUser_cnt(int user_cnt) {
		this.user_cnt = user_cnt;
	}

	public String getCreated_at() {
		return created_at;
	}

	public void setCreated_at(String created_at) {
		this.created_at = created_at;
	}

	@Override
	public String toString() {
		return "ChatDto [chat_pk=" + chat_pk + ", opener_id=" + opener_id + ", sex=" + sex + ", nickname=" + nickname
				+ ", title=" + title + ", location=" + location + ", description=" + description
				+ ", user_cnt=" + user_cnt + ", created_at=" + created_at + "]";
	}
}