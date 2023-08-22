package work0821;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;
import java.util.List;

import javax.swing.JOptionPane;

public class EmpEvt extends MouseAdapter {

	private EmpView eView;

	public EmpEvt(EmpView eView) {
		this.eView = eView;
		setEmpnoList();
		eView.addWindowListener( new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				eView.dispose();
			}//windowClosing
		});//addWIndowListener
	}// EmpEvt

	@Override
	public void mouseClicked(MouseEvent e) {
		if( e.getSource() == eView.getjlEmpno() ) {
			setInfoView();
		}//end if
	}//mouseClicked

	public void setEmpnoList() {
		EmpDAO eDAO = EmpDAO.getInstance();
		
		try {
			List<String> list =  eDAO.selectEmpno();
			
			eView.getDlmEmpno().addAll(list);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}//end catch
		
	}// setEmpnoList
	
	public void setInfoView() {
		EmpDAO eDAO = EmpDAO.getInstance();
		
		String empno = eView.getjlEmpno().getSelectedValue();
		
		InfoVO iVO = null;
		
		try {
			iVO = eDAO.selectOneInfo(empno);
			if( iVO == null ) {
				JOptionPane.showMessageDialog(eView, empno + "번 사원은 존재하지 않습니다");
				return;
			}//end if
			
			new InfoView(eView, iVO);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}//end catch
		
	}//end setInfo
	


//	@Override
//	public void valueChanged(ListSelectionEvent e) {
//		if ( e.getSource() == eView.getjlEmpno() ) {
//			InfoView iView = new InfoView();
			
//			setInfo(iView);
			
//			System.out.println(
//			eView.getjlEmpno().getSelectedValue()
//			);
//		}
//	}

}// class
