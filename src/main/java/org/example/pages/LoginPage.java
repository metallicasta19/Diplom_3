package org.example.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.example.utils.URLs.LOGIN_URL;

public class LoginPage extends BasePage {
    // Поле ввода "Email"
    private final By emailInput = By.xpath(".//input[@type='text']");
    // Поле ввода "Пароль"
    private final By passwordInput = By.xpath(".//input[@type='password']");
    // Кнопка "Войти"
    private final By signInButton = By.xpath(".//button[text()='Войти']");

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    @Step("Открытие страницы")
    public void openPage() {
        driver.get(LOGIN_URL);
    }

    @Step("Ввод данных в поле 'Email'")
    public void setEmail(String email) {
        driver.findElement(emailInput).sendKeys(email);
    }

    @Step("Ввод данных в поле 'Пароль'")
    public void setPassword(String password) {
        driver.findElement(passwordInput).sendKeys(password);
    }

    @Step("Нажатие по кнопке 'Зарегистрироваться'")
    public void clickSighInButton() {
        driver.findElement(signInButton).click();
    }

    @Step("Заполнение полей и авторизация пользователя")
    public void signIn(String email, String password) {
        setEmail(email);
        setPassword(password);
        clickSighInButton();
    }

    @Step("Ожидание загрузки страницы")
    public void waitLoginPage() {
        new WebDriverWait(driver, Duration.ofSeconds(3))
                .until(ExpectedConditions.elementToBeClickable(signInButton));
    }
}
