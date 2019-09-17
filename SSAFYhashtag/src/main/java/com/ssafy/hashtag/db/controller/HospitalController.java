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

import com.ssafy.hashtag.db.dto.HospitalDto;
import com.ssafy.hashtag.db.service.HospitalService;

@CrossOrigin(origins = {"*"}, maxAge = 6000)
@Controller
@RequestMapping("/api")
@Api(value="SSAFY", description="Hospital Resouces Management 2019")
public class HospitalController { 
    public static final Logger logger = 
        LoggerFactory.getLogger(HospitalController.class);

    String ns = "com.ssafy.hashtag.db.";
    
    @Autowired
    private HospitalService hospitalservice;
    
    
    @RequestMapping(value = "/FindHospital/{XPos}/{YPos}", method = RequestMethod.GET)
    public ResponseEntity<List<HospitalDto>> FindHospital(@PathVariable double XPos, @PathVariable double YPos) throws Exception {
        logger.info("****************FindHospital HospitalController**********************");
        System.out.println(XPos);
        System.out.println(YPos);
        List<HospitalDto> lists = hospitalservice.getInfo(XPos, YPos);
        System.out.println(lists.toString());
        return new ResponseEntity<List<HospitalDto>>(lists, HttpStatus.OK);
    }

}
