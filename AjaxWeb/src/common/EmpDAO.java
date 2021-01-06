package common;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmpDAO {
	Connection conn = null;
	
	public EmpDAO() {
		try {
			String user = "hr";
			String pw = "hr";
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection(url, user, pw);
			System.out.println("Database에 연결되었습니다.\n");
		} catch (ClassNotFoundException cnfe) {
			System.out.println("DB 드라이버 로딩 실패 :" + cnfe.toString());
		} catch (SQLException sqle) {
			System.out.println("DB 접속실패 : " + sqle.toString());
		} catch (Exception e) {
			System.out.println("Unkonwn error");
			e.printStackTrace();
		}
	}// end of 생성자.

	// 추가 method
	public boolean insertEmp(EmployeeVO vo) {
		String sql = "insert into emp_temp(employee_id,first_name,last_name,email,hire_date,job_id)"
				+ "values (employees_seq.nextval,?,?,?,sysdate,?)";
		int r = 0;
		try {
			PreparedStatement ppst = conn.prepareStatement(sql);
			ppst.setString(1, vo.getFirstName());
			ppst.setString(2, vo.getLastName());
			ppst.setString(3, vo.getEmail());
			ppst.setString(4, vo.getJobId());
			r = ppst.executeUpdate();
			System.out.println(r + "건이 입력됨.");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return r == 1 ? true : false;
	}
	//삭제 method
	public boolean deleteEmp(EmployeeVO vo) {
		String sql = "delete from emp_temp where employee_id=?";
		int r = 0;
		try {
			PreparedStatement psmt = conn.prepareStatement(sql);
			psmt.setInt(1, vo.getEmployeeId());

			r = psmt.executeUpdate();
			System.out.println(r + "건이 삭제됨.");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return r == 1 ? true : false;
	}
	//전체리스트 method
	public List<EmployeeVO> getEmplist() {
		String sql = "select * from emp_temp order by 1 desc";
		List<EmployeeVO> list = new ArrayList<>();
		try {
			PreparedStatement psmt = conn.prepareStatement(sql);
			ResultSet rs = psmt.executeQuery();
			while (rs.next()) {
				EmployeeVO vo = new EmployeeVO();
				vo.setEmployeeId(rs.getInt("employee_id"));
				vo.setFirstName(rs.getString("first_name"));
				vo.setLastName(rs.getString("last_name"));
				vo.setEmail(rs.getString("email"));
				vo.setPhoneNumber(rs.getString("phone_number"));
				vo.setHireDate(rs.getString("hire_date"));
				vo.setJobId(rs.getString("job_id"));
				vo.setSalary(rs.getInt("salary"));
				list.add(vo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return list;
	}// end of getEmplist()
}
