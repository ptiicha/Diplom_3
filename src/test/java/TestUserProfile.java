import pageObject.LoginPage;
import model.UserSettings;
import model.UserCredentials;
import model.User;
import com.codeborne.selenide.Selenide;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class TestUserProfile extends SetUp {

    private UserSettings userSet;
    private User userNew;
    private String accessToken;
    private boolean afterToBeLaunched;
    private UserCredentials credentials;


    @Before
    public void startup() {
        System.setProperty("selenide.browser", "chrome");
        afterToBeLaunched = true;
        userSet = new UserSettings();
        userNew = model.UserGenerator.getRandomUser();
        userSet.create(userNew);
        credentials = UserCredentials.from(userNew);

    }

    @Test
    //("Check user is able to get into the user profile successfully")
    public void checkDisplayUserProfile() {
        final boolean profileTextDisplayed = Selenide.open(LoginPage.URL, LoginPage.class)
                .userLogin(userNew)
                .clickProfileLinkUserLogged()
                .isProfileTextDisplayed();
        assertTrue(profileTextDisplayed);
    }

    @Test
    //("Check user is able to click the create burger link from the user profile page")
    public void successfullyDisplayCreateBurgerTextByClickingTheCreateBurgerLink() {
        final boolean createBurgerTextDisplayed = Selenide.open(LoginPage.URL, LoginPage.class)
                .userLogin(userNew)
                .clickProfileLinkUserLogged()
                .clickCreateBurger()
                .isCreateBurgerTextDisplayed();
        assertTrue(createBurgerTextDisplayed);
    }

    @Test
    //("Check user is able to click the stellar burger logo from the user profile page")
    public void successfullyDisplayCreateBurgerTextByClickingTheBurgerLogo() {
        final boolean createBurgerTextDisplayed = Selenide.open(LoginPage.URL, LoginPage.class)
                .userLogin(userNew)
                .clickProfileLinkUserLogged()
                .clickCreateBurger()
                .isCreateBurgerTextDisplayed();
        assertTrue(createBurgerTextDisplayed);
    }

    @Test //("Check user is able to logout successfully")
    public void successfullyLogoutUser() {
        final boolean userLoginTextDisplayed = Selenide.open(LoginPage.URL, LoginPage.class)
                .userLogin(userNew)
                .clickProfileLinkUserLogged()
                .clickLogoutButton()
                .isUserLoginTextDisplayed();
        assertTrue(userLoginTextDisplayed);
    }

    @Test
   //("Check user is able to login successfully via the login button from the main page")
    public void successfullyLoginUserUsingLoginButtonOnTheMainPage() {
        final boolean orderButtonDisplayed = Selenide.open(pageObject.MainPage.URL, pageObject.MainPage.class)
                .clickLoginButton()
                .userLogin(userNew)
                .isOrderButtonDisplayed();

        assertTrue(orderButtonDisplayed);
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
