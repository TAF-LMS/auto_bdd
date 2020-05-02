package in.bntu.lms.enums;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor(access = AccessLevel.PACKAGE)
public enum AssertMessages {
    PAGE_WAS_NOT_OPENED("'%s' has not opened with timeOut: %s sec");

    @Getter
    private final String message;
}
