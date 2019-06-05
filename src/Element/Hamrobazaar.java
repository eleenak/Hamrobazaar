package Element;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Hamrobazaar {
	WebDriver dvr;
    public Hamrobazaar(WebDriver paramdvr) {
        PageFactory.initElements(paramdvr, this);
        this.dvr = paramdvr;
        
    }

    
    @FindBy(xpath=".//input[1][@name='searchword']")
    WebElement Searchkeyword;
    
    @FindBy(xpath=".//input[@type='submit']")
    WebElement GoBtn;
    
    @FindBy(xpath=".//option[text()[contains(.,'Price: High to Low')]]")
    WebElement SortAdsByoption;
    
  
    
    public void SetSearchKeyword(String Keyword) {
    	Searchkeyword.sendKeys(Keyword);
    }
    public void GoButtonClick() {
    	GoBtn.click();
    }
    public void HighToLowOption() {
    	SortAdsByoption.click();
    }
    
}