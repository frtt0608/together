package com.ssafy.hashtag.db.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.ssafy.hashtag.db.dto.PostCartDto;
import com.ssafy.hashtag.db.dto.PostDto;
import com.ssafy.hashtag.db.dto.PostLikeDto;
import com.ssafy.hashtag.db.dto.ScoreDto;
import com.ssafy.hashtag.db.dao.PostDao;

@Service
public class PostService {

    @Autowired
    private PostDao postdao;

    public List<PostDto> Areacode(int areacode) throws Exception {
        System.out.println("****************Areacode PostService**********************");

        return postdao.Areacode(areacode);
    }

    public List<ScoreDto> allScore(int post_pk) throws Exception {
        System.out.println("****************allScore PostService**********************");
        return postdao.allScore(post_pk);
    }

    public void Create_score(ScoreDto scoredto) throws Exception {
        System.out.println("****************Create_score PostService**********************");
        postdao.Create_Score(scoredto);
    }

    public void Update_score(ScoreDto scoredto) throws Exception {
        System.out.println("****************Update_score PostService**********************");
        postdao.Update_Score(scoredto);
    }

    public void Delete_score(int score_pk) throws Exception {
        System.out.println("****************Delete_score PostService**********************");
        postdao.Delete_score(score_pk);
    }

    public int Post_like(PostLikeDto like) throws Exception {
        System.out.println("****************Post like PostService**********************");
        int cnt = postdao.Post_like(like);
        return cnt;
    }

    public int Check_like(PostLikeDto like) throws Exception {
        System.out.println("****************Check like PostService**********************");
        return postdao.Check_like(like);
    }

    public int Post_cart(PostCartDto cart) throws Exception {
        System.out.println("****************Post cart PostService**********************");
        int cnt = postdao.Post_cart(cart);
        return cnt;
    }

    public int Check_cart(PostCartDto cart) throws Exception {
        System.out.println("****************Check cart PostService**********************");
        return postdao.Check_cart(cart);
    }

    public List<PostDto> Incart(int user_pk) throws Exception {
        System.out.println("****************Incart PostService**********************");
        List<Integer> post_pks = postdao.posts(user_pk); // cart에 담긴 post_pk를 모두 배열에 저장
        System.out.println(post_pks);
        System.out.println(post_pks.getClass());
        
        List<PostDto> posts = new ArrayList<PostDto>(); 

        for(Integer post_pk:post_pks) {
            PostDto postdto = postdao.Detail_post(post_pk);
            posts.add(postdto);
        }

        return posts;
    }





//     public void locationBasedList() throws Exception {
//         System.out.println("****************add locationBasedList**********************");

//         List<Map<String, Object>> lists = new ArrayList<Map<String, Object>>();

//         int[] codes = { 1, 2, 3, 4, 5, 6, 7, 8, 31, 32, 33, 34, 35, 36, 37, 38, 39 };

//         for (int j = 0; j < codes.length; j++) {
//             lists = insertInfo(codes[j]);

//             PostDto postdto = new PostDto();
//             // String non_image =
//             // "https://images.unsplash.com/photo-1536765659537-ac6b544ea73b?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=500&q=60";

//             for (int i = 0; i < lists.size(); i++) {
//                 // System.out.println("****************add in for**********************");

//                 Map map = lists.get(i);
//                 postdto.setAddress((String) map.get("address"));

//                 if (map.get("image") != null) {
//                     postdto.setImage((String) map.get("image"));
//                 } else {
//                     postdto.setImage(
//                             "https://images.unsplash.com/photo-1536765659537-ac6b544ea73b?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=500&q=60");
//                 }
//                 postdto.setTitle((String) map.get("title"));
//                 postdto.setAreacode((int) map.get("areacode"));
//                 postdto.setContent_id((int) map.get("content_id"));
//                 postdto.setCat1((String) map.get("cat1"));
//                 postdto.setCat2((String) map.get("cat2"));
//                 postdto.setCat3((String) map.get("cat3"));
//                 postdto.setContent_type_id((int) map.get("content_type_id"));

//                 if (map.get("mapx") != null && map.get("mapy") != null) {
//                     postdto.setMapx((double) map.get("mapx"));
//                     postdto.setMapy((double) map.get("mapy"));
//                 } else {
//                     postdto.setMapx(0.0);
//                     postdto.setMapy(0.0);
//                 }
//                 postdao.addlocationBasedList(postdto);
//             }
//         }
//     }

//     public List<Map<String, Object>> insertInfo(int n) throws Exception {
//         // System.out.println("****************insertInfo**********************");

//         List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
//         String base_url = "http://api.visitkorea.or.kr/openapi/service/rest/KorService/areaBasedList?ServiceKey=";
//         String key = "rbssLvuPaumnRlgRCPsgU5IeLlHAf5nHHGU8P3JVSYqJvgSFL8l%2FJbCYNE9zVd5Je%2BFoFlSBFo%2Fochd7h97a%2Fg%3D%3D";
//         URL url = new URL(base_url + key + "&areaCode=" + n + "&numOfRows=5000&MobileOS=ETC&MobileApp=AppTest");
//         System.out.println(url);
//         XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
//         factory.setNamespaceAware(true);
//         XmlPullParser xpp = factory.newPullParser();
//         BufferedInputStream bis = new BufferedInputStream(url.openStream());
//         xpp.setInput(bis, "utf-8");

//         String tag = null;
//         int event_type = xpp.getEventType();

//         Map<String, Object> tempMap = null;

//         while (event_type != XmlPullParser.END_DOCUMENT) {
//             if (event_type == XmlPullParser.START_TAG) {
//                 tag = xpp.getName();
//                 if (tag.equals("item")) {
//                     tempMap = new HashMap<String, Object>();
//                 }
//             } else if (event_type == XmlPullParser.TEXT) {
//                 // System.out.println(tag.equals("firstimage"));
//                 if (tag.equals("firstimage")) {
//                     tempMap.put("image", xpp.getText());
//                 } else if (tag.equals("addr1")) {
//                     tempMap.put("address", xpp.getText());
//                 } else if (tag.equals("title")) {
//                     tempMap.put("title", xpp.getText());
//                 } else if (tag.equals("areacode")) {
//                     tempMap.put("areacode", Integer.parseInt(xpp.getText()));
//                 } else if (tag.equals("contentid")) {
//                     tempMap.put("content_id", Integer.parseInt(xpp.getText()));
//                 } else if (tag.equals("cat1")) {
//                     tempMap.put("cat1", xpp.getText());
//                 } else if (tag.equals("cat2")) {
//                     tempMap.put("cat2", xpp.getText());
//                 } else if (tag.equals("cat3")) {
//                     tempMap.put("cat3", xpp.getText());
//                 } else if (tag.equals("contenttypeid")) {
//                     tempMap.put("content_type_id", Integer.parseInt(xpp.getText()));
//                 } else if (tag.equals("mapx")) {
//                     tempMap.put("mapx", Double.parseDouble(xpp.getText()));
//                 } else if (tag.equals("mapy")) {
//                     tempMap.put("mapy", Double.parseDouble(xpp.getText()));
//                 }
//             } else if (event_type == XmlPullParser.END_TAG) {
//                 tag = xpp.getName();
//                 if (tag.equals("item")) {
//                     list.add(tempMap);
//                 }
//             }
//             event_type = xpp.next();
//         }
//         bis.close();
//         return list;
//     }
}
