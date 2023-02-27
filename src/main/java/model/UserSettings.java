package model;
import io.restassured.response.ValidatableResponse;

public class UserSettings extends BaseURL {
    private final String PATH = "https://stellarburgers.nomoreparties.site/";
    public String accessToken = "";

    //@Step("User registration")
    public ValidatableResponse create(User user) {
        return getSpec
                .body(user)
                .when()
                .post("https://stellarburgers.nomoreparties.site/register")
                .then();
    }

    //@Step("User login")
    public ValidatableResponse login(UserCredentials credentials) {
        return getSpec
                .body(credentials)
                .when()
                .post(PATH +"login")
                .then();
    }

   // @Step("User deletion")
    public void delete(String user, String accessToken) {
        getSpec
                .header("authorization", accessToken)
                .pathParams("user", user)
                .when()
                .delete("https://stellarburgers.nomoreparties.site/api/auth" + "user?={user}")
                .then().log().all()
                .assertThat()
                .statusCode(202);
    }
}


