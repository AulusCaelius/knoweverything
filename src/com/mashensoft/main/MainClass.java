package com.mashensoft.main;

import com.mashensoft.service.BusinessSocketService;

/**
 * 功能：启动业务类
 * 
 * @author zongx
 *
 */
public class MainClass {

	public static void main(String[] args) {
		System.out.println("启动百事通平台，端口号：6688");
		BusinessSocketService.startServer();
	}

}
