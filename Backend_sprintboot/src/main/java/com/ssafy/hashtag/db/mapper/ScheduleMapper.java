package com.ssafy.hashtag.db.mapper;

import java.util.List;

import com.ssafy.hashtag.db.dto.ScheduleDto;

public interface ScheduleMapper {
  public int Check(ScheduleDto scheduledto) throws Exception;
  public void Add_schedule(ScheduleDto scheduledto) throws Exception;
  public void Delete_schedule(int schedule_pk) throws Exception;
  public List<ScheduleDto> allSchedule(int user_pk) throws Exception;
}