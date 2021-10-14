package com.douzone.mysite.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.douzone.mysite.vo.BoardVo;

public class BoardDao {

	public BoardVo findByTitleandContents(Long no) {
		
		BoardVo vo = null;

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = getConnection();

			String sql = " select no, title, contents, user_no"+
						 "   from board"+
						 "  where no=?";
			pstmt = conn.prepareStatement(sql);

			pstmt.setLong(1, no);

			rs = pstmt.executeQuery();

			if (rs.next()) {
				
				Long no1 = rs.getLong(1);
				String title = rs.getString(2);	
				String contents = rs.getString(3);
				Long userNu = rs.getLong(4);
				
				vo = new BoardVo();
				vo.setNo(no1);
				vo.setTitle(title);
				vo.setContent(contents);
				vo.setUserNu(userNu);

			}

		} catch (SQLException e) {
			System.out.println("error:" + e);
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
	public BoardVo findByNo(Long no) {
		BoardVo vo = null;

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = getConnection();

			String sql = "	  select a.no, a.title, a.contents, a.hit, a.reg_date, a.group_no, a.order_no, a.depth_no, b.no"
					   + "        from board a, user b"
					   + "	   where a.no = b.?" 
					   + "    order by a.group_no desc,"
					   + "			 a.order_no asc" 
					   + "	   limit 0, 10";
			pstmt = conn.prepareStatement(sql);

			pstmt.setLong(1, no);

			rs = pstmt.executeQuery();

			if (rs.next()) {

//				String name = rs.getString(1);
//				String email = rs.getString(2);
//				String password = rs.getString(3);
//				String gender = rs.getString(4);
//
//
//				vo = new UserVo();
//			
//				vo.setName(name);
//				vo.setEmail(email);
//				vo.setPassword(password);
//				vo.setGender(gender);

			}

		} catch (SQLException e) {
			System.out.println("error:" + e);
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

	public List<BoardVo> findAll() {
		List<BoardVo> list = new ArrayList<>();

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = getConnection();

			String sql = "	select b.no, b.title, u.name ,b.hit, b.reg_date" + 
						 "	 from board b, user u" +
						 "	where b.user_no = u.no" + 
						 " order by group_no desc, order_no asc";
			pstmt = conn.prepareStatement(sql);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				Long no = rs.getLong(1);
				String title = rs.getString(2);
				// String contents = rs.getString(3);
				String name = rs.getString(3);
				Long hit = rs.getLong(4);
				String regdate = rs.getString(5);
				// Long groupNo = rs.getLong(6);
				// Long orderNo = rs.getLong(7);
				// Long depthNo = rs.getLong(8);

				BoardVo vo = new BoardVo();
				vo.setNo(no);
				vo.setTitle(title);
				// vo.setContents(contents);
				vo.setHit(hit);
				vo.setRegDate(regdate);
				vo.setUserName(name);
//				vo.setGroupNu(groupNo);
//				vo.setOrderNu(orderNo);
//				vo.setDepthNu(depthNo);

				list.add(vo);
			}

		} catch (SQLException e) {
			System.out.println("error:" + e);
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

		return list;
	}

	public boolean delete(BoardVo vo) {
		boolean result = false;

		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = getConnection();

			String sql = " delete" + 
						 "   from guestbook" + 
						 "  where no=?" + 
						 "    and password=?";
			pstmt = conn.prepareStatement(sql);

			pstmt.setLong(1, vo.getNo());

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

	public boolean insert(BoardVo boardVo) {
		boolean result = false;

		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = getConnection();

			String sql = " insert into board values(null, ?, ?, default, now(),"+
						 " ifnull((select max(group_no) from board b), 0)+1, 0, 0, ?);";
			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, boardVo.getTitle());
			pstmt.setString(2, boardVo.getContent());
			pstmt.setLong(3, boardVo.getUserNu());

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

	public BoardVo findByPost(Long userNo, Long no) {
		BoardVo vo = null;

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = getConnection();

			String sql = " select title, contents"+
						 "   from board"+
						 "  where no=?"+
  						 "    and user_no=?";
			pstmt = conn.prepareStatement(sql);

			pstmt.setLong(1, no);
			pstmt.setLong(2, userNo);

			rs = pstmt.executeQuery();

			if (rs.next()) {
				
				String title = rs.getString(1);	
				String contents = rs.getString(2);
				
				vo = new BoardVo();
				vo.setTitle(title);
				vo.setContent(contents);
				vo.setNo(no);

			}

		} catch (SQLException e) {
			System.out.println("error:" + e);
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
	
	public boolean update(BoardVo boardVo) {
		boolean result = false;
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = null;
		try {
			conn = getConnection();

			// 3. statement 생성
			sql = " update board "
				+ "   set title =?, contents =?"
				+ " where no =?"
				+ "   and user_no =?";
			pstmt = conn.prepareStatement(sql);
			// 4. 바인딩
			pstmt.setString(1, boardVo.getTitle());
			pstmt.setString(2, boardVo.getContent());
			pstmt.setLong(3, boardVo.getNo());
			pstmt.setLong(4, boardVo.getUserNu());
			
			// 5. SQL 실행
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
	private Connection getConnection() throws SQLException {
		Connection conn = null;
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			String url = "jdbc:mysql://127.0.0.1:3306/webdb?characterEncoding=utf8";
			conn = DriverManager.getConnection(url, "webdb", "webdb");
		} catch (ClassNotFoundException e) {
			System.out.println("드라이버 로딩 실패:" + e);
		}

		return conn;
	}

}
