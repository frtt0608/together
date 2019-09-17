package com.ssafy.hashtag.db.dao;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ssafy.hashtag.db.dto.UserDto;

import java.util.List;

import com.ssafy.hashtag.db.dto.LoginUserDto;
import com.ssafy.hashtag.db.mapper.UserMapper;

@Repository
public class UserDao implements UserMapper {
    public static final Logger logger = LoggerFactory.getLogger(UserMapper.class);
    
    String ns = "com.ssafy.hashtag.db.";
    
    @Autowired
    private SqlSession sqlSession;

    @Override
    public List<LoginUserDto> allUser() throws Exception {
        List<LoginUserDto> users = sqlSession.selectList(ns + "alluser");
        return users;
    }

    @Override
    public List<LoginUserDto> Locate_user(String location) throws Exception {
        System.out.println("**************** locate_user UserDao **********************");
        List<LoginUserDto> users = sqlSession.selectList(ns + "locate_user", location);
        return users;
    }

    // 회원가입
    @Override
    public void Signup(UserDto userdto) throws Exception {
        System.out.println("**************** signup UserDao **********************");
        System.out.println(userdto.toString());
        sqlSession.insert(ns + "Signup", userdto);
    }

    // 아이디 중복 검사
	public int Check_name(String nickname) throws Exception {
        System.out.println("**************** check_name UserDao **********************");
		return sqlSession.selectOne(ns+ "check_name", nickname);
	}

	// 이메일 중복 검사
	public int Check_email(String email) throws Exception {
        System.out.println("**************** check_email UserDao **********************");
		return sqlSession.selectOne(ns+ "check_email", email);
    }
    
    // 로그인 체크(이메일, 비밀번호가 맞는지)
    public UserDto Check_login(String email) throws Exception {
        System.out.println("**************** check_login UserDao **********************");
        return sqlSession.selectOne(ns + "check_login", email);
    }

    // 로그인
    @Override
    public UserDto Login(UserDto userdto) throws Exception {
        System.out.println("**************** login UserDao **********************");
        UserDto user = sqlSession.selectOne(ns + "login", userdto);
        userdto.setUser_pk(user.getUser_pk());
        sqlSession.insert(ns + "login_user", userdto);
        return user;
    }

    // 로그아웃
    @Override
    public void Logout(int user_pk) throws Exception {
        System.out.println("**************** logout UserDao **********************");

        sqlSession.delete(ns + "logout", user_pk);
    }

    // 회원 탈퇴 및 삭제
    @Override
    public void Delete_user(int user_pk) throws Exception {
        System.out.println("**************** delete UserDao **********************");
        sqlSession.delete(ns+"delete_user", user_pk);
    }

    // 유저 개인정보 수정
    @Override
    public void Update_user(UserDto userdto) throws Exception {
        System.out.println("****************udpate_user UserDao **********************");
        sqlSession.update(ns + "update_user", userdto);
    }

    // my page
    @Override
    public UserDto Mypage(int user_pk) throws Exception {
        System.out.println("****************mypage UserDao **********************");
        return sqlSession.selectOne(ns + "mypage", user_pk);
    }

    @Override
    public UserDto Signin(UserDto userdto) throws Exception {
        UserDto user = sqlSession.selectOne(ns + "signin", userdto);
        return user;
    }

    // 비밀번호 변경
	public void Change_uPassword(UserDto userdto) {
        System.out.println("****************Change_uPassword UserDao **********************");
		sqlSession.update(ns + "change_upassword", userdto);
	}
}
