package com.ssafy.hashtag.db.controller;
 
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import io.swagger.annotations.Api;

import com.ssafy.hashtag.db.dto.AirDto;
import com.ssafy.hashtag.db.service.AirService;

@CrossOrigin(origins = {"*"}, maxAge = 6000)
@Controller
@RequestMapping("/api")
@Api(value="SSAFY", description="Air Resouces Management 2019")
public class AirController { 
    public static final Logger logger = 
        LoggerFactory.getLogger(AirController.class);

    String ns = "com.ssafy.hashtag.db.";
    
    @Autowired
    private AirService airservice;
    
    
    @RequestMapping(value = "/FindDust/{city}", method = RequestMethod.GET)
    public ResponseEntity<List<AirDto>> FindDust(@PathVariable String city) throws Exception {
        System.out.println("****************FindDust AirController**********************");
        List<AirDto> lists = airservice.getInfo(city);
        
        return new ResponseEntity<List<AirDto>>(lists, HttpStatus.OK);
    }

}
