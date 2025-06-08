package org.example.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.example.utils.URLs.RESET_PASSWORD_URL;

public class ResetPasswordPage extends BasePage {

    // Кнопка "Войти"
    private final By signInButton = By.xpath(".//*[text()='Войти']");

    public ResetPasswordPage(WebDriver driver) {
        super(driver);
    }

    @Step("Открытие страницы")
    public void openPage() {
        driver.get(RESET_PASSWORD_URL);
    }

    @Step("Нажатие по кнопке 'Войти'")
    public void clickSignInButton() {
        driver.findElement(signInButton).click();
    }

    @Step("Ожидание загрузки страницы")
    public void waitResetPasswordPage() {
        new WebDriverWait(driver, Duration.ofSeconds(3))
                .until(ExpectedConditions.elementToBeClickable(signInButton));
    }

}
