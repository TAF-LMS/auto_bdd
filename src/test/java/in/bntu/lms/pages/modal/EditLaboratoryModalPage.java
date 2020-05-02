package in.bntu.lms.pages.modal;

import in.bntu.lms.framework.annotation.PageInfo;
import in.bntu.lms.framework.base.BasePage;
import in.bntu.lms.framework.ui.ElementHandler;
import org.openqa.selenium.By;

@PageInfo(id = "dialogAddLabs", pageName = "Модальное окно: Создание/Редактирование дабораторной работы")
public class EditLaboratoryModalPage extends BasePage {

    public final ElementHandler getLaboratoryNameInput() {
        return new ElementHandler(By.cssSelector("[ng-model*=Theme]"), "Название лабораторной работы");
    }

    public final ElementHandler getShortLaboratoryNameInput() {
        return new ElementHandler(By.cssSelector("[ng-model*=ShortName]"), "Сокращенное название лабораторной работы");
    }

    public final ElementHandler getOrderLaboratoryNameInput() {
        return new ElementHandler(By.cssSelector("[ng-model*=Order]"), "Порядковый номер лабораторной работы");
    }

    public final ElementHandler getDurationLaboratoryNameInput() {
        return new ElementHandler(By.cssSelector("[ng-model*=Duration]"), "Количество часов (1-99)");
    }

    public final ElementHandler getSaveButton() {
        return new ElementHandler(By.cssSelector("#dialogAddLabs a:not(#cancelButton)"), "Сохранить");
    }

    public final ElementHandler getCancelButton() {
        return new ElementHandler(By.cssSelector("#dialogAddLabs #cancelButton"), "Отмена");
    }
}
