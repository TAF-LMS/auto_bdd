package in.bntu.lms.pages;

import in.bntu.lms.framework.annotation.PageInfo;
import in.bntu.lms.framework.base.BasePage;
import in.bntu.lms.framework.ui.ElementHandler;
import in.bntu.lms.framework.ui.TableHandler;
import in.bntu.lms.models.CoursePercentage;
import org.openqa.selenium.By;

@PageInfo(css = "[ng-click*=editPercentage]", pageName = "Страница: График процентовки")
public class CoursePercentSchedulePage extends BasePage {

    public final ElementHandler getAddNewPercentageButton() {
        return new ElementHandler(By.cssSelector("[ng-click*=editPercentage]"), "Добавить этап");
    }

    public final ElementHandler getEditCoursePercentageButton(String name) {
        return new ElementHandler(By.xpath(String.format("//tr[./td[text() = '%s']]//a[contains(@ng-click, 'editPercentage')]", name)),
                "Редактировать этап: " + name);
    }

    public final ElementHandler getDeleteCoursePercentageButton(String name) {
        return new ElementHandler(By.xpath(String.format("//tr[./td[text() = '%s']]//a[contains(@ng-click, 'deletePercentage')]", name)),
                "Удалить этап: " + name);
    }

    public final TableHandler<CoursePercentage> getCoursePercentScheduleTable() {
        return new TableHandler<>(By.xpath("//*[contains(@ng-click, 'editPercentage')]/../following-sibling::div"), "", CoursePercentage.class);
    }
}
