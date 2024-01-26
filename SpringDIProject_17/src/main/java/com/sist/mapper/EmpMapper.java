package com.sist.mapper;

import java.util.*;

import com.sist.dao.*;

public interface EmpMapper {
	// <select id="empAllData" resultMap="empMap">
	// id=mehtod명
	// resultType, resultMap = return형
	// parameterType = 매개변수
	public List<EmpVO> empAllData();
	
	// <select id="empDetailData" resultMap="empMap" parameterType="int">
	public EmpVO empDetailData(int empno);
}
