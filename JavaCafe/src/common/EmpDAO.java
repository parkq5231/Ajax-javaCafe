package common;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

	// insert schedule
	public void insertschedule(Schedule sch) {
		String sql = "insert into calendar values(?,?,?,?)";
		try {
			PreparedStatement psmt = conn.prepareStatement(sql);// parameter에 값 넣어줌
			psmt.setString(1, sch.getTitle());// 1번째 물음표에 sch의 title항목을 넣겠다는 의미
			psmt.setString(2, sch.getStartDate());
			psmt.setString(3, sch.getEndDate());
			psmt.setString(4, sch.getUrl());
			int r =psmt.executeUpdate();
			System.out.println(r+"건이 업데이트됨.");
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally { 
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}//end of insertSchedule
	
	// schedule DAO
	//calendar table 생성하고 나서 해야함 (title/start_date/end_date/url)순
	public List<Schedule> getScheduleList() {// void타입
		String sql = "select * from calendar order by 1";
		List<Schedule> list = new ArrayList<>();
		try {
			PreparedStatement psmt = conn.prepareStatement(sql);
			ResultSet rs = psmt.executeQuery();
			while (rs.next()) {
				Schedule sch = new Schedule();
				sch.setTitle(rs.getString("title"));
				sch.setStartDate(rs.getString("start_date"));
				sch.setEndDate(rs.getString("end_date"));
				sch.setUrl(rs.getString("url"));
				list.add(sch);
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
	}

	// 부서이름과 숫자 나오는 메소드 chart할 때사용했음
	//Chart DAO
	public Map<String, Integer> getMemberByDept() {
		String sql = "select department_name ,count(*) " + "from employees e , departments d "
				+ "where e.department_id = d.department_id " + "group by department_name";
		Map<String, Integer> map = new HashMap<String, Integer>();// 할때마다 선언하게 하지 않기 위해 위로 뺌
		try {
			PreparedStatement psmt = conn.prepareStatement(sql);
			ResultSet rs = psmt.executeQuery();
			while (rs.next()) {
				map.put(rs.getString(1), rs.getInt(2));// key value순서
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
		return map;
	}

	// 특정 컬럼 가져오는 메소드
	public List<EmployeeVO> getSelectList() {
		String sql = "select employee_id,first_name,last_name,email,hire_date,salary " + "from employees";
		List<EmployeeVO> list = new ArrayList<EmployeeVO>();
		try {
			PreparedStatement psmt = conn.prepareStatement(sql);
			ResultSet rs = psmt.executeQuery();
			while (rs.next()) {
				EmployeeVO vo = new EmployeeVO();
				vo.setEmployeeId(rs.getInt("employee_id"));
				vo.setFirstName(rs.getString("first_name"));
				vo.setLastName(rs.getString("last_name"));
				vo.setEmail(rs.getString("email"));
				vo.setHireDate(rs.getString("hire_date"));
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
	}

	// update방식
	public EmployeeVO updateEmp(EmployeeVO vo) { // EmployeeVO타입의 vo매개변수
		String sql1 = " update emp_temp " + "set first_name=?,last_name=?,email=?,job_id=? " + "where employee_id = ?";
		int r = 0;
		try {
			PreparedStatement ppst = conn.prepareStatement(sql1);
			ppst.setString(1, vo.getFirstName());
			ppst.setString(2, vo.getLastName());
			ppst.setString(3, vo.getEmail());
			ppst.setString(4, vo.getJobId());
			ppst.setInt(5, vo.getEmployeeId());
			r = ppst.executeUpdate();
			System.out.println(r + "건이 업데이트됨.");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return vo;
	}

	// 추가 method
	public EmployeeVO insertEmp(EmployeeVO vo) { // EmployeeVO타입의 vo매개변수
		String sql1 = "select employees_seq.nextval from dual"; // seq번호 가져옴
		String sql2 = "select * from emp_temp where employee_id = ?";
		String sql = "insert into emp_temp(employee_id,first_name,last_name,email,hire_date,job_id)"
				+ "values (?,?,?,?,sysdate,?)";
		int r = 0;
		String newSeq = null; // null값을 넣었을 때 if에서 처리 후 다시 null값이 안될듯.
		EmployeeVO newVo = new EmployeeVO(); // instance할당
		try {
			PreparedStatement ppst = conn.prepareStatement(sql1);
			ResultSet rs = ppst.executeQuery(); // sql1의 쿼리 결과 1건을 가져옴
			if (rs.next()) {
				newSeq = rs.getString(1);
			}
			ppst = conn.prepareStatement(sql);
			ppst.setString(1, newSeq);
			ppst.setString(2, vo.getFirstName());
			ppst.setString(3, vo.getLastName());
			ppst.setString(4, vo.getEmail());
			ppst.setString(5, vo.getJobId());
			r = ppst.executeUpdate();
			System.out.println(r + "건이 입력됨.");

			ppst = conn.prepareStatement(sql2);// sql2의 조회값 넣어줌
			ppst.setString(1, newSeq);
			rs = ppst.executeQuery();
			if (rs.next()) {
				newVo.setEmail(rs.getString("email"));
				newVo.setEmployeeId(rs.getInt("employee_id"));
				newVo.setFirstName(rs.getString("first_name"));
				newVo.setLastName(rs.getString("last_name"));
				newVo.setJobId(rs.getString("job_id"));
				newVo.setSalary(rs.getInt("salary"));
				newVo.setHireDate(rs.getString("hire_date"));
				newVo.setPhoneNumber(rs.getString("phone_number"));
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
		return newVo;
	}

	// 삭제 method
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

	// 전체리스트 method
	public List<EmployeeVO> getEmplist() {
		String sql = "select * from emp_temp order by 1";
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