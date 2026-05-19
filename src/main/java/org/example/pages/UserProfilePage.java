package org.example.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.example.utils.URLs.USER_PROFILE_URL;

public class UserProfilePage extends BasePage {

    // Кнопка "Конструктор"
    private final By constructorButton = By.xpath(".//*[text()='Конструктор']");
    // Кнопка "Выход"
    private final By exitButton = By.xpath(".//button[text()='Выход']");
    // Логотип "Stellar Burgers"
    private final By logoButton = By.xpath(".//*[@class='AppHeader_header__logo__2D0X2']//a");

    public UserProfilePage(WebDriver driver) {
        super(driver);
    }

    @Step("Открытие страницы")
    public void openPage() {
        driver.get(USER_PROFILE_URL);
    }

    @Step("Нажатие по кнопке 'Конструктор'")
    public void clickConstructorButton() {
        driver.findElement(constructorButton).click();
    }

    @Step("Нажатие по кнопке 'Выход'")
    public void clickExitButton() {
        driver.findElement(exitButton).click();
    }

    @Step("Нажатие по логотипу 'Stellar Burgers'")
    public void clickLogoButton() {
        driver.findElement(logoButton).click();
    }

    @Step("Ожидание загрузки страницы")
    public void waitUserProfilePage() {
        new WebDriverWait(driver, Duration.ofSeconds(3))
                .until(ExpectedConditions.elementToBeClickable(exitButton));
    }
}
