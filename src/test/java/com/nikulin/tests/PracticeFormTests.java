package com.nikulin.tests;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.File;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class PracticeFormTests {

    private final String urlPagePracticeForm = "https://demoqa.com/automation-practice-form";
    private static final String filesStorage = "src/test/java/com/nikulin/files/";
    private String firstName = "Petr";
    private String lastName = "Petrov";
    private String email = "petrov@test.com";
    private String gender = "Male"; // Допустимы только значения: Male, Female, Other
    private String dayOfBirth = "30";
    private String monthOfBirth = "July";
    private String yearOfBirth = "1992";
    private String userNumber = "7912345678";
    private String subjects = "Maths"; // Значение должно быть из доступного списка
    private String hobbiesReading = "Reading"; // Допустимы только значения: Sports, Reading, Music
    private String hobbiesMusic = "Music"; // Допустимы только значения: Sports, Reading, Music. Сделать одной переменной - массив/коллекция
    private String namePicture = "testPicture1.png"; // Допустимы только значения: Sports, Reading, Music. Сделать одной переменной - массив/коллекция
    private String currentAddress = "city: Testes. street: Homework" ;
    private String state = "Haryana"; // Допустимы только значения из справочника
    private String city = "Karnal"; // Допустимы только значения из справочника

    @BeforeAll
    public static void beforeAll() {
        Configuration.browserSize = "500x1000"; // Если есть проблема с отсутствием кнопки Submit, то необходимо подобрать разрешение при котором кнопка будет отображаться
    }

    @Test
    public void practiceForm() {
        // Открытие страницы формы
        open(urlPagePracticeForm);

        // Заполнение формы
        $("#firstName").setValue(firstName);
        $("#lastName").setValue(lastName);
        $("#userEmail").setValue(email);
        $("#genterWrapper").$(byText(gender)).click();
        $("#userNumber").setValue(userNumber);
        $("#dateOfBirthInput").click();
        $(".react-datepicker__year-select").selectOption(yearOfBirth);
        $(".react-datepicker__month-select").selectOption(monthOfBirth);
        $(".react-datepicker__day--0" + dayOfBirth + ":not(.react-datepicker__day--outside-month").click();
        $("#subjectsInput").setValue(subjects).pressEnter();
        $("#hobbiesWrapper").$(byText(hobbiesReading)).click();
        $("#hobbiesWrapper").$(byText(hobbiesMusic)).click();
        $("#uploadPicture").uploadFile(new File(filesStorage + namePicture));
        $("#currentAddress").setValue(currentAddress);
        $("#state").$("#react-select-3-input").setValue(state).pressEnter();
        $("#city").$("#react-select-4-input").setValue(city).pressEnter();
        $("#submit").click();

        // Проверка значений поп-ап
        $(".table-responsive").$(byText("Student Name")).parent().shouldHave(text(firstName + " " + lastName));
        $(".table-responsive").$(byText("Student Email")).parent().shouldHave(text(email));
        $(".table-responsive").$(byText("Gender")).parent().shouldHave(text(gender));
        $(".table-responsive").$(byText("Mobile")).parent().shouldHave(text(userNumber));
        $(".table-responsive").$(byText("Date of Birth")).parent().shouldHave(text(dayOfBirth + " " + monthOfBirth + "," + yearOfBirth));
        $(".table-responsive").$(byText("Subjects")).parent().shouldHave(text(subjects));
        $(".table-responsive").$(byText("Hobbies")).parent().shouldHave(text(hobbiesReading + ", " + hobbiesMusic));
        $(".table-responsive").$(byText("Picture")).parent().shouldHave(text(namePicture));
        $(".table-responsive").$(byText("Address")).parent().shouldHave(text(currentAddress));
        $(".table-responsive").$(byText("State and City")).parent().shouldHave(text(state + " " + city));
    }
}
