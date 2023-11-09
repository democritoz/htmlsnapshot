package com.ziofront.htmlsnapshot;

import com.sun.tools.javac.Main;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class HtmlsnapshotApplication {

	public static void main(String[] args) throws Exception {

		Main.main(new String[] { "standalone", "--port", "4445" });

		WebDriverManager.chromedriver().browserInDocker()
				.remoteAddress("http://localhost:4445/wd/hub")
				.setup();


//		WebDriverManager.chromedriver().setup();
		SpringApplication.run(HtmlsnapshotApplication.class, args);
	}

}
