package com.sist.mapper;

import java.util.*;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.sist.vo.*;

public interface GoodsMapper {
	@Select("SELECT no, goods_name, goods_poster, goods_price, goods_first_price, goods_delivery, num "
			+ "FROM (SELECT no, goods_name, goods_poster, goods_price, goods_first_price, goods_delivery, rownum as num "
			+ "FROM (SELECT no, goods_name, goods_poster, goods_price, goods_first_price, goods_delivery "
			+ "FROM goods_all ORDER BY no ASC)) "
			+ "WHERE num BETWEEN #{start} AND #{end}")
	public List<GoodsVO> goodsAllData(@Param("start") int start, @Param("end") int end);
	
	@Select("SELECT CEIL(COUNT(*)/12.0) FROM goods_all")
	public int goodsAllTotalPage();
	
	@Update("UPDATE goods_all SET "
			+ "hit=hit+1 "
			+ "WHERE no=#{no}")
	public void hitIncrement(int no);
	
	@Select("SELECT no, goods_name, goods_sub, goods_poster, goods_price, goods_first_price, goods_discount, goods_delivery "
			+ "FROM goods_all "
			+ "WHERE no=#{no}")
	public GoodsVO goodsAllDetailData(int no);
	
	// 검색 상품명
	@Select("SELECT no, goods_name, goods_poster, goods_price, goods_first_price, goods_delivery, num "
			+ "FROM (SELECT no, goods_name, goods_poster, goods_price, goods_first_price, goods_delivery, rownum as num "
			+ "FROM (SELECT no, goods_name, goods_poster, goods_price, goods_first_price, goods_delivery "
			+ "FROM goods_all "
			+ "WHERE ${col_name} LIKE '%'||#{ss}||'%' "
			+ "ORDER BY no ASC)) "
			+ "WHERE num BETWEEN #{start} AND #{end}")
	public List<GoodsVO> goodsAllFindData(Map map);
	
	@Select("SELECT CEIL(COUNT(*)/12.0) FROM goods_all "
			+ "WHERE ${col_name} LIKE '%'||#{ss}||'%'")
	public int goodsAllFindTotalPage(Map map);
}
