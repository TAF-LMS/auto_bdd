package in.bntu.lms.framework.ui.interfaces;

import java.time.Duration;

import static in.bntu.lms.framework.configuration.SeleniumConfig.seleniumConfig;

public interface Visible {
    boolean isExists(Duration duration);
    boolean isAbsent(Duration duration);
    boolean isPresent(Duration duration);

    default boolean isExists() {
        return isExists(seleniumConfig().getConditionTimeOut().getTimeOut());
    }

    default boolean isAbsent() {
        return isAbsent(seleniumConfig().getConditionTimeOut().getTimeOut());
    }

    default boolean isPresent() {
        return isPresent(seleniumConfig().getConditionTimeOut().getTimeOut());
    }
}
