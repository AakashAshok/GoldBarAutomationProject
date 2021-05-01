package barGame;

import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import barGame.BarFunctions;

public class TestCase {

	public static void main(String[] args) throws InterruptedException {

//	Setting up path to access the chrome driver to start execution of test cases with Selenium
		String Path = System.getProperty("user.dir");
		String chromePath = Path + "/chromedriver";

//	Initializing the driver and launching the browser with the gold bar game webpage

		System.setProperty("webdriver.chrome.driver", chromePath);
		WebDriver driver = new ChromeDriver();
		driver.get("http://ec2-54-208-152-154.compute-1.amazonaws.com/");

		String finalResult = "0";
		BarFunctions BF = new BarFunctions(driver);
		
// Populating the value of left bowl and right bowl by splitting it from the mid point.
		BF.leftBowlBox("0", "0");
		BF.leftBowlBox("1", "1");
		BF.leftBowlBox("2", "2");
		BF.leftBowlBox("3", "3");
		BF.rightBowlBox("0", "4");
		BF.rightBowlBox("1", "5");
		BF.rightBowlBox("2", "6");
		BF.rightBowlBox("3", "7");

// Once the split is done click on the weigh button to check the result
		BF.clickOnWeighButton();
		Thread.sleep(3000);
		String result;
		result = BF.getResult();
		Thread.sleep(4000);

// Below the code to evaluate based on the result - if the value if the left bowl = right bowl then the 9th bar is the fake gold bar
// If the left bar < right bar - Split the values in the left bar based on the mid point again between the left bar and right bar till the value of gold bar is achieved
// If the right bar < left bar - Split the values in the right bar based on the mid point again between the left bar and right bar till the value of gold bar is achieved
// I've not used iteration as it will increase the time complexity.
		
		if (result.equals("=")) {
			finalResult = "8";
		} else if (result.equals("<")) {
			BF.clickOnResetButton();
			BF.leftBowlBox("0", "0");
			BF.leftBowlBox("1", "1");
			BF.rightBowlBox("0", "2");
			BF.rightBowlBox("1", "3");

			BF.clickOnWeighButton();
			Thread.sleep(3000);
			String result2 = BF.getResult();
			if (result2.equals(">")) {
				BF.clickOnResetButton();
				BF.leftBowlBox("0", "2");
				BF.rightBowlBox("0", "3");
				BF.clickOnWeighButton();
				Thread.sleep(3000);
				String result3 = BF.getResult();
				if (result3.equals("<")) {
					finalResult = "2";
				} else if (result3.equals(">")) {
					finalResult = "3";
				}

			} else if (result2.equals("<")) {
				BF.clickOnResetButton();
				BF.leftBowlBox("0", "0");
				BF.rightBowlBox("0", "1");
				BF.clickOnWeighButton();
				Thread.sleep(3000);
				String result3 = BF.getResult();
				if (result3.equals("<")) {
					finalResult = "0";
				} else if (result3.equals(">")) {
					finalResult = "1";
				}

			}

		} else if (result.equals(">")) {
			BF.clickOnResetButton();
			BF.leftBowlBox("0", "4");
			BF.leftBowlBox("1", "5");
			BF.rightBowlBox("0", "6");
			BF.rightBowlBox("1", "7");

			BF.clickOnWeighButton();
			Thread.sleep(3000);
			String result2 = BF.getResult();
			if (result2.equals(">")) {
				BF.clickOnResetButton();
				BF.leftBowlBox("0", "6");
				BF.rightBowlBox("0", "7");
				BF.clickOnWeighButton();
				Thread.sleep(3000);
				String result3 = BF.getResult();
				if (result3.equals("<")) {
					finalResult = "6";
				} else if (result3.equals(">")) {
					finalResult = "7";
				}

			} else if (result2.equals("<")) {
				BF.clickOnResetButton();
				BF.leftBowlBox("0", "4");
				BF.rightBowlBox("0", "5");
				BF.clickOnWeighButton();
				Thread.sleep(3000);
				String result3 = BF.getResult();
				if (result3.equals("<")) {
					finalResult = "4";
				} else if (result3.equals(">")) {
					finalResult = "5";
				}

			}

		}

//	The below statement prints out which of the 9 bars (0-8) is the fake gold bar
		System.out.println("The fake gold bar is - " + finalResult);

// 	Adding 2 seconds of sleep below so the result can be seen clearly during execution
//	Once the fake gold bar is found, clicking to check if the pop up is displayed
		BF.clickOnCoin(finalResult);
		Thread.sleep(2000);

//	Using the alert in build selenium method to switch to alert and print the text of the alert and accept the alert. 
		Alert alert = driver.switchTo().alert();
		System.out.println("The Alert Message is - " + alert.getText());
		alert.accept();
		Thread.sleep(2000);
		
//	Below statements prints the list of all the weighings that has been done before finding the fake gold bar
		BF.listOfAllWeighings();	
		Thread.sleep(2000);

// Quitting the driver once the process is complete
		driver.quit();
	}

}
