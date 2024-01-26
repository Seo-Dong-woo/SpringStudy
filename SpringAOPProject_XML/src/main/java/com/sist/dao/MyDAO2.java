package com.sist.dao;

public class MyDAO2 {
	public void select()
	{
		// before
		System.out.println("오라클 => SELECT 문장 요청");
		// after
	}
	
	public void insert()
	{
		System.out.println("오라클 => INSERT 문장 요청");
	}
	
	public void update()
	{
		System.out.println("오라클 => UPDATE 문장 요청");
	}
	
	public void delete()
	{
		System.out.println("오라클 => DELETE 문장 요청");
	}
}
