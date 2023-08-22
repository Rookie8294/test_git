package work0821;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import kr.co.sist.dao.DbConn;

public class EmpDAO {
	
	private static EmpDAO eDAO;
	
	private EmpDAO() {
		//이것은 생성자
		System.out.println("이것은 생성자");
	}//EmpDAO
	
	public static EmpDAO getInstance() {
		if( eDAO == null ) {
			eDAO = new EmpDAO();
		}//end if
		return eDAO;
	}//getInstance
	
	public List<String> selectEmpno() throws SQLException {
		List<String> list = new ArrayList<String>();
		
		Connection con = null;
		PreparedStatement  pstmt = null;
		ResultSet rs = null;
		
		DbConn db = DbConn.getInstance();
		
		try {
			con = db.getConnection("localhost", "scott", "tiger");
			
			String sql = "select empno from emp";
			pstmt = con.prepareStatement(sql);
			
			rs = pstmt.executeQuery();
			
			while( rs.next() ) {
				list.add(String.valueOf(rs.getInt("empno")));
			}//end while
		} finally {
			db.dbClose(rs, pstmt, con);
		}
		
		return list;
		
	}//selectEmpno;
	
	public InfoVO selectOneInfo(String empno) throws SQLException{
		InfoVO iVO = null;
		Connection con = null;
		PreparedStatement  pstmt = null;
		ResultSet rs = null;
		
		DbConn db = DbConn.getInstance();
		
		try {
			con = db.getConnection("localhost", "scott", "tiger");
			
			String sql = "select empno, ename, to_char(hiredate, 'yyyy-mm-dd') hiredate, job, sal from emp where empno = ?";
			pstmt = con.prepareStatement(sql);
			
			pstmt.setInt(1, Integer.parseInt(empno));
			
			rs = pstmt.executeQuery();
			
			while( rs.next() ) {
				iVO = new InfoVO();
				iVO.setEmpno(rs.getString("empno"));
				iVO.setEname(rs.getString("ename"));
				iVO.setHiredate(rs.getString("hiredate"));
				iVO.setJob(rs.getString("job"));
				iVO.setSal(rs.getString("sal"));
			}//end while
		} finally {
			db.dbClose(rs, pstmt, con);
		}//end finally
		return iVO;
	}//selectOneInfo

}//class
