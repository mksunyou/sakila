package com.gd.sakila.vo;


import lombok.Data;

@Data
public class Film {
	private int filmId;
	private String title;
	private String description;
	private int releaseYear;
	private int languageId;
	private int originalLanguageId;
	private int rentalDuration;
	private double rentalRate;
	private int length;
	private double replacementCost;
	private String rating;
	private String specialFeatures; // 여러개의 값이 들어감.'Trailers','Commentaries','Deleted Scenes','Behind the Scenes'
	private String lastUpdate;
}
