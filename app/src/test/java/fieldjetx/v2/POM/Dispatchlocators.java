package fieldjetx.v2.POM;

import org.openqa.selenium.By;

public class Dispatchlocators {
    // Declare all the hardcoded locators as private members
    private By serviceButton = By.xpath("//a[@href='/service']");
    private By dispatchButton = By.cssSelector("a[href='http://192.168.5.16:8081/#/dispatch/search']");
    private By newDropdown = By.xpath("//div[@class='dropdown']//button[@data-toggle='dropdown']");
    private By newDispatchButton = By.xpath("//a[@href='/dispatch/new']");
    private By customerName = By.id("name");
    private By customerLocation = By.id("location");
    private By customerclass = By.id("service");
    private By saveButton = By.xpath("//button[@type='submit']");
    private By verifyingdispatchcreated = By.xpath("//span[text()='DSP: Ready for Dispatch']");
    private By assignTechnician = By.xpath("//button[@class='btn btn-outline-secondary btn-technician']");
    private By searchingPerson = By.xpath("//input[@placeholder='Search']");
    private By selectingPerson = By.xpath("//div[@class='user-info']");
    private By assignTechButton = By.xpath("//button[@class='btn btn-primary']");
    private By verifyingAssignedTech = By.xpath("//div[text()='Technician:']//following-sibling::div");
    private By finalAssignButton = By.xpath("//button[@class='btn btn-primary']");

    // Generate getters for all the locators

    public By getServiceButton() {
        // This method generates getters for all the locators
        // It returns the service button locator
        return serviceButton;
    }

    // This method returns the dispatch button locator
    public By getDispatchButton() {
        return dispatchButton;
    }

    // This method returns the new dropdown locator
    public By getNewDropdown() {
        return newDropdown;
    }

    // This method returns the new dispatch button locator
    public By getNewDispatchButton() {
        return newDispatchButton;
    }

    // This method returns the customer name locator
    public By getCustomerName() {
        return customerName;
    }

    // This method returns the customer location locator
    public By getCustomerLocation() {
        return customerLocation;
    }

    // This method returns the customer class locator
    public By getCustomerclass() {
        return customerclass;
    }

    // This method returns the save button locator
    public By getSaveButton() {
        return saveButton;
    }

    // This method returns the verifying dispatch created locator
    public By getVerifyingdispatchcreated() {
        return verifyingdispatchcreated;
    }

    // This method returns the assign technician locator
    public By getAssignTechnician() {
        return assignTechnician;
    }

    // This method returns the searching person locator
    public By getSearchingPerson() {
        return searchingPerson;
    }

    // This method returns the selecting person locator
    public By getSelectingPerson() {
        return selectingPerson;
    }

    // This method returns the assign tech button locator
    public By getAssignTechButton() {
        return assignTechButton;
    }

    // This method returns the verifying assigned tech locator
    public By getVerifyingAssignedTech() {
        return verifyingAssignedTech;
    }

    // This method returns the final assign button locator
    public By getFinalAssignButton() {
        return finalAssignButton;
    }

}
