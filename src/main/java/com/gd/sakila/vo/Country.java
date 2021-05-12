package com.gd.sakila.vo;

import lombok.Data;

@Data //lombok으로 get,set,toString등 모두 자동으로 생성.
public class Country {
	private int countryId;
	private String country;
	private String lastUpdate;
}
