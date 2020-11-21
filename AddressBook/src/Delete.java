import java.awt.event.*;
import java.sql.SQLException;
import java.sql.SQLTimeoutException;

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
		String s = (String) table.getValueAt(rowNum, 1); // 해당 행의 전화번호 가져오기
		
		if (rowNum == -1) // 삭제할 연락처를 선택하지 않았다면
		{ 
			JOptionPane.showMessageDialog(null, "삭제하려는 연락처를 선택해주세요", "연락처 미선택", JOptionPane.WARNING_MESSAGE); // 경고 메시지
		}
		else // 삭제할 연락처를 선택했다면
		{
			int as = JOptionPane.showConfirmDialog(null, "삭제하시겠습니까?", "삭제 확인", JOptionPane.YES_NO_OPTION); // 삭제 확인 창
			if(as == JOptionPane.CLOSED_OPTION || as == JOptionPane.NO_OPTION) // 예, 아니오 없이 창을 닫았거나 아니오를 고른 경우
			{
			}
			else // 예를 누른 경우
			{
				try
				{
					ab.delete(s); // 연락처 삭제
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
				model.removeRow(rowNum); // 테이블에서 해당 행 삭제
			}
		}
	}
}
