package com.sqs.Cloud9A;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

class bookFlightObject {
    WebDriver webDriver;

    private By bookFlightLocator = By.linkText("Book Flight");
    private By originLocator = By.id("Origin");
    private By destinationLocator = By.id("Destination");
    private By seatLocator = By.id("seat");
    private By flightclassLocator = By.id("Flightclass");
    private By buttonBookLocator = By.xpath("/html/body/div/div/div[2]/form/button");
    private By bodyTextLocator = By.tagName("body");

    private String bookSuccessful = "Flight booked successfully";


    public void populateBookFlight(String origin, String destination, String seat, String flightClass){
        clickBookFlight();
        selectDropdownForFlights(originLocator, origin);
        selectDropdownForFlights(destinationLocator, destination);
        webDriver.findElement(seatLocator).sendKeys(seat);
        selectDropdownForFlights(flightclassLocator, flightClass);
        clickBook();
        assertFlightBookSuccessful();
    }

    private void assertFlightBookSuccessful() {
        String bodyText = webDriver.findElement(bodyTextLocator).getText();
        Assert.assertTrue("Flight was not booked", bodyText.contains(bookSuccessful));
    }

    private void clickBook (){
        webDriver.findElement(buttonBookLocator).click();
    }


    private void selectDropdownForFlights(By locator, String origin){
        Select dropDown = new Select(webDriver.findElement(locator));
        dropDown.selectByVisibleText(origin);
    }

    private void clickBookFlight(){
        webDriver.findElement(bookFlightLocator).click();
    }

    public bookFlightObject(WebDriver webDriver){
        this.webDriver = webDriver;
    }
}
