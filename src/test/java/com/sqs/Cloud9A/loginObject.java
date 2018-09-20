package com.sqs.Cloud9A;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class loginObject {
    private WebDriver webDriver;

    private By emailLocator = By.id("inputEmail");
    private By passwordLocator = By.id("inputPassword");
    private By registerButtonLocator = By.xpath("/html/body/div/form/button");
    private By bodyTextLocator = By.tagName("body");
    private By linkHomepageLocator = By.linkText("Homepage");

    private String cloud9LoginHeader = "Cloud9 - Sign in";
    private String loginSuccessful = "Homepage";

    public void populateLogin(String email, String password){
        assertLoginHeader();
        webDriver.findElement(emailLocator).sendKeys(email);
        webDriver.findElement(passwordLocator).sendKeys(password);
        clickLogin();
        assertLoginSuccessful();
        clickHomepage();
    }

    private void clickHomepage() {
        webDriver.findElement(linkHomepageLocator).click();
    }

    public void clickLogin(){
        webDriver.findElement(registerButtonLocator).click();
    }

    public void assertLoginHeader(){
        String bodyText = webDriver.findElement(bodyTextLocator).getText();
        Assert.assertTrue("Header not found!", bodyText.contains(cloud9LoginHeader));
    }

    private void assertLoginSuccessful() {
        String linkHomepage = webDriver.findElement(bodyTextLocator).getText();
        Assert.assertTrue("Welcome text not found!", linkHomepage.contains(loginSuccessful));
    }

    public loginObject(WebDriver webDriver){
        this.webDriver =  webDriver;
    }
}