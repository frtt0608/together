package com.ssafy.hashtag.db.dto;

public class ScoreDto {
  private int score_pk;
  private int user_id;
  private int post_id;
  private int score;
  private String created_at;
  private String updated_at;

  public ScoreDto() {
      super();
      // TODO Auto-generated constructor stub
  }
  public ScoreDto(int score_pk, int user_id, int post_id, int score, String created_at, String updated_at) {
      super();
      this.score_pk = score_pk;
      this.user_id = user_id;
      this.post_id = post_id;
      this.score = score;
      this.created_at = created_at;
      this.updated_at = updated_at;
  }
  public int getScore_pk() {
      return score_pk;
  }
  public void setScore_pk(int score_pk) {
      this.score_pk = score_pk;
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
  public int getScore() {
      return score;
  }
  public void setScore(int score) {
      this.score = score;
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
      return "ScoreDto [score_pk=" + score_pk + ", user_id=" + user_id + 
                ", post_id=" + post_id + ", score=" + score +
                ", created_at=" + created_at + ", updated_at=" + updated_at + "]";
  }
}