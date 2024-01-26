package com.sist.dao;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sist.mapper.*;
@Repository("cDao")
public class CategoryDAO {
	
	@Autowired // 스프링에서 저장된 것 중에 mapper를 찾아서 저장
	private CategoryMapper mapper;
	
	public List<CategoryVO> foodCategoryData()
	{
		return mapper.foodCategoryData();
	}
	
	public CategoryVO categoryInfoData(int cno)
	{
		return mapper.categoryInfoData(cno);
	}
}
