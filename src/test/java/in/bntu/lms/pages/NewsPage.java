package in.bntu.lms.pages;

import in.bntu.lms.framework.annotation.PageInfo;
import in.bntu.lms.framework.base.BasePage;
import in.bntu.lms.framework.ui.ElementHandler;
import in.bntu.lms.framework.ui.ElementsContainerHandler;
import in.bntu.lms.models.News;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.openqa.selenium.By;

@NoArgsConstructor(access = AccessLevel.PACKAGE)
@PageInfo(css = "[ng-controller=NewsController]", pageName = "Страница: Новости")
public class NewsPage extends BasePage {

    public final ElementHandler getAddNewsButton() {
        return new ElementHandler(By.id("addNewsButton"), "Добавить новость");
    }

    public final ElementHandler getRemoveNewsButton(String news) {
        return new ElementHandler(By.xpath(String.format("//h4[text() = '%s']/..//a[@class='deleteNewsButton']", news)), "Удалить " + news) {
            @Override
            public void click() {
                moveMouseToNews(news);
                super.click();
            }
        };
    }

    public final ElementHandler getEditNewsButton(String news) {
        return new ElementHandler(By.xpath(String.format("//h4[text() = '%s']/..//a[@class='editNewsButton']", news)), "Редактировать " + news) {
            @Override
            public void click() {
                moveMouseToNews(news);
                super.click();
            }
        };
    }

    public final ElementHandler getHideAllNewsButton() {
        return new ElementHandler(By.cssSelector("[ng-click*=disableNews]"), "Скрыть все новости");
    }

    public final ElementHandler getShowAllNewsButton() {
        return new ElementHandler(By.cssSelector("[ng-click*=enableNews]"), "Показать все новости");
    }

    public final ElementHandler getInformationMessageLabel(String message) {
        return new ElementHandler(By.xpath(String.format("//article[text() = '%s']", message)), "Всплывающее сообщение: " + message);
    }

    public final ElementsContainerHandler<News> getNewsElementsContainer() {
        return new ElementsContainerHandler<>(By.cssSelector("[ng-controller=NewsController] > div:not([class]) > div"), "Новости", News.class);
    }

    private void moveMouseToNews(String news) {
        new ElementHandler(By.xpath(String.format("//h4[text() = '%s']/..", news)), news).moveMouseToElement();
    }
}
