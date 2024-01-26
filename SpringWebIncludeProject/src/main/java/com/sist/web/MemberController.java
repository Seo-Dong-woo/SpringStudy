package com.sist.web;

import com.sist.dao.*;
import com.sist.vo.MemberVO;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class MemberController {
	@Autowired
	private MemberDAO dao;
	
	@GetMapping("member/login.do")
	public String member_login()
	{
		return "member/login";
	}
	
	@PostMapping("member/login_ok.do")
	public String member_login_ok(String id, String pwd, Model model, HttpSession session) // 세션은 DispatcherServlet으로 부터 받아옴. 매개변수로 줄 것
	{
		MemberVO svo=new MemberVO();
		svo.setId(id);
		svo.setPwd(pwd);
		MemberVO vo=dao.memberLogin(svo);
		if(vo.getMsg().equals("OK"))
		{
			session.setAttribute("id", vo.getId());
			session.setAttribute("name", vo.getName());
		}
		model.addAttribute("msg", vo.getMsg());
		
		return "member/login_ok";
	}
	/*
	 *   틀렸을 경우 405오류
	 *   
	 *   1. 500 => 자바 소스 에러
	 *   2. 404 => 파일이 없는 경우
	 *   3. 405 => GET/POST
	 *   4. 400 => Bad request => 매개변수의 데이터형이 다른 경우
	 *   5. 412 => 한글변환 코드가 틀린 경우
	 *             UTF-8 => UFT-8
	 */
	@PostMapping("member/logout.do")
	public String member_logout(HttpSession session)
	{
		session.invalidate();
		return "redirect:../main/main.do";
	}

}
