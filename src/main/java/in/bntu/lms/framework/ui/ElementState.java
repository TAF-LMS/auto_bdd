package in.bntu.lms.framework.ui;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.openqa.selenium.WebElement;

import java.util.Objects;
import java.util.function.Predicate;

@AllArgsConstructor(access = AccessLevel.PACKAGE)
public enum ElementState {
    EXISTS(Objects::nonNull),
    HIDDEN(element -> !element.isDisplayed()),
    VISIBLE(WebElement::isDisplayed);

    @Getter
    private Predicate<WebElement> predicate;
}
