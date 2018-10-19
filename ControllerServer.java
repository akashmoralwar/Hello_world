package com.tresk.controller;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;

public class ControllerServer {

	static int port = 8082;
	
	public static void main(String[] args) {
		listen();
	}
	
	public static void execute()
	{
		System.out.println("Got it");
	}
	public static void listen()
	{
		ServerSocket serverSocket = null;
		try {
			serverSocket = new ServerSocket(port);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			return;
		}
		
		while(true) {
	         try {
		        	 
		             serverSocket.setSoTimeout(30000);
		             
		            System.out.println("Waiting for client on port " + 
		            
		            serverSocket.getLocalPort() + "...");
		            Socket server = serverSocket.accept();
		            
		            System.out.println("Just connected to " + server.getRemoteSocketAddress());
		            DataInputStream in = new DataInputStream(server.getInputStream());
		            
		            System.out.println(in.readUTF());
		            
		            execute();
		            
		            DataOutputStream out = new DataOutputStream(server.getOutputStream());
		            out.writeUTF("Thank you for connecting to " + server.getLocalSocketAddress()
		               + "\nGoodbye!");
		            
		            
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
