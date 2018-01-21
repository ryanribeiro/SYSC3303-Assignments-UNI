import java.io.*;
import java.net.*;

public class Client {

	private int socketSendNumber = 23;
	private DatagramPacket sendPacket, receivePacket;
	private DatagramSocket sendReceiveSocket;
	
	public Client() {
		try {
			sendReceiveSocket = new DatagramSocket();
		} catch (SocketException se) {
			se.printStackTrace();
			System.exit(1);
		}
	}
	
	/**
	 * Takes a message (read, write, or invalid) and calls sendReceiveRequest however many times
	 * to test if sending and receiving work correctly.
	 */
	public void sendAndReceive() {
		byte messageRead[] = createReadBytes();
		byte messageWrite[] = createWriteBytes();
		int i, numberOfRequests = 11;
		byte receivedData[] = new byte[1000];
		receivePacket = new DatagramPacket(receivedData, receivedData.length);
		
		for (i = 0; i < numberOfRequests; i++) {
			if (i == numberOfRequests - 1) {
				//do something wrong
				byte invalidMessage[] = {0,3};
				sendReceiveRequest(invalidMessage);
			} else if (i % 2 == 0) {
				//Will do five 'read requests' total
				sendReceiveRequest(messageRead);
			} else  {
				//Will do write 'read requests' total
				sendReceiveRequest(messageWrite);
			}			
		}		
	}	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Client c = new Client();
		c.sendAndReceive();
	}
	
	/**
	 * Creates a message to be used in a read packet by combining the different pieces that make up the single stream of bytes 
	 * arraycopy is used so that we don't have to worry about the specific length of the file name or the mode
	 * @return the message that will be used for the read packet
	 */
	public byte[] createReadBytes () {
		String filename = "filename.txt";
		byte file[] = filename.getBytes();
		String modeType = "netascii";
		byte mode[] = modeType.getBytes();
		
		byte readFirstTwoBit[] = {0, 1};
		byte zeroBit[] = {0};
		
		//Creating a 'read' packet message by combining the different pieces that make up the single stream of bytes
		//arraycopy is used so that we don't have to worry about the specific length of the file name or the mode		
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
	
	/**
	 * Creates a message to be used in a write packet by combining the different pieces that make up the single stream of bytes 
	 * arraycopy is used so that we don't have to worry about the specific length of the file name or the mode
	 * @return the message that will be used for the write packet
	 */
	public byte[] createWriteBytes() {
		String filename = "filename.txt";
		byte file[] = filename.getBytes();
		String modeType = "netascii";
		byte mode[] = modeType.getBytes();
		
		byte writeFirstTwoBit[] = {0, 2};
		byte zeroBit[] = {0};
		
		//Creating a 'write' packet	by combining the different pieces that make up the single stream of bytes
		//arraycopy is used so that we don't have to worry about the specific length of the file name or the mode
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

	/**
	 * Creates send packet containing message, tries to send it, and then tries to receive the returned package.
	 * @param message
	 */
	public void sendReceiveRequest(byte message[]) {
		try {
			sendPacket = new DatagramPacket(message, message.length, InetAddress.getLocalHost(), socketSendNumber);
			printSendPacketInfo(sendPacket);
		} catch (UnknownHostException e) {
			e.printStackTrace();
			System.exit(1);
		}
		try {
			sendReceiveSocket.send(sendPacket);
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(1);
		}
		try {
			sendReceiveSocket.receive(receivePacket);
			printReceivePacketInfo(receivePacket);
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(1);
		}
		sendReceiveSocket.close();
	}
}
