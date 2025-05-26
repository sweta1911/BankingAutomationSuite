package tests.api;

import tests.ui.LoginTest;
import utils.ConfigReader;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utils.LoggerUtil;

public class AccountAPITest {


    private static final org.apache.logging.log4j.Logger log = LoggerUtil.getLogger(LoginTest.class);


    @BeforeClass
    public void setUp() {
//        RestAssured.baseURI = "https://parabank.parasoft.com/parabank/services/bank";
        RestAssured.baseURI = ConfigReader.get("base.url");
    }

    @Test
    public void testGetAccountDetails() {
        int accountId = 13344; // You can change based on test account

        Response response = RestAssured
                .given()
                .accept(ContentType.JSON)
                .when()
                .get("/accounts/" + accountId);

        response.then().statusCode(200);

        String balance = response.jsonPath().getString("balance");
        Assert.assertNotNull(balance, "Balance should not be null");
    }
}
