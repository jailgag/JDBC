package com.jailgag.day02.stmt.member.model.dao;

import java.sql.*;// Connection 지우고 * 입력하면 임포트 안해줘도됨!!
import java.util.ArrayList;
import java.util.List;

import com.jailgag.day02.stmt.member.model.vo.Member;

public class MemberDAO {
//oracle.jdbc.driver.OracleDriver
	private static final String DRIVER_NAME = "oracle.jdbc.driver.OracleDriver";
private static final String URL = "jdbc:oracle:thin:@127.0.0.1:1521:xe"; //조금다르다??!!
private static final String USERNAME = "KH";
private static final String PASSWORD = "KH";
 //**ORA-00001: 무결성 제약 조건(KH.PK_MEMBER)에 위배됩니다
// 회원정보 입력 할때 뜸!!!수정할때 잘못될 가능성있음!!
// 수정하는거 다시 보면서 복습할겸 봅시다!!다음코드는 
//홈꺼에다가 해봅시다!!
// 

	/*
	 * 여기서 jdbc코딩 할거!!
	 * 1.드라이버 등록
	 * 2.dbms 연결 생성
	 * 3. satatement 생성
	 * 4. sql 전송
	 * 5. 결과받기
	 * 6. 자원해제
	 */
	
	//1회원가입!!
	public int insertMember(Member member) {
		// TODO Auto-generated metho
		String query = "INSERT INTO MEMBER_TBL(MEMBER_ID,MEMBER_PWD,MEMBER_NAME,AGE)"  
				+"VALUES('"+member.getMemberId()+"','"+member.getMemberPwd()+"','"+member.getMemberName()+"',"+member.getAge()+")";
		//ORA-00926: 누락된 VALUES 키워드 //ES인데 SE로 오입력...
//시작쿼리문?	query = "INSERT INTO MEMBER_TBL VALUES('','','',)";
		int result = 0;//변수를 하나 선언!!
		Connection conn =null; //*2.옮겨주면
		Statement stmt = null;
		try {
//			Class.forName(DRIVER_NAME);//상수화!!////getConnection 이 만들어졌으니 이건 지워도 된다?!
			//*3.아래코드가 만들어짐!!(코드가 바뀜.....)
			//코드가 꼬임....getConnectrion 만드는과정에서 바뀌었나봄..일단 강제 수정!!
//			conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
//			conn = DriverManager.getConnection(); 
			//***4 아래코드 입력!후 다시 주석처리!!
//			Connection conn = this.getConnection();
//			Statement stmt = conn.createStatement();
			//***5. connection,statemente 지워줌!!
			conn = this.getConnection();
			stmt = conn.createStatement();
			result = stmt.executeUpdate(query);
//			stmt = conn.createStatement(); //쿼리문을 실행하기 위한 코드
			
			
			//*2.close() 옮겨주면 밑에코드를 위로 올려줌!!!그래서 Connection conn =null;생김!!
//			Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
//			Statement stmt = conn.createStatement(); //쿼리문을 실행하기 위한 코드
			//밑에 코드에서 예외가 발생하면 close()코드는 실행되지 않음
			result = stmt.executeUpdate(query); //int 지워줌!Duplicate local variable result
			//*1.finally 를 쓰면 밑에 close코드를 finally 로 옮겨준다!
//			stmt.close();
//			conn.close();  
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} //상수화!!!
		catch (SQLException e) { 
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			//예외가 발생하던 안하던 실행문을 동작시켜줌!!!
			try {
				stmt.close(); //***6.트라이캐취해줌!!!해주고나머지conn.close도 옮겨줌!
				conn.close();  
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} //***6. 캐취다시해줌!!
		}
		return result; //result cannot be resolved to a variable//int result = 0위에 입력해줌!!
	}
	//controller에서 넘어옴 4.회원정보조회
	public List<Member> selectList() {  
		// TODO Auto-generated method stub
		String query = "SELECT * FROM MEMBER_TBL";
		List<Member> mList = new ArrayList<Member>(); //서라운드캐취해주고선언해주기!!
		Connection conn =null;  //****1. 
		Statement stmt=null; 
		ResultSet rset =null;
		try {
			//**2. 지우고!!
			conn = this.getConnection(); //서라운드 멀티캐취?
			stmt =conn.createStatement(); //그다음 Statement ~~그다음 ArrayList선언!!
			rset= stmt.executeQuery(query); //List선언후 이거 적어주고!!위에String query 작성!
			//rset에 테이블 형태로 데이터가 있으나 그대로 사용 못함
			//rset을 member에 담아주는 코드를 작성해주어야함
			// 그럴때 사용하는 rset의 메소드가 next().getString(),....이있음!!저코드어디서봤지??
			//**옮기는 작업끝나면 지울텐데 일단 주석으로 표시해뒀음!!!
			while(rset.next()) {
//				String memberId = rset.getNString("MEMBER_ID"); //10번!!!!
//				String memberPwd = rset.getString("MEMBER_PWD");
//				String memberName = rset.getString("MEMBER_NAME");
//				String gender = rset.getString("GENDER");
//				int age = rset.getInt("AGE");
//				String email = rset.getString("EMAIL");
//				String phone = rset.getString("PHONE");
//				String address =rset.getString("ADDRESS");
//				String hobby =rset.getString("HOBBY");
//				Date enrollDate = rset.getDate("ENROLL_DATE");
	//			Member member; //생성자를 먼저 만들고!!! 밑에 =new Member적고 콘스누르고 10개짜리 선택!!(좋은거!!)
	//			Member member = new Member(memberId, memberPwd, memberName, gender, age, email, phone, address, hobby, enrollDate);
				
				//위에코드는 남겨둠!! 아래 코드는 위코드에 대한 수정 코드!!!
//				Member member; 이코드에서 아래코드 변경!!
				
				Member member = this.rsetToMember(rset);
				//그러면 코드가 엄청 줄어든다!!!
				
				
				//while문이 동작하면서 모든레코드에 대한 정보를
				// mList에 담을수 있도록 add 해줌!!
				mList.add(member);
			}
			//자원해제!!
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			//****3.finaly적고!!! close옮겨주고!!!
			try {
				rset.close(); //4. 트라이캐취!! 나머지 옮겨줌!
				stmt.close();
				conn.close();  //아래 캐취 잘못한듯... 다시 만들때 잘만들것!!
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
		
		//매번똑같으니 모듈화하기위해 getConnectiion을 만듬!
		//리턴을 null로 하면 NullPointerException 발생할수있다
		// mList로 리턴해주어야함!!
		return mList;
	}
	//콘트롤러에서 넘어옴!5.회원검색!!
	//복습때는 이거 보고 코드 주석해준건 정리!!!
	public Member selectOneById(String memberId) {
		// TODO Auto-generated method stub
//		String query= "SELECT* FROM MEMBER_TBL WHERE MEMBER_ID = 'user01'"; //이렇게쓰면 정해지니아래 코드로 변경!
		String query= "SELECT* FROM MEMBER_TBL WHERE MEMBER_ID = '"+memberId+"'"; //****' 끝에 ' 홋따옴표!!주의!!!참중요하다!!!!****
		Member member = null;
		Connection conn = null; //**1. 이거 적고 
		Statement stmt = null;
		ResultSet rset =null;
		try {
			//***2.지우고!!
			conn = this.getConnection(); //3번째꺼 빠르게 캐취해야 함!!
			//그래야 2개 한꺼번에 캐취 나옴!!!
			stmt = conn.createStatement();
			rset = stmt.executeQuery(query);//쿼리문 !!
			
			if(rset.next()) { //while문을 if문으로 바꿨음..
				//여기안에 아래코드 넣어준다 //넣어주고 주석처리했음!!
				//rset 은 member로 변환되어야 함 (rsetTomember) 알셋!
//				Member member = this.rsetToMember(rset); //옆에 코드쓰면 rset.getString~이부분!!을 안적어된다?\
				member = this.rsetToMember(rset); //선언해주고 Member지워줌!!
			}
			
			//rset 은 member로 변환되어야 함 (rsetTomember) 알셋!
//			Member member = this.rsetToMember(rset); //옆에 코드쓰면 rset.getString~이부분!!을 안적어된다?\
			//위에 코드 rsetTomember 메소드 만듬!!!
			
			//자원해제
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			//**3. 적고 close 옮기고//트라이캐치!!
			try {
				rset.close();
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
		}
		
		
		
		//선언을 바깥에다가!!위로 올라가서 Stirng Member member =null; 적어줌!
		return member; //member cannot be resolved to a variable =멤버를 변수로 확인할 수 없습니다
	}
	//5.회원탈퇴!!Online으로 올려줌!!
	public int deleteMeber(String memberId) { 
		// TODO Auto-generated method stub
		int result =0;
//		String query = "DELETE FROM MEMBER_TBL WHERE MEBER_ID='user01'";
		//ORA-00904: 실행할때 부적합한 식별자 뜨길래
		//아래 쿼리문 오타및누락!!! 수정후 잘 실행됨!! 
//		String query = "DELETE FROM MEMBER_TBL WHERE MEMBER_ID='"+memberId+"'";
		//다른 쿼리문 추가!! 아이디 영어 대소문자 구별 쿼리문!!
		//대문자로 입력되던 소문자로 입력되던 다 삭제된다!!
		String query ="DELETE FROM MEMBER_TBL WHERE LOWER(MEMBER_ID) = LOWER('"+memberId+"')";
		Connection conn =null; //***1.이거 써주고!!!
		Statement stmt= null;
		try {
			//***2.connection,statement 지워주고!!
			conn = this.getConnection(); //빠르게 3번째 캐취!!
			//쿼리문 생성
			stmt = conn.createStatement(); //쿼리문 실행을 해주는코드
			//쿼리문 실행!
			result = stmt.executeUpdate(query);//이거 적고 쿼리문 작성!
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			//***3. finally 적고 close 옮겨주고!!
			try {
				stmt.close();  //***4.트라이캐취해서 나머지 옮겨주고!!!
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		//성공 여부를 알수있도록 result를 리턴해줌!
		return result;
	}
	//selectOneById에서 넘어옴!!!위에 rsetTomember!!
	//코드를 줄이기 위해 쓴코드!!!!아래!!
	private Member rsetToMember(ResultSet rset) throws SQLException {
		// TODO Auto-generated method stub
		// restnext() 밑에 있는거에서 복사해옴!!
		//복사하고 붙여 놓고 add스로우 해줌!!
		//그리고 rsetnext에서 복사해준곳에 가서 지워준다!!!
		String memberId = rset.getNString("MEMBER_ID"); //10번!!!!
		String memberPwd = rset.getString("MEMBER_PWD");
		String memberName = rset.getString("MEMBER_NAME");
		String gender = rset.getString("GENDER");
		int age = rset.getInt("AGE");
		String email = rset.getString("EMAIL");
		String phone = rset.getString("PHONE");
		String address =rset.getString("ADDRESS");
		String hobby =rset.getString("HOBBY");
		Date enrollDate = rset.getDate("ENROLL_DATE");
//			Member member; //생성자를 먼저 만들고!!! 밑에 =new Member적고 콘스누르고 10개짜리 선택!!(좋은거!!)
		Member member = new Member(memberId, memberPwd, memberName, gender, age, email, phone, address, hobby, enrollDate);
		return member;
	}
	//getConnection 만듬!!!여기서 드라이버 등록!!!
	//이건 online으로 맨밑으로 내렸음!!!!!!다른건 안건드림!!
	private Connection getConnection() throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		Class.forName(DRIVER_NAME); //1.이번엔 던진다?ADD스로우로!!
		Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD); //2.여기서도 add스로우!!
		return conn;
	}

}
