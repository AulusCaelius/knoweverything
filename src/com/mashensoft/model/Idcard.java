package com.mashensoft.model;

import com.mashensoft.service.BusinessSocketService;

/**
 * 存储一个身份证信息的javabean对象。
 * 1：属性
 * 2：构造方法
 * 它只能用来存储数据
 * @author zongx
 *
 */
public class Idcard {
	private String idcardNumer;
	private String sex;
	private String birthday;
	private String address;
	public Idcard() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Idcard(String idcardNumer, String sex, String birthday, String address) {
		super();
		this.idcardNumer = idcardNumer;
		this.sex = sex;
		this.birthday = birthday;
		this.address = address;
	}
	public String getIdcardNumer() {
		return idcardNumer;
	}
	public void setIdcardNumer(String idcardNumer) {
		this.idcardNumer = idcardNumer;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getBirthday() {
		return birthday;
	}
	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	@Override
	public String toString() {
		String content = "";
		content+="身份证号："+idcardNumer+BusinessSocketService.enter;
		content+="性别："+sex+BusinessSocketService.enter;
		content+="生日："+birthday+BusinessSocketService.enter;
		content+="地址："+address+BusinessSocketService.enter;
		return content;
	}
	
}
