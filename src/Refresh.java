import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.*;

public class Refresh implements ActionListener {
	JTable table;
	AddressBook ab;
	
	Refresh(JTable table, AddressBook ab)
	{
		this.table = table;
		this.ab = ab;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		DefaultTableModel model = (DefaultTableModel) table.getModel();
		//String arr[] = new String[5];
		for(int i = 0; i < ab.list.size(); i++)
		{
			/*
			 * arr[0] = ab.list.get(i).getName(); arr[1] = ab.list.get(i).getPhoneNum();
			 * arr[2] = ab.list.get(i).getBirthDate(); arr[3] = ab.list.get(i).getAddress();
			 * arr[4] = ab.list.get(i).getEmail();
			 */
			model.setValueAt(ab.list.get(i).getName(), i, 0);
			model.setValueAt(ab.list.get(i).getPhoneNum(), i, 1);
			model.setValueAt(ab.list.get(i).getBirthDate(), i, 2);
			model.setValueAt(ab.list.get(i).getAddress(), i, 3);
			model.setValueAt(ab.list.get(i).getEmail(), i, 4);
		}
	}

}
