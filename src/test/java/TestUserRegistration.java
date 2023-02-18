import com.codeborne.selenide.Selenide;
import io.qameta.allure.junit4.DisplayName;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertTrue;
import pageObject.PasswordRecoveryPage;
import pageObject.MainPage;
import pageObject.RegistrationPage;
import io.restassured.response.ValidatableResponse;
import model.UserSettings;
import model.UserGenerator;
import model.UserCredentials;
import model.User;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import io.qameta.allure.Step;

public class TestUserRegistration {

    private UserSettings userSet;
    private User userNew;
    private String accessToken;

    @Before
    public void setUp() {
        System.setProperty("selenide.browser", "chrome");
        userSet = new UserSettings();
        userNew = UserGenerator.getRandomUser();
        UserCredentials credentials = UserCredentials.from(userNew);
        userSet.create(userNew);
        accessToken = userSet.login(credentials)
                .log().all()
                .assertThat()
                .statusCode(200)
                .extract()
                .path("accessToken");
    }

    @Step("Login")
    public ValidatableResponse login(UserCredentials credentials) {
        return given()
                .spec(getSpec())
                .body(credentials)
                .when()
                .post(PATH +"login");
                .then()
                .assertThat()
                .statusCode(200);
    }

    @After
    public void teardown() {
        Selenide.closeWindow();
    }
}
