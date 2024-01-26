package com.sist.dao;

import java.util.*;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sist.vo.*;
import com.sist.mapper.*;

@Repository
public class GoodsDAO {
	@Autowired
	private GoodsMapper mapper;
	
	public List<GoodsVO> goodsAllData(int start, int end)
	{
		return mapper.goodsAllData(start, end);
	}
	
	public int goodsAllTotalPage()
	{
		return mapper.goodsAllTotalPage();
	}
	
	public GoodsVO goodsAllDetailData(int no)
	{
		mapper.hitIncrement(no);
		return mapper.goodsAllDetailData(no);
	}
	
	public GoodsVO goodsAllCookieData(int no)
	{
		return mapper.goodsAllDetailData(no);
	}
	
	public List<GoodsVO> goodsAllFindData(Map map)
	{
		return mapper.goodsAllFindData(map);
	}
	
	public int goodsAllFindTotalPage(Map map)
	{
		return mapper.goodsAllFindTotalPage(map);
	}
}
