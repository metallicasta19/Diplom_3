import com.github.javafaker.Faker;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.apache.commons.lang3.RandomStringUtils;
import org.example.models.User;
import org.example.steps.UserSteps;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import static org.apache.http.HttpStatus.SC_ACCEPTED;
import static org.apache.http.HttpStatus.SC_OK;

public class BaseTest {
    protected WebDriver driver;
    String browserName = "chrome";
    User user = new User();
    UserSteps userSteps = new UserSteps();

    @Before
    public void setUpConfigure() {
        if (browserName.equalsIgnoreCase("chrome")) {
            driver = new ChromeDriver();
        } else if (browserName.equalsIgnoreCase("yandex")) {
            System.setProperty("webdriver.chrome.driver", "C:/Users/RYZEN3/.cache/selenium/chromedriver/win64/133.0.6943.98/chromedriver.exe");
            ChromeOptions options = new ChromeOptions();
            options.setBinary("C:/Users/RYZEN3/AppData/Local/Yandex/YandexBrowser/Application/browser.exe");
            driver = new ChromeDriver(options);
        }

        RestAssured.baseURI = "https://stellarburgers.nomoreparties.site";
        user = generateRandomUser();
    }

    private User generateRandomUser() {
        Faker faker = new Faker();
        String email = faker.internet().emailAddress();
        String password = RandomStringUtils.randomAlphabetic(6);
        String name = faker.name().firstName();

        user.setEmail(email);
        user.setPassword(password);
        user.setName(name);
        return user;
    }

    @After
    public void tearDownSteps() {
        driver.quit();

        Response loginResponse = userSteps.loginUser(user).extract().response();
        if (loginResponse.getStatusCode() == SC_OK) {
            String accessToken = loginResponse.path("accessToken");
            userSteps
                    .deleteUser(accessToken)
                    .and()
                    .statusCode(SC_ACCEPTED);
        }
    }
}
