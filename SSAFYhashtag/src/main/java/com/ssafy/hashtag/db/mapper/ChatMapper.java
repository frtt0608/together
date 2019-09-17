package com.ssafy.hashtag.db.mapper;

import java.util.List;

import com.ssafy.hashtag.db.dto.ChatDto;
import com.ssafy.hashtag.db.dto.ChatroomDto;

public interface ChatMapper {
  public ChatDto Create(ChatDto chatdto) throws Exception;
  public void Delete(ChatDto chatdto) throws Exception;
  public List<ChatDto> Locate_chat(String locate) throws Exception;
  public void Enter(ChatroomDto chatroomdto) throws Exception;
  public void Leave(ChatroomDto chatroomdto) throws Exception;
}