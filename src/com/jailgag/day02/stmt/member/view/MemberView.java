package com.jailgag.day02.stmt.member.view;

import java.util.List;
import java.util.Scanner;

import com.jailgag.day02.stmt.member.controller.MemberController;
import com.jailgag.day02.stmt.member.model.vo.Member;

public class MemberView {
	
	private static final String SUCCES_MSG = "[서비스성공]";
	private static final String FAIL_MSG = "[서비스실패]";
	private static final String BYE_MSG = "프로그램이 종료되었습니다";
	private static final String NO_DATA_FOUND = "데이터가 존재하지 않습니다";
	private Scanner sc;//스캐너 1번만쓰는
	private MemberController manage; //임포트 주의!!
	
	
	public MemberView() {
		sc = new Scanner(System.in); //스캐너 1번만쓰는!~!!
		manage= new MemberController();
	}
	
	public void startProgram() {
		// TODO Auto-generated method stub
		끝:
		while(true) {
			int menu = this.showMainMenu(); //뜨자마자 바로만듬!(그럼강제로안써도됨...)
			switch(menu) {
			case 1 :
				//1.회원가입(회원 정보 입력)
				Member member = this.inputMember(); //임포트 주의!!
				//회원정보 저장
				int result = manage.insertMember(member);
				//결과여부 확인
				if(result >0) {
					//성공메세지
					this.showMessage(SUCCES_MSG);
				}else {
					//실패메세지
					this.showMessage(FAIL_MSG);
				}
				break;
			case 2 :
				//회원정보 수정
				break;
			case 3 :
				//회원탈퇴
				//DELETE FROM MEMBER_TBL WHERE MEBER_ID = 'user01';
				String memberId = this.inputMemberId(); //회원검색이랑 같은코드?
				result = manage.deleteMember(memberId); //int 썼는데 Duplicate~ 떠서 지워줌!!
				if(result > 0) {
					this.showMessage(SUCCES_MSG);
				}else {
					this.showMessage(FAIL_MSG);
				}
				break;
			case 4 :
				//4.회원 정보 조회
				List<Member> mList = manage.selectList();
				this.viewAllMembers(mList); //바로 클릭!!!
				break;
			case 5 :
				//회원검색
				//SELECT * FROM  MEMBER_TBL WHERE MEMBER_ID ='memberid';
				//SELECT를 실행한 결과는 딱 1개 나옴 List<Member>가 아닌
				//Member 로 받아야함
				memberId = this.inputMemberId(); //Duplicate local variable memberId 이거떠서 String 지움!!
	//			Member member = manage.selectOneById(memberId); //Duplicate local variable membe한번썻기때문에?String 지워줌!!
				member = manage.selectOneById(memberId);
				if(member != null) {
					this.viewOneMember(member); // 끝나고 출력해줘야해서 생성!!!
				}else {
					this.showMessage(NO_DATA_FOUND); //상수화!!
				}
				break;
			case 0 :
				this.showMessage(BYE_MSG);
				break 끝;
			default : break;
			}
		}
	}
	//5.회원검색!!
	private void viewOneMember(Member member) {
		// TODO Auto-generated method stub
		//ViewAllMember 에서 그대로 복사해옴!!
		//복사하고 for문 지움!!!지울때 {} <--- 이거 조심!!
		System.out.println("=====회원 검색 결과=====");
		System.out.println("아이디\t\t이름\t\t성별\t\t나이\t\t이메일\t\t전화번호\t\t주소");
		System.out.println(member.getMemberId()+"\t\t"+member.getMemberName()+"\t\t"+member.getGender()+"\t\t"+member.getAge()+"\t\t"+member.getEmail()+"\t\t"+member.getPhone()+"\t\t"+member.getAddress());
		
		
	}

	//5.회원검색!!+//3.회원탈퇴
	private String inputMemberId() {
		// TODO Auto-generated method stub
//		System.out.print("검색할 아이디 입력 : ");//매게변수하나를 덜!쓰기위해서!!아이디로 수정!!!
		System.out.print("아이디 입력 : ");
		String memberId = sc.next();
		return memberId;
	}

	//4.회원 정보 조회
	private void viewAllMembers(List<Member> mList) {
		// TODO Auto-generated method stub
		System.out.println("=====회원정보 출력=====");
		System.out.println("아이디\t\t이름\t\t성별\t\t나이\t\t이메일\t\t전화번호\t\t주소");
		for(Member member :mList) {
//			System.out.println("아이디\t\t이름\t\t성별\t\t나이\t\t이메일\t\t전화번호\t\t주소");
//			첫시작할때 ? 만약 이름저위에 이름 지우고 "" 쌍따옴포 안에 ++넣어주고 ++안에 member.get~~넣어주면됨!!			
			System.out.println(member.getMemberId()+"\t\t"+member.getMemberName()+"\t\t"+member.getGender()+"\t\t"+member.getAge()+"\t\t"+member.getEmail()+"\t\t"+member.getPhone()+"\t\t"+member.getAddress());
		}
	}

	//회원가입 성공메세지!!!
	private void showMessage(String message) {
		// TODO Auto-generated method stub
		System.out.println(message);
		
	}

	//1회원가입
	private Member inputMember() {
		// TODO Auto-generated method stub
		System.out.println("=====회원정보 입력=====");
		System.out.print("아이디 :");
		String memberId = sc.next();
		System.out.print("비밀번호 :");
		String memberPwd = sc.next();
		System.out.print("이름 :");
		String memberName = sc.next();
		System.out.print("나이 :");
		int age = sc.nextInt();
		Member member = new Member(memberId, memberPwd,memberName, age); //생성자!!
		return member;
	}
	//첫메뉴 화면!!
	private int showMainMenu() {
		// TODO Auto-generated method stub
		System.out.println("======회원관리 프로그램=====");
		System.out.println("1. 회원가입");
		System.out.println("2. 회원정보 수정");
		System.out.println("3. 회원탈퇴");
		System.out.println("4. 회원정보 조회(ALL)");
		System.out.println("5. 회원검색(아이디)");
		System.out.println("0.프로그램 종료");
		System.out.print("메뉴선택 :");
		int choice = sc.nextInt();
		return choice;
	}
	
}
