package in.bntu.lms.pages.modal;

import in.bntu.lms.framework.annotation.PageInfo;
import in.bntu.lms.framework.base.BasePage;
import in.bntu.lms.framework.ui.ElementHandler;
import org.openqa.selenium.By;

@PageInfo(css = "[name*=percentageForm]", pageName = "Модальное окно: Редактирование графика процентовки")
public class EditCoursePercentageModalPage extends BasePage {

    public final ElementHandler getNameInput() {
        return new ElementHandler(By.cssSelector("[ng-model='percentage.Name']"), "Название этапа");
    }

    public final ElementHandler getPercentInput() {
        return new ElementHandler(By.cssSelector("[ng-model='percentage.Percentage']"), "Процент выполнения");
    }

    public final ElementHandler getDateInput() {
        return new ElementHandler(By.cssSelector("[ng-model='percentage.Date']"), "Дата");
    }

    public final ElementHandler getOpenDatePickerButton() {
        return new ElementHandler(By.cssSelector("[ng-click*=datePickerOpen]"), "Открыть datepicker");
    }

    public final ElementHandler getDateInDatePicker(String date) {
        return new ElementHandler(By.xpath(
                String.format("//*[@ng-switch='datepickerMode']//td//span[not(contains(@class, 'text-muted')) and text() = '%s']", date)),
                "Дата: " + date);
    }

    public final ElementHandler getHeaderInDatePicker() {
        return new ElementHandler(By.cssSelector("[ng-switch=datepickerMode ] [role=heading]"), "Месяц/Год");
    }

    public final ElementHandler getMonthInDatePicker(String month) {
        return new ElementHandler(By.xpath(String.format("//*[@ng-switch='datepickerMode']//td//span[text() = '%s']", month)), "Месяц: " + month);
    }

    public final ElementHandler getSaveButton() {
        return new ElementHandler(By.cssSelector("[name*=percentageForm] [type=submit]"), "Сохранить");
    }
}
