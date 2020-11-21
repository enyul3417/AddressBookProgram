// 화학전공 2017111701 최유리
// 연락처 프로젝트 - 주소록 클래스
import java.sql.*;

public class AddressBook
{
	Connection conn = null;
	Statement stmt = null;
	ResultSet rs = null;
	
	public AddressBook() throws Exception // 생성자 함수
	{
		Class.forName("org.mariadb.jdbc.Driver"); // JDBC 드라이버 연결
		conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/addressbook", "root", "20140812"); // DB 연결
		readFile();
	}
	
	public boolean checkPhoneNum(String phoneNum) throws SQLException //등록된 전화번호가 있는지 확인
	{ 
		ResultSet result = stmt.executeQuery("select * from addressbook where 전화번호 = '" + phoneNum + "';" ); // 일치하는 전화번호 검색
		String find = null;
		
		while(result.next())
		{
			find = (String) result.getString("전화번호");
		}
		
		if(find.equals(phoneNum)) // 등록된 전화번호가 존재한다면
			return true;
		
		return false;
	}	
	
	// 검색이 더 편리하도록 입력한 문자를 포함하는 데이터를 검색하도록 했습니다.
	public ResultSet searchName(String name) throws SQLException // 이름을 이용한 검색
	{
		return stmt.executeQuery("select * from addressbook where 이름 like '%" + name + "%';");
	}
	
	public ResultSet searchNum(String phoneNum) throws SQLException // 전화번호를 이용한 검색
	{
		return stmt.executeQuery("select * from addressbook where 전화번호 like '%" + phoneNum + "%';");
	}
	
	public void add(Person p) throws SQLException // 연락처 추가
	{
		stmt.executeUpdate(
				"insert into addressbook (이름, 전화번호, 생일, 주소, 이메일) values('" +
						p.getName() + "', '" + p.getPhoneNum() + "', '" + p.getBirthDate() + "', '" + p.getAddress() + "', '" + p.getEmail() + "');");
	}
	
	public void modify(String phoneNum, Person p) throws SQLException // 일치하는 전화번호(primary key)가 있는 데이터를 수정
	{
		stmt.executeUpdate("update addressbook set 이름:='" + p.getName() + "', 전화번호:='" + p.getPhoneNum() + 
				"', 생일:='" + p.getBirthDate() + "', 주소:='" + p.getAddress() + "', 이메일:='" + p.getEmail() +
				"' where 전화번호 = '" + phoneNum + "';");
	}
	
	public void delete(String phoneNum) throws SQLException // 일치하는 전화번호(primary key)가 있는 데이터를 삭제
	{
		stmt.executeUpdate("delete from addressbook where 전화번호 = '" + phoneNum + "';");
	}
	
	public void closeAll() throws SQLException
	{
		stmt.close(); // statement 닫기 
		conn.close(); // DB 연결 끊기 
	}
	
	// 파일 쓰기 함수는 사용할 일이 없어서 삭제했습니다.
	public void readFile() throws SQLException
	{
		stmt = conn.createStatement();
		rs = stmt.executeQuery("select * from addressbook;"); // addressbook 테이블 내용 읽어오기
	}
}