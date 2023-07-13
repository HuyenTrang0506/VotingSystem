package Tool;


import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class GetData {
    public String Fetch(String json,String url,int port) {
        String res="";
        try {
            Socket socket = new Socket(url, port);
            OutputStreamWriter outToServer =
                    new OutputStreamWriter(socket.getOutputStream(), "UTF-8");
            BufferedReader inFromServer =
                    new BufferedReader(new
                            InputStreamReader(socket.getInputStream(), "UTF-8"));
            outToServer.write(json + '\n');
            outToServer.flush();
            res = inFromServer.readLine();
            socket.close();
        }
        catch (Exception exception) {
            exception.printStackTrace();
        }
        return res;
    }

}