package com.ssafy.hashtag.db.controller;
 
import java.util.List;

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


import com.ssafy.hashtag.db.dto.PostCartDto;
import com.ssafy.hashtag.db.dto.PostDto;
import com.ssafy.hashtag.db.dto.PostLikeDto;
import com.ssafy.hashtag.db.dto.ScoreDto;
import com.ssafy.hashtag.db.service.PostService;



@CrossOrigin(origins = {"*"}, maxAge = 6000)
@Controller
@RequestMapping("/api/post")
@Api(value="SSAFY", description="Post Resouces Management 2019")
public class PostController { 
    public static final Logger logger = 
        LoggerFactory.getLogger(PostController.class);

    
    @Autowired
    private PostService postservice;

    // @RequestMapping(value = "/addlocationBasedList", method = RequestMethod.POST)
    // public void addlocationBasedList() throws Exception {
    //     logger.info("\n****************add Controller**********************");
    //     postservice.locationBasedList();
    // }
    
    @RequestMapping(value = "/areacode/{areacode}", method = RequestMethod.GET)
    public ResponseEntity<List<PostDto>> Areacode(@PathVariable int areacode) throws Exception {
        System.out.println("****************AreaCode Controller**********************");
        List<PostDto> posts = postservice.Areacode(areacode);
        
        return new ResponseEntity<List<PostDto>>(posts, HttpStatus.OK);
    }

    // Score CRUD
    @RequestMapping(value = "/{post_pk}/score", method = RequestMethod.GET)
    public ResponseEntity<List<ScoreDto>> allScore(@PathVariable int post_pk) throws Exception {
        System.out.println("****************allComment Controller**********************");
        List<ScoreDto> scores = postservice.allScore(post_pk);
        
        return new ResponseEntity<List<ScoreDto>>(scores, HttpStatus.OK);
    }

    @RequestMapping(value = "/{post_pk}/create_score", method = RequestMethod.POST)
    public ResponseEntity<String> Create_Score(@RequestBody ScoreDto scoredto, @PathVariable int post_pk) throws Exception {
        System.out.println("****************create_score Controller**********************");
        scoredto.setPost_id(post_pk);
        postservice.Create_score(scoredto);
        String message = "평점등록 했습니다.";
        
        return new ResponseEntity<String>(message, HttpStatus.OK);
    }

    @RequestMapping(value = "/{score_pk}/update_score", method = RequestMethod.POST)
    public ResponseEntity<String> Update_score(@RequestBody ScoreDto scoredto, @PathVariable int score_pk) throws Exception {
        System.out.println("****************update_score Controller**********************");
        postservice.Update_score(scoredto);
        String message = "평점을 수정했습니다.";
        
        return new ResponseEntity<String>(message, HttpStatus.OK);
    }
 
    @RequestMapping(value = "/{score_pk}/delete_score", method = RequestMethod.GET)
    public ResponseEntity<String> Delete_score(@PathVariable int score_pk) throws Exception {
        System.out.println("****************delete_score Controller**********************");
        postservice.Delete_score(score_pk);
        String message = "평점을 삭제했습니다.";
        
        return new ResponseEntity<String>(message, HttpStatus.OK);
    }
    
    // post에 좋아요
    @RequestMapping(value="/{post_pk}/like", method=RequestMethod.POST)
    public ResponseEntity<Integer> Post_like(@PathVariable int post_pk, @RequestBody PostLikeDto like) throws Exception {
        System.out.println("****************like Controller**********************");

        int cnt = postservice.Post_like(like);

        return new ResponseEntity<Integer>(cnt, HttpStatus.OK);
    }
    
    // post에 좋아요 확인하기
    @RequestMapping(value="/{post_pk}/checklike", method=RequestMethod.POST)
    public ResponseEntity<Integer> Check_like(@PathVariable int post_pk, @RequestBody PostLikeDto like) throws Exception {
        System.out.println("****************check_like Controller**********************");

        int check = postservice.Check_like(like);

        return new ResponseEntity<Integer>(check, HttpStatus.OK);
    }

    // cart에 추가하기
    @RequestMapping(value="/{post_pk}/cart", method=RequestMethod.POST)
    public ResponseEntity<Integer> Post_like(@PathVariable int post_pk, @RequestBody PostCartDto cart) throws Exception {
        System.out.println("****************cart Controller**********************");

        int cnt = postservice.Post_cart(cart);

        return new ResponseEntity<Integer>(cnt, HttpStatus.OK);
    }

    // cart에 있는지 확인하기
    @RequestMapping(value="/{post_pk}/checkcart", method=RequestMethod.POST)
    public ResponseEntity<Integer> Check_cart(@PathVariable int post_pk, @RequestBody PostCartDto cart) throws Exception {
        System.out.println("****************check_cart Controller**********************");

        int check = postservice.Check_cart(cart);

        return new ResponseEntity<Integer>(check, HttpStatus.OK);
    }

    // user_pk로 cart에 담긴 post 가져오기
    @RequestMapping(value="/{user_pk}/incart", method=RequestMethod.POST)
    public ResponseEntity<List<PostDto>> Incart(@PathVariable int user_pk) throws Exception {
        System.out.println("****************Incart Controller**********************");
        List<PostDto> posts = postservice.Incart(user_pk);

        return new ResponseEntity<List<PostDto>> (posts, HttpStatus.OK);
    }
    
    
}
