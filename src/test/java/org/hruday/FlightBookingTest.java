package org.hruday;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class FlightBookingTest
{
    private WebDriver driver;

    //@BeforeMethod
    public void setup() {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\hkatakam\\Downloads\\chromedriver_win32\\chromedriver.exe");
        ChromeOptions chromeOptions = new ChromeOptions();
        driver = new ChromeDriver(chromeOptions);
        // Opens a website hosted on below machine
        driver.get("http://www.newtours.demoaut.com/");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        //Sign on
        driver.findElement(By.xpath("/html/body/div/table/tbody/tr/td[2]/table/tbody/tr[2]/td/table/tbody/tr/td[1]/a")).click();

    }

    @Test
    public void flightBooking() {
        //Navigate to flights
        driver.findElement(By.xpath("/html/body/div/table/tbody/tr/td[1]/table/tbody/tr/td/table/tbody/tr/td/table/tbody/tr[2]/td[2]/a")).click();
        //Flight finder
        Select fromPort = new Select(driver.findElement(By.name("fromPort")));
        fromPort.selectByVisibleText("New York");
        Select toPort = new Select(driver.findElement(By.name("toPort")));
        toPort.selectByVisibleText("San Francisco");
        driver.findElement(By.name("findFlights")).click();

    }

    //@AfterMethod
    public void tearDown() {
        if (driver !=null) {
            driver.quit();
        }
    }


    //@Test
    public void signIn() {
        //click on sign in
        driver.findElement(By.xpath("/html/body/div/table/tbody/tr/td[2]/table/tbody/tr[4]/td/table/tbody/tr/td[2]/table/tbody/tr[3]/td/p[2]/font/a[1]")).click();
        WebDriverWait signInWait=new WebDriverWait(driver,10);
        // Wait till the sign in element is visible
        signInWait.until(ExpectedConditions.visibilityOfElementLocated(By.name("userName")));
        driver.findElement(By.name("userName")).sendKeys("test@gmail.com");
        driver.findElement(By.name("password")).sendKeys("test");
        driver.findElement(By.name("login")).click();
    }

}
