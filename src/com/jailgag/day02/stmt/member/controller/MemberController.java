package com.jailgag.day02.stmt.member.controller;

import java.util.List;

import com.jailgag.day02.stmt.member.model.dao.MemberDAO;
import com.jailgag.day02.stmt.member.model.vo.Member;

public class MemberController {

	private MemberDAO mDao; //1.
	
	public MemberController() {
		mDao = new MemberDAO(); //2.
	}
	
	//1.회원가입
	public int insertMember(Member member) {
		// TODO Auto-generated method stub
		int result = mDao.insertMember(member);
		//return result를 view로 결과값 보내줌!!
		return result;
	}
	//회원정보 조회
	public List<Member> selectList() {
		// TODO Auto-generated method stub      //아래 에러 갑자기 사라짐...새로고침이 늦어서일까..
		List<Member> mList = mDao.selectList(); //The method selectList() is undefined for the type MemberDAO
		//View에서 mList를 사용할수 있도록 리턴해줌!
		return mList;
	}
	//5.회원검색
	public Member selectOneById(String memberId) {
		// TODO Auto-generated method stub
		//SELECT * FROM  MEMBER_TBL WHERE MEMBER_ID ='memberid';
		Member member = mDao.selectOneById(memberId);
		//Member를 View에서 사용할수 있도록 리턴해줌!!
		return member;
	}
	//3회원탈퇴
	public int deleteMember(String memberId) {
		// TODO Auto-generated method stub
		//회원가입이랑 비슷하다!!!
		//10분컷?
		int result = mDao.deleteMeber(memberId);
		return result;
	}

}
