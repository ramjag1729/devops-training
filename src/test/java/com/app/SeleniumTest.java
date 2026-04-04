package com.app;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.junit.Test;

public class SeleniumTest {

    @Test
    public void testGoogleTitle() {

        System.setProperty("webdriver.chrome.driver", "/usr/bin/chromedriver");

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");

        WebDriver driver = new ChromeDriver(options);

        driver.get("https://www.google.com");

        String title = driver.getTitle();
        System.out.println("Page Title: " + title);

        assert title.contains("Google");

        driver.quit();
    }
}
