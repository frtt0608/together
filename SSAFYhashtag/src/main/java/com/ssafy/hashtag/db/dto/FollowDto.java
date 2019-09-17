package com.ssafy.hashtag.db.dto;

public class FollowDto {
    
  private int follow_pk;
  private int follower;
  private int following;
  private String created_at;
  public FollowDto() {
      super();
      // TODO Auto-generated constructor stub
  }
  public FollowDto(int follow_pk, int follower, int following, String created_at) {
      super();
      this.follow_pk = follow_pk;
      this.follower = follower;
      this.following = following;
      this.created_at = created_at;
  }
  public int getFollow_pk() {
      return follow_pk;
  }
  public void setFollow_pk(int follow_pk) {
      this.follow_pk = follow_pk;
  }
  public int getFollower() {
      return follower;
  }
  public void setFollower(int follower) {
      this.follower = follower;
  }
  public int getFollowing() {
      return following;
  }
  public void setFollowing(int following) {
      this.following = following;
  }
  public String getCreated_at() {
      return created_at;
  }
  public void setCreated_at(String created_at) {
      this.created_at = created_at;
  }
  @Override
  public String toString() {
      return "Follow [follow_pk=" + follow_pk + ", follower=" + follower + ", following=" + following
              + ", created_at=" + created_at + "]";
  } 
}