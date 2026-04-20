package com.orangehrm.page;

import com.orangehrm.base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class ContactSalesPage extends BasePage {

    public ContactSalesPage(WebDriver driver){
        super(driver);
    }

    //locators
    By fullName=By.name("FullName");
    By emailBox=By.name("Email");
    By phone=By.name("Contact");
    By countryDropdown =By.name("Country");
    By job=By.name("JobTitle");
    By companyNamefield=By.name("CompanyName");
    By noOfEmployeesDropdown=By.name("NoOfEmployees");
    By messageBox=By.name("Comment");
    By submitButton=By.name("action_submitForm");


    //actions
    //form filling
    //filling fields
    public void typeName(String name){
        type(fullName,name);
    }
    public void typeEmail(String email){
        type(emailBox,email);
    }
    public void typePhone(String phoneNumber){
        type(phone,phoneNumber);
    }
    public void typeJobTitle(String jobTitle){
        type(job,jobTitle);
    }
    public void typeCompanyName(String companyName){
        type(companyNamefield,companyName);
    }
    public void typeMessage(String message){
        type(messageBox,message);
    }

    //filling dropdowns
    public void typeCountry(String countryName){
        WebElement country=driver.findElement(countryDropdown);
        Select select=new Select(country);
        select.selectByVisibleText(countryName);

    }
    public void typeNoOfEmployees(String noOfEmployees){
        Select select=new Select(driver.findElement(noOfEmployeesDropdown));
        select.selectByValue(noOfEmployees);
    }


    public void formFillWithoutMessage(String name,String email,String phone,String job,String company,String country,String employee){
        typeName(name);
        typeEmail(email);
        typePhone(phone);
        typeJobTitle(job);
        typeCompanyName(company);
        typeCountry(country);
        typeNoOfEmployees(employee);
        //no message
    }
    //submitting form
    public void submitButton(){
        click(submitButton);
    }

    //capture ss
    public String captureScreenShot(String fileName){
        return takeScreenshot(fileName);
    }

}
