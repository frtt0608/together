package com.ssafy.hashtag.db.dto;


public class ChatroomDto {
	private int chatroom_pk;
	private int chat_id;
	private int user_id;
	private String nickname;
	private String created_at;
	
	public ChatroomDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ChatroomDto(int chatroom_pk, int chat_id, int user_id, String nickname, String created_at) {
		super();
		this.chatroom_pk = chatroom_pk;
		this.chat_id = chat_id;
		this.user_id = user_id;
		this.nickname = nickname;
		this.created_at = created_at;
	}

	public int getChatroom_pk() {
		return chatroom_pk;
	}

	public void setChatroom_pk(int chatroom_pk) {
		this.chatroom_pk = chatroom_pk;
	}

	public int getChat_id() {
		return chat_id;
	}

	public void setChat_id(int chat_id) {
		this.chat_id = chat_id;
	}

	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getCreated_at() {
		return created_at;
	}

	public void setCreated_at(String created_at) {
		this.created_at = created_at;
	}

	@Override
	public String toString() {
		return "ChatroomDto [chatroom_pk=" + chatroom_pk + ", chat_id=" + chat_id + ", user_id=" + user_id
				+ ", nickname=" + nickname + ", created_at=" + created_at + "]";
	}
}