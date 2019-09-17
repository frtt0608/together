package com.ssafy.hashtag.db.controller;

import com.ssafy.hashtag.db.service.BorderService;

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

import java.util.List;

import com.ssafy.hashtag.db.dto.BorderDto;

@CrossOrigin(origins = { "*" }, maxAge = 6000)
@Controller
@RequestMapping("/api/border")
@Api(value = "SSAFY", description = "Board Resouces Management 2019")
public class BorderController {
  public static final Logger logger = 
        LoggerFactory.getLogger(BorderController.class);

  String ns = "com.ssafy.hashtag.db.";

  @Autowired
  private BorderService borderservice;

  @RequestMapping(value = "/", method = RequestMethod.POST)
  public ResponseEntity<List<BorderDto>> allBorder() throws Exception {
    System.out.println("\n****************Border Controller**********************");
    
    List<BorderDto> borders = borderservice.allBorder();
    return new ResponseEntity<List<BorderDto>> (borders, HttpStatus.OK);
  }

  @RequestMapping(value = "/{border_pk}", method = RequestMethod.GET)
  public ResponseEntity<BorderDto> Detail_border(@PathVariable int border_pk) throws Exception {
    System.out.println("\n****************Detail_border Controller**********************");
    BorderDto border = borderservice.Detail_border(border_pk);

    return new ResponseEntity<BorderDto>(border, HttpStatus.OK);
  }

  @RequestMapping(value = "/Create_border", method = RequestMethod.POST)
  public void Create_border(@RequestBody BorderDto borderdto) throws Exception {
    System.out.println("\n****************Create_border Controller**********************");
    
    borderservice.Create_border(borderdto);
    // return new ResponseEntity<BorderDto> (border, HttpStatus.OK);
  }

  @RequestMapping(value = "/{border_pk}/delete", method = RequestMethod.POST)
  public ResponseEntity<String> Delete_border(@PathVariable int border_pk) throws Exception {
    System.out.println("\n****************delete border Controller**********************");
    borderservice.Delete_border(border_pk);
    String message = "게시글을 삭제했습니다.";
    return new ResponseEntity<String> (message, HttpStatus.OK);
  }

  @RequestMapping(value = "/{border_pk}/update", method = RequestMethod.POST)
  public ResponseEntity<String> Update_border(@PathVariable int border_pk, @RequestBody BorderDto borderdto) throws Exception {
    System.out.println("\n****************delete border Controller**********************");
    borderservice.Update_border(borderdto);
    String message = "게시글을 수정했습니다.";
    return new ResponseEntity<String>(message, HttpStatus.OK);
  }
}
