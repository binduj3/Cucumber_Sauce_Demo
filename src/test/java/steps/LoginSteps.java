package steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import utils.CommonMethods;
import utils.ExcelReader;
import utils.Log;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public class LoginSteps extends CommonMethods {

    @Given("user navigates to the SauceDemo login page")
    public void user_navigates_to_the_saucedemo_login_page() {
        Log.info("Navigating to the SauceDemo login page");
        openBrowser();
        Log.info("Login page loaded successfully");
    }

    @Then("user is able to see the {string} input field")
    public void user_is_able_to_see_the_input_field(String fieldName) {
        Log.info("Verifying that the '" + fieldName + "' input field is displayed");

        switch (fieldName) {
            case "Username":
                Assert.assertTrue(loginPage.isUsernameFieldDisplayed());
                break;
            case "Password":
                Assert.assertTrue(loginPage.isPasswordFieldDisplayed());
                break;
        }
        Log.info("'" + fieldName + "' input field is displayed as expected");
    }

    @Then("user is able to see the {string} button")
    public void user_is_able_to_see_the_button(String buttonName) {
        Log.info("Verifying that the '" + buttonName + "' button is displayed");

        if (buttonName.equalsIgnoreCase("Login")) {
            Assert.assertTrue(loginPage.isLoginButtonDisplayed());
        }
        Log.info("'" + buttonName + "' button is displayed as expected");
    }

    @Then("user is able to see that the {string} button is enabled")
    public void user_is_able_to_see_that_the_button_is_enabled(String buttonName) {
        Log.info("Verifying that the '" + buttonName + "' button is enabled");

        if (buttonName.equalsIgnoreCase("Login")) {
            Assert.assertTrue(loginPage.isLoginButtonEnabled());
        }
        Log.info("'" + buttonName + "' button is enabled as expected");
    }

    @When("user enters {string} into the Username field")
    public void user_enters_into_the_username_field(String username) {
        Log.info("Entering username: " + username);
        loginPage.enterUsername(username);
    }

    @When("user enters {string} into the Password field")
    public void user_enters_into_the_password_field(String password) {
        Log.info("Entering password: ****");
        loginPage.enterPassword(password);
    }

    @When("user leaves the Username field empty")
    public void user_leaves_the_username_field_empty() {
        Log.info("Leaving the Username field empty");
        loginPage.enterUsername("");
    }

    @When("user leaves the Password field empty")
    public void user_leaves_the_password_field_empty() {
        Log.info("Leaving the Password field empty");
        loginPage.enterPassword("");
    }

    @When("user clicks the Login button")
    public void user_clicks_the_login_button() {
        Log.info("Clicking the Login button");
        loginPage.clickLoginButton();
    }

    @Then("user is able to see the Products page")
    public void user_is_able_to_see_the_products_page() {
        Log.info("Verifying redirection to the Products page");
        boolean redirected = loginPage.isRedirectedToUrl("https://www.saucedemo.com/inventory.html");
        Assert.assertTrue(redirected);
        Log.info("Products page loaded successfully");
    }

    @Then("user is not able to log in")
    public void user_is_not_able_to_log_in() {
        Log.info("Verifying that login was blocked and an error message is displayed");
        Assert.assertTrue(loginPage.isErrorMessageDisplayed());
        Log.info("Login was blocked as expected");
    }

    @Then("user is able to see the error message {string}")
    public void user_is_able_to_see_the_error_message(String expectedErrorMessage) {
        Log.info("Verifying error message. Expected: " + expectedErrorMessage);
        String actualErrorMessage = loginPage.getErrorMessageText();
        Log.info("Actual error message: " + actualErrorMessage);
        Assert.assertEquals(expectedErrorMessage, actualErrorMessage);
        Log.info("Error message matched expected value");
    }

    @When("user logs in using credentials from the {string} sheet in the Excel test data file")
    public void user_logs_in_using_credentials_from_the_excel_test_data_file(String sheetName) throws IOException {
        Log.info("Reading login credentials from Excel sheet: " + sheetName);
        List<Map<String, String>> excelData = ExcelReader.read(sheetName);
        Map<String, String> credentials = excelData.get(0);
        Log.debug("Excel row used: username=" + credentials.get("username") + ", password=****");

        loginPage.enterUsername(credentials.get("username"));
        loginPage.enterPassword(credentials.get("password"));
        loginPage.clickLoginButton();
        Log.info("Login submitted using credentials from Excel sheet: " + sheetName);
    }
}
