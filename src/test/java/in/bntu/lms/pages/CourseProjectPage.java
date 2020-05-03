package in.bntu.lms.pages;

import in.bntu.lms.framework.annotation.PageInfo;
import in.bntu.lms.framework.base.BasePage;
import in.bntu.lms.framework.ui.ElementHandler;
import org.openqa.selenium.By;

@PageInfo(xpath = "//h1/small[contains(text(), 'Объявления по курсовому проектированию')]", pageName = "Страница: Курсовое проектирование")
public class CourseProjectPage extends BasePage {
    private static final String SIDE_BAR_BUTTON = "//*[@class='sidebar-menu']//*[contains(text(), '%s')]";

    public final ElementHandler getCourseThemeSideBarButton() {
        return new ElementHandler(getSideBarLocator("Темы курсовых проектов (работ)"), "Темы курсовых проектов (работ)");
    }

    public final ElementHandler getEditThemeButton(String name) {
        return new ElementHandler(By.xpath(String.format("//tr[./td[text() = '%s']]//*[contains(@class, 'edit')]", name)),
                "Редактировать тему: " + name);
    }

    private By getSideBarLocator(String name) {
        return By.xpath(String.format(SIDE_BAR_BUTTON, name));
    }

}
