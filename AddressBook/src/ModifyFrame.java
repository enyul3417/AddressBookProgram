// 화학전공 2017111701 최유리
// 연락처를 수정하는 화면

import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;
import java.sql.SQLTimeoutException;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class ModifyFrame {
	JFrame frame;
	JTable table;
	JTextField text1, text2, text3, text4, text5;
	JButton button1, button2;
	AddressBook ab;
	Person p;
	int rowNum;
	
	ModifyFrame(JTable table, AddressBook ab, int rowNum)
	{
		this.table = table;
		this.ab = ab;
		this.rowNum = rowNum;
		
		frame = new JFrame("연락처 수정"); // 프레임 생성
		frame.setPreferredSize(new Dimension(300, 200)); // 프레임 사이즈 지정
		frame.setLocation(500, 400); // 프레임 위치 지정
		Container contentPane = frame.getContentPane(); //contentPane 생성 
		
		JPanel panel1 = new JPanel(); // 패널 생성
		panel1.setLayout(new GridLayout(5, 2)); // 패널의 레이아웃 설정
		// JLabel과 JTextField를 이용해서 이름, 전화번호, 생일, 주소, 이메일을 입력할 화면 만들기
		text1 = new JTextField();
		text1.setText((String) table.getValueAt(rowNum, 0));
		text2 = new JTextField();
		text2.setText((String) table.getValueAt(rowNum, 1));
		text3 = new JTextField();
		text3.setText((String) table.getValueAt(rowNum, 2));
		text4 = new JTextField();
		text4.setText((String) table.getValueAt(rowNum, 3));
		text5 = new JTextField();
		text5.setText((String) table.getValueAt(rowNum, 4));
		panel1.add(new JLabel("이름"));
		panel1.add(text1);
		panel1.add(new JLabel("전화번호"));
		panel1.add(text2);
		panel1.add(new JLabel("생일"));
		panel1.add(text3);
		panel1.add(new JLabel("주소"));
		panel1.add(text4);
		panel1.add(new JLabel("이메일"));
		panel1.add(text5);
		contentPane.add(panel1, BorderLayout.CENTER);
		
		// 하단에 완료, 취소 버튼 배치하기
		JPanel panel2 = new JPanel();
		button1 = new JButton("완료");
		button2 = new JButton("취소");
		panel2.add(button1);
		panel2.add(button2);
		contentPane.add(panel2, BorderLayout.SOUTH);
				
		button1.addActionListener(new MyMouseListener()); // 완료 버튼 동작
		button2.addActionListener(new MyMouseListener()); // 취소 버튼 동작
		
		//윈도우 디스플레이
		frame.pack();
		frame.setVisible(true);
	}
	
	class MyMouseListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e) 
		{
			String s = (String) table.getValueAt(rowNum, 1); // 선택한 행의 전화번호 가져오기
			
			if(e.getSource() == button1)
			{
				while(true) // 전화번호 중복 등록은 막기 위한 while문
				{
					String arr[] = new String[5];
				
					arr[0] = text1.getText(); // 이름
					arr[1] = text2.getText(); // 전화번호
					if(arr[0].length() == 0 || arr[1].length() == 0) // 이름이나 전화번호를 입력하지 않았다면
					{
						JOptionPane.showMessageDialog(null, "이름 및 전화번호는 필수로 입력해주세요.", "이름 및 전화번호 입력", JOptionPane.WARNING_MESSAGE); // 입력해야한다는 알림
						break; // 등록되지 않고 while문 탈출
					}
					
					try 
					{
						if(s.equals(arr[1])) // 전화번호를 수정하지 않을 경우
						{
						}
						else if(ab.checkPhoneNum(arr[1]) == true) // 다른 사람의 전화번호와 중복될 경우
						{
							JOptionPane.showMessageDialog(null, "해당 전화번호가 존재합니다.", "전화번호 중복 경고", JOptionPane.WARNING_MESSAGE); // 전화번호 중복 알림
							break; // 등록되지 않고 while문 탈출
						}
					} 
					catch (SQLTimeoutException  ste)
					{
						JOptionPane.showMessageDialog(null, ste.getMessage(), "에러 메시지", JOptionPane.WARNING_MESSAGE); // 에러 메시지 
					}
					catch (SQLException se)
					{
						JOptionPane.showMessageDialog(null, se.getMessage(), "에러 메시지", JOptionPane.WARNING_MESSAGE); // 에러 메시지 
					}
					catch (Exception ex)
					{
						
					}

					arr[2] = text3.getText(); // 생일
					arr[3] = text4.getText(); // 주소
					arr[4] = text5.getText(); // 이메일
					
					p = new Person(arr[0], arr[1], arr[2], arr[3], arr[4]);
					
					try 
					{
						ab.modify(s, p); // 수정하기
					} 
					catch (SQLTimeoutException  ste)
					{
						JOptionPane.showMessageDialog(null, ste.getMessage(), "에러 메시지", JOptionPane.WARNING_MESSAGE); // 에러 메시지 
					}
					catch (SQLException se)
					{
						JOptionPane.showMessageDialog(null, se.getMessage(), "에러 메시지", JOptionPane.WARNING_MESSAGE); // 에러 메시지 
					}
					
					DefaultTableModel model = (DefaultTableModel) table.getModel();
					model.setValueAt(arr[0], rowNum, 0);
					model.setValueAt(arr[1], rowNum, 1);
					model.setValueAt(arr[2], rowNum, 2);
					model.setValueAt(arr[3], rowNum, 3);
					model.setValueAt(arr[4], rowNum, 4);
					JOptionPane.showMessageDialog(null, "수정이 완료되었습니다.");
					break; // 연락처 정상 등록 후 while문 탈출
				}
			}
			
			if(e.getSource() == button2)
			{
				frame.dispose();
			}
		
		}
	}
}
