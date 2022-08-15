package ru.netology;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.time.Duration;
import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Selenide.*;


public class Selenide {


    @BeforeEach
    void setUp() {
        open("http://localhost:9999");
    }


    @Test
    void shouldTestForm() {
        Configuration.holdBrowserOpen = true;
        String planningDate = DataGenerator.generateDate(3);
        $("[data-test-id=city] input").setValue("Казань");
        $("[data-test-id=date] input").doubleClick();
        $("[data-test-id=date] input").sendKeys(planningDate);
        $("[data-test-id=name] input").setValue("Арсений Орлов-Чесменский");
        $("[data-test-id=phone] input").setValue("+79514555555");
        $("[data-test-id=agreement]").click();
        $x("//*[contains(text(),'Забронировать')]").click();
        $("[data-test-id=notification]")
                .shouldHave(exactText("Успешно! Встреча успешно забронирована на " + planningDate), Duration.ofSeconds(15));
    }
}
