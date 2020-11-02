// 화학전공 2017111701 최유리
// 연락처 프로젝트 - 주소록 클래스
import java.io.*;
import java.util.*;

public class AddressBook
{
	ObjectOutputStream out = null;
	ObjectInputStream in = null;
	ArrayList<Person> list; // ArrayList 객체 선언
	private int find = 0; // 찾은 사람의 수(같은 이름을 가진 사람들이 있을 경우 2 이상)
	private int index = 0; // 배열의 인덱스

	/*
	 * public AddressBook(int num) throws Exception {//UI에서 생성할 사람의 수 가져와서 객체 생성
	 * list = new ArrayList<Person>(num); }
	 */
	
	public AddressBook(int num, ObjectInputStream in) throws Exception // 생성자 함수
	{
		this.in = in;
		list = new ArrayList<Person>(); // ArrayList 객체 생성
		try
		{
			readFile(in); // 저장된 연락처 읽어오기
		}
		catch (IOException e) 
		{
			throw new IOException("IOException");
		}
		catch(NullPointerException e) 
		{
			throw new IOException("NullPointerException");
		}
	}

	public int getFind() // find 접근자
	{
		return find;
	}
	
	public void setIndex(int index) // 인덱스 설정자
	{
		this.index = index;
	}
	
	public int duplicateCount(String name) throws Exception // 해당 이름이 몇 명이 있는가 확인
	{
		find = 0;
		int count = list.size();
		
		for (int i = 0; i < count; i++)
		{
			if(name.equals(list.get(i).getName()))
				find++;
		}
		
		if (find == 0) // 만약 아무것도 찾지 못했다면 알리기
			throw new Exception("에러: 해당하는 이름이 없습니다.");
		
		return find;
	}
	
	public boolean checkPhoneNum(String phoneNum) //등록된 전화번호가 있는지 확인
	{ 
		int count = list.size();
	
		for (int i = 0; i < count; i++)
		{
			if(phoneNum.equals(list.get(i).getPhoneNum())) //등록된 전화번호가 있을 때 true 리턴
				return true;
		}
		
		return false;
		
	}	
	
	public int searchName(String name) // 이름을 이용한 검색
	{
		for (int i = index; i < list.size(); i++) // 인덱스부터 전체 인원수까지
		{
			if(name.equals(list.get(i).getName())) // 해당 이름을 찾으면
			{
				index = i; // index에 해당하는 인덱스 i를 넣기
				break; // 찾으면 반복문 탈출
			}
		}
		return index;	
	}
	
	public int searchNum(String phoneNum) throws Exception // 전화번호를 이용한 검색
	{
		index = -1; // 인덱스가 배열 밖(-1)을 가리킴
		
		for (int i = 0; i < list.size(); i++) // 0부터 전체 인원수까지
		{
			if(phoneNum.equals(list.get(i).getPhoneNum())) // 찾으려는 번호가 있다면
				index = i; // index에 해당하는 인덱스 i를 넣기.
		}
		
		if (index == -1) // 인덱스가 배열 밖(-1)을 가리킨다면 아무것도 찾지 못한 것.
			throw new Exception("에러: 해당하는 전화번호가 없습니다.");
		
		return index;
	}
	
	public void add(Person p)
	{
		list.add(p); // person에 추가
	}
	
	/*
	 * 연락처에 주어진 번호로 검색을 하는 것이기에
	 * 연락처 수정, 삭제 함수는
	 * 번호를 잘못 입력한 경우에 대해서 
	 * 익셉션이 발생합니다.
	 */
	public void modify(int index, Person p) throws Exception // 검색에서 알게된 인덱스로 연락처 수정
	{
		if (list.size() == 0) // 등록된 연락처가 아무것도 없다면 알리기
			throw new Exception("에러: 등록된 연락처가 없습니다.");
		
		if (index < list.size()) // 인덱스가 전체 인원수 보다 작은지 확인
		{
			if (index >= 0) // 배열의 해당 인덱스 부분 수정
			{
				list.set(index, p);
			}
			else // 인덱스가 0보다 작으면 익셉션 발생
				throw new Exception("에러: 번호가 0보다 작을수는 없습니다.");
		}
		else // 전체 인원수 보다 인덱스가 크면 익셉션 발생
		{
			throw new Exception("에러: 입력하신 번호가 등록된 인원 수 보다 큽니다.");	
		}
	}
	
	public void delete(int index) throws Exception // 검색에서 알게된 인덱스로 연락처 삭제
	{
		if (list.size() == 0) // 등록된 연락처가 아무것도 없다면 알리기
			throw new Exception("에러: 등록된 연락처가 없습니다.");
		
		if (index < list.size()) // 인덱스가 전체 인원수 보다 작은지 확인
		{
			if (index >= 0) // 인덱스가 0보다 같거나 큰지 확인
			{
				list.remove(index);
			}
			else // 인덱스가 0보다 작으면 익셉션 발생
				throw new Exception("에러: 번호가 0보다 작을수는 없습니다.");
		}
		else // 전체 인원수 보다 인덱스가 크면 익셉션 발생
			throw new Exception("에러: 입력하신 번호가 등록된 인원 수 보다 큽니다.");		
	}
	
	public void writeFile(ObjectOutputStream out) throws Exception
	{
		int count = list.size();
		out.writeInt(count); // 등록된 사람수 저장
		
		for(int i = 0; i < list.size(); i++) // 0부터 전체 사람수까지
		{
			out.writeObject(list.get(i)); // 연락처를 차례차례 저장하기
		}
	}
	
	public void readFile(ObjectInputStream in) throws Exception
	{

		int count = in.readInt(); // 저장된 갯수 읽기
		Person p; // Person 객체 생성

		for (int i = 0; i < count; i++)
		{
			p = (Person) in.readObject(); // 객체 역직렬화
			list.add(p); // person 배열에 차례대로 입력
		}
	}
}