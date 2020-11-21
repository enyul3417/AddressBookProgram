// 2017111701 화학전공 최유리
// 새로고침 버튼 -> 검색을 이용한 수정, 삭제 등을 한 후 연락처 목록을 새로고침할 때 사용합니다.

import java.awt.event.*;
import java.sql.*;
import javax.swing.*;
import javax.swing.table.*;

public class Refresh implements ActionListener {
	JTable table;
	AddressBook ab;
	
	Refresh(JTable table, AddressBook ab)
	{
		this.table = table;
		this.ab = ab;
	}

	public void actionPerformed(ActionEvent e) 
	{
		DefaultTableModel model = (DefaultTableModel) table.getModel();
		model.setNumRows(0); // 테이블 초기화
		String arr[] = new String[5];
		
		try {
			ab.readFile();
			while(ab.rs.next())  // DB에서 읽어온 값으로 다시 나타내기
			{
				arr[0] = ab.rs.getString("이름");
				arr[1] = ab.rs.getString("전화번호");
				arr[2] = ab.rs.getString("생일");
				arr[3] = ab.rs.getString("주소");
				arr[4] = ab.rs.getString("이메일");
				model.addRow(arr);
			}
		} 
		catch (SQLFeatureNotSupportedException  sfnse)
		{
			JOptionPane.showMessageDialog(null, sfnse.getMessage(), "에러 메시지", JOptionPane.WARNING_MESSAGE); // 에러 메시지 
		}
		catch (SQLTimeoutException  ste)
		{
			JOptionPane.showMessageDialog(null, ste.getMessage(), "에러 메시지", JOptionPane.WARNING_MESSAGE); // 에러 메시지 
		}
		catch (SQLException se)
		{
			JOptionPane.showMessageDialog(null, se.getMessage(), "에러 메시지", JOptionPane.WARNING_MESSAGE); // 에러 메시지 
		}
	}

}
