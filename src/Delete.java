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
			JOptionPane.showMessageDialog(null, "�����Ϸ��� ����ó�� �������ּ���", "����ó �̼���", JOptionPane.WARNING_MESSAGE);
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
				JOptionPane.showMessageDialog(null, str, "���� �޽���", JOptionPane.ERROR_MESSAGE); // ���� �޽���
			}
			DefaultTableModel model = (DefaultTableModel) table.getModel();
			model.removeRow(rowNum);
		}
	}
}
