package server;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

import server.Cookie.Role;

//Socket connection -> UDP

public class Server {

	private static final int PORT = 1237;
	private static final int TIMEOUT = 0;
	private static final int MAXBUFF = 10240;// Maximale Größe für Nachrichten
	private static DatagramSocket server;
	private enum Input{isCookie, isString};
	private static Cookie cookie;
	private static DatagramPacket input;
	
	public static void main(String[] args) {
		cookie = new Cookie();
		try {
			server = new DatagramSocket(PORT);
			server.setSoTimeout(TIMEOUT);	

			while (true) {
				input = new DatagramPacket(new byte[MAXBUFF], MAXBUFF);
				server.receive(input);
				
				byte[] data = getData(input);
				Input in = checkInput(data);
				handleInput(in, data);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			server.close();
		}

	}
	
	private static byte[] getData(DatagramPacket input) {
		int actualSize = actualSize(input);
		byte[] data = new byte[actualSize];
		for(int i = 0; i < actualSize; i++) 
			data[i] = input.getData()[i];
		return data;
	}
	
	private static int actualSize(DatagramPacket input) {
		int i;
		for(i = input.getLength(); i > 0; i--) {
			if(input.getData()[i] != 0)
				break;
		}
		return i+1;
	}
	
	private static Input checkInput(byte[] data) {
		int size = data.length;
		//A cookie has at least 5 bytes (1 Byte for username)
		//If a Datagram's last three bytes are 'coo' then it is a cookie
		if(size > 4 && data[size-3]==(byte)'c' && data[size-2]==(byte)'o' && data[size-1]==(byte)'o') {
			return Input.isCookie;
		}
		return Input.isString;
	}
	
	private static void handleInput(Input in, byte[] input) {
		if(in == Input.isString) {
			boolean equal = strcmp("print_cookie".getBytes(), input);
			String message = new String(input);
			System.out.println(message);
			if(equal) {
				printCookie();
			}
		} else if(in == Input.isCookie) {
			cookie = new Cookie(input);
		}
	}
	
	private static boolean strcmp(byte[] a, byte[] b) {
		if(b.length != a.length) return false;
		boolean equal = true;
		for(int i = 0; i < b.length && i < a.length; i++) {
			if(b[i] != a[i]) {
				equal = false;
				break;
			}
		}
		return equal;
	}
	
	private static void printCookie() {
		System.out.println("Username: " + cookie.username);
		System.out.print("Role: ");
		if(cookie.role == Role.admin) {
			System.out.println("Admin");
		} else {
			System.out.println("normal User");
		}
	}
}
