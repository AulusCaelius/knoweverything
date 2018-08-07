package com.mashensoft.service;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class MobilePhoneServiceImplTest {
	/**
	 * 正常的手机号码
	 */
	@Test
	void testCheckMobileIsValid1() {
		MobilePhoneServiceImpl ms = new MobilePhoneServiceImpl();
		assertEquals(true, ms.checkMobileIsValid("13560415821"));
	}
	/**
	 * 长度不够的情况
	 */
	@Test
	void testCheckMobileIsValid2() {
		MobilePhoneServiceImpl ms = new MobilePhoneServiceImpl();
		assertEquals(false, ms.checkMobileIsValid("135604"));
	}
	/**
	 * 第一位是其他字符
	 */
	@Test
	void testCheckMobileIsValid3() {
		MobilePhoneServiceImpl ms = new MobilePhoneServiceImpl();
		assertEquals(false, ms.checkMobileIsValid("03560415821"));
	}
	/**
	 * 有字符的情况
	 */
	@Test
	void testCheckMobileIsValid4() {
		MobilePhoneServiceImpl ms = new MobilePhoneServiceImpl();
		assertEquals(false, ms.checkMobileIsValid("13560xb5821"));
	}

	@Test
	void testGetContent() {
		MobilePhoneServiceImpl ms = new MobilePhoneServiceImpl();
		System.out.println(ms.getContent("18560415821"));
	}

}
