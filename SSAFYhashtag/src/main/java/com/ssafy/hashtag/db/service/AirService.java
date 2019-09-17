package com.ssafy.hashtag.db.service;

import java.io.BufferedInputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.springframework.stereotype.Service;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import com.ssafy.hashtag.db.dto.AirDto;

@Service
public class AirService {

	public List<AirDto> getInfo(String city) throws Exception {

		List<Map<String, Object>> lists = new ArrayList<Map<String, Object>>();
		List<AirDto> infos = new ArrayList<AirDto>();

		lists = insertInfoJson(city);

		for (int i = 0; i < lists.size(); i++) {
			// System.out.println("*****************add in for***********************");

			Map<String, Object> temp = lists.get(i);

			infos.add(new AirDto());

			// stationname
			if (temp.get("stationName") != null) {
				infos.get(i).setStationName((String) temp.get("stationName"));
			}

			// dataTime
			if (temp.get("dataTime") != null) {
				infos.get(i).setDataTime((String) temp.get("dataTime"));
			}

			// pm10Grade1h
			if (!StringUtils.isEmpty((String)temp.get("pm10Grade1h"))) {
				infos.get(i).setPm10Grade1h(Integer.parseInt((String)temp.get("pm10Grade1h")));
			} else {
				infos.get(i).setPm10Grade1h(0);
			}

			// pm25Grade1h
			if (!StringUtils.isEmpty((String)temp.get("pm25Grade1h"))) {
				infos.get(i).setPm25Grade1h(Integer.parseInt((String)temp.get("pm25Grade1h")));
			} else {
				infos.get(i).setPm25Grade1h(0);
			}
		}
		return infos;
	}
	
	// xml parser 사용 x
	public List<Map<String, Object>> insertInfo(String city) throws Exception {
		System.out.println("********************insertInfo********************");

		// dust api
		// http://openapi.airkorea.or.kr/openapi/services/rest/ArpltnInforInqireSvc/getCtprvnRltmMesureDnsty?sidoName=대전&pageNo=1&numOfRows=10&ServiceKey=rbssLvuPaumnRlgRCPsgU5IeLlHAf5nHHGU8P3JVSYqJvgSFL8l%2FJbCYNE9zVd5Je%2BFoFlSBFo%2Fochd7h97a%2Fg%3D%3D&ver=1.3
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		String front_url = "http://openapi.airkorea.or.kr/openapi/services/rest/ArpltnInforInqireSvc/getCtprvnRltmMesureDnsty?sidoName=";
		String last_url = "&pageNo=1&numOfRows=10&ServiceKey=rbssLvuPaumnRlgRCPsgU5IeLlHAf5nHHGU8P3JVSYqJvgSFL8l%2FJbCYNE9zVd5Je%2BFoFlSBFo%2Fochd7h97a%2Fg%3D%3D&ver=1.3";
		URL url = new URL(front_url + city + last_url);

		// print url
		System.out.println(url);

		XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
		factory.setNamespaceAware(true);
		XmlPullParser xpp = factory.newPullParser();
		BufferedInputStream bis = new BufferedInputStream(url.openStream());
		xpp.setInput(bis, "utf-8");

		String tag = null;
		int event_type = xpp.getEventType();

		Map<String, Object> tempMap = null;

		while (event_type != XmlPullParser.END_DOCUMENT) {
			if (event_type == XmlPullParser.START_TAG) {
				tag = xpp.getName();
				if (tag.equals("item")) {
					tempMap = new HashMap<String, Object>();
				}
			} else if (event_type == XmlPullParser.TEXT) {
				if (tag.equals("stationName")) {
					tempMap.put("stationName", xpp.getText());
				} else if (tag.equals("dataTime")) {
					tempMap.put("dataTime", xpp.getText());
				} else if (tag.equals("pm10Grade1h")) {
					tempMap.put("pm10Grade1h", xpp.getText());
				} else if (tag.equals("pm25Grade1h")) {
					tempMap.put("pm25Grade1h", xpp.getText());
				}
			} else if (event_type == XmlPullParser.END_TAG) {
				tag = xpp.getName();
				if (tag.equals("item")) {
					list.add(tempMap);
				}
			}
			event_type = xpp.next();
		}
		bis.close();
		return list;
	}

	public List<Map<String, Object>> insertInfoJson(String location) throws Exception {
		// URL
		// http://openapi.airkorea.or.kr/openapi/services/rest/ArpltnInforInqireSvc/getCtprvnRltmMesureDnsty?sidoName=%EB%8C%80%EC%A0%84&pageNo=1&numOfRows=10&ServiceKey=rbssLvuPaumnRlgRCPsgU5IeLlHAf5nHHGU8P3JVSYqJvgSFL8l%2FJbCYNE9zVd5Je%2BFoFlSBFo%2Fochd7h97a%2Fg%3D%3D&ver=1.3&_returnType=json
		String urlstr = "http://openapi.airkorea.or.kr/openapi/services/rest/ArpltnInforInqireSvc/getCtprvnRltmMesureDnsty?sidoName="
				+ location
				+ "&pageNo=1&numOfRows=10&ServiceKey=rbssLvuPaumnRlgRCPsgU5IeLlHAf5nHHGU8P3JVSYqJvgSFL8l%2FJbCYNE9zVd5Je%2BFoFlSBFo%2Fochd7h97a%2Fg%3D%3D&ver=1.3&_returnType=json";
		URL url = new URL(urlstr);

		List<Map<String, Object>> dust_info = new ArrayList<Map<String, Object>>();

		InputStreamReader isr = new InputStreamReader(url.openConnection().getInputStream(), "UTF-8");
		JSONObject object = (JSONObject) JSONValue.parse(isr);

		JSONArray lists = (JSONArray) object.get("list");
		Map<String, Object> tempmap = null;
		

		for (int i = 0; i < lists.size(); i++) {
			JSONObject data = (JSONObject) lists.get(i);
			
			tempmap = new HashMap<String, Object>();
			
			tempmap.put("stationName", data.get("stationName"));
			tempmap.put("dataTime", data.get("dataTime"));
			tempmap.put("pm10Grade1h", data.get("pm10Grade1h"));
			tempmap.put("pm25Grade1h", data.get("pm25Grade1h"));

			dust_info.add(tempmap);
		}

		return dust_info;
	}

}
