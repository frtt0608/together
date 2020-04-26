package com.ssafy.hashtag.db.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ssafy.hashtag.db.dto.PostCartDto;
import com.ssafy.hashtag.db.dto.PostDto;
import com.ssafy.hashtag.db.dto.PostLikeDto;
import com.ssafy.hashtag.db.dto.ScoreDto;
import com.ssafy.hashtag.db.mapper.PostMapper;

@Repository
public class PostDao implements PostMapper {
  List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();

  String ns = "com.ssafy.hashtag.db.";

  public static final Logger logger = LoggerFactory.getLogger(PostMapper.class);

  @Autowired
  private SqlSession sqlSession;

  @Override
  public void addlocationBasedList(PostDto postdto) throws Exception {
    sqlSession.insert(ns + "addlocationBasedList", postdto);
  }

  @Override
  public List<PostDto> Areacode(int areacode) throws Exception {
    List<PostDto> posts = sqlSession.selectList(ns + "Areacode" , areacode);
    return posts;
  }

  @Override
  public List<ScoreDto> allScore(int post_pk) throws Exception {
    List<ScoreDto> scores = sqlSession.selectList(ns + "allScore", post_pk);
    return scores;
  }

  @Override
  public void Create_Score(ScoreDto scoredto) throws Exception {
    sqlSession.insert(ns + "create_score", scoredto);
  }

  @Override
  public void Update_Score(ScoreDto scoredto) throws Exception {
    sqlSession.update(ns + "update_score", scoredto);
  }

  @Override
  public void Delete_score(int score_pk) throws Exception {
    sqlSession.delete(ns + "delete_score", score_pk);
  }

  @Override
  public int Post_like(PostLikeDto like) throws Exception {
    int cnt = sqlSession.selectOne(ns + "check_like", like);

    if(cnt==0) {
      sqlSession.insert(ns + "post_like", like);
    } else {
      sqlSession.delete(ns + "post_unlike", like);
    }

    cnt = sqlSession.selectOne(ns + "check_like", like);
    return cnt;
  }

  @Override
  public int Check_like(PostLikeDto like) throws Exception {
    int check = sqlSession.selectOne(ns + "check_like", like);
    return check;
  }

  @Override
  public int Post_cart(PostCartDto cart) throws Exception {
    int cnt = sqlSession.selectOne(ns + "check_cart", cart);

    if(cnt==0) {
      sqlSession.insert(ns + "post_cart", cart);
    } else {
      sqlSession.delete(ns + "post_uncart", cart);
    }

    cnt = sqlSession.selectOne(ns + "check_cart", cart);
    return cnt;
  }

  @Override
  public int Check_cart(PostCartDto cart) throws Exception {
    int check = sqlSession.selectOne(ns + "check_cart", cart);
    return check;
  }

  public List<Integer> posts(int user_pk) throws Exception {
    List<Integer> posts_pk = sqlSession.selectList(ns + "find_post", user_pk);
    return posts_pk;
  }

  @Override
  public PostDto Detail_post(int post_pk) throws Exception {
    return sqlSession.selectOne(ns + "Detail_post", post_pk);
  }
}
