package in.bntu.lms.pages;

import in.bntu.lms.framework.annotation.PageInfo;
import in.bntu.lms.framework.base.BasePage;
import in.bntu.lms.framework.ui.ElementHandler;
import org.openqa.selenium.By;

@PageInfo(id = "nameMin", pageName = "Страница: Управление предметом")
public class SubjectPage extends BasePage {
    private static final String SIDE_BAR_BUTTON = "//*[@class='sidebar-menu']//*[contains(text(), '%s')]";

    public final ElementHandler getNewsSideBarButton() {
        return new ElementHandler(getSideBarLocator("Новости"), "Новости");
    }

    public final ElementHandler getLecturesSideBarButton() {
        return new ElementHandler(getSideBarLocator("Лекции"), "Лекции");
    }

    public final ElementHandler getLaboratorySideBarButton() {
        return new ElementHandler(getSideBarLocator("Лабораторные работы"), "Лабораторные работы");
    }

    public final ElementHandler getFilesSideBarButton() {
        return new ElementHandler(getSideBarLocator("Файлы"), "Файлы");
    }

    public final ElementHandler getCourseProjectSideBarButton() {
        return new ElementHandler(getSideBarLocator("Курсовой проект (работа)"), "Курсовой проект (работа)");
    }

    public final NewsPage getNewsPage() {
        return new NewsPage();
    }

    public final LecturesPage getLecturersPage() {
        return new LecturesPage();
    }

    public final LaboratoryPage getLaboratoryPage() {
        return new LaboratoryPage();
    }

    public final LecturesAttendancePage getLecturesAttendancePage() {
        return new LecturesAttendancePage();
    }

    private By getSideBarLocator(String name) {
        return By.xpath(String.format(SIDE_BAR_BUTTON, name));
    }
}
