package Server;

import com.google.gson.*;

import DAO.ChoiceDAO;
import DAO.PollDAO;
import DAO.UserDAO;
import DAO.VoteDAO;
import DAO.VotersDAO;
import Models.Choice;
import Models.Poll;
import Models.User;
import Models.Voters;
import Models.Vote;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class UseFunction {
	public String StringFromJSON(String jsonStr) {
		String res = "";
		for (int i = 1; i < jsonStr.length() - 1; i++) {
			res += jsonStr.charAt(i);
		}
		return res;
	}

	public String Mapping(String json) {
		String res = "";
		try {
			Gson gson = new Gson();
			JsonObject jsonObject = gson.fromJson(json, JsonObject.class);
			System.out.println(json.toString());
			String function = jsonObject.get("function").toString();
			// System.out.println(function);
			switch (StringFromJSON(function)) {
			//user
			case "UserDAO_selectAll": {
				res = new Gson().toJson(new UserDAO().selectAll());
				break;
			}
			case "UserDAO_selectByNameAndPass": {
				JsonObject data = jsonObject.getAsJsonObject("data");
				String user = StringFromJSON(data.get("username").toString());
				String pwd = StringFromJSON(data.get("password").toString());
				User userres = new UserDAO().selectByNameAndPass(user, pwd);
				res = new Gson().toJson(userres);
				break;
			}
			case "UserDAO_selectByEmail": {
				JsonObject data = jsonObject.getAsJsonObject("data");
				String mail = StringFromJSON(data.get("email").toString());
				User mailres = new UserDAO().selectByEmail(mail);
				res = new Gson().toJson(mailres);
				break;

			}
			case "UserDAO_selectById": {
				JsonObject data = jsonObject.getAsJsonObject("data");
				String id = data.get("id").toString();
				User idres = new UserDAO().selectById(Integer.parseInt(id));
				res = new Gson().toJson(idres);
				break;

			}
			case "UserDAO_selectVoterByPollId":{
				JsonObject data = jsonObject.getAsJsonObject("data");
				int id=new Gson().fromJson(data.get("id").toString(),int.class);
				res = new Gson().toJson(new UserDAO().selectVoterByPollId(id));
				break;
			}
			case "UserDAO_insert": {
				JsonObject data = jsonObject.getAsJsonObject("data");
				String userJson=data.get("user").toString();
				User user=new Gson().fromJson(userJson, User.class);
				res=""+new UserDAO().insert(user);
				break;

			}case "UserDAO_delete":{
				JsonObject data = jsonObject.getAsJsonObject("data");
				String userJson=data.get("user").toString();
				User user=new Gson().fromJson(userJson, User.class);
				res=""+new UserDAO().delete(user);
				break;
				
			}case "UserDAO_update":{
				JsonObject data = jsonObject.getAsJsonObject("data");
				String userJson=data.get("user").toString();
				User user=new Gson().fromJson(userJson, User.class);
				res=""+new UserDAO().update(user);
				break;
				
				
			}//poll
			case "PollDAO_selectAll":{
				res = new Gson().toJson(new PollDAO().selectAll());
				break;
			}
			case "PollDAO_selectById":{
				JsonObject data = jsonObject.getAsJsonObject("data");
				String id = data.get("id").toString();
				Poll idres = new PollDAO().selectById(Integer.parseInt(id));
				res = new Gson().toJson(idres);
				break;
			}case "PollDAO_selectByUserId":{
				JsonObject data = jsonObject.getAsJsonObject("data");
				int id=new Gson().fromJson(data.get("id").toString(),int.class);
				res = new Gson().toJson(new PollDAO().selectByUser_Id(id));
				break;
			}case "PollDAO_insert":{
				JsonObject data = jsonObject.getAsJsonObject("data");
				String pollJson=data.get("poll").toString();
				Poll poll=new Gson().fromJson(pollJson, Poll.class);
				res=""+new PollDAO().insert(poll);
				break;
			}
			case "PollDAO_delete":{
				JsonObject data = jsonObject.getAsJsonObject("data");
				String pollJson=data.get("poll").toString();
				Poll poll=new Gson().fromJson(pollJson, Poll.class);
				res=""+new PollDAO().delete(poll);
				break;
			}
			case "PollDAO_update":{
				JsonObject data = jsonObject.getAsJsonObject("data");
				String pollJson=data.get("poll").toString();
				Poll poll=new Gson().fromJson(pollJson, Poll.class);
				res=""+new PollDAO().update(poll);
				break;
			}//choice
			case "ChoiceDAO_selectAll":{
				res = new Gson().toJson(new ChoiceDAO().selectAll());
				break;
			}case "ChoiceDAO_selectById":{
				JsonObject data = jsonObject.getAsJsonObject("data");
				String id = data.get("id").toString();
				Choice idres = new ChoiceDAO().selectById(Integer.parseInt(id));
				//trả về 1 đối tượng
				res = new Gson().toJson(idres);
				break;
			}case "ChoiceDAO_selectByPollIdAndContent":{
				JsonObject data = jsonObject.getAsJsonObject("data");
				String poll_id= data.get("poll_id").toString();
				String content = StringFromJSON(data.get("content").toString());
				Choice choiceres = new ChoiceDAO().selectByPollIdAndContent(Integer.parseInt(poll_id), content);
				res = new Gson().toJson(choiceres);
				break;
			}case "ChoiceDAO_selectByPollId":{
				
				JsonObject data = jsonObject.getAsJsonObject("data");
				int id=new Gson().fromJson(data.get("poll_id").toString(),int.class);
				//trả về arrayList
				res = new Gson().toJson(new ChoiceDAO().selectByPollID(id));
				break;
			}case "ChoiceDAO_insert":{
				JsonObject data = jsonObject.getAsJsonObject("data");
				
				String choiceJson=data.get("choice").toString();
				Choice choice=new Gson().fromJson(choiceJson, Choice.class);
				//trả về int
				res=""+new ChoiceDAO().insert(choice);
				break;
			}case "ChoiceDAO_delete":{
				JsonObject data = jsonObject.getAsJsonObject("data");
				
				String choiceJson=data.get("choice").toString();
				Choice choice=new Gson().fromJson(choiceJson, Choice.class);
				res=""+new ChoiceDAO().delete(choice);
				break;
				
			}//ktra lại hàm này
			case "ChoiceDAO_deleteByPollId":{
				JsonObject data = jsonObject.getAsJsonObject("data");
				//lấy ra id đưa vào
				int id=new Gson().fromJson(data.get("poll_id").toString(),int.class);				
				res = ""+new ChoiceDAO().deleteByPollId(id);
				break;

			}case "ChoiceDAO_update":{
				JsonObject data = jsonObject.getAsJsonObject("data");
				String choiceJson=data.get("choice").toString();
				Choice choice=new Gson().fromJson(choiceJson, Choice.class);
				res=""+new ChoiceDAO().update(choice);
				break;
			}			
			
			
			//voters
			case "VotersDAO_selectAll":{
				res = new Gson().toJson(new VotersDAO().selectAll());
				break;
			}case "VotersDAO_insert":{
				JsonObject data = jsonObject.getAsJsonObject("data");
				String votersJson=data.get("voters").toString();
				Voters voters=new Gson().fromJson(votersJson, Voters.class);
				res=""+new VotersDAO().insert(voters);
				break;
			}case "VotersDAO_delete":{
				JsonObject data = jsonObject.getAsJsonObject("data");
				String votersJson=data.get("voters").toString();
				Voters voters=new Gson().fromJson(votersJson, Voters.class);
				res=""+new VotersDAO().delete(voters);
				break;
			}
			case "VotersDAO_deleteByPollID":{
				JsonObject data = jsonObject.getAsJsonObject("data");
				//lấy ra id đưa vào
				int id=new Gson().fromJson(data.get("poll_id").toString(),int.class);				
				res = ""+new VotersDAO().deleteByPollID(id);
				break;
			}case "VotersDAO_deleteByUserId":{
				JsonObject data = jsonObject.getAsJsonObject("data");
				//lấy ra id đưa vào
				int id=new Gson().fromJson(data.get("user_id").toString(),int.class);				
				res = ""+new VotersDAO().deleteByUserId(id);
				break;
			}
			//vote
			case "VoteDAO_selectAll":{
				res = new Gson().toJson(new VoteDAO().selectAll());
				break;
			}case "VoteDAO_selectByPollId":{			
			
				JsonObject data = jsonObject.getAsJsonObject("data");
				int id=new Gson().fromJson(data.get("poll_id").toString(),int.class);
				//trả về arrayList
				res = new Gson().toJson(new VoteDAO().selectByPollId(id));
				break;
			}case "VoteDAO_selectByPollIdAndChoiceId":{
				JsonObject data = jsonObject.getAsJsonObject("data");
				int poll_id=new Gson().fromJson(data.get("poll_id").toString(),int.class);
				int choice_id=new Gson().fromJson(data.get("choice_id").toString(),int.class);
				//trả về arrayList
				res = new Gson().toJson(new VoteDAO().selectByPollIdAndChoiceId(poll_id,choice_id));
				break;
			}case "VoteDAO_selectById":{
				JsonObject data = jsonObject.getAsJsonObject("data");
				String id =data.get("id").toString();
				Vote idres = new VoteDAO().selectById(Integer.parseInt(id));
				//trả về 1 đối tượng
				res = new Gson().toJson(idres);
				break;
			}case "VoteDAO_insert":{
				JsonObject data = jsonObject.getAsJsonObject("data");
				String voteJson=data.get("vote").toString();
				Vote vote=new Gson().fromJson(voteJson, Vote.class);
				res=""+new VoteDAO().insert(vote);
				break;
			}case "VoteDAO_delete":{
				JsonObject data = jsonObject.getAsJsonObject("data");
				String voteJson=data.get("vote").toString();
				Vote vote=new Gson().fromJson(voteJson, Vote.class);
				res=""+new VoteDAO().delete(vote);
				break;
			}
			
			
			
			//date
			case "dateNow": {
				try {
				SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		        res = formatter.format(new Date());
				}
				catch (Exception exception) {
					exception.printStackTrace();
				}
				break;
			}
			}
		} catch (Exception exception) {
			exception.printStackTrace();
		}
		return res;
	}

}