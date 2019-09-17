package com.ssafy.hashtag.db.controller;

import java.util.List;

import com.ssafy.hashtag.db.dto.CommentDto;
import com.ssafy.hashtag.db.service.CommentService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import io.swagger.annotations.Api;

@CrossOrigin(origins = { "*" }, maxAge = 6000)
@Controller
@RequestMapping("/api/post")
@Api(value = "SSAFY", description = "Comment Resouces Management 2019")
public class CommentController {
  public static final Logger logger = LoggerFactory.getLogger(CommentController.class);

  @Autowired
  private CommentService commentservice;

  // Comment CRUD
  @RequestMapping(value = "/{post_pk}/comment", method = RequestMethod.GET)
  public ResponseEntity<List<CommentDto>> allComment(@PathVariable int post_pk) throws Exception {
    System.out.println("****************all_comment Controller**********************");

    List<CommentDto> comments = commentservice.allComment(post_pk);
    System.out.println(comments.toString());
    return new ResponseEntity<List<CommentDto>>(comments, HttpStatus.OK);
  }

  @RequestMapping(value = "/{post_pk}/create_comment", method = RequestMethod.POST)
  public ResponseEntity<String> Create_comment(@RequestBody CommentDto commentdto, @PathVariable int post_pk)
      throws Exception {
    System.out.println("****************create_comment Controller**********************");
    String message = "create comment";
    commentdto.setPost_id(post_pk);
    commentservice.Create_comment(commentdto);

    return new ResponseEntity<String>(message, HttpStatus.OK);
  }

  @RequestMapping(value = "/{comment_pk}/update_comment", method = RequestMethod.POST)
  public ResponseEntity<String> Update_comment(@RequestBody CommentDto commentdto, @PathVariable int comment_pk)
      throws Exception {
    System.out.println("****************update_comment Controller**********************");
    commentservice.Update_comment(commentdto);
    String message = "update comment";

    return new ResponseEntity<String>(message, HttpStatus.OK);
  }

  @RequestMapping(value = "/{comment_pk}/delete_comment", method = RequestMethod.POST)
  public ResponseEntity<String> Delete_comment(@PathVariable int comment_pk) throws Exception {
    System.out.println("****************delete_comment Controller**********************");
    commentservice.Delete_comment(comment_pk);
    String message = "delete comment";
    return new ResponseEntity<String>(message, HttpStatus.OK);
  }
}