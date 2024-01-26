package com.sist.main;
/*
 *   @Autowired: 반드시 스프링에서 메모리 할당을 해야 자동 주입이 가능
 *   
 *   class A
 *   {
 *   	@Autowired
 *   	B b; ==> null
 *   }
 *   
 *   @Component
 *   class B
 *   {
 *   	@Autowired
 *   	A a; ==> 주소
 *   }
 */
import java.util.*;

import org.apache.commons.collections.map.HashedMap;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.sist.dao.*;

public class MainClass3 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ApplicationContext app=new ClassPathXmlApplicationContext("application-*.xml");
		FoodDAO fDao=(FoodDAO)app.getBean("fDao");
		Scanner scan=new Scanner(System.in);
		System.out.print("1.업체명, 2.주소, 3.음식종류 선택: ");
		int col=scan.nextInt();
		String fd="";
		String[] temp= {"", "name", "address", "type"};
		fd=temp[col];
		System.out.print("검색어 입력: ");
		String ss=scan.next();
		
		Map map=new HashMap();
		map.put("col_name", fd); // 키의 이름이 food_mapper.xml의 명칭과 같아야함
		map.put("ss", ss);
		
		List<FoodVO> list=fDao.foodFindData(map);
		
		for(FoodVO vo:list)
		{
			System.out.println(vo.getFno() + " "
							+ vo.getName() + " "
							+ vo.getAddress() + " "
							+ vo.getType());
		}
		System.out.println("=======================================");
		GoodsDAO gDao=(GoodsDAO)app.getBean("gDao");
		System.out.print("1.제품명: ");
		int col2=scan.nextInt();
		String fd2="";
		String[] temp2= {"", "goods_name"};
		fd2=temp2[col2];
		System.out.print("검색어 입력: ");
		String ss2=scan.next();
		
		Map map2=new HashMap();
		map2.put("goods_name", fd2);
		map2.put("ss2", ss2);
		
		List<GoodsVO> list2=gDao.goodsFindData(map2);
		
		for(GoodsVO vo:list2)
		{
			System.out.println(vo.getNo() + " "
							+ vo.getGoods_name() + " "
							+ vo.getGoods_price() + " "
							+ vo.getGoods_delivery());
		}
		
	}

}
