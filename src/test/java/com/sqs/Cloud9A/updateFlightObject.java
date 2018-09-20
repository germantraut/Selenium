package com.sqs.Cloud9A;

import com.sun.xml.internal.ws.wsdl.writer.document.soap.Body;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class updateFlightObject {
    WebDriver driver;
    private By itineraryLocator = By.xpath("/html/body/div/div/div[1]/ul/li[2]/a");
    private By tableLocator = By.className("table table-striped");
    private By updateLocator;
    private By buttonUpdateLocator = By.xpath("/html/body/div/div/div[2]/form/button");
    private By bodyTextLocator = By.tagName("body");

    private String headerBookFlight = "Book Flight";
    private String headerItinerary = "Itinerary";
    private String headerEditFlight = "Edit Flight";
    private String headerFlightSuccessfullyUpdated = "Flight successfully updated";

    public updateFlightObject(WebDriver driver) {
        this.driver = driver;
    }
    public void populateupdateFlight(String flightID, String seat) {
        String bodyText = driver.findElement(bodyTextLocator).getText();
        Assert.assertTrue("Itinerary not found!", bodyText.contains(headerBookFlight));

        driver.findElement(itineraryLocator).click();
        bodyText = driver.findElement(bodyTextLocator).getText();
        Assert.assertTrue("Itinerary not found!", bodyText.contains(headerItinerary));

        searchTable(flightID);
        bodyText = driver.findElement(bodyTextLocator).getText();
        Assert.assertTrue("Edit not found!", bodyText.contains(headerEditFlight));
        driver.findElement(By.id("seat")).clear();
        driver.findElement(By.id("seat")).sendKeys(seat);
        driver.findElement(buttonUpdateLocator).click();
        bodyText = driver.findElement(bodyTextLocator).getText();
        Assert.assertTrue("FlightUpdate not successful!", bodyText.contains(headerFlightSuccessfullyUpdated));
    }

    public void searchTable(String flightID){
//        System.out.println("Looking for Table");
        WebElement baseTable = driver.findElement(By.xpath("/html/body/div/div/div[2]/div/table"));
//        System.out.println("Checking rows");
        List<WebElement> tableRows = baseTable.findElements(By.tagName("tr"));
        String pathUpdate = null;
        for (int i=1;i<tableRows.size()-1;i++){
//            System.out.println("Checking columns");
            List<WebElement> rowColumns = tableRows.get(i).findElements(By.tagName("td"));
//                System.out.println(i + "-" + j);
                if(rowColumns.get(3).getText().equals(flightID)){
                    System.out.println("found " + rowColumns.get(3).getText());
                    pathUpdate = "/html/body/div/div/div[2]/div/table/tbody/tr["+(i+1)+"]/td[1]/a[1]";
                    System.out.println(pathUpdate);
                }
        }
        if (pathUpdate != null){
            //System.out.println(toUpdate.get(0).findElements(By.linkText("Update")).toString());
            driver.findElement(By.xpath(pathUpdate)).click();
        } else {
            System.out.println("Flight ID could not be found.");
        }

//        String href = "//a[@href='editflight.php?FlightID=" + flightID + "']";
//        driver.findElement(By.xpath(href)).click();
    }

    public void withoutTable(String flightID, String seat) {
        //Ensure right page
        String bodyText = driver.findElement(bodyTextLocator).getText();
        Assert.assertTrue("Itinerary not found!", bodyText.contains(headerBookFlight));

        driver.findElement(itineraryLocator).click();
        //Ensure right page
        bodyText = driver.findElement(bodyTextLocator).getText();
        Assert.assertTrue("Itinerary not found!", bodyText.contains(headerItinerary));

        String href = "//a[@href='editflight.php?FlightID=" + flightID + "']";
        driver.findElement(By.xpath(href)).click();


        //Ensure right page
        bodyText = driver.findElement(bodyTextLocator).getText();
        Assert.assertTrue("Edit not found!", bodyText.contains(headerEditFlight));

        driver.findElement(By.id("seat")).clear();
        driver.findElement(By.id("seat")).sendKeys(seat);
        driver.findElement(buttonUpdateLocator).click();

        //Ensure update was successful
        bodyText = driver.findElement(bodyTextLocator).getText();
        Assert.assertTrue("FlightUpdate not successful!", bodyText.contains(headerFlightSuccessfullyUpdated));
    }






}
