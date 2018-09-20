package com.sqs.Cloud9A;

import static org.junit.Assert.assertTrue;

import org.junit.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
    private static WebDriver driver;
    private String registrationSuccessful = "Registration Successful";
    private By bodyTextLocator = By.tagName("body");
    private String cloud9RegisterHeader = "Cloud9 - Register";

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
        System.out.println("Acutal Title is: " + actualTitle);
        Assert.assertEquals(actualTitle, expectedTitle);
    }

    @BeforeClass
    public static void browserSetup(){

    }

//    @Test
//    public void shouldAnswerWithTrue() { assertTrue( true );
//    }

    @Test
    public void registerCustomer() {
        driver.findElement(By.xpath("/html/body/div/form/center/a")).click(); //register link
        driver.findElement(By.id("inputfirstname")).sendKeys("G");
        driver.findElement(By.id("inputlastname")).sendKeys("E");
        driver.findElement(By.id("inputEmail")).sendKeys("GE001@SQS.SA");
        driver.findElement(By.id("inputPassword")).sendKeys("XXX");
        driver.findElement(By.xpath("/html/body/div/form/button")).click();
        String bodyText = driver.findElement(bodyTextLocator).getText();
        Assert.assertTrue("Text not found", bodyText.contains(registrationSuccessful));
    }

//    @Test
//    public void loginCustomer() {
//        driver.findElement(By.id("inputEmail")).sendKeys("GE@SQS.SA");
//        driver.findElement(By.id("inputPassword")).sendKeys("XXX");
//        driver.findElement(By.xpath("/html/body/div/form/button")).click();
//        driver.findElement(By.xpath("/html/body/center[3]/a")).click();
//    }
//
//    @Test
//    public void bookaflightCustomer(){
//        driver.findElement(By.xpath("/html/body/div/div/div[1]/ul/li[1]/a")).click();
//        //driver.findElement(By.id("Origin"));
//    }
//
//    @Test
//    public void logoutCustomer(){
//        driver.findElement(By.xpath("/html/body/div/div/div[1]/ul/li[3]/a")).click();
//    }


    @After
    public void tearDown() throws InterruptedException {
        Thread.sleep(500);
        driver.close();
    }
}
