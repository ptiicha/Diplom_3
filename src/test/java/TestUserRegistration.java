import com.codeborne.selenide.Selenide;
import static org.junit.Assert.assertTrue;
import model.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import pageObject.RegistrationPage;

public class TestUserRegistration extends SetUp {

    private UserSettings userSet;
    private User userNew;
    private UserCredentials credentials;
    private boolean afterToBeLaunched;

    @Before
    public void startup() {
        System.setProperty("selenide.browser", "chrome");
        afterToBeLaunched = true;
        userSet = new UserSettings();
        userNew = UserGenerator.getRandomUser();
        userSet.create(userNew);
        credentials = UserCredentials.from(userNew);
    }


    @Test
    //@DisplayName("Check registering a new user with an incorrect pass, with less than 6 symbols, fails")
    public void registrationUserFailedIncorrectPass() {
        userNew.setPassword("five");
        final boolean incorrectPasswordWarningDisplayed = Selenide.open(RegistrationPage.URL, RegistrationPage.class)
                .clickRegistrationLink()
                .registrationUserFailedIncorrectPass(userNew)
                .isIncorrectPassDisplayed();
        assertTrue(incorrectPasswordWarningDisplayed);
        afterToBeLaunched = false;
    }

    @After
    public void teardown() {
        if (!afterToBeLaunched) {
            return;
        }
        String accessToken = userSet.login(credentials)
                .log().all()
                .extract()
                .path("accessToken");
        userSet.delete(userNew.getEmail(), accessToken);
    }
}
