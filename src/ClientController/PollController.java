package ClientController;

import java.lang.reflect.Type;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import Config.ServerConfig;
import DAO.JDBCUtil;
import DAO.PollDAO;
import Models.Poll;
import Models.User;
import Tool.GetData;

public class PollController {
	private String ip;
	private int port;

	public PollController() {
		ip = ServerConfig.ip;
		port = ServerConfig.port;
	}

	public ArrayList<Poll> selectAll() {
		String toServer = "{\"function\":\"PollDAO_selectAll\",'data':''}";
		ArrayList<Poll> pollList = new ArrayList<Poll>();
		String res = "";
		try {
			res = new GetData().Fetch(toServer, ip, port);
			Type type = new TypeToken<ArrayList<Poll>>() {
			}.getType();
			pollList = new Gson().fromJson(res, type);
		} catch (Exception exception) {
			exception.printStackTrace();
		}
		return pollList;
	}

	public Poll selectById(int id) {
		String message = "{\"function\":\"PollDAO_selectById\"," + "\"data\":{\"id\":" + id + "}}";
		String response = new GetData().Fetch(message, ip, port);
		return new Gson().fromJson(response, Poll.class);
	}

	public ArrayList<Poll> selectByUser_Id(int id) {
		String message = "{\"function\":\"PollDAO_selectByUserId\"," + "\"data\":{\"id\":" + id + "}}";
		ArrayList<Poll> pollList = new ArrayList<Poll>();
		String res = "";
		try {
			res = new GetData().Fetch(message, ip, port);
			Type type = new TypeToken<ArrayList<Poll>>() {
			}.getType();
			pollList = new Gson().fromJson(res, type);
		} catch (Exception exception) {
			exception.printStackTrace();
		}
		return pollList;
	}

	public int insert(Poll t) {
		Gson gson = new Gson();
		String poll = gson.toJson(t);
		String message = "{\"function\":\"PollDAO_insert\"," + "\"data\":{\"poll\":" + poll + "}}";
		String response = new GetData().Fetch(message, ip, port);
		return new Gson().fromJson(response, int.class);
	}

	public int delete(Poll t) {
		Gson gson = new Gson();
		String poll = gson.toJson(t);
		String message = "{\"function\":\"PollDAO_delete\"," + "\"data\":{\"poll\":" + poll + "}}";
		String response = new GetData().Fetch(message, ip, port);
		return new Gson().fromJson(response, int.class);
	}

	public int update(Poll t) {
		Gson gson = new Gson();
		String poll = gson.toJson(t);
		String message = "{\"function\":\"PollDAO_update\"," + "\"data\":{\"poll\":" + poll + "}}";
		String response = new GetData().Fetch(message, ip, port);
		return new Gson().fromJson(response, int.class);
	}

}
