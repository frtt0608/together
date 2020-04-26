package com.ssafy.hashtag.db.dto;

/*
 * title : 제목
 * link : 이미지 링크
 * thumbnail : 썸네일 링크
 * sizeheight : 이미지 세로 길이
 * sizewidth : 이미지 가로 길이
 */

public class ImageDto {
	private String title;
	private String link;
	private String thumbnail;
	private String sizeheight;
	private String sizewidth;
	
	public ImageDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ImageDto(String title, String link, String thumbnail, String sizeheight, String sizewidth) {
		super();
		this.title = title;
		this.link = link;
		this.thumbnail = thumbnail;
		this.sizeheight = sizeheight;
		this.sizewidth = sizewidth;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public String getThumbnail() {
		return thumbnail;
	}

	public void setThumbnail(String thumbnail) {
		this.thumbnail = thumbnail;
	}

	public String getSizeheight() {
		return sizeheight;
	}

	public void setSizeheight(String sizeheight) {
		this.sizeheight = sizeheight;
	}

	public String getSizewidth() {
		return sizewidth;
	}

	public void setSizewidth(String sizewidth) {
		this.sizewidth = sizewidth;
	}

	@Override
	public String toString() {
		return "ImageDto [title=" + title + ", link=" + link + ", thumbnail=" + thumbnail + ", sizeheight=" + sizeheight
				+ ", sizewidth=" + sizewidth + "]";
	}
}
