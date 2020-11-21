// 2017111701 화학전공 최유리
// 프로그램 종료 버튼을 누르면 Statement를 닫고 DB와 연결을 끊은 후, 프로그램이 종료됩니다

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Close implements ActionListener 
{
	AddressBook ab;
	
	Close(AddressBook ab)
	{
		this.ab = ab;
	}

	public void actionPerformed(ActionEvent e) 
	{
		try
		{
			ab.closeAll(); // Statement와 DB 닫기
		}
		catch (Exception ignored)
		{
		}
		System.exit(0); // 모든 창 닫기
	}
}
