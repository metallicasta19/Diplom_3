import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.example.models.User;
import org.example.pages.ConstructorPage;
import org.example.pages.LoginPage;
import org.example.pages.UserProfilePage;
import org.example.steps.UserSteps;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.Random;

import static org.apache.http.HttpStatus.SC_ACCEPTED;
import static org.apache.http.HttpStatus.SC_OK;
import static org.example.utils.URLs.*;
import static org.junit.Assert.assertEquals;

public class UserProfileTest {
    private WebDriver driver;
    private String email;
    private String password;

    @Before
    public void setUp() {
        driver = new ChromeDriver();

        RestAssured.baseURI = "https://stellarburgers.nomoreparties.site";

        Random random = new Random();
        String name = "name" + random.nextInt(10000000);
        email = "something" + random.nextInt(10000000) + "@yandex.ru";
        password = "password" + random.nextInt(10000000);

        User user = new User();
        user.setEmail(email);
        user.setPassword(password);
        user.setName(name);

        UserSteps userSteps = new UserSteps();
        userSteps.createUser(user);

        LoginPage loginPage = new LoginPage(driver);
        loginPage.openPage();
        loginPage.waitLoginPage();
        loginPage.signIn(email, password);

        ConstructorPage constructorPage = new ConstructorPage(driver);
        constructorPage.waitMainPage();
        constructorPage.clickUserProfileButton();

        UserProfilePage userProfilePage = new UserProfilePage(driver);
        userProfilePage.waitUserProfilePage();
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
    public void constructorToProfileNavigationTest() {
        assertEquals(USER_PROFILE_URL, driver.getCurrentUrl());
    }

    @Test
    public void profileToConstructorByButtonNavigationTest() {
        UserProfilePage userProfilePage = new UserProfilePage(driver);
        userProfilePage.clickConstructorButton();

        ConstructorPage constructorPage = new ConstructorPage(driver);
        constructorPage.waitMainPage();
        assertEquals(CONSTRUCTOR_URL, driver.getCurrentUrl());
    }

    @Test
    public void profileToConstructorByLogoNavigationTest() {
        UserProfilePage userProfilePage = new UserProfilePage(driver);
        userProfilePage.clickLogoButton();

        ConstructorPage constructorPage = new ConstructorPage(driver);
        constructorPage.waitMainPage();
        assertEquals(CONSTRUCTOR_URL, driver.getCurrentUrl());
    }

    @Test
    public void exitFromProfileTest() {
        UserProfilePage userProfilePage = new UserProfilePage(driver);
        userProfilePage.clickExitButton();

        LoginPage loginPage = new LoginPage(driver);
        loginPage.waitLoginPage();
        assertEquals(LOGIN_URL, driver.getCurrentUrl());
    }
}
