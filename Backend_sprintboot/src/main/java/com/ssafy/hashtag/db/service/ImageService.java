package com.ssafy.hashtag.db.service;

import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.springframework.stereotype.Service;

import com.ssafy.hashtag.db.dto.ImageDto;

// NAVER OPENAPI URL : https://openapi.naver.com/v1/search/image.json?query=" + text + "&display=" + display + "&";
// NAVER OPENAPI URL 설명 : 검색어(text)를 입력하여 나오는 결과를 json형태로 보여준다.

/*
 * title : 제목
 * link : 이미지 링크
 * thumbnail : 썸네일 링크
 * sizeheight : 이미지 세로 길이
 * sizewidth : 이미지 가로 길이
 */
@Service
public class ImageService {
	public List<ImageDto> getInfo(String search_text) {
		List<Map<String, Object>> lists = new ArrayList<Map<String, Object>>();
		List<ImageDto> infos = new ArrayList<ImageDto>();

		lists = OpenApiImage(search_text);

		for (int i = 0; i < lists.size(); i++) {
			Map<String, Object> temp = lists.get(i);

			infos.add(new ImageDto());

			// title : 제목
			if (!StringUtils.isEmpty((String) temp.get("title"))) {
				infos.get(i).setTitle((String) temp.get("title"));
			} else {
				infos.get(i).setTitle("");
			}

			// link : 이미지 링크
			if (!StringUtils.isEmpty((String) temp.get("link"))) {
				infos.get(i).setLink((String) temp.get("link"));
			} else {
				infos.get(i).setLink("");
			}

			// thumbnail : 썸네일 링크
			if (!StringUtils.isEmpty((String) temp.get("thumbnail"))) {
				infos.get(i).setThumbnail((String) temp.get("thumbnail"));
			} else {
				infos.get(i).setThumbnail("");
			}

			// sizeheight : 이미지 세로 길이
			if (!StringUtils.isEmpty((String) temp.get("sizeheight"))) {
				infos.get(i).setSizeheight((String) temp.get("sizeheight"));
			} else {
				infos.get(i).setSizeheight("");
			}

			// sizewidth : 이미지 가로 길이
			if (!StringUtils.isEmpty((String) temp.get("sizewidth"))) {
				infos.get(i).setSizewidth((String) temp.get("sizewidth"));
			} else {
				infos.get(i).setSizewidth("");
			}
		}
		return infos;
	}

	private List<Map<String, Object>> OpenApiImage(String search_text) {
		String clientId = "mjEhXv98N91tGkVf_JIQ";// 애플리케이션 클라이언트 아이디값";
		String clientSecret = "WV3Obq7PxV";// 애플리케이션 클라이언트 시크릿값";\
		int display = 5; // 검색결과갯수. 최대100개

		List<Map<String, Object>> img_info = new ArrayList<Map<String, Object>>();

		try {
			String text = URLEncoder.encode(search_text, "utf-8");
			String apiURL = "https://openapi.naver.com/v1/search/image.json?query=" + text + "&display=" + display
					+ "&";

			URL url = new URL(apiURL);
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			con.setRequestMethod("GET");
			con.setRequestProperty("X-Naver-Client-Id", clientId);
			con.setRequestProperty("X-Naver-Client-Secret", clientSecret);
			int responseCode = con.getResponseCode();
			InputStreamReader isr = null;

			if (responseCode == 200) {
				isr = new InputStreamReader(con.getInputStream(), "UTF-8");
			} else {
				isr = new InputStreamReader(con.getInputStream(), "UTF-8");
				;
			}

			JSONObject object = (JSONObject) JSONValue.parse(isr);
			JSONArray items = (JSONArray) object.get("items");

			Map<String, Object> tempmap = null;

			for (int i = 0; i < items.size(); i++) {
				JSONObject data = (JSONObject) items.get(i);

				tempmap = new HashMap<String, Object>();

				tempmap.put("title", data.get("title"));
				tempmap.put("link", data.get("link"));
				tempmap.put("thumbnail", data.get("thumbnail"));
				tempmap.put("sizeheight", data.get("sizeheight"));
				tempmap.put("sizewidth", data.get("sizewidth"));

				img_info.add(tempmap);
			}

			con.disconnect();
		} catch (Exception e) {
			System.out.println(e);
		}

		return img_info;
	}
}
