package test;

import base.BaseTest;
import com.orangehrm.page.ContactSalesPage;
import com.orangehrm.page.GooglePage;
import com.orangehrm.page.OrangeHrmPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class NavigationTest extends BaseTest {

    private GooglePage google;
    private OrangeHrmPage orangeHrm;
    private ContactSalesPage contactSales;
    private String originalWindow;
    private String contactWindow;

    @Test(priority = 0)
    public void navigationTest() {

        extentTest.info("Navigation test started");

        google = new GooglePage(driver);
        orangeHrm = new OrangeHrmPage(driver);

        // 1. Open Google
        extentTest.info("Opening Google page");
        google.openGooglePage(prop.getProperty("google.url"));

        Assert.assertTrue(driver.getCurrentUrl().contains("google.com"));
        extentTest.pass("Google page opened successfully");

        // 2. Search for OrangeHRM
        extentTest.info("Searching for OrangeHRM");
        google.search(prop.getProperty("google.search"));
        google.navigateTo(prop.getProperty("orangeHrmResult.url"));

        Assert.assertTrue(driver.getCurrentUrl().contains("orangehrm.com"));
        extentTest.pass("OrangeHRM page opened successfully");

        // 3. Navigate back
        extentTest.info("Navigating back to Google");
        google.navigateBack();

        Assert.assertTrue(driver.getCurrentUrl().contains("google.com"));
        extentTest.pass("Navigated back to Google successfully");

        // 4. Navigate forward
        extentTest.info("Navigating forward to OrangeHRM");
        google.navigateForward();

        Assert.assertTrue(driver.getCurrentUrl().contains("orangehrm.com"));
        extentTest.pass("Navigated forward to OrangeHRM successfully");

        extentTest.pass("Navigation test completed successfully");
    }

    @Test(priority = 1, dependsOnMethods = "navigationTest")
    public void contactSalesTest() throws InterruptedException {

        extentTest.info("Contact Sales test started");

        orangeHrm.dismissCookiePopup();
        extentTest.info("Cookie popup dismissed");

        originalWindow = driver.getWindowHandle();
        extentTest.info("Stored original window handle");

        extentTest.info("Clicking Contact Sales button");
        orangeHrm.clickContactSalesButton();

        contactSales = new ContactSalesPage(driver);

        for (String handle : driver.getWindowHandles()) {
            if (!handle.equals(originalWindow)) {
                driver.switchTo().window(handle);
                break;
            }
        }
        extentTest.info("Switched to Contact Sales window");

        extentTest.info("Filling form without message");
        contactSales.formFillWithoutMessage(
                prop.getProperty("orangeHrm.name"),
                prop.getProperty("orangeHrm.email"),
                prop.getProperty("orangeHrm.phone"),
                prop.getProperty("orangeHrm.jobTitle"),
                prop.getProperty("orangeHrm.companyName"),
                prop.getProperty("orangeHrm.country"),
                prop.getProperty("orangeHrm.noOfEmployees")
        );

        extentTest.info("Submitting form without message");
        contactSales.submitButton();
        Thread.sleep(2000);

        extentTest.info("Capturing validation screenshot");
        contactSales.captureScreenShot("Without_Message");

        extentTest.info(    "Entering message");
        contactSales.typeMessage(prop.getProperty("orangeHrm.message"));

        contactWindow = driver.getWindowHandle();
        extentTest.info("Stored contact window handle");

        extentTest.info("Submitting form with message");
        contactSales.typeMessage(prop.getProperty("orangeHrm.message"));
        System.out.println("Solve captcha till 40 seconds ");
        extentTest.info("Solving captcha...");
        Thread.sleep(50000);
        contactSales.submitButton();

        for (String handle : driver.getWindowHandles()) {
            if (!handle.equals(contactWindow)) {
                driver.switchTo().window(handle);
                break;
            }
        }

        extentTest.info("Capturing confirmation screenshot");
        contactSales.captureScreenShot("With_Message");

        extentTest.pass("Contact Sales test completed successfully");
    }

    @Test(priority = 2, dependsOnMethods = "contactSalesTest")
    public void windowHandling() {

        extentTest.info("Window handling test started");

        if (driver.getWindowHandles().size() > 1) {
            driver.close();
            driver.switchTo().window(contactWindow);
            extentTest.info("Closed confirmation window");
        }

        if (driver.getWindowHandles().size() > 1) {
            driver.close();
            driver.switchTo().window(originalWindow);
            extentTest.info("Returned to main OrangeHRM window");
        }

        Assert.assertEquals(driver.getWindowHandle(), originalWindow);
        extentTest.pass("Window handling completed successfully");
    }
}