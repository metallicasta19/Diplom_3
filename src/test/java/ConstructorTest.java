import org.example.pages.ConstructorPage;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ConstructorTest extends BaseTest {

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
