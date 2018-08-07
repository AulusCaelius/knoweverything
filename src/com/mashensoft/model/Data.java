package com.mashensoft.model;

import java.util.ArrayList;
import java.util.List;

public class Data {
	private String shidu;
	private String pm25;
	private String pm10;
	private String quality;
	private String wendu;
	private String ganmao;
	private Forecast yesterday = new Forecast();
	private List<Forecast> forecast = new ArrayList<>();
	public String getShidu() {
		return shidu;
	}
	public void setShidu(String shidu) {
		this.shidu = shidu;
	}
	public String getPm25() {
		return pm25;
	}
	public void setPm25(String pm25) {
		this.pm25 = pm25;
	}
	public String getPm10() {
		return pm10;
	}
	public void setPm10(String pm10) {
		this.pm10 = pm10;
	}
	public String getQuality() {
		return quality;
	}
	public void setQuality(String quality) {
		this.quality = quality;
	}
	public String getWendu() {
		return wendu;
	}
	public void setWendu(String wendu) {
		this.wendu = wendu;
	}
	public String getGanmao() {
		return ganmao;
	}
	public void setGanmao(String ganmao) {
		this.ganmao = ganmao;
	}
	public Forecast getYesterday() {
		return yesterday;
	}
	public void setYesterday(Forecast yesterday) {
		this.yesterday = yesterday;
	}
	public List<Forecast> getForecast() {
		return forecast;
	}
	public void setForecast(List<Forecast> forecast) {
		this.forecast = forecast;
	}
	
}
