package com.mashensoft.service;

import java.io.PrintWriter;
import java.util.Scanner;

public interface IpService {
	/**
	 * ip地址查询的主方法，进入到这个方法中，我们可以跟客户端进行交互，可接收客户端的输入，也可以向客户端输出内容
	 * @param scanner 客户端输入字符流
	 * @param pw 客户端输出流
	 */
	public void mainMehtod(Scanner scanner,PrintWriter pw);
	public String getContent(Scanner scanner,PrintWriter pw,String ip);
}
