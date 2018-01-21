import java.io.*;
import java.net.*;

public class IntermediateHost {

	int socketSendNumber = 69, socketReceiveNumber = 23;
	DatagramPacket sendPacket, receivePacket;
	DatagramSocket sendReceiveSocket, receiveSocket;
		
	public IntermediateHost() {
		try {
			sendReceiveSocket = new DatagramSocket();
			receiveSocket = new DatagramSocket(socketReceiveNumber);
		} catch (SocketException se) {
			se.printStackTrace();
			System.exit(1);
		}
	}
	
	public void sendAndReceive() {
		byte data[] = new byte[1000];
		receivePacket = new DatagramPacket(data, data.length);
		while (true) {
			try {
				System.out.println("Host is waiting for a packet.");
				receiveSocket.receive(receivePacket);
				System.out.println("Host is received a packet!");
				printReceivePacketInfo(receivePacket);
			} catch (IOException e) {
				System.out.print("IO Exception: likely:");
				System.out.println("Receive Socket Timed Out.\n" + e);
				e.printStackTrace();
				System.exit(1);
			}
			//sending to server
			try {
				sendPacket = new DatagramPacket(receivePacket.getData(), receivePacket.getData().length, InetAddress.getLocalHost(), socketSendNumber);
			} catch (UnknownHostException e) {
				e.printStackTrace();
				System.exit(1);
			}		
			try {
				sendReceiveSocket.send(sendPacket);
				System.out.println("Intermediate host sending packet to Server.");
			} catch (IOException e) {
				e.printStackTrace();
				System.exit(1);
			}
			try {
				sendReceiveSocket.receive(receivePacket);
				System.out.println("Intermediate host received packet from Server.");
				printReceivePacketInfo(receivePacket);
			} catch (IOException e) {
				e.printStackTrace();
				System.exit(1);
			}
			sendReceiveSocket.close();
			//return to client
			try {
				sendPacket = new DatagramPacket(receivePacket.getData(), receivePacket.getData().length, InetAddress.getLocalHost(), socketReceiveNumber);
			} catch (UnknownHostException e) {
				e.printStackTrace();
				System.exit(1);
			}
			try {
				receiveSocket.send(sendPacket);
			} catch (IOException e) {
				e.printStackTrace();
				System.exit(1);
			}
			receiveSocket.close();
		}
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		IntermediateHost intHost = new IntermediateHost();
		intHost.sendAndReceive();
	}

	public void printsendPacketInfo(DatagramPacket packet) {
		System.out.print("sendPacket contents as Bytes: ");
		System.out.println(packet.getData());
		System.out.print("sendPacket contents as a String: ");
		System.out.println(new String(packet.getData(),0,packet.getLength()));
	}
	
	public void printReceivePacketInfo(DatagramPacket packet) {
		System.out.print("RecievePacket contents as Bytes: ");
		System.out.println(packet.getData());
		System.out.print("RecievePacket contents as a String: ");
		System.out.println(new String(packet.getData(),0,packet.getLength()));
	}

	
}
