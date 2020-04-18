package in.bntu.lms.pages;

import in.bntu.lms.framework.annotation.PageInfo;
import in.bntu.lms.framework.base.BasePage;
import in.bntu.lms.framework.ui.ElementHandler;
import org.openqa.selenium.By;

@PageInfo(css = ".user-panel", pageName = "Страница: Система управления учебным процессом")
public class LmsMainPage extends BasePage {
    private static final String SIDE_BAR_BUTTON = "//*[@class='sidebar-menu']//*[contains(text(), '%s')]";

    public final SubjectsForm getSubjectsForm() {
        return new SubjectsForm();
    }

    public final ElementHandler getSubjectSideBarButton() {
        return new ElementHandler(getSideBarLocator("Предметы"), "Предметы");
    }

    public final ElementHandler getNewsSideBarButton() {
        return new ElementHandler(getSideBarLocator("Новости"), "Новости");

    }

    private By getSideBarLocator(String name) {
        return By.xpath(String.format(SIDE_BAR_BUTTON, name));
    }
}
