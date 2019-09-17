package com.ssafy.hashtag.db.service;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.ssafy.hashtag.db.dto.LoginUserDto;
import com.ssafy.hashtag.db.dto.UserDto;
import com.ssafy.hashtag.db.dao.UserDao;

@Service
public class UserService {

    @Autowired
    private UserDao userdao;

    public List<LoginUserDto> allUser() throws Exception {
        System.out.println("****************allUser userservice**********************");

        return userdao.allUser();
    }

    public List<LoginUserDto> Locate_user(String location) throws Exception {
        System.out.println("****************Locate_user userservice**********************");
        List<LoginUserDto> users = userdao.Locate_user(location);
        return users;
    }
    
    public String Signup(UserDto userdto) throws Exception {
        System.out.println("****************signup userservice**********************");

        if (userdao.Check_email(userdto.getEmail()) == 0 && userdao.Check_name(userdto.getNickname()) == 0) {
            userdao.Signup(userdto);
            return "회원가입에 성공했습니다.";
        } else {
            return "회원가입에 실패했습니다. 이메일 또는 닉네임 중복을 확인해주세요.";
        }
    }

    public String Check_name(@RequestBody UserDto userdto) throws Exception {
        System.out.println("****************Check_name userservice**********************");
        System.out.println(userdto.getNickname());
        int flag = userdao.Check_name(userdto.getNickname());
        String msg;

        if (userdto.getNickname().length() == 0) {
            msg = "닉네임을 적어주세요.";
        } else {
            if (flag == 0) {
                msg = "사용가능한 닉네임입니다.";
            } else {
                msg = "이미있는 닉네임입니다.";
            }
        }
        return msg;
    }

    public String Check_email(@RequestBody UserDto userdto) throws Exception {
        System.out.println("****************Check_email userservice**********************");
        System.out.println(userdto.getEmail());
        int flag = userdao.Check_email(userdto.getEmail());
        String msg;

        if (userdto.getEmail().length() == 0) {
            msg = "이메일을 적어주세요.";
        } else {
            if (flag == 0) {
                msg = "사용가능한 이메일입니다.";
            } else {
                msg = "이미있는 이메일입니다.";
            }
        }
        return msg;
    }

    public UserDto Signin(UserDto userdto) throws Exception {
        System.out.println("****************signin userservice**********************");
        String email;
        String pw;
        int code;

        if ((userdao.Check_email(userdto.getEmail()) == 0)) {
            code = 404;
        }
        // 이메일이 있는 경우에 비밀번호 확인
        else {
            email = userdto.getEmail();
            pw = userdto.getPassword();
            // 비밀번호가 틀리면
            if (!userdao.Check_login(email).getPassword().equals(pw)) {
                code = 404;
            }
            // 비밀번호가 맞으면
            else {
                code = 200;
            }
        }
        UserDto user = userdao.Signin(userdto);
        return user;
    }

    public UserDto Login(UserDto userdto) throws Exception {
        System.out.println("****************login userservice**********************");
        String email;
        String pw;

        // 이메일 확인해서 없으면
        if ((userdao.Check_email(userdto.getEmail()) == 0)) {
            return null;
        }
        // 이메일이 있는 경우에 비밀번호 확인
        else {
            email = userdto.getEmail();
            pw = userdto.getPassword();
            // 비밀번호가 맞으면
            if (userdao.Check_login(email).getPassword().equals(pw)) {
                UserDto user = userdao.Login(userdto);
                return user;
            }
        }
        return null;
    }

    public void Logout(int user_pk) throws Exception {
        System.out.println("****************logout userservice**********************");
        userdao.Logout(user_pk);
    }

    public String Update_user(UserDto userdto) throws Exception {
        System.out.println("****************Update_user userservice**********************");
        userdao.Update_user(userdto);
        return "회원정보가 수정되었습니다.";
    }

    public void Delete_user(int user_pk) throws Exception {
        System.out.println("****************delete userservice**********************");
        userdao.Delete_user(user_pk);
    }

    public UserDto Mypage(int user_pk) throws Exception {
        System.out.println("****************mypage userservice**********************");
        return userdao.Mypage(user_pk);
    }

	public String Change_uPassword(UserDto userdto) {
        System.out.println("****************Change_uPassword userservice**********************");
        userdao.Change_uPassword(userdto);
        return "비밀번호가 변경되었습니다.";
    }

}