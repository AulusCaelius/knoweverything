package com.mashensoft.main;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

import com.mashensoft.thread.ClientThreadSocketInputStream;

public class Client {
	public static void main(String[] args) {
		System.out.println("客户端已启动");
		try {
			Socket socket = new Socket("localhost", 6688);
			//因为服务端会先发送一段话，我们要先把它打印出来
//			Scanner serverScanner = new Scanner(socket.getInputStream());
//			String welcome = serverScanner.nextLine();
//			System.out.println(welcome);
			ClientThreadSocketInputStream thread = new ClientThreadSocketInputStream(socket);
			thread.start();
			PrintWriter pw =new PrintWriter(socket.getOutputStream());
			//监听键盘的输入
			Scanner s = new Scanner(System.in);
			while(s.hasNextLine()) {
				//键盘输入一段话，我们就发送给服务端
				String msg = s.nextLine();
				pw.println(msg);
				pw.flush();
				//等待服务器响应，如果不响应，我们阻塞在这里
//				String line = serverScanner.nextLine();
//				System.out.println(line);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
