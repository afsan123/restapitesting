package tests;

import aquality.selenium.browser.AqualityServices;
import aquality.selenium.browser.Browser;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import utility.ConfigReader;

public class BaseClass {
    protected Browser browser;

    @BeforeMethod
    public void setUp() {
        browser = AqualityServices.getBrowser();
        browser.maximize();
        browser.goTo(ConfigReader.getBaseUrl());
    }

    @AfterMethod
    public void tearDown() {
        browser.quit();
    }
}
