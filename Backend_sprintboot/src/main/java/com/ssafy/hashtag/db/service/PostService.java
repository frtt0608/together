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
        return postdao.Areacode(areacode);
    }

    public List<ScoreDto> allScore(int post_pk) throws Exception {
        return postdao.allScore(post_pk);
    }

    public void Create_score(ScoreDto scoredto) throws Exception {
        postdao.Create_Score(scoredto);
    }

    public void Update_score(ScoreDto scoredto) throws Exception {
        postdao.Update_Score(scoredto);
    }

    public void Delete_score(int score_pk) throws Exception {
        postdao.Delete_score(score_pk);
    }

    public int Post_like(PostLikeDto like) throws Exception {
        int cnt = postdao.Post_like(like);
        return cnt;
    }

    public int Check_like(PostLikeDto like) throws Exception {
        return postdao.Check_like(like);
    }

    public int Post_cart(PostCartDto cart) throws Exception {
        int cnt = postdao.Post_cart(cart);
        return cnt;
    }

    public int Check_cart(PostCartDto cart) throws Exception {
        return postdao.Check_cart(cart);
    }

    public List<PostDto> Incart(int user_pk) throws Exception {
        List<Integer> post_pks = postdao.posts(user_pk); // cart에 담긴 post_pk를 모두 배열에 저장
        
        List<PostDto> posts = new ArrayList<PostDto>(); 

        for(Integer post_pk:post_pks) {
            PostDto postdto = postdao.Detail_post(post_pk);
            posts.add(postdto);
        }

        return posts;
    }
}
