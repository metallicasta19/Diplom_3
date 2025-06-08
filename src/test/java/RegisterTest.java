import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.example.models.User;
import org.example.pages.RegisterPage;
import org.example.steps.UserSteps;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.Random;

import static org.apache.http.HttpStatus.SC_ACCEPTED;
import static org.apache.http.HttpStatus.SC_OK;
import static org.example.utils.URLs.LOGIN_URL;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class RegisterTest {
    private WebDriver driver;

    private String name;
    private String email;
    private String password;

    @Before
    public void setUp() {
        driver = new ChromeDriver();

        RegisterPage registerPage = new RegisterPage(driver);
        registerPage.openPage();
        registerPage.waitRegisterPage();
    }

    @After
    public void tearDown() {
        RestAssured.baseURI = "https://stellarburgers.nomoreparties.site";

        User user = new User();
        user.setEmail(email);
        user.setPassword(password);

        UserSteps userSteps = new UserSteps();
        Response loginResponse = userSteps.loginUser(user).extract().response();

        if (loginResponse.getStatusCode() == SC_OK) {
            String accessToken = loginResponse.path("accessToken");
            userSteps
                    .deleteUser(accessToken)
                    .and()
                    .statusCode(SC_ACCEPTED);
        }
        driver.quit();
    }

    @Test
    public void createNewUserTest() {
        Random random = new Random();
        name = "name" + random.nextInt(10000000);
        email = "something" + random.nextInt(10000000) + "@yandex.ru";
        password = "password" + random.nextInt(10000000);

        RegisterPage registerPage = new RegisterPage(driver);
        registerPage.createNewUser(name, email, password);
        registerPage.clickLoginButton();
        assertEquals(LOGIN_URL, driver.getCurrentUrl());
    }

    @Test
    public void createNewUserWithInvalidPasswordTest() {
        Random random = new Random();
        name = "name" + random.nextInt(10000000);
        email = "something" + random.nextInt(10000000) + "@yandex.ru";
        password = "p" + random.nextInt(1000);

        RegisterPage registerPage = new RegisterPage(driver);
        registerPage.createNewUser(name, email, password);
        assertTrue(registerPage.isInvalidPasswordMessageVisible());
    }
}
