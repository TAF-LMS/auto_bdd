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
        return new TableHandler<>(By.id("subjectList_wrapper"), "Таблица с предметами",
                By.cssSelector(":scope thead th"), By.xpath(".//tbody//tr"), "td", SubjectTable.class
        );
    }

}
