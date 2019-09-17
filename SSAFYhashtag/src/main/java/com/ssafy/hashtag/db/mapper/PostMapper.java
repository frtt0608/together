package com.ssafy.hashtag.db.mapper;
 
import java.util.List;

import com.ssafy.hashtag.db.dto.PostCartDto;
import com.ssafy.hashtag.db.dto.PostDto;
import com.ssafy.hashtag.db.dto.PostLikeDto;
import com.ssafy.hashtag.db.dto.ScoreDto;

public interface PostMapper {
 
    // public List<Test> Getall() throws Exception;
    public void addlocationBasedList(PostDto postdto) throws Exception;
    public List<PostDto> Areacode(int areacode) throws Exception;
    
    public List<ScoreDto> allScore(int post_pk) throws Exception;
    public void Create_Score(ScoreDto scoredto) throws Exception;
    public void Update_Score(ScoreDto scoredto) throws Exception;
    public void Delete_score(int score_pk) throws Exception;

    public int Post_like(PostLikeDto like) throws Exception;
    public int Check_like(PostLikeDto like) throws Exception;

    public int Post_cart(PostCartDto cart) throws Exception;
    public int Check_cart(PostCartDto cart) throws Exception;

    public PostDto Detail_post(int user_pk) throws Exception;
}