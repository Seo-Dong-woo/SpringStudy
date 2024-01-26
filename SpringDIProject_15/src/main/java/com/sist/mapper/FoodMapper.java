package com.sist.mapper;
/*
 *   name => N
 *   address => A
 *   type => T
 *   name + address => NA
 *   name + type => NT
 *   address + type => AT
 *   name + address + type => NAT
 *   
 *   if(ss=='N')
 *   OR name LIKE ~
 *   else if(ss='A')
 *   OR address LIKE ~
 *   else if(ss='T')
 *   OR type LIKE ~
 */
import java.util.*;
// (name LIKE '%'||#{ss}||'%') OR (address LIKE '%'||#{ss}||'%')
import org.apache.ibatis.annotations.Select;

import com.sist.dao.FoodDAO;
import com.sist.dao.FoodVO;

public interface FoodMapper {
	// XML
	/*
	 *   <trim prefix="(" suffix=")" prefixOverrides="(" suffixOverrides=")">
	 *          접두어        접미어
	 *   => 추가/제거
	 *       name LIKE '%'||#{ss}||'%'
	 *   </trim>
	 *   
	 *   => name LIKE '%'||#{ss}||'%'
	 *   
	 *   <foreach collection=\"fsArr\" item=\"fd\">
	 *                        배열/리스트     값을 한 개씩 읽기
	 *   for(String fd:fsArr)
	 *   
	 *   ==> fd=='A' "A" == 65
	 */
	@Select("<script>"
			+ "SELECT fno, name, type, address "
			+ "FROM food_menu_house "
			+ "WHERE "
			+ "<trim prefix=\"(\" suffix=\")\" prefixOverrides=\"OR\">"
			+ "<foreach collection=\"fsArr\" item=\"fd\">"
			+ "<trim prefix=\"OR\">"
			+ "<choose>"
			+ "<when test=\"fd=='N'.toString()\">"
			+ "name LIKE '%'||#{ss}||'%'"
			+ "</when>"
			+ "<when test=\"fd=='A'.toString()\">"
			+ "address LIKE '%'||#{ss}||'%'"
			+ "</when>"
			+ "<when test=\"fd=='T'.toString()\">"
			+ "type LIKE '%'||#{ss}||'%'"
			+ "</when>"
			+ "</choose>"
			+ "</trim>"
			+ "</foreach>"
			+ "</trim>"
			+ "</script>")
	public List<FoodVO> foodFindData(Map map);
}
