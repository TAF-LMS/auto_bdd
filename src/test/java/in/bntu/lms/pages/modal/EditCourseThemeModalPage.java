package in.bntu.lms.pages.modal;

import in.bntu.lms.framework.annotation.PageInfo;
import in.bntu.lms.framework.base.BasePage;
import in.bntu.lms.framework.ui.ElementHandler;
import org.openqa.selenium.By;

@PageInfo(css = "[ng-submit*=saveProject]", pageName = "Модальное окно: Редактирование темы курсового проекта (работы)")
public class EditCourseThemeModalPage extends BasePage {

    public final ElementHandler getThemeInput() {
        return new ElementHandler(By.cssSelector("[name=Theme]"), "Тема");
    }

    public final ElementHandler getGroupOption(String group) {
        return new ElementHandler(By.xpath(String.format("//select[@multiple='multiple']/*[text() = '%s']", group)), "Добавить группу: " + group);
    }

    public final ElementHandler getSaveButton() {
        return new ElementHandler(By.cssSelector("[ng-submit*=saveProject] [type=submit]"), "Сохранить");
    }
}
