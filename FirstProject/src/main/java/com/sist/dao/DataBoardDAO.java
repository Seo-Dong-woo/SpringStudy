package com.sist.dao;

import java.util.*;
import java.io.*;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.sist.vo.*;

public class DataBoardDAO {
	private static SqlSessionFactory ssf;
	static
	{
		// xml => parse
		try
		{
			Reader reader=Resources.getResourceAsReader("Config.xml");
			ssf=new SqlSessionFactoryBuilder().build(reader);
		}catch(Exception ex)
		{
			ex.printStackTrace();
		}
	}
	// => 단순 (1. JOIN, 2. 동적쿼리)
	// 목록 출력
	public static List<DataBoardVO> databoardListData(Map map)
	{
		SqlSession session=null;
		List<DataBoardVO> list=new ArrayList<DataBoardVO>();
		try
		{
			session=ssf.openSession();
			list=session.selectList("databoardListData", map);;
		}catch(Exception ex)
		{
			ex.printStackTrace();
		}
		finally
		{
			if(session!=null)
				session.close();
		}
		return list;
	}
	
	public static int databoardRowCount()
	{
		SqlSession session=null;
		int count=0;
		try
		{
			// getConnection()
			session=ssf.openSession();
			count=session.selectOne("databoardRowCount");
		}catch(Exception ex)
		{
			ex.printStackTrace();
		}
		finally
		{
			if(session!=null)
				session.close(); // 반환 => disConnection()
			// => Connection/PreparedStatement
		}
		return count;
	}
	
	public static void databoardInsert(DataBoardVO vo)
	{
		SqlSession session=null;
		try
		{
			session=ssf.openSession(); // autocommit
			session.insert("databoardInsert", vo);
			session.commit();
		}catch(Exception ex)
		{
			ex.printStackTrace();
		}
		finally
		{
			if(session!=null)
				session.close();
		}
	}
	
	public static DataBoardVO databoardDetailData(int no)
	{
		DataBoardVO vo=new DataBoardVO();
		SqlSession session=null;
		try
		{
			session=ssf.openSession();
			session.update("hitIncrement", no);
			vo=session.selectOne("databoardDetailData", no);
		}catch(Exception ex)
		{
			ex.printStackTrace();
		}
		finally
		{
			if(session!=null)
				session.close();
		}
		return vo;
	}
}
