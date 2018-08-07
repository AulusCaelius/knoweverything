package com.mashensoft.model;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

public class FastJsonDemo {
	static class Person {
		String name;
		String sex;

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getSex() {
			return sex;
		}

		public void setSex(String sex) {
			this.sex = sex;
		}

	}

	/**
	 * 1:把对象转成json字符串
	 */
	public void test1() {
		Person p = new Person();
		p.setName("星哥");
		p.setSex("男");
		String json = JSON.toJSONString(p);
		System.out.println(json);
	}

	/**
	 * 把一个集合变成字符串
	 */
	public void test2() {
		Person p1 = new Person();
		p1.setName("星哥");
		p1.setSex("男");
		Person p2 = new Person();
		p2.setName("林志玲");
		p2.setSex("女");

		List<Person> list = new ArrayList<>();
		list.add(p1);
		list.add(p2);
		String json = JSON.toJSONString(list);
		System.out.println(json);
	}

	public void test3() {
		String content = "{\"name\":\"星哥\",\"sex\":\"男\"}";
		// Person p = (Person)JSON.toJavaObject(content, Person.class);
		// Person p = (Person)JSON.parse(content);
		Person p = JSON.parseObject(content, Person.class);
		System.out.println(p.getName());
	}

	public void test4() {
		String content = "[{\"name\":\"星哥\",\"sex\":\"男\"},{\"name\":\"林志玲\",\"sex\":\"女\"}]";
		// Person p = (Person)JSON.toJavaObject(content, Person.class);
		// Person p = (Person)JSON.parse(content);
		List<Person> list = JSON.parseObject(content, ArrayList.class);
		for (Iterator iterator = list.iterator(); iterator.hasNext();) {
			Person person = (Person) iterator.next();
			System.out.println(person.name);
		}
	}

	/**
	 * jsonObject
	 */
	public void test5() {
		String content = "{\"name\":\"星哥\",\"sex\":\"男\"}";
		JSONObject json = JSON.parseObject(content);
		System.out.println(json.get("name"));
		System.out.println(json.get("sex"));
	}

	public void test6() {
		String content = getContentFromFile();
		JSONObject json = JSON.parseObject(content);
		System.out.println(json.get("data"));
		JSONObject data = (JSONObject) json.get("data");
		System.out.println(data.get("forecast"));
		JSONArray array = data.getJSONArray("forecast");
		JSONObject f1 = (JSONObject) array.get(0);
		System.out.println(f1.get("type"));
		System.out.println(f1.get("high"));
		System.out.println(f1.get("low"));
		// JSONObject forecast = (JSONObject)data.get("forecast");
		// forecast.;
	}

	public String getContentFromFile() {
		String content = "";
		Scanner s = null;
		try {
			s = new Scanner(new FileInputStream("json.txt"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		while (s.hasNextLine()) {
			content += s.nextLine();
		}
		return content;
	}
	/**
	 * 使用json字符串转换成对象，很方便
	 */
	public void test7() {
		String content = getContentFromFile();
		Result result = JSON.parseObject(content, Result.class);
		Forecast f = result.getData().getForecast().get(0);
		
		System.out.println(f.getType());
		System.out.println(f.getNotice());
	}

	/**
	 * 2:把json字符串转成对象
	 */
	public static void main(String[] args) {
		FastJsonDemo fs = new FastJsonDemo();
		fs.test7();
	}

}
