package com.nikulin.pages;

import com.codeborne.selenide.SelenideElement;
import com.nikulin.pages.components.CalendarComponent;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class RegistrationPage {

    private final String FORM_TITLE = "Student Registration Form";
    private final String RESULTS_TITLE = "Thanks for submitting the form";
    private SelenideElement
            formTitle = $(".practice-form-wrapper"),
            modalTitle =  $(".modal-title"),
            firstNameInput = $("#firstName"),
            lastNameInput = $("#lastName"),
            emailInput = $("#userEmail"),
            genderInput = $("#genterWrapper"),
            phoneInput = $("#userNumber"),
            subjectsInput = $("#subjectsInput"),
            hobbiesInput =  $("#hobbiesWrapper"),
            fileInput =  $("#uploadPicture"),
            addressInput =   $("#currentAddress"),
            stateInput =   $("#state").$("#react-select-3-input"),
            cityInput =   $("#city").$("#react-select-4-input"),
            sumbitInput =   $("#submit");

    public CalendarComponent calendar = new CalendarComponent();

    public RegistrationPage openPage() {
        open("/automation-practice-form");
        formTitle.shouldHave(text(FORM_TITLE));
        return this;
    }

    public RegistrationPage typeFirstName(String firstName) {
        firstNameInput.setValue(firstName);
        return this;
    }

    public RegistrationPage typeLastName(String lastName) {
        lastNameInput.setValue(lastName);
        return this;
    }

    public RegistrationPage typeEmail(String email) {
        emailInput.setValue(email);
        return this;
    }

    public RegistrationPage selectGender(String gender) {
        genderInput.$(byText(gender)).click();
        return this;
    }

    public RegistrationPage typePhone(String userNumber) {
        phoneInput.setValue(userNumber);
        return this;
    }

    public RegistrationPage setDate(String day, String month, String year) {
        calendar.setDate(day, month, year);
        return this;
    }

    public RegistrationPage selectSubject(String subjects) {
        subjectsInput.setValue(subjects).pressEnter();
        return this;
    }

    public RegistrationPage selectHobby(String hobbies) {
        hobbiesInput.$(byText(hobbies)).click();
        return this;
    }

    public RegistrationPage selectFile(String path) {
        fileInput.uploadFromClasspath(path);
        return this;
    }

    public RegistrationPage typeAddress(String currentAddress) {
        addressInput.setValue(currentAddress);
        return this;
    }

    public RegistrationPage selectState(String state) {
        stateInput.setValue(state).pressEnter();
        return this;
    }

    public RegistrationPage selectCity(String city) {
        cityInput.setValue(city).pressEnter();
        return this;
    }

    public RegistrationPage submitForm() {
        sumbitInput.click();
        return this;
    }

    public RegistrationPage checkResultsTitle(){
        modalTitle.shouldHave(text(RESULTS_TITLE));
        return this;
    }

    public RegistrationPage checkResultsValue(String key, String value) {
        $x("//td[text()='" + key + "']").parent()
                .shouldHave(text(value));
        return this;
    }
}
