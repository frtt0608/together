package com.ssafy.hashtag.db.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import com.ssafy.hashtag.db.dao.ChatDao;
import com.ssafy.hashtag.db.dto.ChatDto;
import com.ssafy.hashtag.db.dto.ChatroomDto;

@Service
public class ChatService {

  @Autowired
  private ChatDao chatdao;

  // 방장이 채팅방 생성
  public ChatDto Create(ChatDto chatdto) throws Exception {
    System.out.println("****************** create chatservice **********************");
    if(chatdao.Check_opener(chatdto.getOpener_id())==0) {
      return chatdao.Create(chatdto);
    }
    return null;
  }

  // 방장이 채팅방 삭제
  public void Delete(ChatDto chatdto) throws Exception {
    System.out.println("****************** delete chatservice **********************");
    chatdao.Delete(chatdto);
  }

  // 지역에 따른 채팅방 리스트
  public List<ChatDto> Locate_chat(String locate) throws Exception {
    System.out.println("****************** Locate_chat chatservice **********************");
    return chatdao.Locate_chat(locate);
  }

  // 입장하기
  public int Enter(ChatroomDto chatroomdto) throws Exception {
    System.out.println("****************** Enter chatservice **********************");
    String check_sex = chatdao.Check(chatroomdto.getChat_id());
    String user_sex = chatdao.Check_user(chatroomdto.getUser_id());
    int cnt = chatdao.Chat_user(chatroomdto);

    if(check_sex.equals("남/여 무관") || check_sex.equals(user_sex)) {
      if(cnt != 0) {
        return 2;
      }
    } else if(!check_sex.equals(user_sex)) {
      return 1;
    }
    chatdao.Enter(chatroomdto);
    return 0;
  }

  // 퇴장하기
  public Boolean Leave(ChatroomDto chatroomdto) throws Exception {
    System.out.println("****************** Leave chatservice **********************");
    if(chatdao.Chat_user(chatroomdto) > 0) {
      chatdao.Leave(chatroomdto);
      return true;
    } else {
      return false;
    }
  }

  // public List<Integer> Chat_user(int chat_pk) throws Exception {
  //   System.out.println("****************** Chat_user chatservice **********************");
  //   return chatdao.Chat_user(chat_pk);
  // }
}