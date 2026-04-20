package com.orangehrm.page;

import com.orangehrm.base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class OrangeHrmPage extends BasePage{

    public OrangeHrmPage(WebDriver driver){
        super(driver);
    }

    //locators
    //*[@id="navbarNav"]/ul[2]/li[3]/a/button


    private By clickButton = By.xpath("(//a[contains(@href,'/contact-sales')]//button)[2]");
    private By cookieAllowButton = By.id("CybotCookiebotDialogBodyLevelButtonLevelOptinAllowAll");

    //navigate to orangeHrm
    public void goToOrangeHrmPage(String url){
        navigateTo(url);
    }
    //click contactSales Button
    public void clickContactSalesButton(){
        try{
            click(clickButton);
        }
        catch(Exception e){
            System.out.println("Click Failed "+e.getMessage());
            //navigateTo("https://www.orangehrm.com/en/contact-sales/");
        }

    }

    //dismiss cookie
    public void dismissCookiePopup() {
        try {
            click(cookieAllowButton);
            Thread.sleep(1000);
        } catch (Exception e) {
            System.out.println("Cookie popup not found or already dismissed.");
        }
    }




}
