package ClientController;

import java.lang.reflect.Type;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import Config.ServerConfig;
import DAO.JDBCUtil;
import Models.User;
import Models.Voters;
import Tool.GetData;

public class VotersController {
	private String ip;
	private int port;

	public VotersController() {
		ip = ServerConfig.ip;
		port = ServerConfig.port;
	}
	public ArrayList<Voters> selectAll() {
		String toServer = "{\"function\":\"VotersDAO_selectAll\",'data':''}";
		ArrayList<Voters> voterList = new ArrayList<Voters>();
		String res = "";
		try {
			res = new GetData().Fetch(toServer, ip, port);
			Type type = new TypeToken<ArrayList<Voters>>() {
			}.getType();
			voterList = new Gson().fromJson(res, type);
		} catch (Exception exception) {
			exception.printStackTrace();
		}
		return voterList;
	}
	


	public int insert(Voters t) {
		Gson gson = new Gson();
		String voters = gson.toJson(t);
		String message = "{\"function\":\"UserDAO_insert\"," + "\"data\":{\"voters\":" + voters + "}}";
		String response = new GetData().Fetch(message, ip, port);
		return new Gson().fromJson(response, int.class);
	}

	

	public int delete(Voters t) {
		Gson gson = new Gson();
		String voters = gson.toJson(t);
		String message = "{\"function\":\"VotersDAO_delete\"," + "\"data\":{\"voters\":" + voters + "}}";
		String response = new GetData().Fetch(message, ip, port);
		return new Gson().fromJson(response, int.class);
		
		
	}


	public int deleteByPollID(int poll_id) {
		String message = "{\"function\":\"VotersDAO_deleteByPollID\"," + "\"data\":{\"poll_id\":" + poll_id +"}}";
		String response = new GetData().Fetch(message, ip, port);
		return new Gson().fromJson(response, int.class);
	}
	public int deleteByUserId(int user_id) {
		String message = "{\"function\":\"VotersDAO_deleteByUserId\"," + "\"data\":{\"user_id\":" + user_id +"}}";
		String response = new GetData().Fetch(message, ip, port);
		return new Gson().fromJson(response, int.class);
	}

	


}


