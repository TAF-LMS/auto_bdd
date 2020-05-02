package in.bntu.lms.framework.driver;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor(access = AccessLevel.PACKAGE)
public enum JavaScript {
    SET_VALUE("setValue.js");

    @Getter
    private final String fileName;
    public static final String MAIN_RESOURCES = "src/main/resources/scripts";
}
