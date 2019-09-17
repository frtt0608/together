package com.ssafy.hashtag.db.controller;

import java.util.List;

import com.ssafy.hashtag.db.dto.ScheduleDto;
import com.ssafy.hashtag.db.service.ScheduleService;

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
@RequestMapping("/api/schedule")
@Api(value = "SSAFY", description = "Schedule Resouces Management 2019")
public class ScheduleController {
  public static final Logger logger = LoggerFactory.getLogger(ScheduleController.class);

  @Autowired
  private ScheduleService scheduleservice;

  @RequestMapping(value = "/add", method = RequestMethod.POST)
  public ResponseEntity<String> Add_schedule(@RequestBody ScheduleDto scheduledto) throws Exception {
    System.out.println("\n****************add ScheduleController**********************");
    String msg = scheduleservice.Add_schedule(scheduledto);
    return new ResponseEntity<String>(msg, HttpStatus.OK);
  }

  @RequestMapping(value = "/{user_pk}", method = RequestMethod.POST)
  public ResponseEntity<List<ScheduleDto>> allSchedule(@PathVariable int user_pk) throws Exception {
    System.out.println("\n****************allschedule ScheduleController**********************");

    List<ScheduleDto> schedules = scheduleservice.allSchedule(user_pk);
    System.out.println(schedules.toString());
    return new ResponseEntity<List<ScheduleDto>>(schedules, HttpStatus.OK);  }

  @RequestMapping(value = "/{schedule_pk}/delete", method = RequestMethod.POST)
  public void Delete_schedule(@PathVariable int schedule_pk) throws Exception {
    System.out.println("\n****************delete ScheduleController**********************");

    scheduleservice.Delete_schedule(schedule_pk);
  }
}