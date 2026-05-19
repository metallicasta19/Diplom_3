package org.example.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.example.utils.URLs.REGISTER_URL;

public class RegisterPage extends BasePage {

    // Поле ввода "Имя"
    private final By nameInput = By.xpath(".//*[label='Имя']/../div/input");
    // Поле ввода "Email"
    private final By emailInput = By.xpath(".//*[label='Email']/../div/input");
    // Поле ввода "Пароль"
    private final By passwordInput = By.xpath(".//*[label='Пароль']/../div/input");
    // Кнопка "Зарегистрироваться"
    private final By registerButton = By.xpath(".//*[text()='Зарегистрироваться']");
    // Кнопка "Войти"
    private final By signInButton = By.xpath(".//*[text()='Войти']");
    // Ошибка "Некорректный пароль"
    private final By invalidPasswordMessage = By.xpath(".//*[text()='Некорректный пароль']");

    public RegisterPage(WebDriver driver) {
        super(driver);
    }

    @Step("Открытие страницы")
    public void openPage() {
        driver.get(REGISTER_URL);
    }

    @Step("Ввод данных в поле 'Имя'")
    public void setName(String name) {
        driver.findElement(nameInput).sendKeys(name);
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
    public void clickRegisterButton() {
        driver.findElement(registerButton).click();
    }

    @Step("Регистрация нового пользователя")
    public void createNewUser(String name, String email, String password) {
        setName(name);
        setEmail(email);
        setPassword(password);
        clickRegisterButton();
    }

    @Step("Нажатие по кнопке 'Войти'")
    public void clickLoginButton() {
        driver.findElement(signInButton).click();
    }

    @Step("Проверка видимости сообщения об ошибке при вводе невалидного пароля")
    public boolean isInvalidPasswordMessageVisible() {
        return driver.findElement(invalidPasswordMessage).isDisplayed();
    }

    @Step("Ожидание загрузки страницы")
    public void waitRegisterPage() {
        new WebDriverWait(driver, Duration.ofSeconds(3))
                .until(ExpectedConditions.elementToBeClickable(signInButton));
    }
}
