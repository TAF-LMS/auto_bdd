package in.bntu.lms.pages.modal;

import in.bntu.lms.framework.annotation.PageInfo;
import in.bntu.lms.framework.base.BasePage;
import in.bntu.lms.framework.ui.ElementHandler;
import org.openqa.selenium.By;

@PageInfo(id = "dialogexposeMarkOne", pageName = "Модальное окно: Выставление отметки")
public class EditMarkModalPage extends BasePage {

    public final ElementHandler getMarkInput() {
        return new ElementHandler(By.id("markInputOne"), "Отметка");
    }

    public final ElementHandler getSaveButton() {
        return new ElementHandler(By.cssSelector("#dialogexposeMarkOne a:not(#cancelButton)"), "Сохранить");
    }
}
