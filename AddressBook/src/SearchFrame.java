// 화학전공 2017111701 최유리
// 검색 버튼을 누르면 새 창으로 이름 또는 전화번호를 이용해 검색하는 클래스

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.*;

public class SearchFrame {
	JFrame frame;
	JTable table, table2;
	JTextField text1, text2;
	JButton button1, button2, button3, button4;
	DefaultTableModel model;
	AddressBook ab;
	
	SearchFrame(AddressBook ab, JTable table)
	{
		this.ab = ab;
		//this.table = table;
		
		frame = new JFrame("연락처 검색"); // 프레임 생성
		frame.setPreferredSize(new Dimension(600, 400)); // 프레임 사이즈 지정
		frame.setLocation(500, 400); // 프레임 위치 지정
		Container contentPane = frame.getContentPane(); // contentPane 생성
		
		String colNames[] = {"이름", "전화번호", "생일", "주소", "이메일"};
		model = new DefaultTableModel (colNames, 0);
		JTable table2 = new JTable(model);
		JScrollPane scrollPane = new JScrollPane(table2); // 스크롤바 추가
		scrollPane.setPreferredSize(new Dimension(600, 300));
		contentPane.add(scrollPane, BorderLayout.NORTH);
		
		JPanel panel1 = new JPanel(); // 패널 생성
		panel1.setLayout(new GridLayout(1,5));
		text1 = new JTextField();
		text2 = new JTextField();
		button1 = new JButton("검색");
		panel1.add(new JLabel("이름"));
		panel1.add(text1);
		panel1.add(new JLabel("전화번호"));
		panel1.add(text2);
		panel1.add(button1);
		contentPane.add(panel1, BorderLayout.CENTER);
		
		JPanel panel2 = new JPanel();
		button2 = new JButton("수정");
		button3 = new JButton("삭제");
		button4 = new JButton("돌아가기");
		panel2.add(button2);
		panel2.add(button3);
		panel2.add(button4);
		contentPane.add(panel2, BorderLayout.SOUTH);
		
		button1.addActionListener(new MyMouseListener());
		button2.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				int rowNum = table2.getSelectedRow();
				if(rowNum == -1)
					JOptionPane.showMessageDialog(null, "수정하려는 연락처를 선택해주세요", "연락처 미선택", JOptionPane.WARNING_MESSAGE);
				else
					new ModifyFrame(table2, ab, rowNum);
			}
		});
		button3.addActionListener(new Delete(table2, ab));
		button4.addActionListener(new MyMouseListener());
		
		frame.pack();
		frame.setVisible(true);
	}
	
	class MyMouseListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			model.setNumRows(0);
			
			if (e.getSource() == button1) // 검색 버튼을 누르면 검색
			{				
				String arr[] = new String[5]; // 검색 결과를 담을 String 배열
				
				if(text1.getText().length() == 0 && text2.getText().length() == 0) // 아무것도 입력하지 않으면
				{
					JOptionPane.showMessageDialog(null, "이름 또는 전화번호를 입력해주세요", "입력 없음", JOptionPane.WARNING_MESSAGE); // 경고 메시지
				}
				else 
				{
					if(text1.getText().length() > 0) // 이름으로 검색
					{
						try {
							ResultSet rs = ab.searchName(text1.getText());
							while(rs.next()) // 찾은 내용 읽어오기
							{
								arr[0] = rs.getString("이름");
								arr[1] = rs.getString("전화번호");
								arr[2] = rs.getString("생일");
								arr[3] = rs.getString("주소");
								arr[4] = rs.getString("이메일");
								model.addRow(arr); // table에 결과 나타내기
							}
							JOptionPane.showMessageDialog(null, "검색이 완료되었습니다.");
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
					else if(text2.getText().length() > 0) // 전화번호로 검색
					{
						try {
							ResultSet rs = ab.searchNum(text2.getText());
							while(rs.next()) // 찾은 내용 읽어오기
							{
								arr[0] = rs.getString("이름");
								arr[1] = rs.getString("전화번호");
								arr[2] = rs.getString("생일");
								arr[3] = rs.getString("주소");
								arr[4] = rs.getString("이메일");
								model.addRow(arr); // table에 결과 나타내기
							}
							JOptionPane.showMessageDialog(null, "검색이 완료되었습니다.");
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
			}
			if(e.getSource() == button4) // 돌아가기 버튼 누르면
			{
				frame.dispose();
			}
		}
	}
}
