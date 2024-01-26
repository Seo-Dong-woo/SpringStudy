package com.sist.aop;

import com.sist.dao.*;

import java.util.*;

import org.aspectj.lang.ProceedingJoinPoint;
// DI => Injection
// DI => 클래스와 클래스의 연결관계 설정
/*
 *   Advice
 *   ======
 *     PointCut => 어떤 메소드에 적용
 *     JoinPoint => 위치
 *       = Before => 메소드 시작 전 
 *       = After => finally
 *       = AfterThrowing => catch(에러 발생)
 *       = AfterReturning => return(정상 수행)
 *       = Around => 전 후
 *       ===== Around
 *        소스
 *       ===== Around
 *   ==============================================
 *     통합해서 새로운 기능을 만듬(위빙 => Weaving) => Proxy패턴
 *     
 *     PointCut: 어떤 메소드 적용 여부
 *     execution("* 패키지명.클래스명.*()")
 *                =             ====
 *               리턴형      모든 메소드에 매개변수가 없는 것
 *                         매개변수 상관 없이 하려면(..)
 *     모든 패키지에 있는 모든 클래스에 적용
 *     => 로그
 *     within("패키지명.*")
 *     
 *     => 모든 모델 클래스 => * 패키지명.*Controller.*(..)
 */
public class DBAspect {
	private EmpDAO dao;

	public void setDao(EmpDAO dao) {
		this.dao = dao;
	}
	
	public void before()
	{
		dao.getConnection();
	}
	
	public void after()
	{
		dao.disConnection();
	}
	
	// 데이터 출력 => After-Returning
	public void afterReturning(Object obj) // obj와 app.xml의 returning값이랑 같아야 함
	{
		System.out.println("===== 결과값 자동 처리 ======");
		List<EmpVO> list=(List<EmpVO>)obj;
		for(EmpVO vo:list)
		{
			System.out.println(vo.getEmpno() + " "
					         + vo.getEname() + " "
					         + vo.getJob() + " "
					         + vo.getDbday() + " "
					         + vo.getSal());
		}
	}
	
	// 에러 => After-Throwing => catch
	public void afterThrowing(Throwable ex) // obj와 app.xml의 throwing값이랑 같아야 함
	{
		System.out.println("======= 에러발생 =======");
		ex.printStackTrace();
		// Web => @ControllerAdvice: 공통 예외처리
	}
	
	// 시간 => Around => 트랜잭션/보안(이미 AOP가 제작됨)/로그
	public Object around(ProceedingJoinPoint jp) throws Throwable
	{
		Object obj=null;
		long start=System.currentTimeMillis();
		System.out.println("호출된 메소드: " + jp.getSignature().getName()); // 어떤 메소드가 호출 됐는지 알 수 있음(사용자가 호출한 메소드)
		
		// 메소드 호출
		obj=jp.proceed(); // dao.empListData() => invoke()
		long end=System.currentTimeMillis();
		System.out.println("수행시간: " + (end-start));
		return obj;
	}
}
