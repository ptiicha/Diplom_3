import pageObject.*;
import model.UserSettings;
import model.UserCredentials;
import model.*;
import com.codeborne.selenide.Selenide;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertTrue;

public class TestUserLogin extends SetUp {

    private UserSettings userSet;
    private User userNew;
    private UserCredentials credentials;
    private String accessToken;
    private boolean afterToBeLaunched;

    @Before
    public void setUp() {
        userSet = new UserSettings();
        userNew = UserGenerator.getRandomUser();
        userSet.create(userNew);
        credentials = UserCredentials.from(userNew);
        accessToken = userSet.login(credentials)
                .log().all()
                .assertThat()
                .statusCode(200)
                .extract()
                .path("accessToken");
    }

    @Test
    //@DisplayName("Check user is able to login successfully via the profile link from the main page")
    public void successfullyLoginUserUsingProfileLinkOnTheMainPage() {
        final boolean orderButtonDisplayed = Selenide.open(MainPage.URL, MainPage.class)
                .clickProfileLink()
                .userLogin(userNew)
                .isOrderButtonDisplayed();

        assertTrue(orderButtonDisplayed);
    }


    @Test
    //@DisplayName("Check user is able to login successfully via the login link from the register page")
    public void successfullyLoginUserUsingLoginLinkOnTheRegisterPage() {
        final boolean orderButtonDisplayed = Selenide.open(RegistrationPage.URL, RegistrationPage.class)
                .clickLoginLink()
                .userLogin(userNew)
                .isOrderButtonDisplayed();

        assertTrue(orderButtonDisplayed);
    }

    @Test
    //@DisplayName("Check user is able to login successfully via the login link from the restore password page")
    public void successfullyLoginUserUsingLoginLinkOnTheRestorePasswordPage() {
        final boolean orderButtonDisplayed = Selenide.open(PasswordRecoveryPage.URL, PasswordRecoveryPage.class)
                .clickLoginLink()
                .userLogin(userNew)
                .isOrderButtonDisplayed();

        assertTrue(orderButtonDisplayed);
    }


    @After
    public void teardown() {
        if (!afterToBeLaunched) {
            return;
        }
        userSet.delete(userNew.getEmail(), accessToken);
    }
}
