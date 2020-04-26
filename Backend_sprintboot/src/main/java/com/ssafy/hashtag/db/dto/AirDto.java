package com.ssafy.hashtag.db.dto;

public class AirDto {
	private String stationName;
	private String dataTime;
	private int pm10Grade1h;
	private int pm25Grade1h;
	
	public AirDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public AirDto(String stationName, String dataTime, int pm10Grade1h, int pm25Grade1h) {
		super();
		this.stationName = stationName;
		this.dataTime = dataTime;
		this.pm10Grade1h = pm10Grade1h;
		this.pm25Grade1h = pm25Grade1h;
	}

	public String getStationName() {
		return stationName;
	}

	public void setStationName(String stationName) {
		this.stationName = stationName;
	}

	public String getDataTime() {
		return dataTime;
	}

	public void setDataTime(String dataTime) {
		this.dataTime = dataTime;
	}

	public int getPm10Grade1h() {
		return pm10Grade1h;
	}

	public void setPm10Grade1h(int pm10Grade1h) {
		this.pm10Grade1h = pm10Grade1h;
	}

	public int getPm25Grade1h() {
		return pm25Grade1h;
	}

	public void setPm25Grade1h(int pm25Grade1h) {
		this.pm25Grade1h = pm25Grade1h;
	}

	@Override
	public String toString() {
		return "AirDto [stationName=" + stationName + ", dataTime=" + dataTime + ", pm10Grade1h=" + pm10Grade1h
				+ ", pm25Grade1h=" + pm25Grade1h + "]";
	}
}
