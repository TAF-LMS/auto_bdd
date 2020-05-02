package in.bntu.lms.pages;

import in.bntu.lms.framework.annotation.PageInfo;
import in.bntu.lms.framework.base.BasePage;
import in.bntu.lms.framework.ui.ElementHandler;
import in.bntu.lms.framework.ui.SelectElementHandler;
import in.bntu.lms.framework.ui.TableHandler;
import in.bntu.lms.models.LectureAttendance;
import org.openqa.selenium.By;

@PageInfo(css = "[name=tableformVisiting]", pageName = "Вкладка: Посещение лекций")
public class LecturesAttendancePage extends BasePage {
    public final SelectElementHandler getGroupSelect() {
        return new SelectElementHandler(By.cssSelector("[ng-model*=selectedGroup]"), "Группа");
    }

    public final ElementHandler getScheduleManagementButton() {
        return new ElementHandler(By.cssSelector("#buttonActionSection a[ng-click*=addSheduleVisitingGraph]"), "Управление расписанием");
    }

    public final ElementHandler getShowAttendanceButton() {
        return new ElementHandler(By.cssSelector("[ng-click*=show]"), "Отметить посещяемость");
    }

    public final ElementHandler getSaveAttendanceButton() {
        return new ElementHandler(By.cssSelector("[type=submit].btn-success"), "Сохранить");
    }

    public final TableHandler<LectureAttendance> getLectureAttendanceTable() {
        return new TableHandler<>(By.id("tableformVisiting"), "Таблица со студентами",
                By.cssSelector(":scope thead th"), By.cssSelector(":scope tbody tr"), "td", LectureAttendance.class
        );
    }
}
