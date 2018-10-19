
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.ArrayList;

public class ControllerServer {

	static int port = 8082;
	
	static ArrayList<Command> cmds = new ArrayList<Command>();
	
	
	
	public static void main(String[] args) {
		listen();
	}
	
	public static void listen()
	{
		ServerSocket serverSocket = null;
		try {
			serverSocket = new ServerSocket(port);
		} catch (IOException e1) {
			e1.printStackTrace();
			return;
		}
		
		while(true) {
	         try {
		        	 
		             serverSocket.setSoTimeout(300000);
		             
		            System.out.println("Waiting for client on port " + 
		            
		            serverSocket.getLocalPort() + "...");
		            Socket server = serverSocket.accept();
		            
		            System.out.println("Just connected to " + server.getRemoteSocketAddress());
		            DataInputStream in = new DataInputStream(server.getInputStream());
		            
		            String req = in.readUTF();
		            
		            System.out.println(req);
		            String tokens[] = req.split(" ");

		            String result = "NONE";
		            
		            if(tokens.length==1) //client asking
		            {
		            	for(Command cmd : cmds)
		            	{
		            		if(cmd.getTo().equalsIgnoreCase(tokens[0]))
		            		{
		            			result = cmd.getCmd()+" "+cmd.getFrom();
		            		}
		            	}
		            }else if(tokens.length==3) //client giving command
		            {
		            	Command cmd = new Command(tokens[0],tokens[1],tokens[2]);
		            	cmds.add(cmd);
		            	System.out.println("Command added "+cmd);
		            	result = "SUCCESS";
		            }else
		            {
		            	result = "FAIL";
		            }
		            	
		            DataOutputStream out = new DataOutputStream(server.getOutputStream());
		            
		            out.writeUTF(result);
		            
		            /*out.writeUTF("Thank you for connecting to " + server.getLocalSocketAddress()
		               + "\nGoodbye!");
		            */
		            
		            server.close();
		            
	         } catch (SocketTimeoutException s) {
	            System.out.println("Socket timed out!");
	            break;
	         } catch (IOException e) {
	            e.printStackTrace();
	            break;
	         }
	      }
	}
}

class Command
{
	public String from;
	public String cmd;
	public String to;
	public Command(String from, String cmd, String to) {
		super();
		this.from = from;
		this.cmd = cmd;
		this.to = to;
	}
	public String getFrom() {
		return from;
	}
	public void setFrom(String from) {
		this.from = from;
	}
	public String getCmd() {
		return cmd;
	}
	public void setCmd(String cmd) {
		this.cmd = cmd;
	}
	public String getTo() {
		return to;
	}
	public void setTo(String to) {
		this.to = to;
	}
	@Override
	public String toString() {
		return "Command [from=" + from + ", cmd=" + cmd + ", to=" + to + "]";
	}

	
	
	
}

/*public static void execute()
	{
		System.out.println("Got it");
	}
 */
