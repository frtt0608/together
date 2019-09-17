package com.ssafy.hashtag.db.dao;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ssafy.hashtag.db.dto.BorderDto;
import com.ssafy.hashtag.db.mapper.BorderMapper;

@Repository
public class BorderDao implements BorderMapper {
  
  public static final Logger logger = LoggerFactory.getLogger(BorderMapper.class);

  String ns = "com.ssafy.hashtag.db.";

  @Autowired
  private SqlSession sqlSession;

  @Override
  public List<BorderDto> allBorder() throws Exception {
    System.out.println("**************** allBorder BorderDao **********************");
    return sqlSession.selectList(ns + "allBorder");
  }

  @Override
  public void Create_border(BorderDto borderdto) throws Exception {
    System.out.println("**************** create BorderDao **********************");
    sqlSession.insert(ns + "create_border", borderdto);
  }

  @Override
  public BorderDto Detail_border(int border_pk) throws Exception {
    System.out.println("**************** detail BorderDao **********************");
    BorderDto border = sqlSession.selectOne(ns + "detail_border", border_pk);
    border.setHits(border.getHits() + 1);
    sqlSession.update(ns + "hit_border", border);
    return border;
  }

  @Override
  public void Update_border(BorderDto borderdto) throws Exception {
    System.out.println("**************** update BorderDao **********************");
    sqlSession.update(ns + "update_border", borderdto);
  }

  @Override
  public void Delete_border(int border_pk) throws Exception {
    System.out.println("**************** delete BorderDao **********************");
    sqlSession.delete(ns + "delete_border", border_pk);
  }
}