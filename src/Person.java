// 화학전공 2017111701 최유리
// 연락처 프로젝트-사람 클래스
import java.io.*;

public class Person implements Serializable // 직렬화 가능
{
	private String name; // 이름
	private String phoneNum; // 전화번호
	private String birthDate; // 생년월일
	private String address; // 주소
	private String email; // 이메일

	public Person(String name, String phoneNum, String birthDate, String address, String email) // 이름, 전화번호, 생년월일, 주소, 이메일을 파라미터로 받는 생성자
	{
		this.name = name;
		this.phoneNum = phoneNum;
		this.birthDate = birthDate;
		this.address = address;
		this.email = email;
	}
	
	public Person() {
		this.name=null;
		this.phoneNum = null;
		this.birthDate = null;
		this.address = null;
		this.email = null;
	}

	public String getName() // 이름 접근자
	{
		return name;
	}
	public String getPhoneNum() // 전화번호 접근자
	{
		return phoneNum;
	}
	public String getBirthDate() // 생년월일 접근자
	{
		return birthDate;
	}
	public String getAddress() // 주소 접근자
	{
		return address;
	}
	public String getEmail() // 이메일 접근자
	{
		return email;
	}

	public void setName(String name) // 이름 설정자
	{
		this.name = name;
	}
	public void setPhoneNum(String phoneNum) // 전화번호 설정자
	{
		this.phoneNum = phoneNum;
	}
	public void setBirthDate(String birthDate) // 생년월일 설정자
	{
		this.birthDate = birthDate;
	}
	public void setAddress(String address) // 주소 설정자
	{
		this.address = address;
	}
	public void setEmail(String email) // 이메일 설정자
	{
		this.email = email;
	}
	public String toString() // 이름, 전화번호, 생년월일, 주소, 이메일
	{
		return " | 이름: " + name + " | 전화번호: " + phoneNum + " | 생년월일: " + 
		birthDate + " | 주소: " + address + " | 이메일: " + email + " |";
	}
	
	// 아래의 두 함수는 앞으로 사용되지 않습니다.
	public void writeMyField(DataOutputStream out) throws Exception
	{
		try
		{
			out.writeUTF(name); // 이름 쓰기
			out.writeUTF(phoneNum); // 전화번호 쓰기
			out.writeUTF(birthDate); // 생일 쓰기
			out.writeUTF(address); // 주소 쓰기
			out.writeUTF(email); // 이메일 쓰기
		}
		catch (IOException ioe) 
		{
			throw new IOException("PersonSaveIOE\"");
		}
		catch(Exception ex) 
		{
			throw new Exception("PersonSaveEx");
		}
	}
	public void readMyField(DataInputStream in) throws Exception
	{
		try
		{
			name = in.readUTF(); // 이름 읽기
			phoneNum = in.readUTF(); // 전화번호 읽기
			birthDate = in.readUTF(); // 생일 읽기
			address = in.readUTF(); // 주소 읽기
			email = in.readUTF(); // 이메일 읽기
		}
		catch (EOFException eofe) 
		{
			throw new EOFException("PersonReadEOFE");
		}
		catch (IOException ioe)
		{
			throw new IOException("PersonReadIOE");
		}
		catch(Exception ex) 
		{
			throw new Exception("PersonReadEx");
		}
		
	}
}
