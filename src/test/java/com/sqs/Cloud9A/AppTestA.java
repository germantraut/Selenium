package com.sqs.Cloud9A;

import org.junit.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

/**
 * Unit test for simple App.
 */
public class AppTestA
{
    private static WebDriver driver;

    @Before
    public void testSetup() {
        System.out.println("In testSetup");
        System.setProperty("webdriver.chrome.driver", "C:\\Drivers\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
        String baseUrl = "http://10.9.10.39:81/sqlite/Main/login.html";
        String expectedTitle = "Cloud9 Airlines";
        driver.get(baseUrl);
        String actualTitle = driver.getTitle();
        System.out.println("Actual Title is: " + actualTitle);
        Assert.assertEquals(actualTitle, expectedTitle);
    }

    @BeforeClass
    public static void browserSetup(){

    }

    @Test
    public void registerCustomer() {
        driver.findElement(By.linkText("Register")).click();
        registrationObject registrationPage = new registrationObject(driver);
        registrationPage.populateRegistration("G", "E", "GE002@SQS.SA", "xxx");
    }
    @Test
    public void loginCustomer(){
        loginObject loginPage = new loginObject(driver);
        loginPage.populateLogin("GE@SQS.SA", "XXX");
    }

    @Test
    public void bookFlight(){
        loginObject loginPage = new loginObject(driver);
        loginPage.populateLogin("GE@SQS.SA", "XXX");

        bookFlightObject bookFlightPage = new bookFlightObject(driver);
        bookFlightPage.populateBookFlight("Durban", "London", "25e", "First");
    }

    @Test
    public void updateFlightObject() {
        System.out.println("Test updateFlightSeat running");
        loginObject loginPage = new loginObject(driver);
        loginPage.populateLogin("GE@SQS.SA", "XXX");

        updateFlightObject updateFlightPage = new updateFlightObject(driver);
        updateFlightPage.populateupdateFlight("282","qw1");
    }

    @Test
    public void updateFlightObjectwithoutTable() {
        System.out.println("Test updateFlightSeat running");
        loginObject loginPage = new loginObject(driver);
        loginPage.populateLogin("GE@SQS.SA", "XXX");

        updateFlightObject updateFlightPage = new updateFlightObject(driver);
        updateFlightPage.withoutTable("279", "21w");
    }

    @Test
    public void deleteFlight() {
        System.out.println("Test updateFlightSeat running");
        loginObject loginPage = new loginObject(driver);
        loginPage.populateLogin("GE@SQS.SA", "XXX");

        deleteFlightObject deleteFlight = new deleteFlightObject(driver);
        deleteFlight.populateupdateFlight("312");
    }

    @Test
    public void endToEnd(){

    }



    @After
    public void tearDown() {
        driver.close();
    }
}
