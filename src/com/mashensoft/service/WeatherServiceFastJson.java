package com.mashensoft.service;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Scanner;

import com.mashensoft.model.Weather;

public class WeatherServiceFastJson implements WeatherService {

	@Override
	public void mainMehtod(Scanner scanner, PrintWriter pw) {
		pw.println("请输入城市名称" + BusinessSocketService.enter);
		pw.println("输入q返回到主菜单" + BusinessSocketService.enter);
		pw.flush();
		String inputLine = scanner.nextLine();
		// 如果输入的是q，返回到主菜单
		if (inputLine.equalsIgnoreCase("q")) {
			BusinessSocketService.mainMenu(pw);
			String msg = scanner.nextLine();
			BusinessSocketService.dealUserInput(scanner, pw, msg);
		}
		// 如果合法，调用一个业务方法
		String content = getContent(inputLine);
		pw.println("服务端响应：" + content);
		pw.flush();

		mainMehtod(scanner, pw);
	}

	/**
	 * 获取当天天气预报的内容
	 * 
	 * @param inputLine
	 * @return
	 */
	public String getContent(String inputLine) {
		Weather weather = getWeather(inputLine);
		return weather.toString();
	}

	/**
	 * 根据城市名称获取天气信息
	 * 
	 * @param city
	 * @return 城市：xx <br>
	 *         日期：xx <br>
	 *         最高温度：xx <br>
	 *         最低温度：xx <br>
	 *         天气：xx <br>
	 *         注意事项：xx <br>
	 */
	public String getContentFromURL(String city) {
		StringBuffer sb = new StringBuffer();
		// http://ip138.com/ips138.asp?ip=120.230.101.12&action=2
		String webUrl = "https://www.sojson.com/open/api/weather/json.shtml?city=" + city;
		try {
			URL url = new URL(webUrl);
			URLConnection conn = url.openConnection();
			Scanner s = new Scanner(conn.getInputStream());
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
	 * 获取天气对象
	 * 
	 * @param city
	 * @return
	 */
	public Weather getWeather(String city) {
		Weather weather = new Weather();
		String content = getContentFromURL(city);
		String date = getDate(content);
		weather.setDate(date);
		weather.setCity(city);
		weather.setHigh(getHigh(content));
		weather.setLow(getLow(content));
		weather.setType(getType(content));
		weather.setNotice(getNotice(content));
		return weather;
	}

	/**
	 * 获取天气预报里的日期
	 * 
	 * @param content
	 * @return 日期
	 */
	public String getDate(String content) {
		int beginIndex = content.indexOf("forecast");
		int dateBeginIndex = content.indexOf("date", beginIndex);
		int dateEndIndex = content.indexOf("\",", dateBeginIndex);
		String date = content.substring(dateBeginIndex + 7, dateEndIndex);
		return date;
	}

	/**
	 * 获取最高温度
	 * 
	 * @param content
	 * @return
	 */
	public String getHigh(String content) {
		int beginIndex = content.indexOf("forecast");
		int dateBeginIndex = content.indexOf("high", beginIndex);
		int dateEndIndex = content.indexOf("\",", dateBeginIndex);
		String str = content.substring(dateBeginIndex + 7, dateEndIndex);
		return str;
	}

	/**
	 * 获取最低温度
	 * 
	 * @param content
	 * @return
	 */
	public String getLow(String content) {
		int beginIndex = content.indexOf("forecast");
		int dateBeginIndex = content.indexOf("low", beginIndex);
		int dateEndIndex = content.indexOf("\",", dateBeginIndex);
		String str = content.substring(dateBeginIndex + 6, dateEndIndex);
		return str;
	}

	/**
	 * 获取天气
	 * 
	 * @param content
	 * @return
	 */
	public String getType(String content) {
		int beginIndex = content.indexOf("forecast");
		int dateBeginIndex = content.indexOf("type", beginIndex);
		int dateEndIndex = content.indexOf("\",", dateBeginIndex);
		String str = content.substring(dateBeginIndex + 7, dateEndIndex);
		return str;
	}

	public String getNotice(String content) {
		int beginIndex = content.indexOf("forecast");
		int dateBeginIndex = content.indexOf("notice", beginIndex);
		int dateEndIndex = content.indexOf("\"}", dateBeginIndex);
		String str = content.substring(dateBeginIndex + 9, dateEndIndex);
		return str;
	}

}
