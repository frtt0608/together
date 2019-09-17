package com.ssafy.hashtag.db.dto;

public class PostCartDto {
    
  private int post_cart_pk;
  private int user_id;
  private int post_id;
  private String created_at;
  public PostCartDto() {
      super();
      // TODO Auto-generated constructor stub
  }
  public PostCartDto(int post_cart_pk, int user_id, int post_id, String created_at) {
      super();
      this.post_cart_pk = post_cart_pk;
      this.user_id = user_id;
      this.post_id = post_id;
      this.created_at = created_at;
  }
  public int getPost_cart_pk() {
      return post_cart_pk;
  }
  public void setPost_cart_pk(int post_cart_pk) {
      this.post_cart_pk = post_cart_pk;
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
      return "PostcartDto [post_cart_pk=" + post_cart_pk + ", user_id=" + user_id + ", post_id=" + post_id
              + ", created_at=" + created_at + "]";
  } 
}