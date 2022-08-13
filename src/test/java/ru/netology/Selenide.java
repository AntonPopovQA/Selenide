package ru.netology;

import com.codeborne.selenide.Configuration;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;


import java.time.Duration;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Selenide.*;

public class Selenide {
    WebDriver driver;

    @BeforeAll
    static void setUpAll() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeEach
    void setUp() {
        driver = new ChromeDriver();
        open("http://localhost:9999");
    }

    @AfterEach
    void tearDown() {
        driver.quit();
    }

    @Test
    void shouldTestForm() {
        Configuration.holdBrowserOpen = true;

        $("[data-test-id=city] input").setValue("Казань");
        $("[data-test-id=date] input").doubleClick();
        $("[data-test-id=date] input").sendKeys("18.08.2022");
        $("[data-test-id=name] input").setValue("Арсений Орлов-Чесменский");
        $("[data-test-id=phone] input").setValue("+79514555555");
        $("[data-test-id=agreement]").click();
        $x("//*[contains(text(),'Забронировать')]").click();
        $("[data-test-id=notification]")
                .shouldHave(exactText("Успешно! Встреча успешно забронирована на 18.08.2022"), Duration.ofSeconds(15));
    }
}
