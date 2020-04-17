package in.bntu.lms.pages.modal;

import in.bntu.lms.framework.annotation.PageInfo;
import in.bntu.lms.framework.base.BasePage;
import in.bntu.lms.framework.ui.ElementHandler;
import org.openqa.selenium.By;

@PageInfo(css = ".modal-dialog [data-bb-handler=danger] + [data-bb-handler=success]",
        pageName = "Модальное окно: Подтверждение")
public class ConfirmationModalPage extends BasePage {

    public final ElementHandler getAcceptButton() {
        return new ElementHandler(By.cssSelector("[data-bb-handler=success]"), "Отмена");
    }

    public final ElementHandler getDeclineButton() {
        return new ElementHandler(By.cssSelector("[data-bb-handler=danger]"), "Удалить");
    }
}
