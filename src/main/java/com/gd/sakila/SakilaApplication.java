package com.gd.sakila;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

@ServletComponentScan // login filer
@SpringBootApplication //@ComponentScan("com.gd.service") //springbootapp에 이줄이 포함되어 있음 따라서 삭제한것. 따라서 com.gd.service를 벗어나면 scan 불가능
@EnableScheduling // Bean만 만든다고 되는 것이 아니라 sakilaapp이 @scheduled을 찾아서 만들 준비.

public class SakilaApplication {

	public static void main(String[] args) {
		SpringApplication.run(SakilaApplication.class, args);
	}

}