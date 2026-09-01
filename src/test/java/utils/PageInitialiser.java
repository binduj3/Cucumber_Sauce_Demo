package utils;


import pages.LoginPage;

public class PageInitialiser {
    public static LoginPage loginPage;

    public static void initializePageObjects(){
        loginPage = new LoginPage();

    }
}
