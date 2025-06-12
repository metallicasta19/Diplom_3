import org.example.pages.ConstructorPage;
import org.example.pages.LoginPage;
import org.example.pages.RegisterPage;
import org.example.pages.ResetPasswordPage;
import org.example.steps.UserSteps;
import org.junit.Before;
import org.junit.Test;

import static org.example.utils.URLs.CONSTRUCTOR_URL;
import static org.junit.Assert.assertEquals;

public class SignInButtonTest extends BaseTest {
    @Before
    public void setUp() {
        UserSteps userSteps = new UserSteps();
        userSteps.createUser(user);
    }

    @Test
    public void checkSignInFromMainPageTest() {
        ConstructorPage constructorPage = new ConstructorPage(driver);
        constructorPage.openPage();
        constructorPage.waitMainPage();

        constructorPage.clickSignInButton();

        LoginPage loginPage = new LoginPage(driver);
        loginPage.waitLoginPage();
        loginPage.signIn(user.getEmail(), user.getPassword());

        constructorPage.waitMainPage();
        assertEquals(CONSTRUCTOR_URL, driver.getCurrentUrl());
    }

    @Test
    public void checkSignInFromUserProfile() {
        ConstructorPage constructorPage = new ConstructorPage(driver);
        constructorPage.openPage();
        constructorPage.waitMainPage();
        constructorPage.clickUserProfileButton();

        LoginPage loginPage = new LoginPage(driver);
        loginPage.waitLoginPage();
        loginPage.signIn(user.getEmail(), user.getPassword());

        constructorPage.waitMainPage();
        assertEquals(CONSTRUCTOR_URL, driver.getCurrentUrl());
    }

    @Test
    public void checkSignInFromRegisterForm() {
        RegisterPage registerPage = new RegisterPage(driver);
        registerPage.openPage();
        registerPage.waitRegisterPage();
        registerPage.clickLoginButton();

        LoginPage loginPage = new LoginPage(driver);
        loginPage.waitLoginPage();
        loginPage.signIn(user.getEmail(), user.getPassword());

        ConstructorPage constructorPage = new ConstructorPage(driver);
        constructorPage.waitMainPage();
        assertEquals(CONSTRUCTOR_URL, driver.getCurrentUrl());
    }

    @Test
    public void checkSignInFromResetPasswordPage() {
        ResetPasswordPage resetPasswordPage = new ResetPasswordPage(driver);
        resetPasswordPage.openPage();
        resetPasswordPage.waitResetPasswordPage();
        resetPasswordPage.clickSignInButton();

        LoginPage loginPage = new LoginPage(driver);
        loginPage.waitLoginPage();
        loginPage.signIn(user.getEmail(), user.getPassword());

        ConstructorPage constructorPage = new ConstructorPage(driver);
        constructorPage.waitMainPage();
        assertEquals(CONSTRUCTOR_URL, driver.getCurrentUrl());
    }
}
