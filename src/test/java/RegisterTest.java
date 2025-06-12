import org.apache.commons.lang3.RandomStringUtils;
import org.example.pages.RegisterPage;
import org.junit.Before;
import org.junit.Test;

import static org.example.utils.URLs.LOGIN_URL;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class RegisterTest extends BaseTest {

    @Before
    public void setUp() {
        RegisterPage registerPage = new RegisterPage(driver);
        registerPage.openPage();
        registerPage.waitRegisterPage();
    }

    @Test
    public void createNewUserTest() {
        RegisterPage registerPage = new RegisterPage(driver);
        registerPage.createNewUser(user.getName(), user.getEmail(), user.getPassword());
        registerPage.clickLoginButton();
        assertEquals(LOGIN_URL, driver.getCurrentUrl());
    }

    @Test
    public void createNewUserWithInvalidPasswordTest() {
        String invalidPassword = RandomStringUtils.randomAlphabetic(4);

        RegisterPage registerPage = new RegisterPage(driver);
        registerPage.createNewUser(user.getName(), user.getEmail(), invalidPassword);
        assertTrue(registerPage.isInvalidPasswordMessageVisible());
    }
}
