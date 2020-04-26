package com.ssafy.hashtag.db.service;

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

import com.ssafy.hashtag.db.dto.HospitalDto;

// OpenApi URL : http://apis.data.go.kr/B551182/hospInfoService/getHospBasisList?numOfRows=30&xPos=127.020454&yPos=37.582804&radius=500&ServiceKey=NYKxCjSMfb3OrHOeBxC%2BX6825AJ6jBOYiYXaIrf4i3yjME8xXllNxFn6F6JHOeoxUxsQB8Uz3oDkDLr%2B2t%2F3NA%3D%3D&_type=json
// OpenApi URL 설명 : 반경 현재 좌표( X : 경도  Y : 위도 )를 기준으로 현재 본인의 위치를 받아와서, 반경 500m 이내에 존재하는 병원들을 30개 정도 보여준다.

/*
 * clCdNm : 종별코드명
 * sidoCdNm : 시도명
 * sgguCdNm : 시군구명
 * emdongNm : 읍면동명
 * postNo : 우편번호
 * addr : 주소
 * telno : 전화번호
 * XPos : x좌표
 * YPos : y좌표
 */

@Service
public class HospitalService {

	public List<HospitalDto> getInfo(double XPos, double YPos) throws Exception {
		List<Map<String, Object>> lists = new ArrayList<Map<String, Object>>();
		List<HospitalDto> infos = new ArrayList<HospitalDto>();

		lists = OpenApiHospital(XPos, YPos);

		if (lists == null) {
			return null;
		}

		for (int i = 0; i < lists.size(); i++) {
			Map<String, Object> temp = lists.get(i);

			infos.add(new HospitalDto());

			// clCdNm : 종별코드명
			if (!StringUtils.isEmpty((String) temp.get("clCdNm"))) {
				infos.get(i).setClCdNm((String) temp.get("clCdNm"));
			} else {
				infos.get(i).setClCdNm("");
			}

			// sidoCdNm : 시도명
			if (!StringUtils.isEmpty((String) temp.get("sidoCdNm"))) {
				infos.get(i).setSidoCdNm((String) temp.get("sidoCdNm"));
			} else {
				infos.get(i).setSidoCdNm("");
			}

			// sgguCdNm : 시구군명
			if (!StringUtils.isEmpty((String) temp.get("sgguCdNm"))) {
				infos.get(i).setSgguCdNm((String) temp.get("sgguCdNm"));
			} else {
				infos.get(i).setSgguCdNm("");
			}

			// emdongNm : 읍면동명
			if (!StringUtils.isEmpty((String) temp.get("emdongNm"))) {
				infos.get(i).setEmdongNm((String) temp.get("emdongNm"));
			} else {
				infos.get(i).setEmdongNm("");
			}

			// postNo : 우편번호
			if (!StringUtils.isEmpty(temp.get("postNo").toString())) {
				infos.get(i).setPostNo(temp.get("postNo").toString());
			} else {
				infos.get(i).setPostNo("");
			}

			// addr : 주소
			if (!StringUtils.isEmpty((String) temp.get("addr"))) {
				infos.get(i).setAddr((String) temp.get("addr"));
			} else {
				infos.get(i).setAddr("");
			}

			// telno : 전화번호
			if (!StringUtils.isEmpty((String) temp.get("telno"))) {
				infos.get(i).setTelno((String) temp.get("telno"));
			} else {
				infos.get(i).setTelno("");
			}

			// yadmNm : 병원명
			if (!StringUtils.isEmpty((String) temp.get("yadmNm"))) {
				infos.get(i).setYadmNm((String) temp.get("yadmNm"));
			} else {
				infos.get(i).setYadmNm("");
			}

			// XPos : x좌표
			infos.get(i).setXPos((double) temp.get("XPos"));

			// YPos : y좌표
			infos.get(i).setYPos((double) temp.get("YPos"));
		}
		return infos;
	}

	private List<Map<String, Object>> OpenApiHospital(double xPos, double yPos) throws Exception {

		// OpenApi URL :
		// http://apis.data.go.kr/B551182/hospInfoService/getHospBasisList?numOfRows=30&xPos=127.020454&yPos=37.582804&radius=500&ServiceKey=NYKxCjSMfb3OrHOeBxC%2BX6825AJ6jBOYiYXaIrf4i3yjME8xXllNxFn6F6JHOeoxUxsQB8Uz3oDkDLr%2B2t%2F3NA%3D%3D&_type=json
		String urlstr = "http://apis.data.go.kr/B551182/hospInfoService/getHospBasisList?numOfRows=30&xPos=" + xPos
				+ "&yPos=" + yPos
				+ "&radius=1000&ServiceKey=NYKxCjSMfb3OrHOeBxC%2BX6825AJ6jBOYiYXaIrf4i3yjME8xXllNxFn6F6JHOeoxUxsQB8Uz3oDkDLr%2B2t%2F3NA%3D%3D&_type=json";
		URL url = new URL(urlstr);

		List<Map<String, Object>> hospi_info = new ArrayList<Map<String, Object>>();

		InputStreamReader isr = new InputStreamReader(url.openConnection().getInputStream(), "UTF-8");
		JSONObject object = (JSONObject) JSONValue.parse(isr);

		JSONObject response = (JSONObject) object.get("response");
		JSONObject body = (JSONObject) response.get("body");

		// 검색 결과가 없는 경우,
		int total = Integer.parseInt(body.get("totalCount").toString());
		JSONObject items = null;
		Map<String, Object> tempmap = null;

		if (total == 0) {
			return null;
		} else if (total == 1) {
			items = (JSONObject) body.get("items");
			JSONObject item = (JSONObject) items.get("item");

			tempmap = new HashMap<String, Object>();

			tempmap.put("clCdNm", item.get("clCdNm"));
			tempmap.put("sidoCdNm", item.get("sidoCdNm"));
			tempmap.put("sgguCdNm", item.get("sgguCdNm"));
			tempmap.put("emdongNm", item.get("emdongNm"));
			tempmap.put("postNo", item.get("postNo"));
			tempmap.put("addr", item.get("addr"));
			tempmap.put("telno", item.get("telno"));
			tempmap.put("XPos", item.get("XPos"));
			tempmap.put("YPos", item.get("YPos"));
			tempmap.put("yadmNm", item.get("yadmNm"));

			hospi_info.add(tempmap);
		} else {
			items = (JSONObject) body.get("items");
			JSONArray item = (JSONArray) items.get("item");

			for (int i = 0; i < item.size(); i++) {
				JSONObject data = (JSONObject) item.get(i);

				tempmap = new HashMap<String, Object>();

				tempmap.put("clCdNm", data.get("clCdNm"));
				tempmap.put("sidoCdNm", data.get("sidoCdNm"));
				tempmap.put("sgguCdNm", data.get("sgguCdNm"));
				tempmap.put("emdongNm", data.get("emdongNm"));
				tempmap.put("postNo", data.get("postNo"));
				tempmap.put("addr", data.get("addr"));
				tempmap.put("telno", data.get("telno"));
				tempmap.put("XPos", data.get("XPos"));
				tempmap.put("YPos", data.get("YPos"));
				tempmap.put("yadmNm", data.get("yadmNm"));

				hospi_info.add(tempmap);
			}
		}

		return hospi_info;
	}

}
