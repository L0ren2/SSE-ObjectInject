package evilclient;
import java.util.*;
import java.net.*;
public class EvilClient {
	
	private static final int PORT = 1237;
	private static final int MAXBUFF = 10240;
	private static DatagramSocket socket;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		try {
			socket = new DatagramSocket();
			DatagramPacket output = new DatagramPacket(new byte[MAXBUFF], MAXBUFF, InetAddress.getLocalHost(), PORT);
			while (true) {
				//3vilC00ki31coo
				System.out.print("Input: ");
				String userInput = sc.nextLine();
				byte[] bytes = userInput.getBytes();
				for(byte b : bytes) {
					System.out.print("" + (char) b + ", ");
				}
				System.out.println();
				output.setData(bytes);
				output.setLength(userInput.length());
				socket.send(output);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			sc.close();
		}
	}
}
