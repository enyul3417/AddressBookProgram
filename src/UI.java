// 화학전공 2017111701 최유리
// 연락처 프로젝트-UI클래스

// 해당 UI 클래스 부분은 GUI 클래스의 코드르 작성하고 테스트하는데 이용할 용도로 남겨두었습니다.
/*
import java.util.*;
import java.io.*;

public class UI {
	public static void main(String ars[]) throws Exception
	{
		ObjectInputStream in = null;
		ObjectOutputStream out = null;
		Person newPerson;
		AddressBook ab = null;
		
		Scanner scan = new Scanner(System.in);		
		
		int menu; // 메뉴 번호
		int num; // 수정, 삭제 시 인덱스를 받는 부분

		try
		{
			in = new ObjectInputStream(new FileInputStream("AddressBook.dat")); // 파일 열기
			ab = new AddressBook(100, in); // 객체 생성
			ab.readFile(in);
			in.close(); // in 닫기
		}
		catch (FileNotFoundException fnfe) 
		{
			System.out.println("File not Found... First Time Start......");
			ab = new AddressBook(100);
		}
		catch(Exception e)
		{
		}
	
		
		while(true) // 반복문
		{
			// 사용자 메뉴 출력
			System.out.println("");
			System.out.println("-----------------------*********연락처 관리 프로그램*********------------------------");
			System.out.println("1. 새 연락처 추가");
			System.out.println("2. 연락처 검색");
			System.out.println("3. 전체 연락처 조회");
			System.out.println("4. 연락처 수정");
			System.out.println("5. 연락처 삭제");
			System.out.println("6. 연락처 파일 저장");
			System.out.println("7. 프로그램 종료");
			System.out.println("---------------------------------------------------------------------------");
			System.out.print("원하시는 번호를 입력해주세요: ");
			
			try
			{
				menu = scan.nextInt(); // 번호 입력
				scan.nextLine(); // 버퍼 삭제
			}
			catch(InputMismatchException misExc) // 정수가 아닌 수를 입력했을 때
			{
				System.out.println("잘못된 입력입니다. 1, 2와 같은 정수를 입력해주세요.");
				scan.nextLine(); // 버퍼 삭제
				continue;
			}
			
			if(menu == 7) // 프로그램 종료
			{
				System.out.println("프로그램을 종료합니다.");
				break; // 반복문 종료
			}
				
			switch(menu) // 번호에 따른 메소드 실행
			{
			case 1: // 새 연락처 추가
				while(true)
				{
					System.out.println("새로운 연락처를 추가합니다.");
					
					System.out.print("이름: "); // 이름 입력
					String n = scan.next();
					scan.nextLine(); // 버퍼 삭제
					System.out.print("전화번호: "); // 전화번호 입력
					String pn = scan.next();
					if(ab.checkPhoneNum(pn) == true) // 만약 등록된 전화번호일 경우 
					{
						System.out.println("이미 등록된 전화번호 입니다.");
						break; // 메뉴로 돌아가게 함
					}
					scan.nextLine(); // 버퍼 삭제
					System.out.print("생일(미입력: 0): "); // 생일 입력
					String bd = scan.next();
					scan.nextLine(); // 버퍼 삭제
					System.out.print("주소(미입력: 0): "); // 주소 입력
					String a = scan.next();
					scan.nextLine(); // 버퍼 삭제
					System.out.print("이메일(미입력: 0): "); // 이메일 입력
					String em = scan.next();
					scan.nextLine(); // 버퍼 삭제

					newPerson = new Person(n, pn, bd, a, em); // Person객체로 새로운 연락처 생성
					ab.add(newPerson);
					System.out.println("저장되었습니다.");
					
					System.out.println("연락처를 더 추가하시겠습니까?(Y/N)");
					char as = scan.next().charAt(0);
					scan.nextLine(); // 버퍼 삭제
					
					if(as == 'N' || as == 'n') // 대소문자 N 입력 시
						break; // 연락처 추가 종료
					else if (as == 'Y' || as == 'y') // 대소문자 Y 입력 시
						continue;
					else // 그 외의 것들을 입력한다면
						throw new Exception("잘못입력하셨습니다.");
				}
				break;
			
			case 2: // 연락처 검색
				System.out.println("1. 이름으로 검색");
				System.out.println("2. 전화번호로 검색");
				System.out.print("원하는 항목의 숫자를 입력해주세요: ");
				
				try
				{
					menu = scan.nextInt(); // 번호 입력
					scan.nextLine(); // 버퍼 삭제
				}
				catch(InputMismatchException misExc) // 정수 외에 다른 것을 입력 시
				{
					System.out.println("잘못된 입력입니다. 1, 2와 같은 정수를 입력해주세요.");
					scan.nextLine(); // 버퍼 삭제
					continue;
				}
				
				switch(menu)
				{
				case 1: // 이름으로 검색
					System.out.println("이름으로 검색합니다.");
					System.out.print("이름을 입력해주세요: ");
					ab.setIndex(0); // 인덱스를 0으로 설정 => 이름을 찾을때 처음부터 검색해야하기 때문
					
					String n = scan.next(); // 찾으려는 이름 입력
					scan.nextLine(); // 버퍼 삭제
					try
					{
						ab.duplicateCount(n); // 해당 이름을 몇명이 가지고 있는지 확인, 못찾으면 익셉션 발생
					}
					catch(Exception e)
					{
						String str = e.getMessage();
						System.err.println(str); // 에러 메시지 출력
					}
					
					for ( int i = 0; i < ab.getFind(); i++) // 찾은 사람 수만큼 연락처 찾아서 출력
					{
						int index = ab.searchName(n); // 해당 이름 찾기 및 인덱스 받기
						ab.setIndex(index+1);// 해당 이름이 있는 인덱스에서 +1로 바꾸기 => 바꾼 인덱스부터 저장된 사람수의 범위에서 해당 이름 검색 가능
						System.out.println("번호: " + index + ab.list.get(index)); // 검색 결과 출력
					}
					
					break;
					
				case 2: // 전화번호로 검색
					System.out.println("전화번호로 검색합니다.");
					System.out.print("전화번호를 입력해주세요: ");
					String pn = scan.next(); // 전화번호 입력
					scan.nextLine(); // 버퍼 삭제
					
					try
					{
						int index = ab.searchNum(pn); // 전화번호를 검색하고 해당 인덱스 받기
						System.out.println("번호: " + index + ab.list.get(index)); // 검색 결과 출력
						System.out.println("검색이 완료되었습니다.");
					}
					catch (Exception e)
					{
						String str = e.getMessage();
						System.err.println(str); // 에러 메시지 출력
					}
					break;
					
				default:
					System.out.println("잘못 입력하셨습니다. 다시 입력하세요.");	
				}
				
				break;
			
			case 3: // 전체 연락처 조회
				System.out.println("전체 연락처를 조회합니다.");
				System.out.println("총 연락처의 개수는 " + ab.list.size() + "개 입니다."); // 저장된 연락처의 개수 알려주기
				for (int i = 0; i < ab.list.size(); i++) // 0부터 저장된 연락처 수만큼
				{
					System.out.println("번호: " + i + ab.list.get(i)); // 해당 연락처 정보 출력
				}
				System.out.println("전체 연락처 출력을 완료했습니다.");
				
				break;
			
			case 4: // 연락처 수정
				System.out.println("연락처를 수정합니다.");
				System.out.print("수정하실 연락처의 번호를 입력해주세요: ");
				num = scan.nextInt(); // 검색을 통해 알아낸 인덱스 입력
				scan.nextLine(); // 버퍼 삭제
				
				System.out.println("수정할 내용을 입력합니다.");
				System.out.print("이름: "); // 이름 입력
				String n = scan.next();
				scan.nextLine(); // 버퍼 삭제
				
				String pn;
				do // 다른 번호와 중복되지 않도록 하는 코드
				{
					System.out.print("전화번호: "); // 전화번호 입력
					pn = scan.next();
					if(pn.equals(ab.list.get(num).getPhoneNum())) // 전화번호를 변경하지 않는 경우
						break;
					else
					{
						if(ab.checkPhoneNum(pn) == true) // 만약 다른 번호와 중복될 경우
						{
							System.out.println("이미 등록된 전화번호 입니다.");
						}
						else
							break;
					}	
					scan.nextLine(); // 버퍼 삭제
				}while(true);
				
				System.out.print("생일(미입력: 0): "); // 생일 입력
				String bd = scan.next();
				scan.nextLine(); // 버퍼 삭제
				System.out.print("주소(미입력: 0): "); // 주소 입력
				String a = scan.next();
				scan.nextLine(); // 버퍼 삭제
				System.out.print("이메일(미입력: 0): "); // 이메일 입력
				String em = scan.next();
				scan.nextLine(); // 버퍼 삭제
				
				try
				{
					newPerson = new Person(n, pn, bd, a, em); // Person객체로 새로운 연락처 생성
					ab.modify(num, newPerson); // 배열의 해당 인덱스 부분에 해당 내용 넣기
					System.out.println("수정되었습니다.");
				}
				catch(Exception e)
				{
					String str = e.getMessage();
					System.err.println(str); // 에러 메시지 출력
				}
				
				break;
				
			case 5: // 연락처 삭제
				System.out.println("연락처를 삭제합니다.");
				System.out.print("삭제하실 연락처의 번호를 입력해주세요: ");
				num = scan.nextInt(); // 검색을 통해 알아낸 인덱스 입력
				scan.nextLine(); // 버퍼 삭제
				try
				{
					ab.delete(num);
					System.out.println("삭제가 완료되었습니다.");
				}
				catch(Exception e)
				{
					String str = e.getMessage();
					System.err.println(str); // 에러 메시지 출력
				}
				break;
				
			case 6: // 연락처 파일 저장
				try
				{
					out = new ObjectOutputStream(new FileOutputStream("AddressBook.dat")); // 파일 열기
					ab.writeFile(out);
					System.out.println("파일 저장이 완료되었습니다.");
				}
				catch(IOException ioe)
				{
					System.out.println("파일로 출력할 수 없습니다.");
				}
				finally
				{
					try
					{
						out.close(); // 파일 닫기
					}
					catch(Exception e)
					{
					}
				}
				
				break;
				
			default:
				System.out.println("잘못 입력하셨습니다. 다시 입력하세요.");
			}

		}
		scan.close(); // 스캐너 클래스 닫기
	}
}*/
