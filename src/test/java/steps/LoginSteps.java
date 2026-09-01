package steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import utils.CommonMethods;
import utils.ExcelReader;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public class LoginSteps extends CommonMethods {

    @Given("user navigates to the SauceDemo login page")
    public void user_navigates_to_the_saucedemo_login_page() {
        openBrowser();
    }

    @Then("user is able to see the {string} input field")
    public void user_is_able_to_see_the_input_field(String fieldName) {

        switch (fieldName) {
            case "Username":
                Assert.assertTrue(loginPage.isUsernameFieldDisplayed());
                break;
            case "Password":
                Assert.assertTrue(loginPage.isPasswordFieldDisplayed());
                break;
        }
    }

    @Then("user is able to see the {string} button")
    public void user_is_able_to_see_the_button(String buttonName) {

        if (buttonName.equalsIgnoreCase("Login")) {
            Assert.assertTrue(loginPage.isLoginButtonDisplayed());
        }
    }

    @Then("user is able to see that the {string} button is enabled")
    public void user_is_able_to_see_that_the_button_is_enabled(String buttonName) {

        if (buttonName.equalsIgnoreCase("Login")) {
            Assert.assertTrue(loginPage.isLoginButtonEnabled());
        }
    }

    @When("user enters {string} into the Username field")
    public void user_enters_into_the_username_field(String username) {

        loginPage.enterUsername(username);
    }

    @When("user enters {string} into the Password field")
    public void user_enters_into_the_password_field(String password) {

        loginPage.enterPassword(password);
    }

    @When("user leaves the Username field empty")
    public void user_leaves_the_username_field_empty() {

        loginPage.enterUsername("");
    }

    @When("user leaves the Password field empty")
    public void user_leaves_the_password_field_empty() {

        loginPage.enterPassword("");
    }

    @When("user clicks the Login button")
    public void user_clicks_the_login_button() {
        loginPage.clickLoginButton();
    }

    @Then("user is able to see the Products page")
    public void user_is_able_to_see_the_products_page() {
        Assert.assertTrue(loginPage.isRedirectedToUrl("https://www.saucedemo.com/inventory.html"));
    }

    @Then("user is redirected to {string}")
    public void user_is_redirected_to(String expectedUrl) {

        Assert.assertTrue(loginPage.isRedirectedToUrl(expectedUrl));
    }

    @Then("user is not able to log in")
    public void user_is_not_able_to_log_in() {

        Assert.assertTrue(loginPage.isErrorMessageDisplayed());
    }

    @Then("user is able to see the error message {string}")
    public void user_is_able_to_see_the_error_message(String expectedErrorMessage) {

        String actualErrorMessage = loginPage.getErrorMessageText();
        Assert.assertEquals(expectedErrorMessage, actualErrorMessage);
    }

    @Then("user is able to see the error message {string} beside {string} field")
    public void user_is_able_to_see_the_error_message_beside_field(String expectedErrorMessage, String fieldName) {
        String actualErrorMessage = loginPage.getErrorMessageText();
        Assert.assertEquals(expectedErrorMessage, actualErrorMessage);
    }

    @When("user logs in using credentials from the {string} sheet in the Excel test data file")
    public void user_logs_in_using_credentials_from_the_excel_test_data_file(String sheetName) throws IOException {

        List<Map<String, String>> excelData = ExcelReader.read(sheetName);
        Map<String, String> credentials = excelData.get(0);

        loginPage.enterUsername(credentials.get("username"));
        loginPage.enterPassword(credentials.get("password"));
        loginPage.clickLoginButton();
    }
}