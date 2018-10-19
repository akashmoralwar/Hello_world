package com.tresk.controller;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class ObserverClient {

	public String serverName = "18.191.207.153";
	public int port = 8082;
	
	public String name="C1";
	
	public static void main(String[] args) {
		ObserverClient client = new ObserverClient();
		client .observe();
	}
	
	public void observe()
	{
		while(true)
		{
			try
			{
				System.out.println("Connecting to " + serverName + " on port " + port);
		         Socket client = new Socket(serverName, port);
		         
		         System.out.println("Just connected to " + client.getRemoteSocketAddress());
		         
		         OutputStream outToServer = client.getOutputStream();
		         DataOutputStream out = new DataOutputStream(outToServer);
		         
		         //out.writeUTF(cmd + client.getLocalSocketAddress());
		         
		         out.writeUTF(name);
		         
		         InputStream inFromServer = client.getInputStream();
		         DataInputStream in = new DataInputStream(inFromServer);
		         
		         String response =  in.readUTF();
		         
		         System.out.println("Server says " +response);
		         
		         String tokens[] = response.split(" ");
		         
		         if(tokens.length==2)
		         {
		        	 executeCmd(tokens[0],tokens[1]);
		         }else
		         {
		        	 System.out.println("NONE");
		         }
		         client.close();
	      } catch (IOException e) {
	         e.printStackTrace();
	      }
		}
	}

	private void executeCmd(String cmd,String from) {
		
		System.out.println("Got command "+cmd+" from "+from);
		
	}
}
