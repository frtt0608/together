package com.ssafy.hashtag.db.dto;

public class CommentDto {
	private int comment_pk;
	private int user_id;
	private int post_id;
	private String comment;
	private String nickname;
	private String created_at;
	private String updated_at;

	public CommentDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CommentDto(int comment_pk, int user_id, int post_id, String comment, String nickname, String created_at,
			String updated_at) {
		super();
		this.comment_pk = comment_pk;
		this.user_id = user_id;
		this.post_id = post_id;
		this.comment = comment;
		this.nickname = nickname;
		this.created_at = created_at;
		this.updated_at = updated_at;
	}

	public int getComment_pk() {
		return comment_pk;
	}

	public void setComment_pk(int comment_pk) {
		this.comment_pk = comment_pk;
	}

	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	public int getPost_id() {
		return post_id;
	}

	public void setPost_id(int post_id) {
		this.post_id = post_id;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
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

	public String getUpdated_at() {
		return updated_at;
	}

	public void setUpdated_at(String updated_at) {
		this.updated_at = updated_at;
	}

	@Override
	public String toString() {
		return "CommentDto [comment_pk=" + comment_pk + ", user_id=" + user_id + ", post_id=" + post_id + ", comment=" + 
				comment + ", nickname=" + nickname + ", created_at=" + created_at + ", updated_at=" + updated_at + "]";
	}
}