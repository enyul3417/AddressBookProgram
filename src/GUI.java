// 화학전공 2017111701 최유리
// 연락처 프로젝트-GUI클래스

// 현재 메인 메뉴와 연락처 추가만 구현되어 있습니다.

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.swing.*;
import javax.swing.table.*;

public class GUI {
	static AddressBook ab;
	
	public static void main(String args[]) throws Exception
	{
		ObjectInputStream in = null;
		//AddressBook ab = null;
		
		File file = new File("AddressBook.dat"); // 현재 디렉토리에 있는 AddressBook.dat에 대한 File 객체 생성
		if(!file.exists()) // 만약 파일이 없으면
			file.createNewFile(); // 현재 디렉토리에 AddressBook.dat이라는 이름의 파일 생성
		try
		{
			in = new ObjectInputStream(new FileInputStream("AddressBook.dat")); // 파일 불러오기
			ab = new AddressBook(100, in); // 객체 생성
			ab.readFile(in);
		}
		catch(FileNotFoundException fnfe) // 파일을 찾지 못한 경우
		{
			JOptionPane.showMessageDialog(null, "파일이 존재하지 않습니다.", "에러 메시지", JOptionPane.WARNING_MESSAGE); // 에러 메시지
			
		}
		catch (EOFException eofe) // 더 이상 읽을 데이터가 없는 경우
		{
			JOptionPane.showMessageDialog(null, "끝"); // 에러 메시지
		}
		catch(IOException ioe) // 파일을 읽을 수 없는 경우
		{
			JOptionPane.showMessageDialog(null, "파일을 읽을 수 없습니다.", "에러 메시지", JOptionPane.WARNING_MESSAGE); // 에러 메시지
		}
		catch(ClassNotFoundException cnfe)
		{
			JOptionPane.showMessageDialog(null, "해당 클래스가 존재하지 않습니다.", "에러 메시지", JOptionPane.WARNING_MESSAGE); // 에러 메시지
		}
		finally
		{
			try
			{
				in.close(); // 파일 닫기
			}
			catch(Exception e)
			{
			}
		}
		
		JFrame frame = new JFrame("연락처 프로그램"); // 프레임 생성
		frame.setPreferredSize(new Dimension(600, 400)); // 프레임 사이즈 지정
		frame.setLocation(500,400); // 프레임 위치 지정
		Container contentPane = frame.getContentPane(); //contentPane 생성 
		
		String colNames[] = {"이름", "전화번호", "생일", "주소", "이메일"}; // 각 열의 이름 설정
		// 테이블 생성
		DefaultTableModel model = new DefaultTableModel (colNames, 0);
		JTable table = new JTable(model);
		if(file.exists()) // 파일이 존재하면
		{
			// 연락처의 내용을 테이블에 나타내기
			String arr[] = new String[5];
			for(int i = 0; i < ab.list.size(); i++)
			{
				arr[0] = ab.list.get(i).getName();
				arr[1] = ab.list.get(i).getPhoneNum();
				arr[2] = ab.list.get(i).getBirthDate();
				arr[3] = ab.list.get(i).getAddress();
				arr[4] = ab.list.get(i).getEmail();
				model.addRow(arr);
			}
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
		JButton button6 = new JButton("연락처 저장");
		panel.add(button1);
		panel.add(button2);
		panel.add(button3);
		panel.add(button4);
		panel.add(button5);
		panel.add(button6);
		contentPane.add(panel, BorderLayout.SOUTH);
		
		button1.addActionListener(new ActionListener()
				{
					public void actionPerformed(ActionEvent e)
					{
						new SearchFrame(ab, table);
					}
				});
		button2.addActionListener(new ActionListener()
				{
					public void actionPerformed(ActionEvent e)
					{
						int rowNum = table.getSelectedRow();
						if(rowNum == -1)
							JOptionPane.showMessageDialog(null, "수정하려는 연락처를 선택해주세요", "연락처 미선택", JOptionPane.WARNING_MESSAGE);
						else
							new ModifyFrame(table, ab, rowNum);
					}
				});
		button3.addActionListener(new Delete(table, ab));
		
		button4.addActionListener(new ActionListener() // 추가 버튼에 대한 동작
				{
					public void actionPerformed(ActionEvent e)
					{
						new AddFrame(table, ab);
					}
				});
		button5.addActionListener(new Refresh(table, ab));
		button6.addActionListener(new Save(ab));
		
		
		//윈도우 디스플레이
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);
	}
}
