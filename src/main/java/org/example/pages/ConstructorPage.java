package org.example.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.example.utils.URLs.CONSTRUCTOR_URL;

public class ConstructorPage extends BasePage {

    // Кнопка "Войти в аккаунт"
    private final By signInButton = By.xpath(".//button[text()='Войти в аккаунт']");
    // Кнопка "Личный кабинет"
    private final By userProfileButton = By.xpath(".//p[text()='Личный Кабинет']");
    // Кнопка "Булки"
    private final By bunsButton = By.xpath(".//*[text()='Булки']");
    // Кнопка "Соусы"
    private final By saucesButton = By.xpath(".//*[text()='Соусы']");
    // Кнопка "Начинки"
    private final By fillingsButton = By.xpath(".//*[text()='Начинки']");
    // Текущий раздел конструктора
    private final By currentTab = By.xpath(".//*[@class='tab_tab__1SPyG tab_tab_type_current__2BEPc pt-4 pr-10 pb-4 pl-10 noselect']");

    public ConstructorPage(WebDriver driver) {
        super(driver);
    }

    @Step("Открытие страницы")
    public void openPage() {
        driver.get(CONSTRUCTOR_URL);
    }

    @Step("Нажатие по кнопке 'Войти'")
    public void clickSignInButton() {
        driver.findElement(signInButton).click();
    }

    @Step("Нажатие по кнопке 'Личный кабинет'")
    public void clickUserProfileButton() {
        driver.findElement(userProfileButton).click();
    }

    @Step("Нажатие по табу 'Булки'")
    public void clickBunsButton() {
        driver.findElement(bunsButton).click();
    }

    @Step("Нажатие по табу 'Соусы'")
    public void clickSaucesButton() {
        driver.findElement(saucesButton).click();
    }

    @Step("Нажатие по табу 'Начинки'")
    public void clickFillingsButton() {
        driver.findElement(fillingsButton).click();
    }

    @Step("Проверка текущей активной вкладки конструктора")
    public String checkCurrentTabText() {
        return driver.findElement(currentTab).getText();
    }

    @Step("Ожидание загрузки страницы")
    public void waitMainPage() {
        new WebDriverWait(driver, Duration.ofSeconds(3))
                .until(ExpectedConditions.elementToBeClickable(saucesButton));
    }
}
