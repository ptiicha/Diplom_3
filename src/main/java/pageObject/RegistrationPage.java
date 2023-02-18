package pageObject;

import io.qameta.allure.Step;
import model.User;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import static com.codeborne.selenide.Condition.visible;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class RegistrationPage {
    final public static String URL = "https://stellarburgers.nomoreparties.site/register";

    // локатор поля имени
    @FindBy(how = How.XPATH,using =("//label[text()='Имя']//following-sibling::input"))
    private SelenideElement nameInputField;

    // локатор поля email
    @FindBy(how = How.XPATH,using =("//label[text()='Email']//following-sibling::input"))
    private SelenideElement emailInputField;

    // локатор поля пароля
    @FindBy(how = How.XPATH,using =("//input[@type='password']"))
    private SelenideElement passwordInputField;

    // локатор кнопки "Зарегистрироваться"
    @FindBy(how = How.XPATH,using = ("//button[text()='Зарегистрироваться']"))
    private SelenideElement registerButton;

    // локатор аллерта "Некорректный пароль"
    @FindBy(how = How.XPATH,using = ("//p[text()='Некорректный пароль']"))
    private SelenideElement incorrectPasswordWarning;

    // Регистрация пользователя с неверным email
    @Step("Registration failed - incorrect password")
    public RegistrationPage registerUserFailedIncorrectPass(User user) {
        inputName(user.getName());
        inputEmail(user.getEmail());
        inputPassword("shd");
        clickRegisterButton();
        return this;
    }

    // Вызов подсказки "Некорректный пароль"
    public boolean isIncorrectPassDisplayed() {
        return incorrectPasswordWarning.shouldBe(visible).isDisplayed();
    }

    // локатор ссылки "Войти"
    @FindBy(how = How.XPATH,using = ("//a[text()='Войти']"))
    private SelenideElement loginLink;

    // заполнение поля "Имя"
    @Step("Name input")
    public RegistrationPage inputName(String name) {
        nameInputField.sendKeys(name);
        return this;
    }

    // заполнение поля "Email"
    @Step("Email input")
    public RegistrationPage inputEmail(String email) {
        emailInputField.sendKeys(email);
        return this;
    }

    // ввод пароля
    @Step("Password input")
    public RegistrationPage inputPassword(String password) {
        passwordInputField.sendKeys(password);
        return this;
    }

    // Клик Зарегистрироваться
    @Step("Registration click")
    public LoginPage clickRegisterButton() {
        registerButton.click();
        return Selenide.page(LoginPage.class);
    }

    // регистрация нового пользователя
    @Step("Registration form")
    public LoginPage registrationUser(User user) {
        inputName(user.getName());
        inputEmail(user.getEmail());
        inputPassword(user.getPassword());
        clickRegisterButton();
        return Selenide.page(LoginPage.class);
    }

    // Клик "Войти"
    @Step("Entrance link")
    public LoginPage clickLoginLink() {
        loginLink.click();
        return Selenide.page(LoginPage.class);
    }
}
