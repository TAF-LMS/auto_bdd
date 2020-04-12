package in.bntu.lms.pages;

import in.bntu.lms.framework.annotation.PageInfo;
import in.bntu.lms.framework.base.BasePage;
import in.bntu.lms.framework.ui.ElementHandler;
import in.bntu.lms.framework.ui.interfaces.Visible;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.openqa.selenium.By;

import java.util.List;

import static org.assertj.core.util.Lists.list;

@NoArgsConstructor(access = AccessLevel.PACKAGE)
@PageInfo(xpath = "//*[@class='sidebar-menu']/li[contains(., 'Предметы')]/ul[contains(@class, 'menu')]", pageName = "Форма: Предметы")
public class SubjectsForm extends BasePage {
    public final ElementHandler getSubjectManagementButton() {
        return new ElementHandler(By.xpath("//li//*[contains(@href, 'Subject/Management')]"), "Управление предметами");
    }

    public final ElementHandler getLecturersButton() {
        return new ElementHandler(By.xpath("//li//*[contains(@href, 'Subject/JoinLector')]"), "Преподаватели");
    }

    public List<Visible> getRequiredFormElement() {
        return list(getSubjectManagementButton(), getLecturersButton());
    }
}
