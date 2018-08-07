package com.mashensoft.service;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class BusinessSocketService extends Thread{
	Socket mySocket;
	
	public BusinessSocketService(Socket mySocket) {
		this.mySocket = mySocket;
	}
	public static String enter = "\r\n";

	public static void startServer() {
		ServerSocket serverSocket = null;
		try {
			serverSocket = new ServerSocket(6688);

		} catch (IOException e) {
			e.printStackTrace();
		}

		while (true) {
			try {
				Socket socket = serverSocket.accept();
				BusinessSocketService bs = new BusinessSocketService(socket);
				bs.start();
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
	}

	/**
	 * 输出主菜单给用户的客户端
	 * 
	 * @param pw
	 *            输入字符流
	 */
	public static void mainMenu(PrintWriter pw) {
		String content = "欢迎进入码神百事通平台" + enter;
		content += "1:输入1，进入查询ip功能" + enter;
		content += "2:输入2，进入查询身份证号码功能" + enter;
		content += "3:输入3，进入查询手机号码归属地功能" + enter;
		content += "4:输入4，进入查询电影地址下载功能" + enter;
		content += "5:输入5，进入查询天气预报功能" + enter;
		pw.print(content);
		pw.flush();
		
	}

	/**
	 * 处理用户输入
	 * 
	 * @param scanner
	 * @param pw
	 * @param command
	 */
	public static void dealUserInput(Scanner scanner, PrintWriter pw, String command) {
		switch (command) {
		case "1":
			// 进入ip的功能
			// TODO，加上对应的实现类
			IpService ipService = new IpServiceImpl();
			ipService.mainMehtod(scanner, pw);
			break;
		case "2":
			IdcardService idcardService = new IdcardServiceJsoup();
			idcardService.mainMehtod(scanner, pw);
			break;
		case "3":
			MobilePhoneService mobileService = new MobilePhoneServiceImpl();
			mobileService.mainMehtod(scanner, pw);
			break;
		case "4":
			MovieService ms = new MovieServiceImpl();
			ms.mainMehtod(scanner, pw);
			break;
		case "5":
			WeatherService ws = new WeatherServiceFastJson();
			ws.mainMehtod(scanner, pw);
			break;

		default:
			mainMenu(pw);
			break;
		}
	}
	@Override
	public void run() {
		PrintWriter pw = null;
		try {
			pw = new PrintWriter(mySocket.getOutputStream());
			mainMenu(pw);
			// 客户会输入对应的数字，我们拿到对应的数字
			Scanner scanner = new Scanner(mySocket.getInputStream());
			String command = scanner.nextLine();
			dealUserInput(scanner, pw, command);
		} catch (IOException e) {
		}
		
	}
}
