package com.ssafy.hashtag.db.dao;

import java.util.List;

import com.ssafy.hashtag.db.dto.ChatDto;
import com.ssafy.hashtag.db.dto.ChatroomDto;
import com.ssafy.hashtag.db.mapper.ChatMapper;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ChatDao implements ChatMapper {
  
  String ns = "com.ssafy.hashtag.db.";

  @Autowired
  private SqlSession sqlSession;

  // 채팅방 생성하기
  @Override
  public ChatDto Create(ChatDto chatdto) throws Exception {
    System.out.println("****************** create chatdao **********************");
    sqlSession.insert(ns + "create", chatdto);
    ChatDto chat = sqlSession.selectOne(ns + "detail");
    sqlSession.insert(ns + "enter_admin", chat);
    return chat;
  }

  // 채팅방 생성 시 이미 채팅방을 만들었는지 확인하기
  public int Check_opener(int user_pk) throws Exception {
    System.out.println("****************** Check_create chatdao **********************");
    return sqlSession.selectOne(ns + "check_create", user_pk);
  }

  // 채팅방 삭제하기
  @Override
  public void Delete(ChatDto chatdto) throws Exception {
    System.out.println("****************** delete chatdao **********************");
    sqlSession.delete(ns + "delete", chatdto);
  }

  // 지역에 따른 채팅방 리스트
  @Override
  public List<ChatDto> Locate_chat(String locate) throws Exception {
    System.out.println("****************** Locate chatdao **********************");
    return sqlSession.selectList(ns + "locate", locate);
  }

  // 채팅방 입장하기
  @Override
  public void Enter(ChatroomDto chatroomdto) throws Exception {
    System.out.println("****************** Enter chatdao **********************");
    sqlSession.insert(ns + "enter", chatroomdto);
    int user_cnt = sqlSession.selectOne(ns + "user_cnt", chatroomdto.getChat_id());
    ChatDto chat = new ChatDto();
    chat.setChat_pk(chatroomdto.getChat_id());
    chat.setUser_cnt(user_cnt);

    sqlSession.update(ns + "user_cnt_update", chat);
  }

  // 채팅방 퇴장하기
  @Override
  public void Leave(ChatroomDto chatroomdto) throws Exception {
    System.out.println("****************** Leave chatdao **********************");
    sqlSession.delete(ns + "leave", chatroomdto);
    int user_cnt = sqlSession.selectOne(ns + "user_cnt", chatroomdto.getChat_id());
    ChatDto chat = new ChatDto();
    chat.setChat_pk(chatroomdto.getChat_id());
    chat.setUser_cnt(user_cnt);

    sqlSession.update(ns + "user_cnt_update", chat);
  }

  // 채팅방에 설정된 성별 확인
  public String Check(int chat_pk) throws Exception {
    System.out.println("****************** Check chatdao **********************");
    return sqlSession.selectOne(ns + "check_chat", chat_pk);
  }
  // 채팅방에 입장하려는 유저의 성별 확인
  public String Check_user(int user_pk) throws Exception {
    System.out.println("****************** Check_user chatdao **********************");
    return sqlSession.selectOne(ns + "check_user", user_pk);
  }
  // 채팅방에 유저가 있는지 확인
  public int Chat_user(ChatroomDto chatroomdto) throws Exception {
    System.out.println("****************** Chat_user chatdao **********************");
    return sqlSession.selectOne(ns + "chat_user", chatroomdto);
  }


}
