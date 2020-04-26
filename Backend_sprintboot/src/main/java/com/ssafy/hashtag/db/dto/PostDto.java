package com.ssafy.hashtag.db.dto;

public class PostDto {
  private int post_pk;
  private String image;
  private String description;
  private String address;
  private String title;
  private int areacode;
  private int content_id;
  private String cat1;
  private String cat2;
  private String cat3;
  private int content_type_id;
  private Double mapx;
  private Double mapy;

  public PostDto() {
    super();
    // TODO Auto-generated constructor stub
  }

  public PostDto(int post_pk, String image, String description, String address, String title, int areacode,
      int content_id, String cat1, String cat2, String cat3, int content_type_id, Double mapx, Double mapy) {
    super();
    this.post_pk = post_pk;
    this.image = image;
    this.description = description;
    this.address = address;
    this.title = title;
    this.areacode = areacode;
    this.content_id = content_id;
    this.cat1 = cat1;
    this.cat2 = cat2;
    this.cat3 = cat3;
    this.content_type_id = content_type_id;
    this.mapx = mapx;
    this.mapy = mapy;
  }

  public int getPost_pk() {
    return post_pk;
  }

  public void setPost_pk(int post_pk) {
    this.post_pk = post_pk;
  }

  public String getImage() {
    return image;
  }

  public void setImage(String image) {
    this.image = image;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public int getAreacode() {
    return areacode;
  }

  public void setAreacode(int areacode) {
    this.areacode = areacode;
  }

  public int getContent_id() {
    return content_id;
  }

  public void setContent_id(int content_id) {
    this.content_id = content_id;
  }

  public String getCat1() {
    return cat1;
  }

  public void setCat1(String cat1) {
    this.cat1 = cat1;
  }

  public String getCat2() {
    return cat2;
  }

  public void setCat2(String cat2) {
    this.cat2 = cat2;
  }

  public String getCat3() {
    return cat3;
  }

  public void setCat3(String cat3) {
    this.cat3 = cat3;
  }

  public int getContent_type_id() {
    return content_type_id;
  }

  public void setContent_type_id(int content_type_id) {
    this.content_type_id = content_type_id;
  }

  public Double getMapx() {
    return mapx;
  }

  public void setMapx(Double mapx) {
    this.mapx = mapx;
  }

  public Double getMapy() {
    return mapy;
  }

  public void setMapy(Double mapy) {
    this.mapy = mapy;
  }

  @Override
  public String toString() {
    return "PostDto [post_pk=" + post_pk + ", image=" + image + ", description=" + description + ", address=" + address
        + ", title=" + title + ", areacode=" + areacode + ", content_id=" + content_id + ", cat1=" + cat1 + ", cat2=" + cat2 + ", cat3=" + cat3 
        + ", content_type_id=" + content_type_id + ", mapx=" + mapx + ", mapy=" + mapy + "]";
  }
}