package model;

import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;
import static io.restassured.RestAssured.given;

public class UserSettings extends BaseURL {
    private final String REGISTER = "api/auth/register";
    private final String LOGIN = "api/auth/login";
    private final String DELETE = "api/auth/user?={user}";
    public String accessToken = "";

    @Step("User registration")
    public ValidatableResponse create(User user) {
        return given()
                .spec(getSpec())
                .body(user)
                .when()
                .post(REGISTER)
                .then();
    }

    @Step("User login")
    public ValidatableResponse login(UserCredentials credentials) {
        return given()
                .spec(getSpec())
                .body(credentials)
                .when()
                .post(LOGIN)
                .then();
    }

    @Step("User deletion")
    public ValidatableResponse delete() {
        if (this.accessToken.equals("")) {
            return given()
                    .spec(getSpec())
                    .auth().oauth2(accessToken)
                    .delete(DELETE)
                    .then().log().all()
                    .assertThat()
                    .statusCode(202);
        }
        return null;
    }
}


