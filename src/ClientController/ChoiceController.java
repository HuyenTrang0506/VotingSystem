package ClientController;

import java.lang.reflect.Type;
import java.util.ArrayList;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import Config.ServerConfig;
import Models.Choice;
import Models.User;
import Models.Vote;
import Tool.GetData;

public class ChoiceController {
	private String ip;
	private int port;

	public ChoiceController() {
		ip = ServerConfig.ip;
		port = ServerConfig.port;
	}

	public ArrayList<Choice> selectAll() {
		
		String toServer = "{\"function\":\"ChoiceDAO_selectAll\",'data':''}";
		ArrayList<Choice> userList = new ArrayList<Choice>();
		String res = "";
		try {
			res = new GetData().Fetch(toServer, ip, port);
			Type type = new TypeToken<ArrayList<Choice>>() {
			}.getType();
			userList = new Gson().fromJson(res, type);
		} catch (Exception exception) {
			exception.printStackTrace();
		}
		return userList;
		
	}

	public Choice selectById(int id) {
		String message = "{\"function\":\"ChoiceDAO_selectById\"," + "\"data\":{\"id\":" + id + "}}";
		String response = new GetData().Fetch(message, ip, port);
		return new Gson().fromJson(response, Choice.class);
	}
	public Choice selectByPollIdAndContent(int poll_id,String content) {

		String message = "{\"function\":\"ChoiceDAO_selectByPollIdAndContent\"," + "\"data\":{\"poll_id\":" + poll_id + ",\"content\":\"" + content + "\"}}";
		String response = new GetData().Fetch(message, ip, port);
		return new Gson().fromJson(response, Choice.class);
	}
	public ArrayList<Choice> selectByPollID(int id) {
		String message = "{\"function\":\"ChoiceDAO_selectByPollId\"," + "\"data\":{\"poll_id\":" + id + "}}";
			
		ArrayList<Choice> choiceList = new ArrayList<Choice>();
		String res = "";
		try {
			res = new GetData().Fetch(message, ip, port);
			Type type = new TypeToken<ArrayList<Choice>>() {
			}.getType();
			choiceList = new Gson().fromJson(res, type);
		} catch (Exception exception) {
			exception.printStackTrace();
		}
		return choiceList;
	}
	

	public int insert(Choice t) {
		Gson gson = new Gson();
		String choice = gson.toJson(t);
		String message = "{\"function\":\"ChoiceDAO_insert\"," + "\"data\":{\"choice\":" + choice + "}}";
		String response = new GetData().Fetch(message, ip, port);
		return new Gson().fromJson(response, int.class);
	}

	public int delete(Choice t) {
		Gson gson = new Gson();
		String choice = gson.toJson(t);
		String message = "{\"function\":\"ChoiceDAO_delete\"," + "\"data\":{\"choice\":" + choice + "}}";
		String response = new GetData().Fetch(message, ip, port);
		return new Gson().fromJson(response, int.class);
	}
	public int deleteByPollId(int poll_id) {
		String message = "{\"function\":\"ChoiceDAO_deleteByPollId\"," + "\"data\":{\"poll_id\":" + poll_id +"}}";
		String response = new GetData().Fetch(message, ip, port);
		return new Gson().fromJson(response, int.class);
	}

	public int update(Choice t) {
		Gson gson = new Gson();
		String choice = gson.toJson(t);
		String message = "{\"function\":\"ChoiceDAO_update\"," + "\"data\":{\"choice\":" + choice + "}}";
		String response = new GetData().Fetch(message, ip, port);
		return new Gson().fromJson(response, int.class);
	}


}