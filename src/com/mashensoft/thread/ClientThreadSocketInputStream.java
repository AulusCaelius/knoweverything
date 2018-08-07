package com.mashensoft.thread;

import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class ClientThreadSocketInputStream extends Thread {
	private Socket socket;
	public ClientThreadSocketInputStream(Socket socket) {
			this.socket = socket;
	}

	@Override
	public void run() {
		Scanner s = null;
		try {
			s = new Scanner(socket.getInputStream());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		while(s.hasNextLine()) {
			String msg = s.nextLine();
			System.out.println(msg);
		}
		
	}
}
