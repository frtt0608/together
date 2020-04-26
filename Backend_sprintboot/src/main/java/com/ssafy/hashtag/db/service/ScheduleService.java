package com.ssafy.hashtag.db.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.hashtag.db.dto.ScheduleDto;

import java.util.List;

import com.ssafy.hashtag.db.dao.ScheduleDao;

@Service
public class ScheduleService {

  @Autowired
  private ScheduleDao scheduledao;

  public String Add_schedule(ScheduleDto scheduledto) throws Exception {
    String msg="일정이 등록되었습니다.";
    int cnt = scheduledao.Check(scheduledto);

    if (cnt >= 1) {
      msg="이미 일정에 있습니다.";
    } else {
      scheduledao.Add_schedule(scheduledto);
    }

    return msg;
  }

  public List<ScheduleDto> allSchedule(int user_pk) throws Exception {
    return scheduledao.allSchedule(user_pk);
  }

  public void Delete_schedule(int schedule_pk) throws Exception {
    scheduledao.Delete_schedule(schedule_pk);
  }
}