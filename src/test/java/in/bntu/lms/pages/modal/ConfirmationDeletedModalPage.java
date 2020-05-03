package in.bntu.lms.pages.modal;

import in.bntu.lms.framework.annotation.PageInfo;
import in.bntu.lms.framework.base.BasePage;
import in.bntu.lms.framework.ui.ElementHandler;
import org.openqa.selenium.By;

@PageInfo(css = ".modal-dialog [data-bb-handler=cancel] + [data-bb-handler=confirm]",
        pageName = "Модальное окно: Отменить назначение курсового проекта (работы)")
public class ConfirmationDeletedModalPage extends BasePage {

    public final ElementHandler getAcceptButton() {
        return new ElementHandler(By.cssSelector(".modal-dialog [data-bb-handler=confirm]"), "Удалить");
    }

    public final ElementHandler getDeclineButton() {
        return new ElementHandler(By.cssSelector(".modal-dialog [data-bb-handler=cancel]"), "Отмена");
    }
}
