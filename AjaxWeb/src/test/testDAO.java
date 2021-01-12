package test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class testDAO {
	Connection conn = null;

	// 생성자
	public testDAO() {
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
	}

	// update delete method
	// 등록(insert)method
	// 전체 리스트 메소드
	public List<testVO> getAllList() {
		String sql = "select * from testpr order by 1 desc";
		List<testVO> list = new ArrayList<testVO>();
		try {
			PreparedStatement ppst = conn.prepareStatement(sql);
			ResultSet rs = ppst.executeQuery();// 결과 rs에 넣음(1)
			while (rs.next()) {
				testVO vo = new testVO();
				vo.setNumber(rs.getInt("id"));
				vo.setfName(rs.getString("성"));
				vo.setlName(rs.getString("이름"));
				vo.setJobId(rs.getString("직업"));
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
	}// end of List

}// end of class
