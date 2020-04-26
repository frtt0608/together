package com.ssafy.hashtag.db.dto;

public class ScheduleDto {
	private int schedule_pk;
	private int user_id;
	private int post_id;
	private String schedule_title;
	private String schedule_image;
	private String start_date;
	private String end_date;
	private String start_time;
	private String end_time;
	
	public ScheduleDto() {
		super();
		// TODO Auto-generated constructor stub
		}

	public ScheduleDto(int schedule_pk, int user_id, int post_id, String schedule_title, String schedule_image,
			String start_date, String end_date, String start_time, String end_time) {
		super();
		this.schedule_pk = schedule_pk;
		this.user_id = user_id;
		this.post_id = post_id;
		this.schedule_title = schedule_title;
		this.schedule_image = schedule_image;
		this.start_date = start_date;
		this.end_date = end_date;
		this.start_time = start_time;
		this.end_time = end_time;
	}

	public int getSchedule_pk() {
		return schedule_pk;
	}

	public void setSchedule_pk(int schedule_pk) {
		this.schedule_pk = schedule_pk;
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

	public String getSchedule_title() {
		return schedule_title;
	}

	public void setSchedule_title(String schedule_title) {
		this.schedule_title = schedule_title;
	}

	public String getSchedule_image() {
		return schedule_image;
	}

	public void setSchedule_image(String schedule_image) {
		this.schedule_image = schedule_image;
	}

	public String getStart_date() {
		return start_date;
	}

	public void setStart_date(String start_date) {
		this.start_date = start_date;
	}

	public String getEnd_date() {
		return end_date;
	}

	public void setEnd_date(String end_date) {
		this.end_date = end_date;
	}

	public String getStart_time() {
		return start_time;
	}

	public void setStart_time(String start_time) {
		this.start_time = start_time;
	}

	public String getEnd_time() {
		return end_time;
	}

	public void setEnd_time(String end_time) {
		this.end_time = end_time;
	}

	@Override
	public String toString() {
		return "ScheduleDto [schedule_pk=" + schedule_pk + ", user_id=" + user_id + ", post_id=" + post_id
				+ ", schedule_title=" + schedule_title + ", schedule_image=" + schedule_image + ", start_date="
				+ start_date + ", end_date=" + end_date + ", start_time=" + start_time + ", end_time=" + end_time + "]";
	}
}
