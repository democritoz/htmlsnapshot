package com.ziofront.htmlsnapshot.controller;

import com.api2pdf.client.Api2PdfClient;
import com.api2pdf.models.Api2PdfResponse;
import io.github.bonigarcia.wdm.WebDriverManager;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
public class TestController {


    private static final String URL = "https://map.kakao.com/?urlX=499319&urlY=1129617&urlLevel=3&itemId=8124186&q=%ED%95%98%EB%82%98%EC%9D%80%ED%96%89%20%EC%9D%84%EC%A7%80%EB%A1%9C%EA%B8%88%EC%9C%B5%EC%84%BC%ED%84%B0&srcid=8124186&map_type=TYPE_MAP";
    @GetMapping("/test001")
    public ResponseEntity<Map<String, Object>> test() {

        Map<String, Object> responseMap = new HashMap<>();
        Api2PdfClient api2PdfClient = new Api2PdfClient("1cb91cd0-93c8-4158-8539-f9ce1302b56e");

        try {
            Api2PdfResponse response = api2PdfClient.chromeUrlToImage(URL               , true, "testimg");

            responseMap.put("response", response.getFile());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


        responseMap.put("hello", "world");

        return ResponseEntity.ok(responseMap);
    }


    @GetMapping("/test002")
    public ResponseEntity<Map<String, Object>> test002() {

        Map<String, Object> responseMap = new HashMap<>();

        WebDriver driver = new ChromeDriver();

        driver.get(URL);

        File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        responseMap.put("scrFile", scrFile);

        try {
            FileUtils.copyFile(scrFile, new File("./example.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        driver.quit();


        responseMap.put("hello", "world");

        return ResponseEntity.ok(responseMap);
    }



    @GetMapping(value = "/test003", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    public @ResponseBody byte[] test003() throws IOException {

        WebDriver driver = new ChromeDriver();

        driver.get(URL);

//        File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
//        log.debug("scrFile.getName()=[{}]", scrFile.getName());
//        InputStream in = getClass()
//                .getResourceAsStream(scrFile.getName());
//        return IOUtils.toByteArray(in);
        return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);

    }
}
