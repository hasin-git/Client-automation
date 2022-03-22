package com.automation.client;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AddClientPage {
	
	private static final int TIMEOUT_SECONDS = 50;
	
	private WebDriver driver;
	
	private By firstName = By.id("firstName");	
	private By lastName = By.id("lastName");
	private By email = By.id("email");
	private By contactNo = By.id("contactNo");
	private By address = By.id("address");
	private By save = By.id("save");
	private By update = By.id("update");
	private By successMsg = By.tagName("h3");
		
	 public AddClientPage(WebDriver driver) {
		 this.driver = driver; 
	 }
	 
	 public void clickAddClient() {			
		driver.findElement(By.linkText("New Client")).click();				
	 }				
	
	 public String add_Customer(String fName, String lName, String emailId, Long contact, String address1) throws IOException {
		
		WebDriverWait wait = new WebDriverWait(driver, TIMEOUT_SECONDS);
		clickAddClient();
		
		WebElement fNameElement = wait.until(ExpectedConditions.elementToBeClickable(firstName));
		fNameElement.sendKeys(fName);

	    WebElement lNameElement = wait.until(ExpectedConditions.elementToBeClickable(lastName));
	    lNameElement.sendKeys(lName);

	    WebElement emailElement = wait.until(ExpectedConditions.elementToBeClickable(email));
	    emailElement.sendKeys(emailId);

	    WebElement contactElement = wait.until(ExpectedConditions.elementToBeClickable(contactNo));
	    contactElement.sendKeys(contact.toString());

	    
	    WebElement addressElement = wait.until(ExpectedConditions.elementToBeClickable(address));
	    addressElement.sendKeys(address1);

	    WebElement submitElement = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@type='submit']")));
	    submitElement.click();
	    
	    String actual_title = driver.findElement(successMsg).getText();
	    System.out.println("Actual===" + actual_title);
		
		return actual_title;
	}
	
	
	private void fillCustomerForm(Customer customer) {
		driver.findElement(firstName).sendKeys(customer.getFirstName());
		driver.findElement(lastName).sendKeys(customer.getLastName());
		driver.findElement(email).sendKeys(customer.getEmail());
		driver.findElement(contactNo).sendKeys(customer.getContactNo().toString());
		driver.findElement(address).sendKeys(customer.getAddress());		
	}
	
	public String addClient(Customer customer) {
		fillCustomerForm(customer);
		driver.findElement(save).click();
		return driver.getTitle();
	}
	
	public String updateClient(Customer customer, String fName) {		
		driver.findElement(By.xpath("//td[text()='"+fName+"']//following-sibling::td/a[text()='Update']")).click();
		fillCustomerForm(customer);
		driver.findElement(update).click();
		return driver.getTitle();
	}
	
	public String deleteClient(String fName) {
		driver.findElement(By.xpath("//td[text()='"+fName+"']//following-sibling::td/a[text()='Delete']")).click();
		return driver.findElement(successMsg).getText();
	}
}
