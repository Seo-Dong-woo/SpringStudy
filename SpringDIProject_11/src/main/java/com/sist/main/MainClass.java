package com.sist.main;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;
import com.sist.dao.*;

@Component
/*
 *   @Component
 *     => 사용 위치 ==> 클래스에만 적용
 *   @Autowired @Target(value={CONSTRUCTOR, METHOD, PARAMETER, FIELD, ANNOTATION_TYPE})
 *                                 생성자      메소드     파라미터    멤버변수      어노테이션
 *   class A
 *   {
 *   	@
 *   	B b(생성자)
 *   
 *   	@
 *   	public A(){}
 *   	
 *   	@
 *   	public void display(){}(메소드)
 *   
 *   	public A(@B b){}(멤버변수)
 *   }
 */
public class MainClass {
	@Autowired
	@Qualifier("memberDAO")
	private OracleDB ob;
	public static void main(String[] args) {
		ApplicationContext app=new ClassPathXmlApplicationContext("app.xml");
		MainClass mc=(MainClass)app.getBean("mainClass");
		//MainClass m=new MainClass();
		mc.ob.display();
	}

}
