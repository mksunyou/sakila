package com.gd.sakila.vo;

import lombok.Data;

// view 데이터를 가져올때는 sql에서 사용된 view의 값들을 vo로 먼저 생성.
@Data
public class StaffViewList {
	private int ID;
	private String name;
	private String address;
	private String zipCode;
	private String phone;
	private String city;
	private String country;
	private int SID;
}
