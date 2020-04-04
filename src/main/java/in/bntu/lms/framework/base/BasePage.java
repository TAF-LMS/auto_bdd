package in.bntu.lms.framework.base;

import in.bntu.lms.framework.annotation.PageInfo;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;

import java.util.NoSuchElementException;

@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Slf4j
public abstract class BasePage {
    protected final By locator;
    protected final String pageName;

    protected BasePage() {
        PageInfo pageInfo = this.getClass().getAnnotation(PageInfo.class);
        this.pageName = pageInfo.pageName();
        this.locator = this.getLocatorFromPageInfo(pageInfo);
    }

    private By getLocatorFromPageInfo(PageInfo pageInfo) {
        log.debug("Get page locator from {}", pageInfo.toString());
        if (!"".equals(pageInfo.xpath())) {
            return By.xpath(pageInfo.xpath());
        } else if (!"".equals(pageInfo.id())) {
            return By.id(pageInfo.id());
        } else if (!"".equals(pageInfo.css())) {
            return By.cssSelector(pageInfo.css());
        } else {
            throw new NoSuchElementException();
        }
    }

    public boolean isPagePresent(long timeout) {
        //Implement method
        return true;
    }

    public boolean isPagePresent() {
        //Implement method
        return true;
    }
}
