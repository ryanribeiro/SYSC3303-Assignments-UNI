import java.io.*;
import java.net.*;

public class Client {

	int socketNumberSend = 23, socketNumberReceive = 5000;
	DatagramPacket sendPacket, receivePacket;
	DatagramSocket sendReceiveSocket;
	
	public Client() {
		try {
			sendReceiveSocket = new DatagramSocket();
		} catch (SocketException se) {
			se.printStackTrace();
			System.exit(1);
		}
	}
	

	public void sendAndReceive() {
		byte messageRead[] = createReadBytes();
		byte messageWrite[] = createWriteBytes();
		int i, numberOfRequests = 11;
		
		for (i = 0; i < numberOfRequests; i++) {
			if (i == numberOfRequests - 1) {
				//do something
			} else if (i % 2 == 0) {
				//Will do five 'read requests' total
				try {
					sendPacket = new DatagramPacket(messageRead, messageRead.length, InetAddress.getLocalHost(), socketNumberSend);
				} catch (UnknownHostException e) {
					e.printStackTrace();
					System.exit(1);
				}
			} else  {
				//Will do write 'read requests' total
				try {
					sendPacket = new DatagramPacket(messageWrite, messageWrite.length, InetAddress.getLocalHost(), socketNumberSend);
				} catch (UnknownHostException e) {
					e.printStackTrace();
					System.exit(1);
				}
			}	
		}
		
	}
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub


	}
	
	public byte[] createReadBytes () {
		String filename = "filename.txt";
		byte file[] = filename.getBytes();
		String modeType = "netascii";
		byte mode[] = modeType.getBytes();
		
		byte readFirstTwoBit[] = {0, 1};
		byte zeroBit[] = {0};
		
		//Creating a 'read' packet		
		byte readPart1[] = new byte[readFirstTwoBit.length + file.length];
		System.arraycopy(readFirstTwoBit, 0, readPart1, 0, readFirstTwoBit.length);
		System.arraycopy(file, 0, readPart1, readFirstTwoBit.length, file.length);
		
		byte readPart2[] = new byte[readPart1.length + zeroBit.length];
		System.arraycopy(readPart1, 0, readPart2, 0, readPart1.length);
		System.arraycopy(zeroBit, 0, readPart2, readPart1.length, zeroBit.length);
				
		byte readPart3[] = new byte[readPart2.length + mode.length];
		System.arraycopy(readPart2, 0, readPart3, 0, readPart2.length);
		System.arraycopy(mode, 0, readPart3, readPart2.length, mode.length);
		
		byte messageRead[] = new byte[readPart3.length + zeroBit.length];
		System.arraycopy(readPart3, 0, messageRead, 0, readPart3.length);
		System.arraycopy(zeroBit, 0, messageRead, readPart3.length, zeroBit.length);
		
		return messageRead;
	}
	
	public byte[] createWriteBytes() {
		String filename = "filename.txt";
		byte file[] = filename.getBytes();
		String modeType = "netascii";
		byte mode[] = modeType.getBytes();
		
		byte writeFirstTwoBit[] = {0, 2};
		byte zeroBit[] = {0};
		
		//Creating a 'write' packet		
		byte writePart1[] = new byte[writeFirstTwoBit.length + file.length];
		System.arraycopy(writeFirstTwoBit, 0, writePart1, 0, writeFirstTwoBit.length);
		System.arraycopy(file, 0, writePart1, writeFirstTwoBit.length, file.length);
		
		byte writePart2[] = new byte[writePart1.length + zeroBit.length];
		System.arraycopy(writePart1, 0, writePart2, 0, writePart1.length);
		System.arraycopy(zeroBit, 0, writePart2, writePart1.length, zeroBit.length);
				
		byte writePart3[] = new byte[writePart2.length + mode.length];
		System.arraycopy(writePart2, 0, writePart3, 0, writePart2.length);
		System.arraycopy(mode, 0, writePart3, writePart2.length, mode.length);
		
		byte messageWrite[] = new byte[writePart3.length + zeroBit.length];
		System.arraycopy(writePart3, 0, messageWrite, 0, writePart3.length);
		System.arraycopy(zeroBit, 0, messageWrite, writePart3.length, zeroBit.length);
		
		return messageWrite;
	}

}
