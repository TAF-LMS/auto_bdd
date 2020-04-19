package in.bntu.lms.pages;

import in.bntu.lms.framework.annotation.PageInfo;
import in.bntu.lms.framework.base.BasePage;
import in.bntu.lms.framework.ui.ElementHandler;
import org.openqa.selenium.By;

@PageInfo(css = ".user-panel", pageName = "Страница: Система управления учебным процессом")
public class LmsMainPage extends BasePage {
    public final SubjectsForm getSubjectsForm() {
        return new SubjectsForm();
    }

    public final ElementHandler getSubjectSideBarButton() {
        return new ElementHandler(By.xpath(String.format("//*[@class='sidebar-menu']//*[contains(text(), '%s')]", "Предметы")), "Предметы");
    }
}
