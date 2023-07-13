package ClientController;

import java.util.Date;

import com.google.gson.Gson;

import Config.ServerConfig;
import Models.User;
import Tool.GetData;

import java.text.SimpleDateFormat;
import java.text.ParseException;
public class DateController {
	private String ip;
	private int port;

	public DateController() {
		ip = ServerConfig.ip;
		port = ServerConfig.port;
	}
	
	public Date dateNow() {
		Date date=new Date();
			String message = "{\"function\":\"dateNow\"," + "\"data\":{}}";
			String response = new GetData().Fetch(message, ip, port);
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			try {
			    date = formatter.parse(response);
			} catch (ParseException e) {
			    e.printStackTrace();
			}
			return date;
	}
	
}
