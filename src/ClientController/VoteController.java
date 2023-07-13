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
import DAO.VoteDAO;
import Models.Choice;
import Models.Vote;
import Models.Voters;
import Tool.GetData;

public class VoteController {
	private String ip;
	private int port;

	public VoteController() {
		ip = ServerConfig.ip;
		port = ServerConfig.port;
	}
	public ArrayList<Vote> selectAll() {
		String toServer = "{\"function\":\"VoteDAO_selectAll\",'data':''}";
		ArrayList<Vote> voteList = new ArrayList<Vote>();
		String res = "";
		try {
			res = new GetData().Fetch(toServer, ip, port);
			Type type = new TypeToken<ArrayList<Vote>>() {
			}.getType();
			voteList = new Gson().fromJson(res, type);
		} catch (Exception exception) {
			exception.printStackTrace();
		}
		return voteList;
	}
	public ArrayList<Vote> selectByPollId(int poll_idd) {
		String message = "{\"function\":\"VoteDAO_selectByPollId\"," + "\"data\":{\"poll_id\":" + poll_idd + "}}";
		ArrayList<Vote> voteList = new ArrayList<Vote>();
		String res = "";
		try {
			res = new GetData().Fetch(message, ip, port);
			Type type = new TypeToken<ArrayList<Vote>>() {
			}.getType();
			voteList = new Gson().fromJson(res, type);
		} catch (Exception exception) {
			exception.printStackTrace();
		}
		return voteList;
	}

	//số lá phiếu cho 1 lựa chọn cho 1 cuộc bỏ phiếu
	public ArrayList<Vote> selectByPollIdAndChoiceId(int poll_idd,int choice_idd ) {
		String message = "{\"function\":\"VoteDAO_selectByPollIdAndChoiceId\"," + "\"data\":{\"poll_id\":" + poll_idd + ",\"choice_id\":" + choice_idd + "}}";
		ArrayList<Vote> voteList = new ArrayList<Vote>();
		String res = "";
		try {
			res = new GetData().Fetch(message, ip, port);
			Type type = new TypeToken<ArrayList<Vote>>() {
			}.getType();
			voteList = new Gson().fromJson(res, type);
		} catch (Exception exception) {
			exception.printStackTrace();
		}
		return voteList;
	}

	public Vote selectById(int id) {
		String message = "{\"function\":\"VoteDAO_selectById\"," + "\"data\":{\"id\":" + id + "}}";
		String response = new GetData().Fetch(message, ip, port);
		return new Gson().fromJson(response, Vote.class);
	}

	public int insert(Vote t) {
		Gson gson = new Gson();
		String vote = gson.toJson(t);
		String message = "{\"function\":\"VoteDAO_insert\"," + "\"data\":{\"vote\":" + vote + "}}";
		String response = new GetData().Fetch(message, ip, port);
		return new Gson().fromJson(response, int.class);
	}

	
	public int delete(Vote t) {
		Gson gson = new Gson();
		String vote = gson.toJson(t);
		String message = "{\"function\":\"VoteDAO_delete\"," + "\"data\":{\"vote\":" + vote + "}}";
		String response = new GetData().Fetch(message, ip, port);
		return new Gson().fromJson(response, int.class);
	}



}

