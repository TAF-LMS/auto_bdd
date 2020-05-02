package in.bntu.lms.pages.modal;

import in.bntu.lms.framework.annotation.PageInfo;
import in.bntu.lms.framework.base.BasePage;
import in.bntu.lms.framework.ui.ElementHandler;
import org.openqa.selenium.By;

@PageInfo(id = "dialogAddVisitData", pageName = "Модальное окно: График посещений")
public class ScheduleManagementModalPage extends BasePage {
    public final ElementHandler getAddButton() {
        return new ElementHandler(By.cssSelector("#dialogAddVisitData input[type=button]"), "Добавить");
    }

    public final ElementHandler getCloseButton() {
        return new ElementHandler(By.cssSelector("#dialogAddVisitData #cancelButton"), "Закрыть");
    }
}
