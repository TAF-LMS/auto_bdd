package in.bntu.lms.pages;

import in.bntu.lms.framework.annotation.PageInfo;
import in.bntu.lms.framework.base.BasePage;
import in.bntu.lms.framework.ui.TableHandler;
import in.bntu.lms.models.Laboratory;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.openqa.selenium.By;

@NoArgsConstructor(access = AccessLevel.PACKAGE)
@PageInfo(xpath = "//*[contains(@class, 'active')]/*[text() = 'Результаты']", pageName = "Вкладка: Результаты")
public class ResultPage extends BasePage {

    public final TableHandler<Laboratory> getLaboratoryResultTable() {
        return new TableHandler<>(By.cssSelector("[name=tableformLabsMarksSubOne]"), "Подгруппа 1", Laboratory.class);
    }

}
