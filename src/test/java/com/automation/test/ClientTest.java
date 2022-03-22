package com.automation.test;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.automation.client.AddClientPage;
import com.automation.client.Customer;

public class ClientTest {
	
	private WebDriver driver;
	private AddClientPage addClientPage;
		
	@BeforeTest
    public void setup() throws IOException {
    	String baseUrl = "http://localhost:9090/";
		System.setProperty("webdriver.chrome.driver", "E:\\Softwares\\chrome_driver\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.get(baseUrl);
		driver.findElement(By.linkText("New Client")).click();
		addClientPage = new AddClientPage(driver); 		
    }
	
	 @Test
	 public void addCustomerTest() throws IOException {
		Customer customer = new Customer("Tania","Roche","tania@gmail.com",7890000034L,"10, Stratford, London");
		//addClientPage.add_Customer("Tania", "Roche", "tania_r@gmail.com", 9808000335L, "56, Whitefield Road,London"); 
		String expected = addClientPage.addClient(customer); 
		Assert.assertEquals(expected, "Client Contact Management");
	}
	 
	@Test
	public void updateCustomerTest() throws IOException {
		Customer customer = new Customer("TaniaUp","RocheUp","tania@gmail.com",7890000034L,"93, Stratford, London");
	    addClientPage.addClient(customer);
	    customer.setLastName("Munoz");
	    customer.setEmail("tania_munoz@gmail.com");
	    addClientPage.updateClient(customer, customer.getFirstName());
	}
	
	@Test 
	public void deleteCustomerTest() throws IOException {
	   Customer customer = new Customer("TaniaDe","RocheDe","tania@gmail.com",7890000034L,"93, Stratford, London");
	   addClientPage.addClient(customer);
	   addClientPage.deleteClient(customer.getFirstName()); 
	}
	
	@AfterTest 
	public void tearDown() throws IOException {
		//driver.close();
		//driver.quit(); 
	} 
}