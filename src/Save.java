import java.awt.event.*;
import java.io.*;

import javax.swing.JOptionPane;

public class Save implements ActionListener
{
	AddressBook ab;
	ObjectOutputStream out;
	
	Save(AddressBook ab) 
	{
		this.ab = ab;
	}
	
	public void actionPerformed(ActionEvent e) 
	{
		try
		{
			out = new ObjectOutputStream(new FileOutputStream("AddressBook.dat")); // ���� ����
			ab.writeFile(out);
			JOptionPane.showMessageDialog(null, "���� ������ �Ϸ�Ǿ����ϴ�."); // ���� �޽���
		}
		catch(IOException ioe)
		{
			JOptionPane.showMessageDialog(null, "���Ϸ� ����� �� �����ϴ�.", "���� �޽���", JOptionPane.ERROR_MESSAGE); // ���� �޽���
		}
		catch(Exception ex)
		{
			
		}
		finally
		{
			try
			{
				out.close(); // ���� �ݱ�
			}
			catch(Exception ex)
			{
			}
		}

	}

}
