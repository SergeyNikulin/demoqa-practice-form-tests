package com.nikulin.tests;

import com.nikulin.pages.RegistrationPage;
import org.junit.jupiter.api.Test;

import static com.nikulin.tests.TestData.*;

public class PracticeFormTests extends TestBase {

    RegistrationPage registrationPage = new RegistrationPage();

    @Test
    public void practiceForm() {
        String firstName = "Petr";
        String lastName = "Petrov";
        String email = "petrov@test.com";
        String dayOfBirth = "30";
        String monthOfBirth = "July";
        String yearOfBirth = "1992";
        String userNumber = "7912345678";
        String subjects = "Maths"; // Значение должно быть из доступного списка
        String namePicture = "testPicture1.png";
        String currentAddress = "city: Testes. street: Homework" ;
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
