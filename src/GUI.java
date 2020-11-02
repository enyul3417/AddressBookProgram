// ȭ������ 2017111701 ������
// ����ó ������Ʈ-GUIŬ����

// ���� ���� �޴��� ����ó �߰��� �����Ǿ� �ֽ��ϴ�.

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
		
		File file = new File("AddressBook.dat"); // ���� ���丮�� �ִ� AddressBook.dat�� ���� File ��ü ����
		if(!file.exists()) // ���� ������ ������
			file.createNewFile(); // ���� ���丮�� AddressBook.dat�̶�� �̸��� ���� ����
		try
		{
			in = new ObjectInputStream(new FileInputStream("AddressBook.dat")); // ���� �ҷ�����
			ab = new AddressBook(100, in); // ��ü ����
			ab.readFile(in);
		}
		catch(FileNotFoundException fnfe) // ������ ã�� ���� ���
		{
			JOptionPane.showMessageDialog(null, "������ �������� �ʽ��ϴ�.", "���� �޽���", JOptionPane.WARNING_MESSAGE); // ���� �޽���
			
		}
		catch (EOFException eofe) // �� �̻� ���� �����Ͱ� ���� ���
		{
			JOptionPane.showMessageDialog(null, "��"); // ���� �޽���
		}
		catch(IOException ioe) // ������ ���� �� ���� ���
		{
			JOptionPane.showMessageDialog(null, "������ ���� �� �����ϴ�.", "���� �޽���", JOptionPane.WARNING_MESSAGE); // ���� �޽���
		}
		catch(ClassNotFoundException cnfe)
		{
			JOptionPane.showMessageDialog(null, "�ش� Ŭ������ �������� �ʽ��ϴ�.", "���� �޽���", JOptionPane.WARNING_MESSAGE); // ���� �޽���
		}
		finally
		{
			try
			{
				in.close(); // ���� �ݱ�
			}
			catch(Exception e)
			{
			}
		}
		
		JFrame frame = new JFrame("����ó ���α׷�"); // ������ ����
		frame.setPreferredSize(new Dimension(600, 400)); // ������ ������ ����
		frame.setLocation(500,400); // ������ ��ġ ����
		Container contentPane = frame.getContentPane(); //contentPane ���� 
		
		String colNames[] = {"�̸�", "��ȭ��ȣ", "����", "�ּ�", "�̸���"}; // �� ���� �̸� ����
		// ���̺� ����
		DefaultTableModel model = new DefaultTableModel (colNames, 0);
		JTable table = new JTable(model);
		if(file.exists()) // ������ �����ϸ�
		{
			// ����ó�� ������ ���̺� ��Ÿ����
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
		JScrollPane scrollPane = new JScrollPane(table); // ��ũ�ѹ� �߰�
		contentPane.add(scrollPane, BorderLayout.CENTER);
		
		// ���̺� �Ʒ��� ��ư ���� �� �߰�
		JPanel panel = new JPanel();
		JButton button1 = new JButton("�˻�");
		JButton button2 = new JButton("����");
		JButton button3 = new JButton("����");
		JButton button4 = new JButton("�߰�");
		JButton button5 = new JButton("���ΰ�ħ");
		JButton button6 = new JButton("����ó ����");
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
							JOptionPane.showMessageDialog(null, "�����Ϸ��� ����ó�� �������ּ���", "����ó �̼���", JOptionPane.WARNING_MESSAGE);
						else
							new ModifyFrame(table, ab, rowNum);
					}
				});
		button3.addActionListener(new Delete(table, ab));
		
		button4.addActionListener(new ActionListener() // �߰� ��ư�� ���� ����
				{
					public void actionPerformed(ActionEvent e)
					{
						new AddFrame(table, ab);
					}
				});
		button5.addActionListener(new Refresh(table, ab));
		button6.addActionListener(new Save(ab));
		
		
		//������ ���÷���
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);
	}
}
