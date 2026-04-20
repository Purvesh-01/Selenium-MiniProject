package com.orangehrm.page;

import com.orangehrm.base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class GooglePage extends BasePage {

    //per page object repo
    By searchBox=By.name("q");
    By searchResult=By.id("search");

    //constructor to call super basePage
    public GooglePage(WebDriver driver) {
        super(driver);
    }

    //opening google
    public void openGooglePage(String url){
        driver.get(url);
    }

    //searching on google search box
    public void search(String text){
        type(searchBox,text);
    }

    public boolean resultDisplayed(){
        return  isDisplayed(searchResult);
    }



}
