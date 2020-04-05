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

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.testng.Assert.assertEquals;

/**
 * Test for registration and login on the portal
 */
public class UserChromeTest {
    public static final String TEST_GMAIL = "test@gmail.com";
    public static final String FIRST_LAST_AND_PASSWORD = "test";
    public static final String WWW_NEWTOURS_DEMOAUT_COM = "http://www.newtours.demoaut.com/";
    private WebDriver driver;

    @BeforeClass
    private void setup() {
        //System.setProperty("webdriver.chrome.driver", "C:\\Users\\hkatakam\\Downloads\\chromedriver_win32\\chromedriver.exe");
        System.setProperty("webdriver.chrome.driver", "/usr/bin/chromedriver");
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("headless");
        chromeOptions.addArguments("--no-sandbox");
        driver = new ChromeDriver(chromeOptions);
        // Opens a website hosted on below machine
        driver.get(WWW_NEWTOURS_DEMOAUT_COM);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    }

    @Test(priority = 1)
    public void registrationAndSignIn() {
        //Registration
        driver.findElement(By.xpath("/html/body/div/table/tbody/tr/td[2]/table/tbody/tr[4]/td/table/tbody/tr/td[2]/table/tbody/tr[2]/td[3]/form/table/tbody/tr[10]/td/table/tbody/tr/td[2]/font/a")).click();
        //Registration form
        driver.findElement(By.name("firstName")).sendKeys(FIRST_LAST_AND_PASSWORD);
        driver.findElement(By.name(("lastName"))).sendKeys(FIRST_LAST_AND_PASSWORD);
        driver.findElement(By.name(("phone"))).sendKeys("352463434");
        driver.findElement(By.xpath(("//*[@id=\"userName\"]"))).sendKeys(TEST_GMAIL);
        driver.findElement(By.name("address1")).sendKeys("christiana mall");
        driver.findElement(By.name("city")).sendKeys("Newark");
        driver.findElement(By.name("state")).sendKeys("Delaware");
        driver.findElement(By.name("postalCode")).sendKeys("19702");
        Select select = new Select(driver.findElement(By.name("country")));
        select.selectByVisibleText("UNITED STATES");
        driver.findElement(By.xpath("//*[@id=\"email\"]")).sendKeys(TEST_GMAIL);
        driver.findElement(By.name("password")).sendKeys(FIRST_LAST_AND_PASSWORD);
        driver.findElement(By.name("confirmPassword")).sendKeys(FIRST_LAST_AND_PASSWORD);
        driver.findElement(By.xpath("/html/body/div/table/tbody/tr/td[2]/table/tbody/tr[4]/td/table/tbody/tr/td[2]/table/tbody/tr[5]/td/form/table/tbody/tr[18]/td/input")).click();
        WebDriverWait wait = new WebDriverWait(driver, 20);
        //Wait till the sign off element is visible
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div/table/tbody/tr/td[2]/table/tbody/tr[2]/td/table/tbody/tr/td[1]/a")));
    }

    @Test(priority = 2)
    public void flightBooking() throws Exception {
        Thread.sleep(1000);
        //Navigate to flights page
        driver.findElement(By.xpath("/html/body/div/table/tbody/tr/td[1]/table/tbody/tr/td/table/tbody/tr/td/table/tbody/tr[2]/td[2]/a")).click();
        // wait until flights is visible
        WebDriverWait flightsPageWait = new WebDriverWait(driver, 10);
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
        driver.findElement(By.xpath("/html/body/div/table/tbody/tr/td[2]/table/tbody/tr[4]/td/table/tbody/tr/td[2]/table/tbody/tr[5]/td/form/table/tbody/tr[4]/td/table/tbody/tr[2]/td[1]/input")).sendKeys(FIRST_LAST_AND_PASSWORD);
        driver.findElement(By.xpath("/html/body/div/table/tbody/tr/td[2]/table/tbody/tr[4]/td/table/tbody/tr/td[2]/table/tbody/tr[5]/td/form/table/tbody/tr[4]/td/table/tbody/tr[2]/td[2]/input")).sendKeys(FIRST_LAST_AND_PASSWORD);
        driver.findElement(By.xpath("/html/body/div/table/tbody/tr/td[2]/table/tbody/tr[4]/td/table/tbody/tr/td[2]/table/tbody/tr[5]/td/form/table/tbody/tr[6]/td/table/tbody/tr[2]/td[2]/input")).sendKeys("234626234");
        driver.findElement(By.xpath("/html/body/div/table/tbody/tr/td[2]/table/tbody/tr[4]/td/table/tbody/tr/td[2]/table/tbody/tr[5]/td/form/table/tbody/tr[23]/td/input")).click();
        Thread.sleep(1000);
        // Verify title of the page
        String actualTitle = driver.getTitle();
        String expectedTitle = "Flight Confirmation: Mercury Tours";
        assertEquals(expectedTitle, actualTitle);
    }

    @Test(priority = 3)
    public void cruiseBooking() throws Exception {
        //Navigate to cruises page
        driver.findElement(By.xpath("/html/body/div/table/tbody/tr/td[1]/table/tbody/tr/td/table/tbody/tr/td/table/tbody/tr[5]/td[2]/a")).click();
        Thread.sleep(1000);
        // Verify title of the page
        String actualTitle = driver.getTitle();
        String expectedTitle = "Cruises: Mercury Tours";
        assertEquals(expectedTitle, actualTitle);
    }

    @Test(priority = 4)
    public void brokenLinks() throws Exception{
        Thread.sleep(1000);
        //Used tagName method to collect the list of items with tagName "a"
        //findElements - to find all the elements with in the current page. It returns a list of all webelements or an empty list if nothing matches
        List<WebElement> links = driver.findElements(By.tagName("a"));
        //To print the total number of links
        System.out.println("Total links are " + links.size());
        //used for loop to
        for (WebElement element : links) {
            //By using "href" attribute, we could get the url of the required link
            String url = element.getAttribute("href");
            //calling verifyLink() method here. Passing the parameter as url which we collected in the above link
            //See the detailed functionality of the verifyLink(url) method below
            verifyLink(url);
        }
    }

    @Test(priority = 5)
    public void signOut() throws Exception{
        Thread.sleep(1000);
        driver.findElement(By.xpath("/html/body/div/table/tbody/tr/td[2]/table/tbody/tr[2]/td/table/tbody/tr/td[1]/a")).click();
        // Verify title of the page
        String actualTitle = driver.getTitle();
        String expectedTitle = "Sign-on: Mercury Tours";
        assertEquals(expectedTitle, actualTitle);
    }
    
    // The below function verifyLink(String urlLink) verifies any broken links and return the server status.
    private void verifyLink(String urlLink) {
        try {
            //Use URL Class - Create object of the URL Class and pass the urlLink as parameter
            URL link = new URL(urlLink);
            // Create a connection using URL object (i.e., link)
            HttpURLConnection httpConn = (HttpURLConnection) link.openConnection();
            //Set the timeout for 2 seconds
            httpConn.setConnectTimeout(2000);
            //connect using connect method
            httpConn.connect();
            //use getResponseCode() to get the response code.
            if (httpConn.getResponseCode() == 200) {
                System.out.println(urlLink + " - " + httpConn.getResponseMessage());
            }
            if (httpConn.getResponseCode() == 404) {
                System.out.println(urlLink + " - " + httpConn.getResponseMessage());
            }
        }
        //getResponseCode method returns = IOException - if an error occurred connecting to the server.
        catch (Exception e) {
            //e.printStackTrace();
        }
    }

    @AfterClass
    public void tearOff() {
        if (driver != null) {
            driver.quit();
        }
    }
}
