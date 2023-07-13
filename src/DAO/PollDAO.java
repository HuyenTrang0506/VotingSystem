package DAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.sql.Timestamp;
import Models.Poll;

public class PollDAO implements DAOInterface<Poll>{


	@Override
	public ArrayList<Poll> selectAll() {
		ArrayList<Poll> ketQua = new ArrayList<Poll>();

		try {
			// Bước 1: tạo kết nối đến CSDL
			Connection con = JDBCUtil.getConnection();

			// Bước 2: tạo ra đối tượng statement
			String sql = "SELECT * FROM poll";
			PreparedStatement st = con.prepareStatement(sql);

			// Bước 3: thực thi câu lệnh SQL
			System.out.println(sql);
			ResultSet rs = st.executeQuery();

			// Bước 4:

			while (rs.next()) {
				int idd = rs.getInt("id");
				String title = rs.getString("title");
				String description = rs.getString("description");
				Timestamp start_tamp = rs.getTimestamp("start_time");
				Timestamp end_tamp = rs.getTimestamp("end_time");				
				Date start_time = new Date(start_tamp.getTime());
				Date end_time = new Date(end_tamp.getTime());
				int max_choices = rs.getInt("max_choices");
				int max_voters = rs.getInt("max_voters");
				Poll poll= new Poll(idd, title, description,start_time , end_time, max_choices,max_voters);
				
				ketQua.add(poll);

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
	public Poll selectById(int id) {
		Poll ketQua = null;
		try {
			// Bước 1: tạo kết nối đến CSDL
			Connection con = JDBCUtil.getConnection();

			// Bước 2: tạo ra đối tượng statement
			String sql = "SELECT * FROM poll WHERE id=?";
			PreparedStatement st = con.prepareStatement(sql);
			st.setInt(1, id);

			// Bước 3: thực thi câu lệnh SQL
			System.out.println(sql);
			ResultSet rs = st.executeQuery();

			// Bước 4:
			while (rs.next()) {
				int idd = rs.getInt("id");
				String title = rs.getString("title");
				String description = rs.getString("description");
				Timestamp start_tamp = rs.getTimestamp("start_time");
				Timestamp end_tamp = rs.getTimestamp("end_time");
				Date start_time = new Date(start_tamp.getTime());
				Date end_time = new Date(end_tamp.getTime());
				int max_choices = rs.getInt("max_choices");
				int max_voters = rs.getInt("max_voters");
			

				ketQua= new Poll(idd, title, description, start_time, end_time, max_choices,max_voters);
				


			}

			// Bước 5:
			JDBCUtil.closeConnection(con);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return ketQua;
	}public ArrayList<Poll> selectByUser_Id(int id) {
		ArrayList<Poll> ketQua = new ArrayList<Poll>();
		try {
			// Bước 1: tạo kết nối đến CSDL
			Connection con = JDBCUtil.getConnection();

			// Bước 2: tạo ra đối tượng statement
			String sql = "SELECT * FROM poll INNER JOIN voter ON poll.id=voter.poll_id WHERE voter.user_id=?";
			PreparedStatement st = con.prepareStatement(sql);
			st.setInt(1, id);

			// Bước 3: thực thi câu lệnh SQL
			System.out.println(sql);
			ResultSet rs = st.executeQuery();

			// Bước 4:
			while (rs.next()) {
				int idd = rs.getInt("id");
				String title = rs.getString("title");
				String description = rs.getString("description");
				Timestamp start_tamp = rs.getTimestamp("start_time");
				Timestamp end_tamp = rs.getTimestamp("end_time");
				Date start_time = new Date(start_tamp.getTime());
				Date end_time = new Date(end_tamp.getTime());
				int max_choices = rs.getInt("max_choices");
				int max_voters = rs.getInt("max_voters");
			

				Poll poll= new Poll(idd, title, description, start_time, end_time, max_choices,max_voters);
				ketQua.add(poll);


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
	public int insert(Poll t) {
		int ketQua = 0;
		try {
			// Bước 1: tạo kết nối đến CSDL
			Connection con = JDBCUtil.getConnection();

			// Bước 2: tạo ra đối tượng statement
			String sql = "INSERT INTO poll (id,title, description, start_time, end_time, max_choices,max_voters)" + " VALUES (?,?,?,?,?,?,?)";

			PreparedStatement st = con.prepareStatement(sql);
			st.setInt(1, t.getId());
			st.setString(2, t.getTitle());
			st.setString(3, t.getDescription());
			Timestamp timeStart = new Timestamp(t.getStartTime().getTime());
			Timestamp timeEnd = new Timestamp(t.getEndTime().getTime());
			st.setTimestamp(4, timeStart);
			st.setTimestamp(5, timeEnd);
			st.setInt(6, t.getMaxChoices());
			st.setInt(7, t.getMaxVotes());

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
	public int insertAll(ArrayList<Poll> arr) {
		int dem = 0;
		for (Poll poll : arr) {
			dem += this.insert(poll);
		}
		return dem;
	}

	@Override
	public int delete(Poll t) {
		int ketQua = 0;
		try {
			// Bước 1: tạo kết nối đến CSDL
			Connection con = JDBCUtil.getConnection();

			// Bước 2: tạo ra đối tượng statement
			String sql = "DELETE from poll " + " WHERE id=?";

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
	public int deleteAll(ArrayList<Poll> arr) {
		int dem = 0;
		for (Poll poll : arr) {
			dem += this.delete(poll);
		}
		return dem;
	}

	@Override
	public int update(Poll t) {
		int ketQua = 0;
		try {
			// Bước 1: tạo kết nối đến CSDL
			Connection con = JDBCUtil.getConnection();

			// Bước 2: tạo ra đối tượng statement
			String sql = "UPDATE poll SET title=?, description=?, start_time=?, end_time=?, max_choices=?, max_voters=?" + " WHERE id=?";

			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, t.getTitle());
			st.setString(2, t.getDescription());
			//format bên hàm lấy gtri
			Timestamp timeStart = new Timestamp(t.getStartTime().getTime());
			Timestamp timeEnd = new Timestamp(t.getEndTime().getTime());
			st.setTimestamp(3, timeStart);
			st.setTimestamp(4, timeEnd);
			st.setInt(5, t.getMaxChoices());
			st.setInt(6, t.getMaxVotes());
			st.setInt(7, t.getId());

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
			String sql = "DELETE from poll ";

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

