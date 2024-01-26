package com.sist.spring2;
/*
 *   인터페이스를 이용하면 결합성이 new보다는 약함
 *   => 인터페이스의 단점(인터페이스를 수정하면 모든 클래스가 에러 발생)
 *   => SW => 인터페이스는 고정
 */
public interface Hello {
	public void sayHello(String name);
}
