package manager;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.events.EventFiringDecorator;
import org.openqa.selenium.support.events.WebDriverListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;


import java.lang.reflect.Method;

public class ApplicationManager {
    private WebDriver driver;

    public static int height;
    public Logger logger = LoggerFactory.getLogger(ApplicationManager.class);

    public WebDriver getDriver() {
        return driver;
    }

    @BeforeMethod
    public void setup() {
        logger.info("Start method --> setup" );
        driver = new ChromeDriver();

        driver.manage().window().maximize();
        height = driver.manage().window().getSize().getHeight();
    }

    @AfterMethod
    public void tearDown() {
        logger.info("Start method --> tearDown");
//        if(driver != null){
//            driver.quit();
//        }
    }
}
