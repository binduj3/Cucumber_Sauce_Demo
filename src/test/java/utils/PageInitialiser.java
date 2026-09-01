package utils;

import pages.AddEmployeePage;
import pages.CreateEmployeeLoginPage;
import pages.EditPersonalDetailsPage;
import pages.LoginPage;

public class PageInitialiser {

    public static AddEmployeePage addEmployeePage;
    public static LoginPage loginPage;
    public static CreateEmployeeLoginPage createEmployeeLoginPage;
    public static EditPersonalDetailsPage editPersonalDetailsPage;

    public static void initializePageObjects(){
        addEmployeePage = new AddEmployeePage();
        loginPage = new LoginPage();
        createEmployeeLoginPage = new CreateEmployeeLoginPage();
        editPersonalDetailsPage = new EditPersonalDetailsPage();
    }
}
