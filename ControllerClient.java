
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class ControllerClient {

	public String serverName = "18.191.207.153";
	public int port = 8082;
	
	public String name = "C1";
	
	public static void main(String[] args) {
	
		ControllerClient client = new ControllerClient();
		client.sendCommand("C1","logoff","C2");
		System.out.println("command sent");
	}
	
	
	public void sendCommand(String from,String cmd,String to)
	{
		
	      try {
	         System.out.println("Connecting to " + serverName + " on port " + port);
	         Socket client = new Socket(serverName, port);
	         
	         System.out.println("Just connected to " + client.getRemoteSocketAddress());
	         
	         OutputStream outToServer = client.getOutputStream();
	         DataOutputStream out = new DataOutputStream(outToServer);
	         
	         //out.writeUTF(cmd + client.getLocalSocketAddress());
	         
	         out.writeUTF(from+" "+cmd+" "+to);
	         
	         InputStream inFromServer = client.getInputStream();
	         DataInputStream in = new DataInputStream(inFromServer);
	         
	         System.out.println("Server says " + in.readUTF());
	         
	         client.close();
	      } catch (IOException e) {
	         e.printStackTrace();
	      }
		
	}
}
