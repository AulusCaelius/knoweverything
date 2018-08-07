package com.mashensoft.service;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Scanner;

public class IpServiceImpl implements IpService {
	/**
	 * ip地址查询的主方法，进入到这个方法中，我们可以跟客户端进行交互，可接收客户端的输入，也可以向客户端输出内容
	 * 
	 * @param scanner
	 *            客户端输入字符流
	 * @param pw
	 *            客户端输出流
	 */
	@Override
	public void mainMehtod(Scanner scanner, PrintWriter pw) {
		pw.println("请输入ip地址" + BusinessSocketService.enter);
		pw.println("输入q返回到主菜单" + BusinessSocketService.enter);
		pw.flush();
		String ip = scanner.nextLine();
		// 如果输入的是q，返回到主菜单
		if (ip.equalsIgnoreCase("q")) {
			BusinessSocketService.mainMenu(pw);
			String msg = scanner.nextLine();
			BusinessSocketService.dealUserInput(scanner, pw, msg);
		}
		// 检查ip地址是否合法
		boolean sign = checkIPIsValid(ip);
		if (!sign) {
			// 如果不合法，提示用户不合法
			pw.println("您输入的ip地址不合法，请重新输入");
			pw.flush();
			mainMehtod(scanner, pw);
		}
		//ip = scanner.nextLine();
		// 如果合法，调用一个业务方法
		String content = getContent(scanner, pw,ip);
		pw.println("服务端响应："+content);
		pw.flush();
		
		mainMehtod(scanner,pw);

	}

	/**
	 * 功能：检查ip地址是否合法
	 * 
	 * @param ip
	 * @return true:合法； false:不合法
	 */
	public boolean checkIPIsValid(String ip) {
		boolean sign = true;
		// 192.168.20.111， 1.1.1.1，8.8.8.1
		// 一个ip地址有4段，其中有3个小数点，每一段的范围0-255 ，255.255.255.255，
		if (ip.length() > 15) {
			sign = false;
		}
		String[] ipArray = ip.split("\\.");
		if (ipArray.length != 4) {
			sign = false;
		}
		for (int i = 0; i < ipArray.length; i++) {
			Integer num = 0;
			try {
				num = Integer.parseInt(ipArray[i]);
			} catch (Exception e) {
				sign = false;
			}

			if (num > 255 || num < 0) {
				sign = false;
			}
		}
		return sign;
	}

	/**
	 * 获取ip地址
	 */
	@Override
	public String getContent(Scanner scanner, PrintWriter pw,String ip) {
		//String ip = scanner.nextLine();
		// 发一起一个网络连接，获取网页的内容
		String content = getContentFromURL(ip);
		// 从网页里取出运营商
		String spName = getSpNameFromContent(content);
		return spName;
	}

	/**
	 * 功能：根据ip地址获取运营商名称
	 * 
	 * @param webUrl
	 *            ip地址
	 * @return
	 */
	public String getContentFromURL(String ip) {
		StringBuffer sb = new StringBuffer();
		// http://ip138.com/ips138.asp?ip=120.230.101.12&action=2
		String webUrl = "http://ip138.com/ips138.asp?ip=" + ip + "&action=2";
		try {
			URL url = new URL(webUrl);
			URLConnection conn = url.openConnection();
			Scanner s = new Scanner(conn.getInputStream(), "gb2312");
			while (s.hasNextLine()) {
				sb.append(s.nextLine()).append("\r\n");
			}
			s.close();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return sb.toString();

	}

	/**
	 * 功能：从一个网页内容里取出运营商名称
	 * 
	 * @param content
	 *            网页的内容
	 */
	public String getSpNameFromContent(String content) {
		String spName = "";
		if (content.contains("本站数据：") && content.contains("</li>")) {
			int beginIndex = content.indexOf("本站数据：");
			int endIndex = content.indexOf("</li>");
			spName = content.substring(beginIndex + 5, endIndex);
		}
		return spName;
	}

}
