package barGame;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


//This class has all necessary functions to make execution of the test case efficient

public class BarFunctions {
	WebDriver driver;
	
// Constructor to initialize the driver
	public BarFunctions(WebDriver driver) {
		this.driver = driver;
	}
	
// Method to fill in details in the left bowl. Argument boxNum indicates the box number inn the left bowl and argument value 
// represents what value needs to be entered in the box number
	public void leftBowlBox(String boxNum, String value) {
		String leftBox = "//input[@id='left_" + boxNum + "']";
		WebElement element = driver.findElement(By.xpath(leftBox));
		element.sendKeys(value);
	}
	
// Method to fill in details in the right bowl. Argument boxNum indicates the box number in the right bowl and argument value 
// represents what value needs to be entered in the box number
	public void rightBowlBox(String boxNum, String value) {
		String rightBox = "//input[@id='right_" + boxNum + "']";
		WebElement element = driver.findElement(By.xpath(rightBox));
		element.sendKeys(value);
	}

//	Method to click on the weight button
	public void clickOnWeighButton() {
		WebElement element = driver.findElement(By.id("weigh"));
		element.click();
	}
	
// Method to get the result in result box (possible return value is >,<,=)
	public String getResult() {
		WebElement element = driver.findElement(By.xpath("//div[text()='Result']//following-sibling::button"));
		String result = element.getText();
		return result;
	}

// Method to click on the reset button
	public void clickOnResetButton() {
		WebElement element = driver.findElement(By.xpath("//button[text()='Reset']"));
		element.click();
	}
	
// Method to click on a particular coin/bar displayed in series below the left and right bowl.The coinNum arguments takes 
// input of what particular coin has to be clicked
	public void clickOnCoin(String coinNum) {
		String coinID = "coin_" + coinNum;
		WebElement element = driver.findElement(By.id(coinID));
		element.click();
	}
	
// Method to display the total number of weighings and to print out all the weighings. 
	public void listOfAllWeighings() {
		List<WebElement> elements = driver.findElements(By.tagName("li"));
		System.out.println("The number of weighings - " + elements.size());
		System.out.println("The weightings are as below - ");
		for(WebElement w : elements) {
			System.out.println(w.getText());
		}
	}

}