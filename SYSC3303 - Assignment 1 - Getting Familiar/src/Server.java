import java.io.*;
import java.net.*;

public class Server {

	private int socketReceiveNumber = 69;
	private DatagramPacket sendPacket, receivePacket;
	private DatagramSocket sendSocket, receiveSocket;
	
	public Server() {
		try {
			sendSocket = new DatagramSocket();
			receiveSocket = new DatagramSocket(socketReceiveNumber);
		} catch (SocketException se) {
			se.printStackTrace();
			System.exit(1);
		}
	}
	
	/**
	 * Handles the receiving of packets, and then calls the appropriate functions to determine what it will
	 * send back
	 */
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
			if (parse(data) == false) {
				System.err.print("Invalid read/write request");
				System.exit(1);
			}
			
			printSendPacketInfo(sendPacket); //print the information about the packet the server is going
											 //to send back to the 'client'
			
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
	
	/**
	 * Parses the given data to check if it is a valid read or write request, or if it is invalid. If valid, it will prepare
	 * the packet to send and return true, if not it will return false.
	 * @param data
	 * @return boolean true or false depending on if data is valid
	 */
	public boolean parse(byte data[]) {
		byte readFirstTwoBit[] = {0, 1};
		byte writeFirstTwoBit[] = {0, 2};
		byte zeroBit[] = {0};
		int j = 0; //used to determine how many characters are between the last identified byte (1 or 2) and the next '0' byte 
		
		if (data[0] == zeroBit[0]) {
			if (data[1] == readFirstTwoBit[1]) { //checking if 'read'
				for (int i = 2; i < data.length; i++) {
					if (data[i] == zeroBit[0] && j != 0 && i != data.length - 1) {
						//Checks that we have found a '0' byte, and that it is not immediately after the first two identifier bytes,
						//and that it is not the last byte in the array of data
						j = 0;
						if (data[i] == zeroBit[0] && j != 0 && i == data.length - 1) {
							//Checks that we have found a '0' byte, and that it is not immediately after the last 0 byte,
							//and that it is the last byte in the array of data
							byte validRead[] = {0, 3, 0, 1};
							//if reached, then it's a valid read request
							sendPacket = new DatagramPacket(validRead, validRead.length, receivePacket.getAddress(), receivePacket.getPort());
							return true;
						}
					} else {
						j++; //this is to make sure that we don't find a 0 without
							 //having some text between it and the initial bytes
					}
				}				
			} else if (data[1] == writeFirstTwoBit[1]) { //checking if 'write'
				for (int i = 2; i < data.length; i++) {
					if (data[i] == zeroBit[0] && j != 0 && i != data.length - 1) {
						//Checks that we have found a '0' byte, and that it is not immediately after the first two identifier bytes,
						//and that it is not the last byte in the array of data
						j = 0;
						if (data[i] == zeroBit[0] && j != 0 && i == data.length - 1) {
							//Checks that we have found a '0' byte, and that it is not immediately after the last 0 byte,
							//and that it is the last byte in the array of data
							byte validWrite[] = {0, 4, 0, 0};
							//if reached, then it's a valid write request
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

	/**
	 * Prints out information about the sendPacket
	 * @param packet
	 */
	public void printSendPacketInfo(DatagramPacket packet) {
		System.out.print("SendPacket contents as Bytes: ");
		System.out.println(packet.getData());
		System.out.print("SendPacket contents as a String: ");
		System.out.println(new String(packet.getData(),0,packet.getLength()));
	}
	
	/**
	 * Prints out information about the receivePacket
	 * @param packet
	 */
	public void printReceivePacketInfo(DatagramPacket packet) {
		System.out.print("RecievePacket contents as Bytes: ");
		System.out.println(packet.getData());
		System.out.print("RecievePacket contents as a String: ");
		System.out.println(new String(packet.getData(),0,packet.getLength()));
	}
}