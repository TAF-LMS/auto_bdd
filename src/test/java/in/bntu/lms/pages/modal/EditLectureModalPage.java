package in.bntu.lms.pages.modal;

import in.bntu.lms.framework.annotation.PageInfo;
import in.bntu.lms.framework.base.BasePage;
import in.bntu.lms.framework.ui.ElementHandler;
import org.openqa.selenium.By;

@PageInfo(id = "dialogAddLectures", pageName = "Модальное окно: Добавление/Редактирование лекции")
public class EditLectureModalPage extends BasePage {

    public final ElementHandler getLectureNameTextArea() {
        return new ElementHandler(By.cssSelector("[ng-model*=Theme]"), "Тема лекции");
    }

    public final ElementHandler getLectureDurationTextArea() {
        return new ElementHandler(By.cssSelector("[ng-model*=Duration]"), "Количество часов");
    }

    public final ElementHandler getLectureOrderInput() {
        return new ElementHandler(By.cssSelector("[ng-model*=Order]"), "Порядок");
    }

    public final ElementHandler getUploadFileInput() {
        return new ElementHandler(By.cssSelector("[type=file]"), "Добавить файл");
    }

    public final ElementHandler getSaveButton() {
        return new ElementHandler(By.cssSelector("[ng-click*=saveLectures]"), "Сохранить");
    }
}
