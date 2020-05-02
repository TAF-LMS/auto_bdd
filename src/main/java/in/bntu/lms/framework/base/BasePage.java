package in.bntu.lms.framework.base;

import in.bntu.lms.framework.annotation.PageInfo;
import in.bntu.lms.framework.ui.ElementHandler;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;

import java.time.Duration;
import java.util.NoSuchElementException;

import static in.bntu.lms.framework.configuration.SeleniumConfig.seleniumConfig;

/**
 * The abstract class for all pageObject. All pages should extends this class
 */
@Slf4j
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public abstract class BasePage {
    protected final By locator;
    protected final String pageName;

    /**
     * Instantiates a new Base page.
     */
    protected BasePage() {
        PageInfo pageInfo = this.getClass().getAnnotation(PageInfo.class);
        this.pageName = pageInfo.pageName();
        this.locator = this.getLocatorFromPageInfo(pageInfo);
    }

    /**
     * The method gets information from the @PageInfo annotation
     * @param pageInfo page information
     * @return page locator from the annotation
     */
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

    public boolean isPagePresent(Duration timeout) {
        return new ElementHandler(locator, pageName).isExists(timeout);
    }

    public boolean isPagePresent() {
        return isPagePresent(seleniumConfig().getConditionTimeOut().getTimeOut());
    }

    @Override
    public String toString() {
        return pageName;
    }
}
