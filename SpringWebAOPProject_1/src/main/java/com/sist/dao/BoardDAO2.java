package com.sist.dao;

import java.util.*;
import java.sql.*;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class BoardDAO2 {
	private Connection conn;
	private PreparedStatement ps;
	private final String URL="jdbc:oracle:thin:@localhost:1521:XE";
	
	public BoardDAO2()
	{
		try
		{
			Class.forName("oracle.jdbc.driver.OracleDrvier");
		}catch(Exception ex) {}
	}
	
	public void getConnection()
	{
		try
		{
			conn=DriverManager.getConnection(URL, "hr", "happy");
		}catch(Exception ex) {}
	}
	
	public void disConnection()
	{
		try
		{
			if(ps!=null)
				ps.close();
			if(conn!=null)
				conn.close();
		}catch(Exception ex) {}
	}
	// SqlSessionFactoryBean
	// 답변하기
	/*
	 *   Insert => commit
	 *   Update => commit => error
	 *   Insert => commit
	 *   
	 */
	@Transactional
	public void boardReplyInsert(int pno, BoardVO vo)
	{
		try
		{
			// 연결
			getConnection();
			// commit => 해제
			conn.setAutoCommit(false); // @Around
			String sql="SELECT group_id, group_step, group_tab "
					+ "FROM springReplyBoard "
					+ "WHERE no=" + pno;
			ps=conn.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			rs.next(); // ?????????????????????????????????????????????????????????????????????????????????
			int gi=rs.getInt(1);
			int gs=rs.getInt(2);
			int gt=rs.getInt(3);
			rs.close();
			
			// update
			sql="UPDATE springReplyBoard SET "
			  + "group_step=group_step+1 "
			  + "WHERE group_id=? AND group_step>?";
			ps=conn.prepareStatement(sql);
			ps.setInt(1, gi);
			ps.setInt(2, gs);
			ps.executeUpdate(); // commit()
			ps.close();
			
			/*                  gi    gs    gt=> tab
			 *   AAAAA           1     0     0
			 *     => BBBBB      1     1     1
			 *       => CCCCC    1     2     2
			 *     => DDDDD      1     1     1
			 *   EEEEE           2     0     0
			 *   
			 *   B랑 D랑 충돌 따라서
			 *   
			 *                  gi    gs    gt=> tab
			 *   AAAAA           1     0     0
			 *     => DDDDD      1     1     1
			 *     => BBBBB      1     2     1
			 *       => CCCCC    1     3     2
			 *   EEEEE           2     0     0
			 */
			
			// insert
			sql="INSERT INTO springReplyBoard VALUES("
			  + "sr_no_seq.nextval, ?, ?, ?, ?, SYSDATE, 0, ?, ?, ?, ?, 0)";
			ps=conn.prepareStatement(sql);
			ps.setString(1, vo.getName());
			ps.setString(2, vo.getSubject());
			ps.setString(3, vo.getContent());
			ps.setString(4, vo.getPwd());
			ps.setInt(5, gi);
			ps.setInt(6, gs+1);
			ps.setInt(7, gt+1);
			ps.setInt(8, pno);
			ps.executeUpdate();
			ps.close();
			
			// update
			sql="UPDATE springReplyBoard SET "
					+ "depth=depth+1 "
					+ "WHERE no=" + pno;
			ps=conn.prepareStatement(sql);
			ps.executeUpdate();
			ps.close();
			conn.commit();
		}catch(Exception ex)
		{
			try
			{
				conn.rollback(); // @AfterThrowing
				ex.printStackTrace();
			}catch(Exception e) {}
		}
		finally
		{
			try
			{
				conn.setAutoCommit(true); // @After
			}catch(Exception ex) {}
		}
	}
	// 삭제하기
}
