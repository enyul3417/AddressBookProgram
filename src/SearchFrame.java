// ȭ������ 2017111701 ������
// �˻� ��ư�� ������ �� â���� �̸� �Ǵ� ��ȭ��ȣ�� �̿��� �˻��ϴ� Ŭ����

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

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
		
		frame = new JFrame("����ó �˻�"); // ������ ����
		frame.setPreferredSize(new Dimension(600, 400)); // ������ ������ ����
		frame.setLocation(500, 400); // ������ ��ġ ����
		Container contentPane = frame.getContentPane(); // contentPane ����
		
		String colNames[] = {"�̸�", "��ȭ��ȣ", "����", "�ּ�", "�̸���"};
		model = new DefaultTableModel (colNames, 0);
		JTable table2 = new JTable(model);
		JScrollPane scrollPane = new JScrollPane(table2); // ��ũ�ѹ� �߰�
		scrollPane.setPreferredSize(new Dimension(600, 300));
		contentPane.add(scrollPane, BorderLayout.NORTH);
		
		JPanel panel1 = new JPanel(); // �г� ����
		panel1.setLayout(new GridLayout(1,5));
		text1 = new JTextField();
		text2 = new JTextField();
		button1 = new JButton("�˻�");
		panel1.add(new JLabel("�̸�"));
		panel1.add(text1);
		panel1.add(new JLabel("��ȭ��ȣ"));
		panel1.add(text2);
		panel1.add(button1);
		contentPane.add(panel1, BorderLayout.CENTER);
		
		JPanel panel2 = new JPanel();
		button2 = new JButton("����");
		button3 = new JButton("����");
		button4 = new JButton("���ư���");
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
					JOptionPane.showMessageDialog(null, "�����Ϸ��� ����ó�� �������ּ���", "����ó �̼���", JOptionPane.WARNING_MESSAGE);
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
			if (e.getSource() == button1) // �˻� ��ư�� ������ �˻�
			{
				if(model.getRowCount() > 0) // ������ �˻� ����� �����ִٸ� �ʱ�ȭ ��Ű��
				{
					ab.setIndex(0);
					model.setNumRows(0);
				}
				
				String arr[] = new String[5]; // �˻� ����� ���� String �迭
				
				if(text1.getText().length() == 0 && text2.getText().length() == 0) // �ƹ��͵� �Է����� ������
				{
					JOptionPane.showMessageDialog(null, "�̸� �Ǵ� ��ȭ��ȣ�� �Է����ּ���", "�Է� ����", JOptionPane.WARNING_MESSAGE); // ��� �޽���
				}
				else 
				{
					if(text1.getText().length() > 0) // �̸����� �˻�
					{
						try
						{
							ab.duplicateCount(text1.getText()); // �ش� �̸��� ����̳� �ִ��� Ȯ��, ��ã���� �ͼ��� �߻�
						}
						catch(Exception ex)
						{
							String str = ex.getMessage();
							JOptionPane.showMessageDialog(null, str); // ��ã�Ҵٴ� �޽���
						}
						for(int i = 0; i < ab.getFind(); i++) // ã�� ��� ����ŭ ����ó ã��
						{
							int index = ab.searchName(text1.getText()); // �̸����� �˻�
							// ã�� ����� arr�� �ֱ�
							arr[0] = ab.list.get(index).getName();
							arr[1] = ab.list.get(index).getPhoneNum();
							arr[2] = ab.list.get(index).getBirthDate();
							arr[3] = ab.list.get(index).getAddress();
							arr[4] = ab.list.get(index).getEmail();
							model.addRow(arr); // table�� ��� ��Ÿ����
							ab.setIndex(index+1); // �������� �˻��� ���� ���� ã�� �ε��� ���ĺ��� �˻��ϴ� �뵵
						}
					}
					else if(text2.getText().length() > 0) // ��ȭ��ȣ�� �˻�
					{
						try 
						{
							int index = ab.searchNum(text2.getText());
							arr[0] = ab.list.get(index).getName();
							arr[1] = ab.list.get(index).getPhoneNum();
							arr[2] = ab.list.get(index).getBirthDate();
							arr[3] = ab.list.get(index).getAddress();
							arr[4] = ab.list.get(index).getEmail();
							model.addRow(arr);
						} 
						catch (Exception ex) 
						{
							String str = ex.getMessage();
							JOptionPane.showMessageDialog(null, str); // ��ã�Ҵٴ� �޽���
						}
					}
				}
				
			}
			if(e.getSource() == button4) // ���ư��� ��ư ������
			{
				frame.dispose();
			}
		}
	}
}
