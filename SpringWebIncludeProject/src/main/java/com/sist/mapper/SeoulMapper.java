package com.sist.mapper;

import java.util.*;

import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.mapping.StatementType;

import com.sist.vo.*;

public interface SeoulMapper {
	/*
	 *  CREATE OR REPLACE PROCEDURE seoulLocationData(
		    pStart NUMBER,
		    pEnd NUMBER,
		    pResult OUT SYS_REFCURSOR
		)
		IS
		BEGIN
		    OPEN pResult FOR
		        SELECT no, title, poster, msg, address, hit, num
		        FROM (SELECT no, title, poster, msg, address, hit, rownum as num 
		        FROM (SELECT no, title, poster, msg, address, hit 
		        FROM seoul_location ORDER BY no ASC))
		        WHERE num BETWEEN pStart AND pEnd;
		END;
		/
	*/
	@Select(value="{CALL seoulLocationData(#{pStart, mode=IN, javaType=java.lang.Integer}, #{pEnd, mode=IN, javaType=java.lang.Integer}, #{pResult, mode=OUT, jdbcType=CURSOR, resultMap=seoulMap})}")
	@Options(statementType = StatementType.CALLABLE)
	public List<SeoulVO> seoulListData(Map map);
	
	/*
	 *  CREATE OR REPLACE PROCEDURE seoulLocationTotalPage(
		    pTotal OUT NUMBER 
		)
		IS
		BEGIN
		    SELECT CEIL(COUNT(*)/12.0) INTO pTotal
		    FROM seoul_location;
		END;
		/
	 */
	@Select(value="{CALL seoulLocationTotalPage(#{pTotal, mode=OUT, jdbcType=INTEGER})}") // OUT변수는 jdbcType. IN변수는 javaType
	@Options(statementType = StatementType.CALLABLE)
	public Integer seoulTotalPage(Map map);
	
	/*
	 *  <select id="seoulLocationTotalPage" resultType="int">
			SELECT CEIL(COUNT(*)/12.0) FROM seoul_location
		</select>
	 */
	public int seoulLocationTotalPage();
}
