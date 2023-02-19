package pageObject;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import static com.codeborne.selenide.Condition.visible;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import io.qameta.allure.Step;

public class ProfilePage {
    final public static String URL = "https://stellarburgers.nomoreparties.site/profile";

    // локатор описания профиля (В этом разделе вы можете изменить свои персональные данные)
    @FindBy(how = How.XPATH,using = ("//p[text()='В этом разделе вы можете изменить свои персональные данные']"))
    private SelenideElement profileText;

    // Проверка видимости описания профиля
    public boolean isProfileTextDisplayed() {
        return profileText.shouldBe(visible).isDisplayed();
    }

    // локатор кнопки Выход
    @FindBy(how = How.XPATH,using = ("//button[text()='Выход']"))
    private SelenideElement logoutButton;

    // Выход из аккаунта
    @Step("Exit button click")
    public LoginPage clickLogoutButton() {
        logoutButton.click();
        return Selenide.page(LoginPage.class);
    }

    // локатор кнопки История заказов
    @FindBy(how = How.XPATH,using = ("//button[text()='История заказов']"))
    private SelenideElement historyButton;

    // локатор кнопки Профиль
    @FindBy(how = How.XPATH,using = ("//button[text()='Профиль']"))
    private SelenideElement profileButton;

    // локатор конструктора бургеров
    @FindBy(how = How.XPATH,using = ("//p[text()='Конструктор']"))
    private SelenideElement createBurger;

    // Клик по конструктору
    @Step("Constructor click")
    public MainPage clickCreateBurger() {
        createBurger.click();
        return Selenide.page(MainPage.class);
    }

    // локатор ленты заказов
    @FindBy(how = How.XPATH,using = ("//p[text()='Лента Заказов']"))
    private SelenideElement ordersList;

    // Клик по ленте заказов
    @Step("List of orders click")
    public MainPage clickOrderList() {
        createBurger.click();
        return Selenide.page(MainPage.class);
    }

    // локатор логотипа <Stellar Burgers>
    @FindBy(how = How.XPATH,using =("//div[@class='AppHeader_header__logo__2D0X2']/a"))
    private SelenideElement burgerLogo;



}
