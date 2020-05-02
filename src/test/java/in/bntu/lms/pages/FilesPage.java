package in.bntu.lms.pages;

import in.bntu.lms.framework.annotation.PageInfo;
import in.bntu.lms.framework.base.BasePage;
import in.bntu.lms.framework.ui.ElementHandler;
import org.openqa.selenium.By;

@PageInfo(css = "[ng-controller*=SubjectAttachmentsController]", pageName = "Страница: Предметные файлы")
public class FilesPage extends BasePage {

    public final ElementHandler getFileByName(String name) {
        return new ElementHandler(By.xpath(String.format("//td[@class='name']/a[text() = '%s']", name)), "Файл: " + name);
    }
}
