package com.mashensoft.service;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class IpServiceImplTest {

	@Test
	void testMainMehtod() {
	}

	@Test
	void testCheckIPIsValid() {
		
		IpServiceImpl service = new IpServiceImpl();
		assertEquals(true,service.checkIPIsValid("192.168.0.111"));
		
	}

	@Test
	void testGetSpNameFromContent() {
		IpServiceImpl service = new IpServiceImpl();
		String content = service.getContentFromURL("8.8.8.8");
		System.out.println(content);
		String spName = service.getSpNameFromContent(content);
		System.out.println(spName);
	}

	@Test
	void testGetContentFromURL() {
		IpServiceImpl service = new IpServiceImpl();
		String content = service.getContentFromURL("192.168.20.111");
		System.out.println(content);
	}

}
