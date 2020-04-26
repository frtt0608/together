package com.ssafy.hashtag.db.dao;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ssafy.hashtag.db.dto.CommentDto;
import com.ssafy.hashtag.db.mapper.CommentMapper;
import com.ssafy.hashtag.db.mapper.PostMapper;

@Repository
public class CommentDao implements CommentMapper {

  String ns = "com.ssafy.hashtag.db.";

  public static final Logger logger = LoggerFactory.getLogger(PostMapper.class);

  @Autowired
  private SqlSession sqlSession;
  
  @Override
  public List<CommentDto> allComment(int post_pk) throws Exception {
    return sqlSession.selectList(ns + "allcomment", post_pk);
  }

  @Override
  public void Create_comment(CommentDto commentdto) throws Exception {
    sqlSession.insert(ns + "create_comment", commentdto);
  }

  @Override
  public void Update_comment(CommentDto commentdto) throws Exception {
    sqlSession.update(ns + "update_comment", commentdto);
  }

  @Override
  public void Delete_comment(int comment_pk) throws Exception {
    sqlSession.delete(ns + "delete_comment", comment_pk);
  }
}
