package com.ssafy.hashtag.db.service;

import java.util.List;

import com.ssafy.hashtag.db.dao.CommentDao;
import com.ssafy.hashtag.db.dto.CommentDto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentService {

  @Autowired
  private CommentDao commentdao;
  
  public List<CommentDto> allComment(int post_pk) throws Exception {
    return commentdao.allComment(post_pk);
  }

  public void Create_comment(CommentDto commentdto) throws Exception {
    commentdao.Create_comment(commentdto);
  }

  public void Update_comment(CommentDto commentdto) throws Exception {
    commentdao.Update_comment(commentdto);
  }

  public void Delete_comment(int comment_pk) throws Exception {
    commentdao.Delete_comment(comment_pk);
  }
}