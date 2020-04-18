package in.bntu.lms.pages.modal;

import in.bntu.lms.framework.annotation.PageInfo;
import in.bntu.lms.framework.base.BasePage;
import in.bntu.lms.framework.ui.ElementHandler;
import org.openqa.selenium.By;

@PageInfo(id = "dialogAddNews", pageName = "Модальное окно: Добавление/Редактирование новости")
public class EditNewsModalPage extends BasePage {

    public final ElementHandler getNewsTitleInput() {
        return new ElementHandler(By.cssSelector("[type=text]"), "Название новости");
    }

    public final ElementHandler getNewsContentTextArea() {
        return new ElementHandler(By.cssSelector("[name=editor] [contenteditable=true]"), "Текст новости");
    }

    public final ElementHandler getSaveNewsButton() {
        return new ElementHandler(By.id("saveNewsButton"), "Сохранить");
    }
}
