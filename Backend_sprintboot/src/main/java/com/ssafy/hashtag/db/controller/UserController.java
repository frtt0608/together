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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import io.swagger.annotations.Api;

import com.ssafy.hashtag.db.dto.LoginUserDto;
import com.ssafy.hashtag.db.dto.UserDto;

import com.ssafy.hashtag.db.service.UserService;


@CrossOrigin(origins = { "*" }, maxAge = 6000)
@Controller
@RequestMapping("/api/user")
@Api(value = "SSAFY", description = "User Resouces Management 2019")
public class UserController {
    public static final Logger logger = 
        LoggerFactory.getLogger(UserController.class);

    String ns = "com.ssafy.hashtag.db.";

    @Autowired
    private UserService userservice;

    @RequestMapping(value = "/signup", method = RequestMethod.POST)
    public ResponseEntity<String> Signup(@RequestBody UserDto userdto) throws Exception {
        String message = userservice.Signup(userdto);
        return new ResponseEntity<String>(message, HttpStatus.OK);
    }

    @RequestMapping(value = "/check_name", method = RequestMethod.POST)
    public ResponseEntity<String> Check_name(@RequestBody UserDto userdto) throws Exception {
        String message = userservice.Check_name(userdto);
        return new ResponseEntity<String>(message, HttpStatus.OK);
    }

    @RequestMapping(value = "/check_email", method = RequestMethod.POST)
    public ResponseEntity<String> Check_email(@RequestBody UserDto userdto) throws Exception {
        String message = userservice.Check_email(userdto);
        return new ResponseEntity<String>(message, HttpStatus.OK);
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseEntity<UserDto> Login(@RequestBody UserDto userdto) throws Exception {
        UserDto user = userservice.Login(userdto);
        return new ResponseEntity<UserDto>(user, HttpStatus.OK);
    }
    
    @RequestMapping(value = "/{user_pk}/logout", method = RequestMethod.POST)
    public void Logout(@PathVariable int user_pk) throws Exception {
        userservice.Logout(user_pk);
    }
    
    @RequestMapping(value = "/{user_pk}/delete", method = RequestMethod.POST)
    public ResponseEntity<String> delete_user(@RequestBody int user_pk) throws Exception {
        userservice.Delete_user(user_pk);
        String message = "회원탈퇴를 하셨습니다.";
        return new ResponseEntity<String>(message, HttpStatus.OK);
    }
    
    @RequestMapping(value="/{user_pk}", method=RequestMethod.POST)
    public ResponseEntity<UserDto> Mypage(@PathVariable int user_pk) throws Exception {
        UserDto user = userservice.Mypage(user_pk);
        return new ResponseEntity<UserDto>(user, HttpStatus.OK);
    }
    
    @RequestMapping(value = "/{user_pk}/update", method = RequestMethod.POST)
    public ResponseEntity<String> Update_user(@RequestBody UserDto userdto) throws Exception {
        String message = userservice.Update_user(userdto);
        return new ResponseEntity<String>(message, HttpStatus.OK);
    }

    @RequestMapping(value = "/{user_pk}/changepassword", method = RequestMethod.POST)
    public ResponseEntity<String> Change_uPassword(@RequestBody UserDto userdto) throws Exception{
        String message = userservice.Change_uPassword(userdto);
        return new ResponseEntity<String>(message, HttpStatus.OK);
    }

    @RequestMapping(value = "/alluser", method = RequestMethod.POST)
    public ResponseEntity<List<LoginUserDto>> allUser() throws Exception{
        List<LoginUserDto> users = userservice.allUser();
        return new ResponseEntity<List<LoginUserDto>>(users, HttpStatus.OK);
    }

    @RequestMapping(value = "/locate", method = RequestMethod.POST)
    public ResponseEntity<List<LoginUserDto>> Locate_user(@RequestBody LoginUserDto loginuserdto) throws Exception {
        String location = loginuserdto.getLocation();
        List<LoginUserDto> users = userservice.Locate_user(location);
        return new ResponseEntity<List<LoginUserDto>>(users, HttpStatus.OK);
    }
}