package work0821;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JList;

@SuppressWarnings("serial")
public class EmpView extends JFrame{
	
	private DefaultListModel<String> dlmEmpno;
	private JList<String> jlEmpno;
	
	

	public EmpView() {
		
		dlmEmpno = new DefaultListModel<String>();
		jlEmpno = new JList<String>(dlmEmpno);
		
		EmpEvt eEvt = new EmpEvt(this);
		jlEmpno.addMouseListener(eEvt);
		
		add(jlEmpno);
		
		setBounds(200, 200, 200, 200);
		setVisible(true);
	}//EmpView
	
	public DefaultListModel<String> getDlmEmpno() {
		return dlmEmpno;
	}

	public JList<String> getjlEmpno() {
		return jlEmpno;
	}

	public static void main(String[] args) {
		new EmpView();
	}

}
