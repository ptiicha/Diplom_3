package model;

import io.restassured.specification.RequestSpecification;
import static io.restassured.RestAssured.given;

public class BaseURL {
    protected final String URL = "https://stellarburgers.nomoreparties.site/api";
    protected final RequestSpecification getSpec = given()
            .baseUri (URL)
            .header("Content-type", "application/json");
}



