package com.ssafy.hashtag.db.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.hashtag.db.dto.BorderDto;
import com.ssafy.hashtag.db.dao.BorderDao;

@Service
public class BorderService {

  @Autowired
  private BorderDao borderdao;

  public List<BorderDto> allBorder() throws Exception {
    System.out.println("****************allborder BorderService**********************");

    return borderdao.allBorder();
  }

  public BorderDto Detail_border(int border_pk) throws Exception {
    System.out.println("****************Detail BorderService**********************");

    return borderdao.Detail_border(border_pk);
  }

  public void Create_border(BorderDto borderdto) throws Exception {
    System.out.println("****************create BorderService**********************");
    Date date = new Date();
    SimpleDateFormat today = new SimpleDateFormat("yyyy-MM-dd");

    borderdto.setCreated_at(today.format(date));

    borderdao.Create_border(borderdto);
  }

  public void Delete_border(int border_pk) throws Exception {
    System.out.println("****************delete BorderService**********************");

    borderdao.Delete_border(border_pk);
  }

  public void Update_border(BorderDto borderdto) throws Exception {
    System.out.println("****************update BorderService**********************");

    borderdao.Update_border(borderdto);
  }
}