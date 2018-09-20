package com.sqs.Cloud9A;

import com.sun.xml.internal.ws.wsdl.writer.document.soap.Body;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class deleteFlightObject {
    WebDriver driver;
    private By itineraryLocator = By.xpath("/html/body/div/div/div[1]/ul/li[2]/a");
    private By tableLocator = By.className("table table-striped");
    private By updateLocator;
    private By buttonUpdateLocator = By.xpath("/html/body/div/div/div[2]/form/button");
    private By bodyTextLocator = By.tagName("body");

    private String headerBookFlight = "Book Flight";
    private String headerItinerary = "Itinerary";
    private String headerEditFlight = "Edit Flight";
    private String headerFlightSuccessfullydeleted = "Flight successfully deleted.";

    public deleteFlightObject(WebDriver driver) {
        this.driver = driver;
    }
    public void populateupdateFlight(String flightID) {
        String bodyText = driver.findElement(bodyTextLocator).getText();
        Assert.assertTrue("Itinerary not found!", bodyText.contains(headerBookFlight));

        driver.findElement(itineraryLocator).click();
        bodyText = driver.findElement(bodyTextLocator).getText();
        Assert.assertTrue("Itinerary not found!", bodyText.contains(headerItinerary));

        String href = "//a[@href='deleteflight.php?FlightID=" + flightID + "']";
        driver.findElement(By.xpath(href)).click();

        bodyText = driver.findElement(bodyTextLocator).getText();
        Assert.assertTrue("FlightUpdate not successful!", bodyText.contains(headerFlightSuccessfullydeleted));
    }
}
