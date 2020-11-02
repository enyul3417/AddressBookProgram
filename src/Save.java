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
			out = new ObjectOutputStream(new FileOutputStream("AddressBook.dat")); // 파일 열기
			ab.writeFile(out);
			JOptionPane.showMessageDialog(null, "파일 저장이 완료되었습니다."); // 에러 메시지
		}
		catch(IOException ioe)
		{
			JOptionPane.showMessageDialog(null, "파일로 출력할 수 없습니다.", "에러 메시지", JOptionPane.ERROR_MESSAGE); // 에러 메시지
		}
		catch(Exception ex)
		{
			
		}
		finally
		{
			try
			{
				out.close(); // 파일 닫기
			}
			catch(Exception ex)
			{
			}
		}

	}

}
