import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class ControllerClient {

	public String serverName = "111.119.237.172";
	public int port = 8082;
	
	public static void main(String[] args) {
	
		ControllerClient client = new ControllerClient();
		client.sendCommand("logoff");
		System.out.println("command sent");
	}
	
	
	public void sendCommand(String cmd)
	{
		
	      try {
	         System.out.println("Connecting to " + serverName + " on port " + port);
	         Socket client = new Socket(serverName, port);
	         
	         System.out.println("Just connected to " + client.getRemoteSocketAddress());
	         
	         OutputStream outToServer = client.getOutputStream();
	         DataOutputStream out = new DataOutputStream(outToServer);
	         
	         out.writeUTF(cmd + client.getLocalSocketAddress());
	         
	         InputStream inFromServer = client.getInputStream();
	         DataInputStream in = new DataInputStream(inFromServer);
	         
	         System.out.println("Server says " + in.readUTF());
	         
	         client.close();
	      } catch (IOException e) {
	         e.printStackTrace();
	      }
		
	}
}
