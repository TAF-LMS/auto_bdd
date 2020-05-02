package in.bntu.lms.pages;

import in.bntu.lms.framework.annotation.PageInfo;
import in.bntu.lms.framework.base.BasePage;
import in.bntu.lms.framework.ui.ElementHandler;
import in.bntu.lms.framework.ui.TableHandler;
import in.bntu.lms.models.Laboratory;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.openqa.selenium.By;

@NoArgsConstructor(access = AccessLevel.PACKAGE)
@PageInfo(css = "[ng-controller*=LabsController]", pageName = "Вкладка: Лабораторные работы")
public class LaboratoryPage extends BasePage {

    public final ElementHandler getLaboratoryButton() {
        return new ElementHandler(By.id("addLabsButton"), "Добавить лабораторную работу");
    }

    public final ElementHandler getRemoveLaboratoryButton(String name) {
        return new ElementHandler(By.xpath(String.format("//tr[./td[text() = '%s']]//*[contains(@class, 'trash')]", name)),
                "Удалить работу: " + name);
    }

    public final ElementHandler getEditLaboratoryButton(String name) {
        return new ElementHandler(By.xpath(String.format("//tr[./td[text() = '%s']]//*[contains(@class, 'edit')]", name)),
                "Редактировать работу: " + name);
    }

    public final TableHandler<Laboratory> getLaboratoryTable() {
        return new TableHandler<>(By.cssSelector("[ng-controller*=LabsController] .active .table-responsive"), "Таблица лабораторных",
                By.cssSelector(":scope thead th"), By.cssSelector(":scope tbody tr"), "td", Laboratory.class);
    }
}
