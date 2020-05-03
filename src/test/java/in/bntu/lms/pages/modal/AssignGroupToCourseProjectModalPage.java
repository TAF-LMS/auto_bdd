package in.bntu.lms.pages.modal;

import in.bntu.lms.framework.annotation.PageInfo;
import in.bntu.lms.framework.base.BasePage;
import in.bntu.lms.framework.ui.ElementHandler;
import org.openqa.selenium.By;

@PageInfo(css = "[role=dialog] [ng-click*=assignProject]", pageName = "Модальное окно: Назначение темы курсового проекта (работы)")
public class AssignGroupToCourseProjectModalPage extends BasePage {

    public final ElementHandler getAssignButton(String student) {
        return new ElementHandler(By.xpath(String.format(
                "//*[contains(text(), 'Назначение темы курсового проекта (работы)')]/../following-sibling::div//table//tr[./td[text() = '%s']]//i", student)),
                "Назначить студента: " + student);
    }
}
