package in.bntu.lms.pages;

import in.bntu.lms.framework.annotation.PageInfo;
import in.bntu.lms.framework.base.BasePage;
import in.bntu.lms.framework.ui.ElementHandler;
import in.bntu.lms.framework.ui.TableHandler;
import in.bntu.lms.models.CourseProject;
import org.openqa.selenium.By;

@PageInfo(xpath = "//h1/small[contains(text(), 'Темы курсовых проектов (работ)')]", pageName = "Страница: Темы курсовых проектов (работ)")
public class CourseProjectThemePage extends BasePage {

    public final ElementHandler getEditProjectButton() {
        return new ElementHandler(By.cssSelector("[ng-click*=editProject]"), "Добавить тему");
    }

    public final ElementHandler getAssignGroupButton(String name) {
        return new ElementHandler(By.xpath(String.format("//tr[./td[text() = '%s']]//*[contains(@class, 'alt')]", name)),
                "Назначит группу: " + name);
    }

    public final ElementHandler getDeleteThemeButton(String name) {
        return new ElementHandler(By.xpath(String.format("//tr[./td[text() = '%s']]//*[contains(@class, 'trash')]", name)),
                "Удалить тему: " + name);
    }

    public final ElementHandler getUnAssignStudentButton(String name) {
        return new ElementHandler(By.xpath(String.format("//tr[./td[text() = '%s']]//*[contains(@ng-click, 'deleteAssignment')]//i", name)),
                "Отменить назначение: " + name);
    }

    public final TableHandler<CourseProject> getCourseProjectTable() {
        return new TableHandler<>(By.cssSelector("[ng-table*=tableParams]"), "Таблица темы курсовых проектов", CourseProject.class);
    }
}
