package in.bntu.lms.pages;

import in.bntu.lms.framework.annotation.PageInfo;
import in.bntu.lms.framework.base.BasePage;
import in.bntu.lms.framework.ui.ElementHandler;
import in.bntu.lms.framework.ui.SelectElementHandler;
import in.bntu.lms.framework.ui.TableHandler;
import in.bntu.lms.models.ui.JoinLecturerTable;
import org.openqa.selenium.By;

@PageInfo(css = "[ng-controller=JoinLectorController]", pageName = "Страница: Присоединение преподователей")
public class JoinLecturerPage extends BasePage {

    public final SelectElementHandler getSubjectSelect() {
        return new SelectElementHandler(By.cssSelector("[ng-model=selectedSubject]"), "Предмет");
    }

    public final SelectElementHandler getLecturerSelect() {
        return new SelectElementHandler(By.cssSelector("[ng-model=selectedLector]"), "Лектор");
    }

    public final ElementHandler getJoinLecturerButton() {
        return new ElementHandler(By.cssSelector("button.btn-primary"), "Присоединить");
    }

    public final ElementHandler getRemoveLecturerButton(String lecturer) {
        return new ElementHandler(By.xpath(String.format("//tr[contains(., '%s')]//a", lecturer)), String.format("Удалить '%s'", lecturer));
    }

    public final TableHandler<JoinLecturerTable> getJoinLecturerTable() {
        return new TableHandler<>(By.className("table-responsive"), "Таблица с преподавателями", JoinLecturerTable.class
        );
    }
}
