package com.ziofront.htmlsnapshot;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class HtmlsnapshotApplication {

	public static void main(String[] args) {

		WebDriverManager.chromedriver().setup();
		SpringApplication.run(HtmlsnapshotApplication.class, args);
	}

}
