package com.nikulin.tests;

import com.github.javafaker.Faker;
import com.nikulin.pages.RegistrationPage;
import org.junit.jupiter.api.Test;

import static com.nikulin.tests.TestData.*;

public class PracticeFormTests extends TestBase {

    RegistrationPage registrationPage = new RegistrationPage();
    Faker faker = new Faker();

    @Test
    public void practiceForm() {
        String firstName = faker.name().firstName();
        String lastName = faker.name().lastName();
        String email = faker.internet().emailAddress();
        String dayOfBirth = "30";
        String monthOfBirth = "July";
        String yearOfBirth = "1992";
        String userNumber = faker.phoneNumber().subscriberNumber(10);
        String subjects = "Maths"; // Значение должно быть из доступного списка
        String namePicture = "testPicture1.png";
        String currentAddress = faker.address().fullAddress();
        String state = "Haryana"; // Допустимы только значения из справочника
        String city = "Karnal"; // Допустимы только значения из справочника

        // Открытие формы и ее заполнение
        registrationPage.openPage()
                        .typeFirstName(firstName)
                        .typeLastName(lastName)
                        .typeEmail(email)
                        .selectGender(genderMale)
                        .typePhone(userNumber)
                        .setDate(dayOfBirth, monthOfBirth, yearOfBirth)
                        .selectSubject(subjects)
                        .selectHobby(hobbiesReading)
                        .selectHobby(hobbiesMusic)
                        .selectFile(namePicture)
                        .typeAddress(currentAddress)
                        .selectState(state)
                        .selectCity(city)
                        .submitForm();

        //Проверка результатов заполнения формы
        registrationPage.checkResultsTitle()
                        .checkResultsValue("Student Name", firstName + " " + lastName)
                        .checkResultsValue("Student Email", email)
                        .checkResultsValue("Gender", genderMale)
                        .checkResultsValue("Mobile", userNumber)
                        .checkResultsValue("Date of Birth", dayOfBirth + " " + monthOfBirth + "," + yearOfBirth)
                        .checkResultsValue("Subjects", subjects)
                        .checkResultsValue("Hobbies", hobbiesReading)
                        .checkResultsValue("Hobbies", hobbiesMusic)
                        .checkResultsValue("Picture", namePicture)
                        .checkResultsValue("Address", currentAddress)
                        .checkResultsValue("State and City", state + " " + city);
    }
}
