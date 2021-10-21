package com.douzone.mysite.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.sql.DataSource;
import org.springframework.stereotype.Repository;
import com.douzone.mysite.exception.UserRepositoryException;
import com.douzone.mysite.vo.UserVo;

@Repository
public class UserRepository {
	
	private DataSource dataSource;
	
	public boolean update(UserVo vo) {
		boolean result = false;
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = null;
		try {
			conn = dataSource.getConnection();

			// 3. statement 생성
			sql = " update user" + "    set name =?, password =?, gender =?" + "  where no =?";
			pstmt = conn.prepareStatement(sql);
			// 4. 바인딩
			pstmt.setString(1, vo.getName());
			pstmt.setString(2, vo.getPassword());
			pstmt.setString(3, vo.getGender());
			pstmt.setLong(4, vo.getNo());
			// 4. SQL 실행
			int count = pstmt.executeUpdate();

			result = count == 1;

		} catch (SQLException e) {
			System.out.println("error:" + e);
		} finally {
			try {
				if (pstmt != null) {
					pstmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return result;
	}

	public UserVo findByNo(Long no) throws UserRepositoryException {
		UserVo vo = null;

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = dataSource.getConnection();

			String sql = " select name, email, password, gender " + "   from user " + "  where no=?";
			pstmt = conn.prepareStatement(sql);

			pstmt.setLong(1, no);

			rs = pstmt.executeQuery();

			if (rs.next()) {

				String name = rs.getString(1);
				String email = rs.getString(2);
				String password = rs.getString(3);
				String gender = rs.getString(4);

				vo = new UserVo();

				vo.setName(name);
				vo.setEmail(email);
				vo.setPassword(password);
				vo.setGender(gender);

			}

		} catch (SQLException e) {
			// 서비스한테 예외를 던지는데
			// 1. 전환 : 상위단에서 이해할수 있는 예외로 부모를 런타임 익셉션으로 해서 알아먹게 해야한다
			throw new UserRepositoryException(e.toString());
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (pstmt != null) {
					pstmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return vo;
	}

	public UserVo findByEmailAndPassword(
					String email, 
					String password) 
					throws UserRepositoryException{
		UserVo vo = null;

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = dataSource.getConnection();

			String sql = " select no, name " + "   from user " + "  where email=?" + "    and password=?";
			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, email);
			pstmt.setString(2, password);

			rs = pstmt.executeQuery();

			if (rs.next()) {
				Long no = rs.getLong(1);
				String name = rs.getString(2);

				vo = new UserVo();
				vo.setNo(no);
				vo.setName(name);
			}

		} catch (SQLException e) {
			throw new UserRepositoryException(e.toString());
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (pstmt != null) {
					pstmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return vo;
	}

	public boolean insert(UserVo vo) {
		boolean result = false;

		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = dataSource.getConnection();

			String sql = " insert " + " into user " + " values(null, ?, ?, ?, ?, now())";
			pstmt = conn.prepareStatement(sql);
			System.out.println(sql);

			pstmt.setString(1, vo.getName());
			pstmt.setString(2, vo.getEmail());
			pstmt.setString(3, vo.getPassword());
			pstmt.setString(4, vo.getGender());

			int count = pstmt.executeUpdate();
			result = count == 1;
		} catch (SQLException e) {
			System.out.println("error:" + e);
		} finally {
			try {
				if (pstmt != null) {
					pstmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return result;
	}

}