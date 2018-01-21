import java.io.*;
import java.net.*;

public class Server {

	int socketNumberSend = 5000, socketNumberReceive = 69;
	DatagramPacket sendPacket, receivePacket;
	DatagramSocket sendSocket, receiveSocket;
	
	public Server() {
		try {
			sendSocket = new DatagramSocket();
			receiveSocket = new DatagramSocket(socketNumberReceive);
		} catch (SocketException se) {
			se.printStackTrace();
			System.exit(1);
		}
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}