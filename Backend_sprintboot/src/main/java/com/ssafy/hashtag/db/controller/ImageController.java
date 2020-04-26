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

import com.ssafy.hashtag.db.dto.ImageDto;
import com.ssafy.hashtag.db.service.ImageService;

import io.swagger.annotations.Api;

@CrossOrigin(origins = {"*"}, maxAge = 6000)
@Controller
@RequestMapping("/api")
@Api(value="SSAFY", description="SearchImage Resouces Management 2019")
public class ImageController {
	public static final Logger Logger = LoggerFactory.getLogger(ImageController.class);
	
	String ns = "com.ssafy.hashtag.db.";
	
	@Autowired
	private ImageService imageservice;
	
	@RequestMapping(value = "/SearchImage/{text}", method = RequestMethod.GET)
	public ResponseEntity<List<ImageDto>> SearchImage(@PathVariable String text){
		Logger.info("*************************SearchImage ImageController******************************");
		List<ImageDto> lists = imageservice.getInfo(text);
		
		return new ResponseEntity<List<ImageDto>>(lists, HttpStatus.OK);
	}
}
