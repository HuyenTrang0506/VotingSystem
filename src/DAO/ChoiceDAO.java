package DAO;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Models.Choice;

public class ChoiceDAO implements DAOInterface<Choice> {

	@Override
	public ArrayList<Choice> selectAll() {
		ArrayList<Choice> ketQua = new ArrayList<Choice>();

		try {
			// Bước 1: tạo kết nối đến CSDL
			Connection con = JDBCUtil.getConnection();

			// Bước 2: tạo ra đối tượng statement
			String sql = "SELECT * FROM choice";
			PreparedStatement st = con.prepareStatement(sql);

			// Bước 3: thực thi câu lệnh SQL
			System.out.println(sql);
			ResultSet rs = st.executeQuery();

			// Bước 4:

			while (rs.next()) {
				int idd = rs.getInt("id");
				int poll_id = rs.getInt("poll_id");
				String content = rs.getString("content");

				Choice choice = new Choice(idd, poll_id, content);

				ketQua.add(choice);

			}

			// Bước 5:
			JDBCUtil.closeConnection(con);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return ketQua;
	}

	@Override
	public Choice selectById(int id) {
		Choice ketQua = null;
		try {
			// Bước 1: tạo kết nối đến CSDL
			Connection con = JDBCUtil.getConnection();

			// Bước 2: tạo ra đối tượng statement
			String sql = "SELECT * FROM choice WHERE id=?";
			PreparedStatement st = con.prepareStatement(sql);
			st.setInt(1, id);

			// Bước 3: thực thi câu lệnh SQL
			System.out.println(sql);
			ResultSet rs = st.executeQuery();

			// Bước 4:
			while (rs.next()) {
				int idd = rs.getInt("id");
				int poll_id = rs.getInt("poll_id");
				String content = rs.getString("content");

				ketQua = new Choice(idd, poll_id, content);

			}

			// Bước 5:
			JDBCUtil.closeConnection(con);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return ketQua;
	}
	public Choice selectByPollIdAndContent(int poll_id,String content) {
		Choice ketQua = null;
		try {
			// Bước 1: tạo kết nối đến CSDL
			Connection con = JDBCUtil.getConnection();

			// Bước 2: tạo ra đối tượng statement
			String sql = "SELECT * FROM choice WHERE poll_id=? AND content=?";
			PreparedStatement st = con.prepareStatement(sql);
			st.setInt(1, poll_id);
			st.setString(2, content);

			// Bước 3: thực thi câu lệnh SQL
			System.out.println(sql);
			ResultSet rs = st.executeQuery();

			// Bước 4:
			while (rs.next()) {
				int idd = rs.getInt("id");
				int poll_idd = rs.getInt("poll_id");
				String contentt = rs.getString("content");

				ketQua = new Choice(idd, poll_idd, contentt);

			}

			// Bước 5:
			JDBCUtil.closeConnection(con);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return ketQua;
	}
	public ArrayList<Choice> selectByPollID(int id) {
		ArrayList<Choice> ketQua = new ArrayList<Choice>();
		try {
			// Bước 1: tạo kết nối đến CSDL
			Connection con = JDBCUtil.getConnection();

			// Bước 2: tạo ra đối tượng statement
			String sql = "SELECT * FROM choice WHERE poll_id=?";
			PreparedStatement st = con.prepareStatement(sql);
			st.setInt(1, id);

			// Bước 3: thực thi câu lệnh SQL
			System.out.println(sql);
			ResultSet rs = st.executeQuery();

			// Bước 4:
			while (rs.next()) {
				int idd = rs.getInt("id");
				int poll_id = rs.getInt("poll_id");
				String content = rs.getString("content");
				Choice choice = new Choice(idd, poll_id, content);
				ketQua.add(choice);

			}

			// Bước 5:
			JDBCUtil.closeConnection(con);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return ketQua;
	}
	

	@Override
	public int insert(Choice t) {
		int ketQua = 0;
		try {
			// Bước 1: tạo kết nối đến CSDL
			Connection con = JDBCUtil.getConnection();

			// Bước 2: tạo ra đối tượng statement
			String sql = "INSERT INTO choice (poll_id, content)" + " VALUES (?,?)";

			PreparedStatement st = con.prepareStatement(sql);

			st.setInt(1, t.getPoll_id());
			st.setString(2, t.getContent());

			// Bước 3: thực thi câu lệnh SQL
			ketQua = st.executeUpdate();

			// Bước 4:
			System.out.println("Bạn đã thực thi: " + sql);
			System.out.println("Có " + ketQua + " dòng bị thay đổi!");

			// Bước 5:
			JDBCUtil.closeConnection(con);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return ketQua;
	}

	@Override
	public int insertAll(ArrayList<Choice> arr) {
		int dem = 0;
		for (Choice choice : arr) {
			dem += this.insert(choice);
		}
		return dem;
	}@Override
	public int delete(Choice t) {
		int ketQua = 0;
		try {
			// Bước 1: tạo kết nối đến CSDL
			Connection con = JDBCUtil.getConnection();

			// Bước 2: tạo ra đối tượng statement
			String sql = "DELETE from choice " + " WHERE poll_id=? AND content=? ";

			PreparedStatement st = con.prepareStatement(sql);
			st.setInt(1, t.getPoll_id());
			st.setString(2, t.getContent());
			

			// Bước 3: thực thi câu lệnh SQL
			System.out.println(sql);
			ketQua = st.executeUpdate();

			// Bước 4:
			System.out.println("Bạn đã thực thi: " + sql);

			System.out.println("Có " + ketQua + " dòng bị thay đổi!");

			// Bước 5:
			JDBCUtil.closeConnection(con);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return ketQua;
	}
	public int deleteByPollId(int poll_id) {
		int ketQua = 0;
		try {
			// Bước 1: tạo kết nối đến CSDL
			Connection con = JDBCUtil.getConnection();

			// Bước 2: tạo ra đối tượng statement
			String sql = "DELETE from choice " + " WHERE poll_id=?";

			PreparedStatement st = con.prepareStatement(sql);
			st.setInt(1,poll_id);
			
			

			// Bước 3: thực thi câu lệnh SQL
			System.out.println(sql);
			ketQua = st.executeUpdate();

			// Bước 4:
			System.out.println("Bạn đã thực thi: " + sql);

			System.out.println("Có " + ketQua + " dòng bị thay đổi!");

			// Bước 5:
			JDBCUtil.closeConnection(con);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return ketQua;
	}

//	
	@Override
	public int deleteAll(ArrayList<Choice> arr) {
		int dem = 0;
		for (Choice choice : arr) {
			dem += this.delete(choice);
		}
		return dem;
	}

	@Override
	public int update(Choice t) {
		int ketQua = 0;
		try {
			// Bước 1: tạo kết nối đến CSDL
			Connection con = JDBCUtil.getConnection();

			// Bước 2: tạo ra đối tượng statement
			String sql = "UPDATE choice SET poll_id=?, content=?" + " WHERE id=?";

			PreparedStatement st = con.prepareStatement(sql);

			st.setInt(1, t.getPoll_id());
			st.setString(2, t.getContent());

			st.setInt(3, t.getId());

			// Bước 3: thực thi câu lệnh SQL

			System.out.println(sql);
			ketQua = st.executeUpdate();

			JDBCUtil.closeConnection(con);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return ketQua;
	}

	@Override
	public int deleteTable() {
		int ketQua = 0;
		try {
			// Bước 1: tạo kết nối đến CSDL
			Connection con = JDBCUtil.getConnection();

			// Bước 2: tạo ra đối tượng statement
			String sql = "DELETE from choice ";

			PreparedStatement st = con.prepareStatement(sql);
			
			
			

			// Bước 3: thực thi câu lệnh SQL
			System.out.println(sql);
			ketQua = st.executeUpdate();

			// Bước 4:
			System.out.println("Bạn đã thực thi: " + sql);

			System.out.println("Có " + ketQua + " dòng bị thay đổi!");

			// Bước 5:
			JDBCUtil.closeConnection(con);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return ketQua;
	}

}
