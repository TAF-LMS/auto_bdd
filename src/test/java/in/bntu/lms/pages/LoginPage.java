package in.bntu.lms.pages;

import in.bntu.lms.framework.annotation.PageInfo;
import in.bntu.lms.framework.base.BasePage;
import in.bntu.lms.framework.ui.ElementHandler;
import org.openqa.selenium.By;

@PageInfo(css = ".page_class", pageName = "Страница Входа")
public class LoginPage extends BasePage {

    public ElementHandler getLoginInput() {
        return new ElementHandler(By.cssSelector("input.login"), "Login");
    }

    public ElementHandler getPasswordInput() {
        return new ElementHandler(By.cssSelector("input.password"), "Password");
    }

    public ElementHandler getLoginButton() {
        return new ElementHandler(By.tagName("button"), "Login");
    }
}
