package com.sist.dao;

import com.sist.vo.*;
import com.sist.mapper.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class MemberDAO {
	@Autowired
	private MemberMapper mapper;
	
	public MemberVO memberLogin(MemberVO vo)
	{
		MemberVO mvo=new MemberVO();
		int count=mapper.idCount(vo.getId());
		if(count==0)
		{
			mvo.setMsg("NOID");
		}
		else
		{
			mvo=mapper.memberLogin(vo);
			if(mvo.getPwd().equals(vo.getPwd())) // 비밀번호가 같아서 로그인이 되는 상태
			{
				mvo.setMsg("OK");
			}
			else
			{
				mvo.setMsg("NOPWD");
			}
		}
		return mvo;
	}
}
