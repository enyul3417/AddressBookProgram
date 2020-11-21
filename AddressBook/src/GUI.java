// 화학전공 2017111701 최유리
// 연락처 프로젝트-GUI클래스

// 현재 메인 메뉴와 연락처 추가만 구현되어 있습니다.

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.*;
import java.sql.*;

public class GUI {
	static AddressBook ab;
	
	public static void main(String args[]) throws Exception
	{		
		// file을 읽어오는 부분을 삭제		
		try
		{
			ab = new AddressBook();
			JOptionPane.showMessageDialog(null, "데이터베이스에 접속했습니다.");
		}
		catch (ClassNotFoundException cnfe)
		{
			JOptionPane.showMessageDialog(null, "해당 클래스를 찾을 수 없습니다." + cnfe.getMessage(), "에러 메시지", JOptionPane.WARNING_MESSAGE); // 에러 메시지 
		}
		catch (SQLException se)
		{
			JOptionPane.showMessageDialog(null, se.getMessage(), "에러 메시지", JOptionPane.WARNING_MESSAGE); // 에러 메시지 
		}
		
		JFrame frame = new JFrame("연락처 프로그램"); // 프레임 생성
		frame.setPreferredSize(new Dimension(600, 400)); // 프레임 사이즈 지정
		frame.setLocation(500,400); // 프레임 위치 지정
		Container contentPane = frame.getContentPane(); //contentPane 생성 
		
		String colNames[] = {"이름", "전화번호", "생일", "주소", "이메일"}; // 각 열의 이름 설정
		// 테이블 생성
		DefaultTableModel model = new DefaultTableModel (colNames, 0);
		JTable table = new JTable(model);		
		String arr[] = new String[5];
		
		while(ab.rs.next())  // 읽어온 내용 나타내기
		{
			arr[0] = ab.rs.getString("이름");
			arr[1] = ab.rs.getString("전화번호");
			arr[2] = ab.rs.getString("생일");
			arr[3] = ab.rs.getString("주소");
			arr[4] = ab.rs.getString("이메일");
			model.addRow(arr);
		}
		
		JScrollPane scrollPane = new JScrollPane(table); // 스크롤바 추가
		contentPane.add(scrollPane, BorderLayout.CENTER);
		
		// 테이블 아래에 버튼 생성 및 추가
		JPanel panel = new JPanel();
		JButton button1 = new JButton("검색");
		JButton button2 = new JButton("수정");
		JButton button3 = new JButton("삭제");
		JButton button4 = new JButton("추가");
		JButton button5 = new JButton("새로고침");
		JButton button6 = new JButton("프로그램 종료");
		panel.add(button1);
		panel.add(button2);
		panel.add(button3);
		panel.add(button4);
		panel.add(button5);
		panel.add(button6);
		contentPane.add(panel, BorderLayout.SOUTH);
		
		button1.addActionListener(new ActionListener() // 검색 버튼에 대한 동작
				{
					public void actionPerformed(ActionEvent e)
					{
						new SearchFrame(ab, table);
					}
				});
		button2.addActionListener(new ActionListener() // 수정 버튼에 대한 동작
				{
					public void actionPerformed(ActionEvent e)
					{
						int rowNum = table.getSelectedRow();
						if(rowNum == -1) // 수정할 연락처를 선택하지 않았다면
							JOptionPane.showMessageDialog(null, "수정하려는 연락처를 선택해주세요", "연락처 미선택", JOptionPane.WARNING_MESSAGE); // 경고 메시지
						else
							new ModifyFrame(table, ab, rowNum); // 연락처를 골랐다면 수정 창 나타내기
					}
				});
		button3.addActionListener(new Delete(table, ab)); // 삭제 버튼에 대한 동작
		
		button4.addActionListener(new ActionListener() // 추가 버튼에 대한 동작
				{
					public void actionPerformed(ActionEvent e)
					{
						new AddFrame(table, ab);
					}
				});
		// 검색을 이용해서 삭제, 수정한 경우 테이블을 새로 고침하는 용도
		button5.addActionListener(new Refresh(table, ab));  // 새로 고침 버튼에 대한 동작
		button6.addActionListener(new Close(ab)); // 프로그램 종료 버튼에 대한 동작
		
		
		//윈도우 디스플레이
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);
	}
}
