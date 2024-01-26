package com.sist.inter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
/*
 *                  FrontController: 요청 받기 => 응답
 *   main.do ===== DispatcherServlet ===== HandlerMapping
 *                                               | @GetMapping/@PostMapping => 기능 수행
 *                                                 ========================    Back-End
 *                                                  프로그래머(Model) 찾기
 *                                                  Model(Controller, Action)
 *                                                    1) VO
 *                                                    2) DAO
 *                                                    3) Manager
 *                                                    4) Service
 *                        |
 *                    preHandle       => @GetMapping("main.do")
 *                                            |
 *                                          return "main/main";
 *                                            | ---> postHandle
 *                                          ViewResolver
 *                                            | request
 *                                            | ---> afterCompletion
 *                                           JSP => Front-End
 */
// <bean이용> => Login
public class FoodInterCeptor extends HandlerInterceptorAdapter {

	@Override
	// main.do 찾기전(모델 수행전: 자동로그인, 로그인 전)
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		// TODO Auto-generated method stub
		System.out.println("===== preHandle() Call... =====");
		return super.preHandle(request, response, handler);
	}

	@Override
	// ViewResolver로 넘어가기전에 사용
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("===== postHanle() Call... =====");
		super.postHandle(request, response, handler, modelAndView);
	}

	@Override
	// JSP로 넘어아기 전
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// TODO Auto-generated method stub
		System.out.println("===== afterCompletion ====");
		super.afterCompletion(request, response, handler, ex);
	}
	
}
