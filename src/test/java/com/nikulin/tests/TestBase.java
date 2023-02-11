package com.nikulin.tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import com.nikulin.config.CredentialsConfig;
import com.nikulin.helpers.Attach;
import io.qameta.allure.selenide.AllureSelenide;
import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.remote.DesiredCapabilities;

public class TestBase {

    @BeforeAll
    public static void beforeAll() {
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide());

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("enableVNC", true);
        capabilities.setCapability("enableVideo", true);

        Configuration.browserCapabilities = capabilities;
        Configuration.baseUrl = "https://demoqa.com";
        Configuration.browserSize = "500x1000"; // Если есть проблема с отсутствием кнопки Submit, то необходимо подобрать разрешение при котором кнопка будет отображаться
        //Configuration.browser = System.getProperty("browser");
        //Configuration.browserVersion = System.getProperty("version");
        //Configuration.browserSize = System.getProperty("size"); // Если есть проблема с отсутствием кнопки Submit, то необходимо подобрать разрешение при котором кнопка будет отображаться
        Configuration.remote = getSelenoidUrl();
        //Configuration.remote = "https://user1:1234@selenoid.autotests.cloud/wd/hub/";
    }

    private static String getSelenoidUrl() {
        CredentialsConfig credentials = ConfigFactory.create(CredentialsConfig.class);
        return "https://" + credentials.login() + ":" + credentials.password() + "@" + System.getProperty("selenoidUrl");
    }

    @AfterEach
    public void tearDown() {
        Attach.screenshotAs("Last screenshot");
        Attach.pageSource();
        Attach.browserConsoleLogs();
        Attach.addVideo();
    }
}
