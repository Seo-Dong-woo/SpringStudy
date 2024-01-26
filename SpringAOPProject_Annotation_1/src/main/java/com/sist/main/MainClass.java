package com.sist.main;

import java.util.*;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Repository;

import com.sist.dao.*;

public class MainClass {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ApplicationContext app=new ClassPathXmlApplicationContext("app.xml");
		EmpDAO dao=(EmpDAO)app.getBean("empDAO"); // EmpDAO의 @Repository에 id를 주지 않았기 떄문에 첫글자를 소문자로 해서 설정해야함
		List<EmpVO> list=dao.empListData();
		for(EmpVO vo:list)
		{
			System.out.println(vo.getEmpno() + " "
					+ vo.getEname() + " "
					+ vo.getJob() + " "
					+ vo.getDbday()
					+ vo.getSal());
		}
		
		System.out.println("=======================");
		Scanner scan=new Scanner(System.in);
		System.out.print("사번 입력: ");
		int empno=scan.nextInt();
		EmpVO vo=dao.empDetailData(empno);
		System.out.println("사번: " + vo.getEmpno());
		System.out.println("이름: " + vo.getEname());
		System.out.println("직위: " + vo.getJob());
		System.out.println("입사일: " + vo.getDbday());
		System.out.println("급여: " + vo.getSal());
		
	}

}
