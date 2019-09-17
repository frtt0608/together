package com.ssafy.hashtag.db.dto;

public class PostLikeDto {
  private int post_like_pk;
  private int user_id;
  private int post_id;
  private String created_at;
  public PostLikeDto() {
      super();
      // TODO Auto-generated constructor stub
  }
  public PostLikeDto(int post_like_pk, int user_id, int post_id, String created_at) {
      super();
      this.post_like_pk = post_like_pk;
      this.user_id = user_id;
      this.post_id = post_id;
      this.created_at = created_at;
  }
  public int getPost_like_pk() {
      return post_like_pk;
  }
  public void setPost_like_pk(int post_like_pk) {
      this.post_like_pk = post_like_pk;
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
  public String getCreated_at() {
      return created_at;
  }
  public void setCreated_at(String created_at) {
      this.created_at = created_at;
  }
  @Override
  public String toString() {
      return "PostlikeDto [post_like_pk=" + post_like_pk + ", user_id=" + user_id + ", post_id=" + post_id
              + ", created_at=" + created_at + "]";
  }
}