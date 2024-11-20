package com.ssafy.trip.model;

public class AreaDto {

	private int sido_code;
	private String sido_name;
	private int gugun_code;
	private String gugun_name;
	
	public int getSido_code() {
		return sido_code;
	}
	
	public void setSido_code(int sido_code) {
		this.sido_code = sido_code;
	}
	
	public String getSido_name() {
		return sido_name;
	}
	
	public void setSido_name(String sido_name) {
		this.sido_name = sido_name;
	}
	
	public int getGugun_code() {
		return gugun_code;
	}
	
	public void setGugun_code(int gugun_code) {
		this.gugun_code = gugun_code;
	}
	
	public String getGugun_name() {
		return gugun_name;
	}
	
	public void setGugun_name(String gugun_name) {
		this.gugun_name = gugun_name;
	}
	
	@Override
	public String toString() {
		return "AreaDto [sido_code=" + sido_code + ", sido_name=" + sido_name + ", gugun_code=" + gugun_code
				+ ", gugun_name=" + gugun_name + "]";
	}
	
	

}
