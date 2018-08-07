package com.mashensoft.service;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class WeatherServiceImplTest {

	@Test
	void testGetWeather() {
		WeatherServiceImpl ws = new WeatherServiceImpl();
		ws.getWeather("广州");
	}

}
