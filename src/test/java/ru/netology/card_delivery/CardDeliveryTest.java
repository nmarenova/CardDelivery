package ru.netology.card_delivery;

import org.junit.jupiter.api.Test;


import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;


import org.openqa.selenium.Keys;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;


public class CardDeliveryTest {

    @Test
    void shouldCardDelivery() {
        String date = LocalDate.now().plusDays(3).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        open("http://localhost:9999/");
        $("[data-test-id='city'] input").setValue("Казань");
        $("[data-test-id= date] input").sendKeys(Keys.CONTROL + "a");
        $("[data-test-id=date] input").sendKeys(Keys.DELETE);
        $("[data-test-id= date] input").sendKeys(date);
        $("[data-test-id=name] input").setValue("Петров Иван");
        $("[data-test-id=phone] input").setValue("+79622347579");
        $("[data-test-id=agreement]").click();
        $$("button").find(exactText("Забронировать")).click();
        $(" [data-test-id=notification]").waitUntil(visible, 1500000)
                .shouldHave(text("Успешно! Встреча успешно забронирована на " + date));

    }

    @Test
    void shouldNotSendIncorrectCity() {
        String date = LocalDate.now().plusDays(3).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        open("http://localhost:9999/");
        $("[data-test-id='city'] input").setValue("Осло");
        $("[data-test-id= date] input").sendKeys(Keys.CONTROL + "a");
        $("[data-test-id=date] input").sendKeys(Keys.DELETE);
        $("[data-test-id= date] input").sendKeys(date);
        $("[data-test-id=name] input").setValue("Петров Иван");
        $("[data-test-id=phone] input").setValue("+79622347579");
        $("[data-test-id=agreement]").click();
        $$("button").find(exactText("Забронировать")).click();
        $("[data-test-id=city] .input__sub").shouldHave(exactText("Доставка в выбранный город недоступна"));

    }

    @Test
    void shouldIncorrectDate() {
        String date = LocalDate.now().plusDays(2).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        open("http://localhost:9999/");
        $("[data-test-id='city'] input").setValue("Казань");
        $("[data-test-id= date] input").sendKeys(Keys.CONTROL + "a");
        $("[data-test-id=date] input").sendKeys(Keys.DELETE);
        $("[data-test-id= date] input").sendKeys(date);
        $("[data-test-id=name] input").setValue("Петров Иван");
        $("[data-test-id=phone] input").setValue("+79622347579");
        $("[data-test-id=agreement]").click();
        $$("button").find(exactText("Забронировать")).click();
        $("[data-test-id=date] .input__sub").shouldHave(exactText("Заказ на выбранную дату невозможен"));

    }

    @Test
    void shouldRequiredField() {
        String date = LocalDate.now().plusDays(3).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        open("http://localhost:9999/");
        $("[data-test-id='city'] input").setValue("Казань");
        $("[data-test-id= date] input").sendKeys(Keys.CONTROL + "a");
        $("[data-test-id=date] input").sendKeys(Keys.DELETE);
        $("[data-test-id= date] input").sendKeys(date);
        $("[data-test-id=name] input").setValue("");
        $("[data-test-id=phone] input").setValue("+79622347579");
        $("[data-test-id=agreement]").click();
        $$("button").find(exactText("Забронировать")).click();
        $("[data-test-id=name] .input__sub").shouldHave(exactText("Поле обязательно для заполнения"));

    }

    @Test
    void shouldIncorrectName() {
        String date = LocalDate.now().plusDays(3).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        open("http://localhost:9999/");
        $("[data-test-id='city'] input").setValue("Казань");
        $("[data-test-id= date] input").sendKeys(Keys.CONTROL + "a");
        $("[data-test-id=date] input").sendKeys(Keys.DELETE);
        $("[data-test-id= date] input").sendKeys(date);
        $("[data-test-id=name] input").setValue("Petrov Ivan");
        $("[data-test-id=phone] input").setValue("+79622347579");
        $("[data-test-id=agreement]").click();
        $$("button").find(exactText("Забронировать")).click();
        $("[data-test-id=name] .input__sub")
                .shouldHave(exactText("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы."));

    }

    @Test
    void shouldNotSendIncorrectPhone() {
        String date = LocalDate.now().plusDays(3).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        open("http://localhost:9999/");
        $("[data-test-id='city'] input").setValue("Казань");
        $("[data-test-id= date] input").sendKeys(Keys.CONTROL + "a");
        $("[data-test-id=date] input").sendKeys(Keys.DELETE);
        $("[data-test-id= date] input").sendKeys(date);
        $("[data-test-id=name] input").setValue("Петров Иван");
        $("[data-test-id=phone] input").setValue("+796223475794");
        $("[data-test-id=agreement]").click();
        $$("button").find(exactText("Забронировать")).click();
        $("[data-test-id=phone] .input__sub")
                .shouldHave(exactText("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678."));

    }

    @Test
    void shouldNotlickAgreement() {
        String date = LocalDate.now().plusDays(3).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        open("http://localhost:9999/");
        $("[data-test-id='city'] input").setValue("Казань");
        $("[data-test-id= date] input").sendKeys(Keys.CONTROL + "a");
        $("[data-test-id=date] input").sendKeys(Keys.DELETE);
        $("[data-test-id= date] input").sendKeys(date);
        $("[data-test-id=name] input").setValue("Петров Иван");
        $("[data-test-id=phone] input").setValue("+79622347579");
        $$("button").find(exactText("Забронировать")).click();
        $("[data-test-id=agreement] .checkbox__text")
                .shouldHave(exactText("Я соглашаюсь с условиями обработки и использования моих персональных данных"));

    }
}


