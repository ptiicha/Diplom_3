package model;

import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;
import static io.restassured.RestAssured.given;

public class UserSettings extends BaseURL {
    private final String PATH = "api/auth/";
    public String accessToken = "";

    @Step("User registration")
    public ValidatableResponse create(User user) {
        return given()
                .spec(getSpec())
                .body(user)
                .when()
                .post(PATH + "register")
                .then();
    }

    @Step("User login")
    public ValidatableResponse login(UserCredentials credentials) {
        return given()
                .spec(getSpec())
                .body(credentials)
                .when()
                .post(PATH +"login")
                .then();
    }

    @Step("User deletion")
    public ValidatableResponse delete() {
        if (this.accessToken.equals("")) {
            return given()
                    .spec(getSpec())
                    .auth().oauth2(accessToken)
                    .delete(PATH + "user?={user}")
                    .then().log().all()
                    .assertThat()
                    .statusCode(202);
        }
        return null;
    }
}


