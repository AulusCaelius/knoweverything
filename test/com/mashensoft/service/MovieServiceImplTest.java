package com.mashensoft.service;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class MovieServiceImplTest {

	@Test
	void testGetDownloadUrlFromWeburl() {
		MovieServiceImpl ms = new MovieServiceImpl();
		String movileUrl = ms.getDownloadUrlFromWeburl("http://www.ygdy8.com/html/gndy/dyzz/20171201/55673.html");
		System.out.println(movileUrl);
	}
	@Test
	void testGetMovieList() {
		MovieServiceImpl ms = new MovieServiceImpl();
		ms.getMovieList("战狼");
	}

}
