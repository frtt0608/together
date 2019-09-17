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
    System.out.println("****************allComment PostService**********************");
    return commentdao.allComment(post_pk);
  }

  public void Create_comment(CommentDto commentdto) throws Exception {
    System.out.println("****************Create_Comment PostService**********************");
    commentdao.Create_comment(commentdto);
  }

  public void Update_comment(CommentDto commentdto) throws Exception {
    System.out.println("****************Update_Comment PostService**********************");
    commentdao.Update_comment(commentdto);
  }

  public void Delete_comment(int comment_pk) throws Exception {
    System.out.println("****************Delete_Comment PostService**********************");
    commentdao.Delete_comment(comment_pk);
  }
}