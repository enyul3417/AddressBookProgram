import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.*;

public class Delete implements ActionListener{
	JTable table;
	AddressBook ab;
	
	Delete(JTable table, AddressBook ab)
	{
		this.table = table;
		this.ab = ab;
	}
	
	public void actionPerformed(ActionEvent e) {
		int rowNum = table.getSelectedRow();
		if (rowNum == -1)
		{
			JOptionPane.showMessageDialog(null, "삭제하려는 연락처를 선택해주세요", "연락처 미선택", JOptionPane.WARNING_MESSAGE);
		}
		else
		{
			try
			{
				ab.delete(rowNum);
			}
			catch (Exception ex)
			{
				String str = ex.getMessage();
				JOptionPane.showMessageDialog(null, str, "에러 메시지", JOptionPane.ERROR_MESSAGE); // 에러 메시지
			}
			DefaultTableModel model = (DefaultTableModel) table.getModel();
			model.removeRow(rowNum);
		}
	}
}
