package ClientController;

import java.lang.reflect.Type;

import java.util.ArrayList;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import Config.ServerConfig;
import Models.User;
import Tool.GetData;

public class UserController {
	private String ip;
	private int port;

	public UserController() {
		ip = ServerConfig.ip;
		port = ServerConfig.port;
	}

	public ArrayList<User> selectAll() {
		String toServer = "{\"function\":\"UserDAO_selectAll\",'data':''}";
		ArrayList<User> userList = new ArrayList<User>();
		String res = "";
		try {
			res = new GetData().Fetch(toServer, ip, port);
			Type type = new TypeToken<ArrayList<User>>() {
			}.getType();
			userList = new Gson().fromJson(res, type);
		} catch (Exception exception) {
			exception.printStackTrace();
		}
		return userList;

	}

	public User selectByNameAndPass(String username, String password) {
		String message = "{\"function\":\"UserDAO_selectByNameAndPass\"," + "\"data\":{\"username\":\"" + username
				+ "\",\"password\":\"" + password + "\"}}";
		String response = new GetData().Fetch(message, ip, port);
		return new Gson().fromJson(response, User.class);
	}

	public User selectById(int id) {
		String message = "{\"function\":\"UserDAO_selectById\"," + "\"data\":{\"id\":" + id + "}}";
		String response = new GetData().Fetch(message, ip, port);
		return new Gson().fromJson(response, User.class);

	}

	public User selectByEmail(String emaill) {
		String message = "{\"function\":\"UserDAO_selectByEmail\"," + "\"data\":{\"email\":\"" + emaill + "\"}}";
		String response = new GetData().Fetch(message, ip, port);
		return new Gson().fromJson(response, User.class);
	}

	public ArrayList<User> selectVoterByPollId(int id) {

		String message = "{\"function\":\"UserDAO_selectVoterByPollId\"," + "\"data\":{\"id\":" + id + "}}";
		ArrayList<User> userList = new ArrayList<User>();
		String res = "";
		try {
			res = new GetData().Fetch(message, ip, port);
			Type type = new TypeToken<ArrayList<User>>() {
			}.getType();
			userList = new Gson().fromJson(res, type);
		} catch (Exception exception) {
			exception.printStackTrace();
		}
		return userList;
	}

	public int insert(User t) {
		Gson gson = new Gson();
		String user = gson.toJson(t);
		String message = "{\"function\":\"UserDAO_insert\"," + "\"data\":{\"user\":" + user + "}}";
		String response = new GetData().Fetch(message, ip, port);
		return new Gson().fromJson(response, int.class);
	}

	public int delete(User t) {
		Gson gson = new Gson();
		String user = gson.toJson(t);
		String message = "{\"function\":\"UserDAO_delete\"," + "\"data\":{\"user\":" + user + "}}";
		String response = new GetData().Fetch(message, ip, port);
		return new Gson().fromJson(response, int.class);
	}

	public int update(User t) {

		Gson gson = new Gson();
		String user = gson.toJson(t);
		String message = "{\"function\":\"UserDAO_update\"," + "\"data\":{\"user\":" + user + "}}";
		String response = new GetData().Fetch(message, ip, port);
		return new Gson().fromJson(response, int.class);
	}

}
