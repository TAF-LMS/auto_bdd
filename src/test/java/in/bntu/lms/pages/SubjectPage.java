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

    public final NewsPage getNewsPage() {
        return new NewsPage();
    }

    public final LecturesPage getLecturersPage() {
        return new LecturesPage();
    }

    private By getSideBarLocator(String name) {
        return  By.xpath(String.format(SIDE_BAR_BUTTON,name));
    }
}
