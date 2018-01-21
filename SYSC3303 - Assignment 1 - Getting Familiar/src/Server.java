import java.io.*;
import java.net.*;

public class Server {

	int socketReceiveNumber = 69;
	DatagramPacket sendPacket, receivePacket;
	DatagramSocket sendSocket, receiveSocket;
	
	public Server() {
		try {
			sendSocket = new DatagramSocket();
			receiveSocket = new DatagramSocket(socketReceiveNumber);
		} catch (SocketException se) {
			se.printStackTrace();
			System.exit(1);
		}
	}
	
	public void receive() {
		byte data[] = new byte[1000];
		receivePacket = new DatagramPacket(data, data.length);
		System.out.println("Server is waiting for a packet");
		
		while (true) {
			try {
				receiveSocket.receive(receivePacket);
			} catch (IOException e) {
				System.out.print("IO Exception: likely:");
				System.out.println("Receive Socket Timed Out.\n" + e);
				e.printStackTrace();
				System.exit(1);
			}
			data = receivePacket.getData();
			if (!parse(data)) {
				System.err.print("Invalid read/write request");
				System.exit(1);
			}
			try {
				sendSocket.send(sendPacket);
			} catch (IOException e) {
				e.printStackTrace();
				System.exit(1);
			}
			sendSocket.close();
			receiveSocket.close();
		}
	}
		
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Server s = new Server();
		s.receive();
	}
	
	public boolean parse(byte data[]) {
		byte readFirstTwoBit[] = {0, 1};
		byte writeFirstTwoBit[] = {0, 2};
		byte zeroBit[] = {0};
		int j = 0;
		
		if (data[0] == zeroBit[0]) {
			if (data[1] == readFirstTwoBit[1]) { //checking read
				for (int i = 2; i < data.length; i++) {
					if (data[i] == zeroBit[0] && j != 0 && i != data.length - 1) {
						j = 0;
						if (data[i] == zeroBit[0] && j != 0 && i == data.length - 1) {
							byte validRead[] = {0, 3, 0, 1};
							sendPacket = new DatagramPacket(validRead, validRead.length, receivePacket.getAddress(), receivePacket.getPort());
							return true;
						}
					} else {
						j++; //this is to make sure that we don't find a 0 without
							 //having some text between it and the initial bytes
					}
				}				
			} else if (data[1] == writeFirstTwoBit[1]) { //checking write
				for (int i = 2; i < data.length; i++) {
					if (data[i] == zeroBit[0] && j != 0 && i != data.length - 1) {
						j = 0;
						if (data[i] == zeroBit[0] && j != 0 && i == data.length - 1) {
							byte validWrite[] = {0, 4, 0, 0};
							sendPacket = new DatagramPacket(validWrite, validWrite.length, receivePacket.getAddress(), receivePacket.getPort());
							return true;
						}
					} else {
						j++; //this is to make sure that we don't find a 0 without
							 //having some text between it and the initial bytes
					}
				}
			}
		} else { //neither, therefore invalid
			return false;
		}
		return false;
	}

}