package com.ssafy.trip.model;

public class TripDto {
	
	private int area_code;
	private int si_gun_gu_code;
	private int content_type_id;
	private int content_id;
	private String title;
	private String img1;
	private String img2;
	private String addr1;
	private String addr2;
	private String tel;
	private double latitude;
	private double longitude;
	private int map_level;
	public int getArea_code() {
		return area_code;
	}
	public void setArea_code(int area_code) {
		this.area_code = area_code;
	}
	public int getSi_gun_gu_code() {
		return si_gun_gu_code;
	}
	public void setSi_gun_gu_code(int si_gun_gu_code) {
		this.si_gun_gu_code = si_gun_gu_code;
	}
	public int getContent_type_id() {
		return content_type_id;
	}
	public void setContent_type_id(int content_type_id) {
		this.content_type_id = content_type_id;
	}
	public int getContent_id() {
		return content_id;
	}
	public void setContent_id(int content_id) {
		this.content_id = content_id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getImg1() {
		return img1;
	}
	public void setImg1(String img1) {
		this.img1 = img1;
	}
	public String getImg2() {
		return img2;
	}
	public void setImg2(String img2) {
		this.img2 = img2;
	}
	public String getAddr1() {
		return addr1;
	}
	public void setAddr1(String addr1) {
		this.addr1 = addr1;
	}
	public String getAddr2() {
		return addr2;
	}
	public void setAddr2(String addr2) {
		this.addr2 = addr2;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public double getLatitude() {
		return latitude;
	}
	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}
	public double getLongitude() {
		return longitude;
	}
	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}
	public int getMap_level() {
		return map_level;
	}
	public void setMap_level(int map_level) {
		this.map_level = map_level;
	}
	@Override
	public String toString() {
		return "TripDto [area_code=" + area_code + ", si_gun_gu_code=" + si_gun_gu_code + ", content_type_id="
				+ content_type_id + ", content_id=" + content_id + ", title=" + title + ", img1=" + img1 + ", img2="
				+ img2 + ", addr1=" + addr1 + ", addr2=" + addr2 + ", tel=" + tel + ", latitude=" + latitude
				+ ", longitude=" + longitude + ", map_level=" + map_level + "]";
	}
	
	

}
