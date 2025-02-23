package pages;

import manager.ApplicationManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class HomePage extends BasePage {
    public HomePage(WebDriver driver) {
        setDriver(driver);
        driver.get("https://ilcarro.web.app/search");
        PageFactory.initElements(new AjaxElementLocatorFactory(driver, 10), this);
    }

    @FindBy(xpath = "//a[text()=' Log in ']")
    WebElement btnLogin;
    @FindBy(xpath = "//a[text()=' Sign up ']")
    WebElement btnSignUp;

    @FindBy(id = "city")
    WebElement inputCity;
    @FindBy(id = "dates")
    WebElement inputDates;
    @FindBy(xpath = "//button[@aria-label='Choose month and year']")
    WebElement btnChooseMonthYear;
    @FindBy(xpath = "//button[text()='Y’alla!']")
    WebElement btnYalla;

    public LoginPage clickBtnLogin() {
        btnLogin.click();
        return new LoginPage(driver);
    }

    public RegistrationPage clickBtnRegistration() {
        btnSignUp.click();
        return new RegistrationPage(driver);
    }

    public void fillSearchCarFormCalendar(String city, String startDate, String endDate) { // 17 Oct 2024 or 3 Nov 2025
        fillInputCity(city);
        fillInputDateCalendar(startDate, endDate);
        btnYalla.click();
    }

    private void fillInputDateCalendar(String startDate, String endDate) {
        inputDates.click();
        String[] startDateArray = startDate.split(" ");
        String[] endDateArray = endDate.split(" ");
        typeYearMonthDay(startDateArray[2], startDateArray[1], startDateArray[0]);  // year month date
        typeYearMonthDay(endDateArray[2], endDateArray[1], endDateArray[0]);  // year month date
    }

    private void typeYearMonthDay(String year, String month, String day) {
        btnChooseMonthYear.click();
        driver.findElement(By.xpath("//div[contains(text(),'" + year + "')]")).click();   ////div[contains(text(),'2024')]
        driver.findElement(By.xpath("//div[contains(text(),'" + month.toUpperCase() + "')]")).click();
        driver.findElement(By.xpath("//div[contains(text(),'" + day + "')]")).click();
    }

    private void fillInputCity(String city) {
        System.out.println("--> " + ApplicationManager.height);
        inputCity.click();
        inputCity.sendKeys(city);
        Actions actions = new Actions(driver);
        actions.moveToElement(inputCity, 0, 26).pause(2000).click().perform();
    }

    public boolean validateUrlContainsResults(){
        return new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.urlContains("search/results"));
    }
}
