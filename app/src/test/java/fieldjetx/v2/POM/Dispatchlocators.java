package fieldjetx.v2.POM;

import org.openqa.selenium.By;

public class Dispatchlocators {
    // Declare all the hardcoded locators as private members
    private By serviceButton = By.id("dispatch_fjx_services");
    private By dispatchButton = By.id("dispatch_fjx_dispatches");
    // private By newDropdown = By.id("dispatch_fjx_dropdown");
    private By newDropdown = By.cssSelector(".k-icon");
    private By newDispatchButton = By.cssSelector(".k-item:nth-child(1)");
    private By customerName = By.id("k-18eff997-9981-4256-b6d4-6b51ec161a80");
    private By customerLocation = By.id("k-fef9ede0-c712-48eb-a4f9-929165452fbd");
    private By customerclass = By.id("k-ef46a527-e96f-4d48-8202-756604ced643");
    private By saveButton = By.id("dispatch_fjx_save");
    private By verifyingdispatchcreated = By.xpath("//div[text()='DSP: Ready for Dispatch']");
    private By assignTechnician = By.id("dispatch_fjx_assignlogo']");
    private By searchingPerson = By.id("k-5f331564-1485-4d2f-9e1a-3d5bf339aa0d");
    private By selectingPerson = By.id("b24fef59-5710-43ad-9782-6a6ccbcdf492-Brian Wright                                      ::795D236B-E8DD-4513-852D-231267F17DCC");
    private By assignTechButton = By.id("//button[@class='btn btn-primary']");
    private By verifyingAssignedTech = By.id("//div[text()='Technician:']//following-sibling::div");
    private By finalAssignButton = By.id("//button[@class='btn btn-primary']");

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
