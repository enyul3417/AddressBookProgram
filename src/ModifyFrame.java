// ȭ������ 2017111701 ������
// ����ó�� �����ϴ� ȭ��

import java.awt.*;
import java.awt.event.*;

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
		
		frame = new JFrame("����ó ����"); // ������ ����
		frame.setPreferredSize(new Dimension(300, 200)); // ������ ������ ����
		frame.setLocation(500, 400); // ������ ��ġ ����
		Container contentPane = frame.getContentPane(); //contentPane ���� 
		
		JPanel panel1 = new JPanel(); // �г� ����
		panel1.setLayout(new GridLayout(5, 2)); // �г��� ���̾ƿ� ����
		// JLabel�� JTextField�� �̿��ؼ� �̸�, ��ȭ��ȣ, ����, �ּ�, �̸����� �Է��� ȭ�� �����
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
		panel1.add(new JLabel("�̸�"));
		panel1.add(text1);
		panel1.add(new JLabel("��ȭ��ȣ"));
		panel1.add(text2);
		panel1.add(new JLabel("����"));
		panel1.add(text3);
		panel1.add(new JLabel("�ּ�"));
		panel1.add(text4);
		panel1.add(new JLabel("�̸���"));
		panel1.add(text5);
		contentPane.add(panel1, BorderLayout.CENTER);
		
		// �ϴܿ� �Ϸ�, ��� ��ư ��ġ�ϱ�
		JPanel panel2 = new JPanel();
		button1 = new JButton("�Ϸ�");
		button2 = new JButton("���");
		panel2.add(button1);
		panel2.add(button2);
		contentPane.add(panel2, BorderLayout.SOUTH);
				
		button1.addActionListener(new MyMouseListener()); // �Ϸ� ��ư ����
		button2.addActionListener(new MyMouseListener()); // ��� ��ư ����
		
		//������ ���÷���
		frame.pack();
		frame.setVisible(true);
	}
	
	class MyMouseListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e) 
		{
			if(e.getSource() == button1)
			{
				while(true) // ��ȭ��ȣ �ߺ� ����� ���� ���� while��
				{
					String arr[] = new String[5];
				
					arr[0] = text1.getText(); // �̸�
					arr[1] = text2.getText(); // ��ȭ��ȣ
					if(arr[0].length() == 0 || arr[1].length() == 0) // �̸��̳� ��ȭ��ȣ�� �Է����� �ʾҴٸ�
					{
						JOptionPane.showMessageDialog(null, "�̸� �� ��ȭ��ȣ�� �ʼ��� �Է����ּ���.", "�̸� �� ��ȭ��ȣ �Է�", JOptionPane.WARNING_MESSAGE); // �Է��ؾ��Ѵٴ� �˸�
						break; // ��ϵ��� �ʰ� while�� Ż��
					}
					/*
					if(arr[1].equals(ab.list.get(rowNum).getPhoneNum())) // ��ȭ��ȣ�� �������� �ʴ� ���
					{
						//break;
					}
					else
					{*/
						if(ab.checkPhoneNum(arr[1]) == true) // ���� ��ϵ� ��ȣ���
						{
							JOptionPane.showMessageDialog(null, "�ش� ��ȭ��ȣ�� �����մϴ�.", "��ȭ��ȣ �ߺ� ���", JOptionPane.WARNING_MESSAGE); // ��ȭ��ȣ �ߺ� �˸�
							break; // ��ϵ��� �ʰ� while�� Ż��
						}
					//}

					arr[2] = text3.getText(); // ����
					arr[3] = text4.getText(); // �ּ�
					arr[4] = text5.getText(); // �̸���
					
					p = new Person(arr[0], arr[1], arr[2], arr[3], arr[4]);
					try {
						ab.modify(rowNum, p);
					} catch (Exception ex) {
						String str = ex.getMessage();
						JOptionPane.showMessageDialog(null, str, "���� �޽���", JOptionPane.ERROR_MESSAGE); // ���� �޽���
					}
				
					DefaultTableModel model = (DefaultTableModel) table.getModel();
					model.setValueAt(arr[0], rowNum, 0);
					model.setValueAt(arr[1], rowNum, 1);
					model.setValueAt(arr[2], rowNum, 2);
					model.setValueAt(arr[3], rowNum, 3);
					model.setValueAt(arr[4], rowNum, 4);
					break; // ����ó ���� ��� �� while�� Ż��
				}
			
				if(e.getSource() == button2)
				{
					frame.dispose();
				}
		}
		
		}
	}
}
