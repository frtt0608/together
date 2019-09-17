package com.ssafy.hashtag.db.dto;

public class BorderDto {
	private int border_pk;
	private String title;
	private String content;
	private String name;
	private int hits;
  private String created_at;
  
	public BorderDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public BorderDto(int border_pk, String title, String content, String name, int hits, String created_at) {
		super();
		this.border_pk = border_pk;
		this.title = title;
		this.content = content;
		this.name = name;
    this.hits = hits;
    this.created_at = created_at;
	}
	
	public int getborder_pk() {
		return border_pk;
	}
	public void setborder_pk(int border_pk) {
		this.border_pk = border_pk;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getHits() {
		return hits;
	}
	public void setHits(int hits) {
		this.hits = hits;
	}

	public void setCreated_at(String created_at) {
    this.created_at = created_at;
  }

  public String getCreated_at() {
    return created_at;
  }

	@Override
	public String toString() {
		return "BorderDto [border_pk=" + border_pk + ", title=" + title + ", content=" + content + ", name=" + name
				+ ", hits=" + hits + ", created_at=" + created_at + "]";
	}
}