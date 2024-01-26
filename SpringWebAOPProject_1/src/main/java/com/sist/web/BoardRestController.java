package com.sist.web;

import java.util.*;

import com.sist.dao.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

// @ResponseBody에서 @RestController로 바뀜. 메소드에서 클래스로 승격함
@RestController // 자바스크립트에 맞는 데이터를 사용할 때는 @RestController
public class BoardRestController {
	@Autowired
	private BoardDAO dao;
	
	@RequestMapping("board/update_ok.do")
	public String board_update_ok(BoardVO vo)
	{
		String result="";
		boolean bCheck=dao.boardUpdate(vo);
		if(bCheck==true)
		{
			result="<script>location.href=\"detail.do?no=" + vo.getNo() + "\"</script>"; // 자바스크립트를 이용할 것이기 떄문에 sendredirect는 못 쓰고 이러한 방식으로 써야 함
		}
		else
		{
			result="<script>"
					+"alert(\"비밀번호가 틀립니다\");"
					+"history.back();"
					+"</script>";
		}
		return result;
	}
	
	@RequestMapping("board/delete_ok.do")
	public String board_delete(int no, String pwd)
	{
		String result="";
		boolean bCheck=dao.boardReplyDelete(no, pwd);
		if(bCheck==true)
			result="yes";
		else
			result="no";
		return result;
	}
}
