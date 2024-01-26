package com.sist.web;

import java.util.*;

import javax.servlet.http.HttpServletRequest;

import com.sist.dao.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class FoodController {
	@Autowired
	private FoodDAO dao;
	
	@RequestMapping("food/list.do")
	public String food_list(String page, Model model) // page는 시작값이 null값이기 때문에 int로 받지 못하고 String으로 받아야함
	{
		if(page==null)
			page="1";
		int curpage=Integer.parseInt(page);
		int rowSize=12;
		int start=(rowSize*curpage)-(rowSize-1);
		int end=rowSize*curpage;
		List<FoodVO> list=dao.foodListData(start, end);
		int totalpage=dao.foodTotalPage();
		final int BLOCK=10;
		int startPage=((curpage-1)/BLOCK*BLOCK)+1;
		int endPage=((curpage-1)/BLOCK*BLOCK)+BLOCK;
		if(endPage>totalpage)
			endPage=totalpage;
		
		model.addAttribute("curpage", curpage);
		model.addAttribute("totalpage", totalpage);
		model.addAttribute("startPage", startPage);
		model.addAttribute("endPage", endPage);
		model.addAttribute("list", list);
		return "food/list";
	}
	
	@RequestMapping("food/detail.do")
	public String food_detail(int fno, Model model) // int 'fno'랑 list.jsp의 detail.do?fno=${vo.fno }의 ?뒤의 'fno'가 같아야함
	{
		FoodVO vo=dao.foodDetailData(fno);
		model.addAttribute("vo", vo);
		/*
		 *   class Model
		 *   {
		 *       private HttpServletRequest request
		 *       public Model(HttpServletRequset request)
		 *       {
		 *           this.request=request;
		 *       }
		 *       public void addAttribute(String key, Object obj)
		 *       {
		 *           request.setAttribute(key, obj)
		 *       }
		 *   }
		 */
		return "food/detail";
	}
	
	@RequestMapping("food/find.do")
	public String food_find(String address, Model model)
	{
		return "food/find";
	}
}
