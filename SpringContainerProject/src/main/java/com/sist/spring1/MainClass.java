package com.sist.spring1;

import com.sist.spring1.Hello;
/*
 *   MainClass는 Hello클래스에 의존
 *   => 결합성이 강한 프로그램 (단점 유지보수 어려움)
 *   => 상호 연결이 어려움(연결은 쉬움 = 연결을 종단)
 *   => 가급적 new 사용 X
 */
public class MainClass {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Hello hello=new Hello();
		hello.sayHello("홍길동");
	}

}
