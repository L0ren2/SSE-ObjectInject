package client;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;


/**
 *You can create a DatagramPacket object by using one of the following constructors:
 *DatagramPacket(byte[] buf, int length)
 *DatagramPacket(byte[] buf, int length, InetAddress address, int port)
 *As you can see, the data must be in the form of an array of bytes. The first constructor is used to create a DatagramPacket to be received.
 *The second constructor creates a DatagramPacket to be sent, so you need to specify the address and port number of the destination host.
 *The parameter length specifies the amount of data in the byte array to be used, usually is the length of the array (buf.length).
 * 
 *
 */
public class Client {

	private static final int PORT = 1237;
	private static final int MAXBUFF = 10240;// Maximale Größe für Nachrichten
	
	/**
	 * So you create a DatagramSocket object to establish a UDP connection for sending and receiving datagram
	 */
	private static DatagramSocket client;

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		try {
			
			DatagramPacket output = new DatagramPacket(new byte[MAXBUFF], MAXBUFF, InetAddress.getLocalHost(), PORT);
			//Verbindung 
			client = new DatagramSocket(PORT);

			while (true) {
				System.out.print("Input: ");
				String userInput = sc.nextLine();
				//Input an den Port
				output.setData(userInput.getBytes());
				output.setLength(userInput.length());

			}

		} catch (Exception e) {

			System.err.println(e.getMessage());
			e.printStackTrace();
			
		} finally {

			sc.close();
			client.close();
		}

	}

}
