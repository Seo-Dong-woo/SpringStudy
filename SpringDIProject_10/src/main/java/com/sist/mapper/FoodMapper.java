package com.sist.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import com.sist.dao.FoodVO;

public interface FoodMapper {
	// => 카테고리별 맛집
	@Select("SELECT fno, name, address, phone, type "
			+ "FROM food_house "
			+ "WHERE cno=#{cno}"
			+ "ORDER BY fno ASC")
	public List<FoodVO> foodCategoryListData(int cno);
	
	// => 맛집의 정보
	@Select("SELECT fno, name, address, score, phone, type, parking, time, menu, price "
			+ "FROM food_house "
			+ "WHERE fno=#{fno}")
	public FoodVO foodDetailData(int fno);
}
