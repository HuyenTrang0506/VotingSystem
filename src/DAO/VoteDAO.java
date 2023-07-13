package DAO;
import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import Models.Vote;
public class VoteDAO implements DAOInterface<Vote>{
	@Override
	public ArrayList<Vote> selectAll() {
		ArrayList<Vote> ketQua = new ArrayList<Vote>();

		try {
			// Bước 1: tạo kết nối đến CSDL
			Connection con = JDBCUtil.getConnection();

			// Bước 2: tạo ra đối tượng statement
			String sql = "SELECT * FROM vote";
			PreparedStatement st = con.prepareStatement(sql);

			// Bước 3: thực thi câu lệnh SQL
			System.out.println(sql);
			ResultSet rs = st.executeQuery();

			// Bước 4:

			while (rs.next()) {
				int idd = rs.getInt("id");
				int poll_id = rs.getInt("poll_id");
				int user_id = rs.getInt("user_id");
				int  choice_id = rs.getInt("choice_id");
				Timestamp create_tamp = rs.getTimestamp("create_at");
				Date created_at = new Date(create_tamp.getTime());
			

				Vote vote= new Vote(idd, poll_id, user_id, choice_id, created_at);
				ketQua.add(vote);

			}

			// Bước 5:
			JDBCUtil.closeConnection(con);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return ketQua;
	}
	public ArrayList<Vote> selectByPollId(int poll_idd) {
		ArrayList<Vote> ketQua = new ArrayList<Vote>();

		try {
			// Bước 1: tạo kết nối đến CSDL
			Connection con = JDBCUtil.getConnection();

			// Bước 2: tạo ra đối tượng statement
			String sql = "SELECT * FROM vote WHERE poll_id=?";
			PreparedStatement st = con.prepareStatement(sql);
			st.setInt(1, poll_idd);
			// Bước 3: thực thi câu lệnh SQL
			System.out.println(sql);
			ResultSet rs = st.executeQuery();

			// Bước 4:

			while (rs.next()) {
				int idd = rs.getInt("id");
				int poll_id = rs.getInt("poll_id");
				int user_id = rs.getInt("user_id");
				int  choice_id = rs.getInt("choice_id");
				Timestamp create_tamp = rs.getTimestamp("create_at");
				Date created_at = new Date(create_tamp.getTime());			
				Vote vote= new Vote(idd, poll_id, user_id, choice_id, created_at);
				ketQua.add(vote);

			}

			// Bước 5:
			JDBCUtil.closeConnection(con);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return ketQua;
	}

	//số lá phiếu cho 1 lựa chọn cho 1 cuộc bỏ phiếu
	public ArrayList<Vote> selectByPollIdAndChoiceId(int poll_idd,int choice_idd ) {
		ArrayList<Vote> ketQua = new ArrayList<Vote>();

		try {
			// Bước 1: tạo kết nối đến CSDL
			Connection con = JDBCUtil.getConnection();

			// Bước 2: tạo ra đối tượng statement
			String sql = "SELECT * FROM vote WHERE poll_id=? AND choice_id=?";
			PreparedStatement st = con.prepareStatement(sql);
			st.setInt(1, poll_idd);
			st.setInt(2, choice_idd);
			// Bước 3: thực thi câu lệnh SQL
			System.out.println(sql);
			ResultSet rs = st.executeQuery();

			// Bước 4:

			while (rs.next()) {
				int idd = rs.getInt("id");
				int poll_id = rs.getInt("poll_id");
				int user_id = rs.getInt("user_id");
				int  choice_id = rs.getInt("choice_id");
				Timestamp create_tamp = rs.getTimestamp("create_at");
				Date created_at = new Date(create_tamp.getTime());			
				Vote vote= new Vote(idd, poll_id, user_id, choice_id, created_at);
				ketQua.add(vote);

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
	public Vote selectById(int id) {
		Vote ketQua = null;
		try {
			// Bước 1: tạo kết nối đến CSDL
			Connection con = JDBCUtil.getConnection();

			// Bước 2: tạo ra đối tượng statement
			String sql = "SELECT * FROM vote WHERE id=?";
			PreparedStatement st = con.prepareStatement(sql);
			st.setInt(1, id);

			// Bước 3: thực thi câu lệnh SQL
			System.out.println(sql);
			ResultSet rs = st.executeQuery();

			// Bước 4:
			while (rs.next()) {
				int idd = rs.getInt("id");
				int poll_id = rs.getInt("poll_id");
				int user_id = rs.getInt("user_id");
				int  choice_id = rs.getInt("choice_id");
				Timestamp create_tamp = rs.getTimestamp("create_at");
				Date created_at = new Date(create_tamp.getTime());

				ketQua= new Vote(idd, poll_id, user_id, choice_id, created_at);
				


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
	public int insert(Vote t) {
		int ketQua = 0;
		try {
			// Bước 1: tạo kết nối đến CSDL
			Connection con = JDBCUtil.getConnection();

			// Bước 2: tạo ra đối tượng statement
			String sql = "INSERT INTO vote (poll_id, user_id, choice_id, create_at)" + " VALUES (?,?,?,?)";

			PreparedStatement st = con.prepareStatement(sql);

			st.setInt(1, t.getPoll_id());
			st.setInt(2, t.getUser_id());
			st.setInt(3, t.getChoice_id());
			Timestamp create_at = new Timestamp(t.getCreated_at().getTime());
			st.setTimestamp(4, create_at);
			

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
	public int insertAll(ArrayList<Vote> arr) {
		int dem = 0;
		for (Vote vote : arr) {
			dem += this.insert(vote);
		}
		return dem;
	}

	@Override
	public int delete(Vote t) {
		int ketQua = 0;
		try {
			// Bước 1: tạo kết nối đến CSDL
			Connection con = JDBCUtil.getConnection();

			// Bước 2: tạo ra đối tượng statement
			String sql = "DELETE from vote" + " WHERE id=?";

			PreparedStatement st = con.prepareStatement(sql);
			st.setInt(1, t.getId());

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

	@Override
	public int deleteAll(ArrayList<Vote> arr) {
		int dem = 0;
		for (Vote vote : arr) {
			dem += this.delete(vote);
		}
		return dem;
	}

	@Override
	public int update(Vote t) {
		//phiếu bầu không thể bị chỉnh sửa
		return 0;
	}
	@Override
	public int deleteTable() {
		int ketQua = 0;
		try {
			// Bước 1: tạo kết nối đến CSDL
			Connection con = JDBCUtil.getConnection();

			// Bước 2: tạo ra đối tượng statement
			String sql = "DELETE from vote ";

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

