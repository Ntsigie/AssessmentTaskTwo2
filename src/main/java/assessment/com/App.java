package assessment.com;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;

import excel.com.Retrivingdata;

public class App {

	
	//Instatiation of webdriver
		WebDriver objdriver=new ChromeDriver();

		
		//This method navigates to the webtables and maximizes the screen  
		  @Test(priority=0)
		    public void NavigateToWebTables()
		    {
		        objdriver.navigate().to("http://www.way2automation.com/angularjs-protractor/webtables/");
		        objdriver.manage().window().maximize();
		        

		    }
		  
		  //This method validates that we have navigated to the WebTable Url	  
		  @Test(priority=1)
		    public void ValidateUrlNavigation()
		    {
			  String objtitle=objdriver.getCurrentUrl();
			  
				String objtitle2="http://www.way2automation.com/angularjs-protractor/webtables/";
				
				if(objtitle.equalsIgnoreCase(objtitle2))
				{
					System.out.println("UrlNavigation verified");
				}
				else
				{
					System.out.println("not on the correct Url");
				}
		    }
		  
		  //This method clicks the AddUser button on the WebTables page
		  @Test(priority=2)
		  public void ClickAddUser()
		  {
			  objdriver.findElement(By.cssSelector("button.pull-right")).click();
		  }
		  
		  //Step1=This method reads the data from an Excel spreadsheet TestData(Located in the Excel package)
		  //Step2=Adds users by passing the data from Excel to the WebTables the loop is dependent on the no users on TestData 
		  //Step3=It clicks the close button after the loop has completed	  
		  @Test(priority=3)
		  public void AddUsers() throws InterruptedException, IOException
		  {
			  Retrivingdata objapp=new Retrivingdata();
			  		  
			  ArrayList<String> FirstName=objapp.PassData(0);
			  ArrayList<String> LastName=objapp.PassData(1);
			  ArrayList<String> UserName=objapp.PassData(2);
			  ArrayList<String> Password=objapp.PassData(3);
			  ArrayList<String> Customer=objapp.PassData(4);
			  ArrayList<String> Role=objapp.PassData(5);
			  ArrayList<String> Email=objapp.PassData(6);
			  ArrayList<String> Cell=objapp.PassData(7);
			 
			
			  
			  for(int p=0;p<FirstName.size();p++)
			  {
				 
				  Thread.sleep(3000);
				  objdriver.findElement(By.cssSelector("tr.smart-table-edit-data-cell:nth-child(1) > td:nth-child(2) > input:nth-child(1)")).clear();
					objdriver.findElement(By.cssSelector("tr.smart-table-edit-data-cell:nth-child(1) > td:nth-child(2) > input:nth-child(1)")).sendKeys(FirstName.get(p));
								
					Thread.sleep(3000);
					objdriver.findElement(By.cssSelector("tr.smart-table-edit-data-cell:nth-child(2) > td:nth-child(2) > input:nth-child(1)")).clear();
					objdriver.findElement(By.cssSelector("tr.smart-table-edit-data-cell:nth-child(2) > td:nth-child(2) > input:nth-child(1)")).sendKeys(LastName.get(p));
					
					Thread.sleep(3000);
					objdriver.findElement(By.cssSelector("tr.smart-table-edit-data-cell:nth-child(3) > td:nth-child(2) > input:nth-child(1)")).clear();
					objdriver.findElement(By.cssSelector("tr.smart-table-edit-data-cell:nth-child(3) > td:nth-child(2) > input:nth-child(1)")).sendKeys(UserName.get(p));
					
					Thread.sleep(3000);
					objdriver.findElement(By.cssSelector("tr.smart-table-edit-data-cell:nth-child(4) > td:nth-child(2) > input:nth-child(1)")).clear();
					objdriver.findElement(By.cssSelector("tr.smart-table-edit-data-cell:nth-child(4) > td:nth-child(2) > input:nth-child(1)")).sendKeys(Password.get(p));
					
					//Selecting radio button
					Thread.sleep(3000);
					 if (Customer.contains("Company AAA ")) {
			             WebElement RadioButton = (objdriver.findElement(By.cssSelector("body > div.modal.ng-scope > div.modal-body > form > table > tbody > tr:nth-child(5) > td:nth-child(2) > label:nth-child(1)")));
			         RadioButton.click();
			             
			         }
			         else {
			             WebElement RadioButton = (objdriver.findElement(By.cssSelector("body > div.modal.ng-scope > div.modal-body > form > table > tbody > tr:nth-child(5) > td:nth-child(2) > label:nth-child(2)")));
			             RadioButton.click();
			         }

									
					Thread.sleep(3000);
					Select select=new Select(objdriver.findElement(By.cssSelector("select.ng-pristine")));
					select.selectByVisibleText(Role.get(p));

					Thread.sleep(3000);
					objdriver.findElement(By.xpath("/html/body/div[3]/div[2]/form/table/tbody/tr[6]/td[2]/select")).sendKeys(Role.get(p));
					 		
					Thread.sleep(3000);
					objdriver.findElement(By.cssSelector(".ng-valid-email")).clear();
					objdriver.findElement(By.cssSelector(".ng-valid-email")).sendKeys(Email.get(p));
					
					Thread.sleep(3000);
					objdriver.findElement(By.cssSelector("tr.smart-table-edit-data-cell:nth-child(8) > td:nth-child(2) > input:nth-child(1)")).clear();
					objdriver.findElement(By.cssSelector("tr.smart-table-edit-data-cell:nth-child(8) > td:nth-child(2) > input:nth-child(1)")).sendKeys(Cell.get(p));
					
					objdriver.findElement(By.cssSelector("button.btn:nth-child(2)")).click(); 
					ClickAddUser();
			  }
			  
			  objdriver.findElement(By.cssSelector(".btn-danger")).click();
			 
		  }
		  
		  
		  //This method verifies that the users from TestData are added to the Webtable by taking a screenshot(stored under the screenshot folder)
		  @Test(priority=4)
		  public void VerifyUserAddition() throws Exception
		  {
			  TakesScreenshot objshot=(TakesScreenshot)objdriver;
			  
			  File source=objshot.getScreenshotAs(OutputType.FILE);

			  FileUtils.copyFile(source,new File("./Screenshots/Webtables6.png"));
			  
			  System.out.println("Screenshot taken users were added succesfully");
			  
			  objdriver.close();
			
		  }
		  
		 
		  

		 
		
		 
}
