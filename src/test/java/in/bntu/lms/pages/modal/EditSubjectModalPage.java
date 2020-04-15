package in.bntu.lms.pages.modal;

import in.bntu.lms.framework.annotation.PageInfo;
import in.bntu.lms.framework.base.BasePage;
import in.bntu.lms.framework.driver.JavaScript;
import in.bntu.lms.framework.driver.WebDriverRunner;
import in.bntu.lms.framework.ui.CheckBoxHandler;
import in.bntu.lms.framework.ui.DropDownCheckBoxesHandler;
import in.bntu.lms.framework.ui.ElementHandler;
import in.bntu.lms.models.enums.Modules;
import org.openqa.selenium.By;

import static java.lang.String.format;

@PageInfo(css = ".modal-dialog #editSubject", pageName = "Модальное окно: Создание/Редактирование предмета")
public class EditSubjectModalPage extends BasePage {
    private static final String TOPIC_CSS_PATTERN = "[value=%s] + div input[type=checkbox]";

    public final ElementHandler getSubjectNameInput() {
        return new ElementHandler(By.id("DisplayName"), "Название предмета");
    }

    public final ElementHandler getShortSubjectNameInput() {
        return new ElementHandler(By.id("ShortName"), "Аббревиатура");
    }

    public final CheckBoxHandler getNewsCheckBox() {
        return new CheckBoxHandler(By.cssSelector(format(TOPIC_CSS_PATTERN, "News")), "Новости");
    }

    public final CheckBoxHandler getLecturersCheckBox() {
        return new CheckBoxHandler(By.cssSelector(format(TOPIC_CSS_PATTERN, "Lectures")), "Лекции");
    }

    public final CheckBoxHandler getLabsCheckBox() {
        return new CheckBoxHandler(By.cssSelector(format(TOPIC_CSS_PATTERN, "Labs")), "Лабораторные работы");
    }

    public final CheckBoxHandler getYeManagementCheckBox() {
        return new CheckBoxHandler(By.cssSelector(format(TOPIC_CSS_PATTERN, "YeManagment")), "Курсовые проекты/работы");
    }

    public final CheckBoxHandler getSubjectAttachmentsCheckBox() {
        return new CheckBoxHandler(By.cssSelector(format(TOPIC_CSS_PATTERN, "SubjectAttachments")), "Файлы");
    }

    public final CheckBoxHandler getSmartTestCheckBox() {
        return new CheckBoxHandler(By.cssSelector(format(TOPIC_CSS_PATTERN, "SmartTest")), "Тестирование знаний");
    }

    public final CheckBoxHandler getPracticalCheckBox() {
        return new CheckBoxHandler(By.cssSelector(format(TOPIC_CSS_PATTERN, "Practical")), "Практические занятия");
    }

    public final CheckBoxHandler getComplexMaterialModuleCheckBox() {
        return new CheckBoxHandler(By.cssSelector(format(TOPIC_CSS_PATTERN, "ComplexMaterial")), "ЭУМК");
    }

    public final DropDownCheckBoxesHandler getGroupsDropDown() {
        return new DropDownCheckBoxesHandler(By.cssSelector("#SelectedGroups + div"), "Группы");
    }

    public final ElementHandler getSubjectColorInput() {
        return new ElementHandler(By.id("html5colorpicker"), "Цвет предмета") {
            @Override
            public void setValue(String value) {
                WebDriverRunner.executeScript(findElement(), JavaScript.SET_VALUE, value);
            }
        };
    }

    public final ElementHandler getSaveButton() {
        return new ElementHandler(By.id("saveButton"), "Сохранить");
    }

    public final CheckBoxHandler getModuleCheckBox(Modules modules) {
        switch (modules) {
            case NEWS:
                return getNewsCheckBox();
            case LABS:
                return getLabsCheckBox();
            case LECTURES:
                return getLecturersCheckBox();
            case PRACTICAL:
                return getPracticalCheckBox();
            case SMART_TEST:
                return getSmartTestCheckBox();
            case YE_MANAGEMENT:
                return getYeManagementCheckBox();
            case COMPLEX_MATERIAL:
                return getComplexMaterialModuleCheckBox();
            case SUBJECT_ATTACHMENTS:
                return getSubjectAttachmentsCheckBox();
            default:
                throw new IllegalArgumentException("");
        }
    }
}
