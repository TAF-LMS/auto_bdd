package in.bntu.lms.pages;

import in.bntu.lms.framework.annotation.PageInfo;
import in.bntu.lms.framework.base.BasePage;
import in.bntu.lms.framework.ui.ElementHandler;
import in.bntu.lms.framework.ui.TableHandler;
import in.bntu.lms.models.Lecture;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.openqa.selenium.By;

@NoArgsConstructor(access = AccessLevel.PACKAGE)
@PageInfo(css = "[ng-controller=LecturesController]", pageName = "Страница: Лекции")
public class LecturesPage extends BasePage {

    public final ElementHandler getAddLectureButton() {
        return new ElementHandler(By.cssSelector("#buttonActionSection > a"), "Добавить лекцию");
    }

    public final ElementHandler getRemoveLectureButton(String lecture) {
        return new ElementHandler(By.xpath(String.format("//tr[./td[text() = '%s']]//*[contains(@class, 'deleteLecturesButton')]", lecture)),
                "Удалить лекцию: " + lecture);
    }

    public final ElementHandler getEditLectureButton(String lecture) {
        return new ElementHandler(By.xpath(String.format("//tr[./td[text() = '%s']]//*[contains(@class, 'editLecturesButton')]", lecture)),
                "Редактировать лекцию: " + lecture);
    }

    public final TableHandler<Lecture> getLecturersTable() {
        return new TableHandler<>(By.className("table-responsive"), "Таблица лекций", Lecture.class);
    }

    public final ElementHandler getLectureAttendanceTabButton() {
        return new ElementHandler(By.xpath("//*[@heading = 'Посещение лекций']/a"), "Посещение лекций");
    }
}
