package in.bntu.lms.pages;

import in.bntu.lms.framework.annotation.PageInfo;
import in.bntu.lms.framework.base.BasePage;
import in.bntu.lms.framework.ui.ElementHandler;
import org.openqa.selenium.By;

@PageInfo(css = ".login-form", pageName = "Страница: Вход")
public class LoginPage extends BasePage {

    public ElementHandler getLoginInput() {
        return new ElementHandler(By.id("UserName"), "Login");
    }

    public ElementHandler getPasswordInput() {
        return new ElementHandler(By.id("Password"), "Password");
    }

    public ElementHandler getLoginButton() {
        return new ElementHandler(By.cssSelector("[type=submit]"), "Login");
    }
}
