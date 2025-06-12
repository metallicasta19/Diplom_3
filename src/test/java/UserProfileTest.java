import org.example.pages.ConstructorPage;
import org.example.pages.LoginPage;
import org.example.pages.UserProfilePage;
import org.example.steps.UserSteps;
import org.junit.Before;
import org.junit.Test;

import static org.example.utils.URLs.*;
import static org.junit.Assert.assertEquals;

public class UserProfileTest extends BaseTest {

    @Before
    public void setUp() {
        UserSteps userSteps = new UserSteps();
        userSteps.createUser(user);

        LoginPage loginPage = new LoginPage(driver);
        loginPage.openPage();
        loginPage.waitLoginPage();
        loginPage.signIn(user.getEmail(), user.getPassword());

        ConstructorPage constructorPage = new ConstructorPage(driver);
        constructorPage.waitMainPage();
        constructorPage.clickUserProfileButton();

        UserProfilePage userProfilePage = new UserProfilePage(driver);
        userProfilePage.waitUserProfilePage();
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
