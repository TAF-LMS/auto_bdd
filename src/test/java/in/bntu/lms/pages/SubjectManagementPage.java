package in.bntu.lms.pages;

import in.bntu.lms.framework.annotation.PageInfo;
import in.bntu.lms.framework.base.BasePage;
import in.bntu.lms.framework.ui.ElementHandler;
import in.bntu.lms.framework.ui.TableHandler;
import in.bntu.lms.models.ui.SubjectTable;
import org.openqa.selenium.By;

@PageInfo(id = "subjectList_wrapper", pageName = "Страница: Управление предметами")
public class SubjectManagementPage extends BasePage {
    public final ElementHandler getAddSubjectButton() {
        return new ElementHandler(By.cssSelector("#buttonActionSection > a"), "Добавить предмет");
    }

    public final TableHandler<SubjectTable> getSubjects() {
        return new TableHandler<>(By.id("subjectList_wrapper"), "Таблица с предметами", SubjectTable.class);
    }

    public final ElementHandler getEditSubjectButton(String subjectName) {
        return new ElementHandler(By.xpath(String.format("//tr[./td/*[text() = '%s']]//a[@class='editSubjectAction']", subjectName)),
                "Редактировать предмет: " + subjectName);
    }

    public final ElementHandler getRemoveSubjectButton(String subjectName) {
        return new ElementHandler(By.xpath(String.format("//tr[./td/*[text() = '%s']]//a[@class='deleteSubjectButton']", subjectName)),
                "Редактировать предмет: " + subjectName);
    }

    public final ElementHandler getSubjectFilterInput() {
        return new ElementHandler(By.id("subjectList_filter_input"), "Поиск");
    }

}
