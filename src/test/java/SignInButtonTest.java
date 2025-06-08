import org.example.pages.ConstructorPage;
import org.example.pages.RegisterPage;
import org.example.pages.ResetPasswordPage;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.example.utils.URLs.LOGIN_URL;
import static org.junit.Assert.assertEquals;

public class SignInButtonTest {

    private WebDriver driver;

    @Before
    public void setUp() {
        driver = new ChromeDriver();
    }

    @After
    public void tearDown() {
        driver.quit();
    }

    @Test
    public void checkSignInFromMainPageTest() {
        ConstructorPage constructorPage = new ConstructorPage(driver);
        constructorPage.openPage();
        constructorPage.waitMainPage();

        constructorPage.clickSignInButton();
        assertEquals(LOGIN_URL, driver.getCurrentUrl());
    }

    @Test
    public void checkSignInFromUserProfile() {
        ConstructorPage constructorPage = new ConstructorPage(driver);
        constructorPage.openPage();
        constructorPage.waitMainPage();

        constructorPage.clickUserProfileButton();
        assertEquals(LOGIN_URL, driver.getCurrentUrl());
    }

    @Test
    public void checkSignInFromRegisterForm() {
        RegisterPage registerPage = new RegisterPage(driver);
        registerPage.openPage();
        registerPage.waitRegisterPage();

        registerPage.clickLoginButton();
        assertEquals(LOGIN_URL, driver.getCurrentUrl());
    }

    @Test
    public void checkSignInFromResetPasswordPage() {
        ResetPasswordPage resetPasswordPage = new ResetPasswordPage(driver);
        resetPasswordPage.openPage();
        resetPasswordPage.waitResetPasswordPage();

        resetPasswordPage.clickSignInButton();
        assertEquals(LOGIN_URL, driver.getCurrentUrl());
    }
}
