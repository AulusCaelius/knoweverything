package com.mashensoft.model;

import com.mashensoft.service.BusinessSocketService;

public class Movie {
	private String name;
	private String title;
	private String url;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Movie() {
		super();
	}

	public Movie(String name, String title, String url) {
		super();
		this.name = name;
		this.title = title;
		this.url = url;
	}

	@Override
	public String toString() {
		String content = "";
		content += "电影名称：" + name + BusinessSocketService.enter;
		content += "标题：" + title + BusinessSocketService.enter;
		content += "下载地址：" + url + BusinessSocketService.enter;

		return content;
	}

}
