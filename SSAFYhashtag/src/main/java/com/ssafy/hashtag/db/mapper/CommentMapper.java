package com.ssafy.hashtag.db.mapper;
 
import java.util.List;

import com.ssafy.hashtag.db.dto.CommentDto;

public interface CommentMapper {
 
    // public List<Test> Getall() throws Exception;
    public List<CommentDto> allComment(int post_pK) throws Exception;
    public void Create_comment(CommentDto commentdto) throws Exception;
    public void Update_comment(CommentDto commentdto) throws Exception;
    public void Delete_comment(int comment_pk) throws Exception;
}