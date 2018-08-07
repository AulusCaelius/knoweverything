package com.mashensoft.service;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import com.mashensoft.model.Idcard;

public class IdcardServiceJsoup implements IdcardService {

	@Override
	public void mainMehtod(Scanner scanner, PrintWriter pw) {
		pw.println("请输入身份证号码" + BusinessSocketService.enter);
		pw.println("输入q返回到主菜单" + BusinessSocketService.enter);
		pw.flush();
		String inputLine = scanner.nextLine();
		// 如果输入的是q，返回到主菜单
		if (inputLine.equalsIgnoreCase("q")) {
			BusinessSocketService.mainMenu(pw);
			String msg = scanner.nextLine();
			BusinessSocketService.dealUserInput(scanner, pw, msg);
		}
		// 检查身份证号码是否合法
		boolean sign = checkIcardIsValid(inputLine);
		if (!sign) {
			// 如果不合法，提示用户不合法
			pw.println("您输入的身份证号码不合法，请重新输入");
			pw.flush();
			mainMehtod(scanner, pw);
		}
		// 如果合法，调用一个业务方法
		String content = getContent(inputLine);
		pw.println("服务端响应：" + content);
		pw.flush();

		mainMehtod(scanner, pw);
	}

	/**
	 * 检查身份证号是否合法
	 * 
	 * @param idcard
	 * @return
	 */
	public boolean checkIcardIsValid(String idcard) {
		// 360822198305022623
		boolean sign = true;
		if (idcard.length() != 18) {
			sign = false;
		}
		return sign;
	}

	/**
	 * 使用jsoup获取身份证信息
	 * 
	 * @param idcardNumber
	 *            身份证号码
	 * @return 一个字符串： <br>
	 *         身份证号：xxxxxxxxxxxxxx; <br>
	 *         性别：男; <br>
	 *         生日：20080810; <br>
	 *         地址：广东省广州市天河区;
	 * 
	 */
	public String getContent(String idcardNumber) {
		Idcard idcard = new Idcard();
		idcard.setIdcardNumer(idcardNumber);
		String content = "";
		String url = "http://qq.ip138.com/idsearch/index.asp?userid=" + idcardNumber
				+ "&action=idcard&B1=%B2%E9+%D1%AF";
		try {
			Document doc = Jsoup.connect(url).get();
			Elements elements = doc.getElementsByAttributeValue("class", "tdc2");
			if (elements.size() >= 3) {
				idcard.setSex(elements.get(0).text());// 性别
				idcard.setBirthday(elements.get(1).text());
				idcard.setAddress(elements.get(2).text());
			}
			content = idcard.toString();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return content;
	}

}
