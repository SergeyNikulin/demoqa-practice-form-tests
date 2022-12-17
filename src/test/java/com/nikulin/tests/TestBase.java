package com.nikulin.tests;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;

public class TestBase {

    @BeforeAll
    public static void beforeAll() {
        Configuration.baseUrl = "https://demoqa.com";
        Configuration.browserSize = "500x1000"; // Если есть проблема с отсутствием кнопки Submit, то необходимо подобрать разрешение при котором кнопка будет отображаться
    }
}
