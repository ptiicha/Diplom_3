package pageObject;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import io.qameta.allure.Step;

public class PasswordRecoveryPage {
    final public static String URL = MainPage.URL + "forgot-password";

    // link
    @FindBy(how = How.XPATH,using = ("//a[text()='Войти']"))
    private SelenideElement loginLink;

    // login method
    @Step("Login from password recovery page")
    public LoginPage clickLoginLink() {
        loginLink.click();
        return Selenide.page(LoginPage.class);
    }
}
