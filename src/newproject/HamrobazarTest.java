package newproject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

class HamrobazarTest {
	@Test
	public static void main(String[] args) {
		System.setProperty("webdriver.chrome.driver", "resources\\newchromedriver.exe");

		// open a new chrome window
		WebDriver browser = new ChromeDriver();

		// maximize the chrome window
		browser.manage().window().maximize();

		// visit hamrobazar
		browser.get("https://hamrobazaar.com/");

		// wait for 10 seconds
		browser.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);

		// find search bar with the relative xpath
		WebElement searchBar = browser.findElement(By.xpath("//table[@class='mainbody']//table//tbody//tr//td//"
				+ "table//tbody//tr//td//table//tbody//tr//td//div//input[@name='searchword']"));

		// search for iphone
		searchBar.sendKeys("iphone");

		// click Go button
		browser.findElement(By.xpath("//body//div//input[2]")).click();

		// find filter dorpdown
		Select dropDownFilter = new Select(browser.findElement(By.xpath(
				"//body/table[@class='mainbody']/tbody/tr" + "/td/table/tbody/tr/td/table/tbody/tr/td/select[1]")));

		// order the products according to price: high to low
		dropDownFilter.selectByVisibleText("Price: High to Low");

		// defining two arrays
		ArrayList<Integer> priceList = new ArrayList<Integer>();
		ArrayList<Integer> toBeSortedArrayList = new ArrayList<Integer>();

		// listing all prices and replacing "RS" and "," then trimming in both array
		List<WebElement> priceObject = browser.findElements(By.xpath("//table[*]//tbody[1]//tr[1]//td[5]//b[1]"));
		try {
			for (WebElement a : priceObject) {
				priceList.add(Integer.parseInt(a.getText().replace("Rs.", "").replace(",", "").trim()));
				toBeSortedArrayList.add(Integer.parseInt(a.getText().replace("Rs.", "").replace(",", "").trim()));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		// sorting toBeSortedArrayList array in descending order
		Collections.sort(toBeSortedArrayList, Collections.reverseOrder());
		Boolean checkArray = true;
		for (int i = 0; i < toBeSortedArrayList.size(); i++) {

			// checking of actually sorted prices matches the our sorted price array
			if (priceList.get(i) > toBeSortedArrayList.get(i) || priceList.get(i) < toBeSortedArrayList.get(i)) {
				checkArray = false;
			}
		}

		// if true then successful
		if (checkArray) {
			System.out.println("Actual: " + priceList.toString());
			System.out.println("Expected: " + toBeSortedArrayList.toString());
			System.out.println("Price Has been sorted in descending(high to low) order successfully");
		}

		// else not successful
		else {
			System.out.println("Price not sorted in descending order successfully");
		}
	}
}
