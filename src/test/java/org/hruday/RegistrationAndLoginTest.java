package org.hruday;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;

import java.util.concurrent.TimeUnit;

import static org.testng.Assert.assertEquals;

/**
 * Test for registration and login on the portal
 */
public class RegistrationAndLoginTest
{
    private WebDriver driver;
    @BeforeClass
    private void setup(){
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\hkatakam\\Downloads\\chromedriver_win32\\chromedriver.exe");
        ChromeOptions chromeOptions = new ChromeOptions();
        driver = new ChromeDriver(chromeOptions);
        // Opens a website hosted on below machine
        driver.get("http://www.newtours.demoaut.com/");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    }

    @Test(priority = 1)
    public void registration()
    {
        //Registration
        driver.findElement(By.xpath("/html/body/div/table/tbody/tr/td[2]/table/tbody/tr[4]/td/table/tbody/tr/td[2]/table/tbody/tr[2]/td[3]/form/table/tbody/tr[10]/td/table/tbody/tr/td[2]/font/a")).click();
        //Registration form
        driver.findElement(By.name("firstName")).sendKeys("test");
        driver.findElement(By.name(("lastName"))).sendKeys("test");
        driver.findElement(By.name(("phone"))).sendKeys("352463434");
        driver.findElement(By.xpath(("//*[@id=\"userName\"]"))).sendKeys("test@gmail.com");
        driver.findElement(By.name("address1")).sendKeys("christiana mall");
        driver.findElement(By.name("city")).sendKeys("Newark");
        driver.findElement(By.name("state")).sendKeys("Delaware");
        driver.findElement(By.name("postalCode")).sendKeys("19702");
        Select select = new Select(driver.findElement(By.name("country")));
        select.selectByVisibleText("UNITED STATES");
        driver.findElement(By.xpath("//*[@id=\"email\"]")).sendKeys("test@gmail.com");
        driver.findElement(By.name("password")).sendKeys("test");
        driver.findElement(By.name("confirmPassword")).sendKeys("test");
        driver.findElement(By.xpath("/html/body/div/table/tbody/tr/td[2]/table/tbody/tr[4]/td/table/tbody/tr/td[2]/table/tbody/tr[5]/td/form/table/tbody/tr[18]/td/input")).click();
        WebDriverWait wait=new WebDriverWait(driver,20);
        //Wait till the sign off element is visible
       wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div/table/tbody/tr/td[2]/table/tbody/tr[2]/td/table/tbody/tr/td[1]/a")));
    }

    @Test(priority = 2)
    public void flightBooking() throws Exception{
        Thread.sleep(1000);
        //Navigate to flights page
        driver.findElement(By.xpath("/html/body/div/table/tbody/tr/td[1]/table/tbody/tr/td/table/tbody/tr/td/table/tbody/tr[2]/td[2]/a")).click();
        // wait until flights is visible
        WebDriverWait flightsPageWait=new WebDriverWait(driver,10);
        // Wait till the flights element is visible
        flightsPageWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div/table/tbody/tr/td[1]/table/tbody/tr/td/table/tbody/tr/td/table/tbody/tr[2]/td[2]/a")));
        //Flight finder
        Select fromPort = new Select(driver.findElement(By.name("fromPort")));
        fromPort.selectByVisibleText("New York");
        Select toPort = new Select(driver.findElement(By.name("toPort")));
        toPort.selectByVisibleText("San Francisco");
        driver.findElement(By.name("findFlights")).click();
        Thread.sleep(1000);
        driver.findElement(By.xpath("/html/body/div/table/tbody/tr/td[2]/table/tbody/tr[4]/td/table/tbody/tr/td[2]/table/tbody/tr[5]/td/form/p/input")).click();
        Thread.sleep(1000);
        driver.findElement(By.xpath("/html/body/div/table/tbody/tr/td[2]/table/tbody/tr[4]/td/table/tbody/tr/td[2]/table/tbody/tr[5]/td/form/table/tbody/tr[4]/td/table/tbody/tr[2]/td[1]/input")).sendKeys("test");
        driver.findElement(By.xpath("/html/body/div/table/tbody/tr/td[2]/table/tbody/tr[4]/td/table/tbody/tr/td[2]/table/tbody/tr[5]/td/form/table/tbody/tr[4]/td/table/tbody/tr[2]/td[2]/input")).sendKeys("test");
        driver.findElement(By.xpath("/html/body/div/table/tbody/tr/td[2]/table/tbody/tr[4]/td/table/tbody/tr/td[2]/table/tbody/tr[5]/td/form/table/tbody/tr[6]/td/table/tbody/tr[2]/td[2]/input")).sendKeys("234626234");
        driver.findElement(By.xpath("/html/body/div/table/tbody/tr/td[2]/table/tbody/tr[4]/td/table/tbody/tr/td[2]/table/tbody/tr[5]/td/form/table/tbody/tr[23]/td/input")).click();
        Thread.sleep(1000);
        String actualTitle = driver.getTitle();
        String expectedTitle = "Flight Confirmation: Mercury Tours";
        assertEquals(expectedTitle,actualTitle);
    }

    //@AfterClass
    public void tearOff(){
        if(driver != null) {
            driver.quit();
        }
    }
}
