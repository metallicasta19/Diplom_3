import org.example.pages.ConstructorPage;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.Assert.assertEquals;

public class ConstructorTest {
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
    public void checkSwitchToBunsTab() {
        ConstructorPage constructorPage = new ConstructorPage(driver);
        constructorPage.openPage();
        constructorPage.waitMainPage();

        constructorPage.clickSaucesButton();
        constructorPage.clickBunsButton();

        assertEquals("Булки", constructorPage.checkCurrentTabText());
    }

    @Test
    public void checkSwitchToSaucesTab() {
        ConstructorPage constructorPage = new ConstructorPage(driver);
        constructorPage.openPage();
        constructorPage.waitMainPage();

        constructorPage.clickSaucesButton();
        assertEquals("Соусы", constructorPage.checkCurrentTabText());
    }

    @Test
    public void checkSwitchToFillingsTab() {
        ConstructorPage mainPage = new ConstructorPage(driver);
        mainPage.openPage();
        mainPage.waitMainPage();

        mainPage.clickFillingsButton();
        assertEquals("Начинки", mainPage.checkCurrentTabText());
    }
}
