package in.bntu.lms.pages;

import in.bntu.lms.framework.annotation.PageInfo;
import in.bntu.lms.framework.base.BasePage;
import in.bntu.lms.framework.ui.ElementHandler;
import org.openqa.selenium.By;

@PageInfo(id = "subjectList_wrapper", pageName = "Страница: Управление предметами")
public class SubjectManagementPage extends BasePage {
    public final ElementHandler getAddSubjectButton() {
        return new ElementHandler(By.cssSelector("#buttonActionSection > a"), "Добавить предмет");
    }

}
