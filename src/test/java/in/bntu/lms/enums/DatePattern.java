package in.bntu.lms.enums;

import lombok.Getter;

import java.time.format.DateTimeFormatter;

public enum DatePattern {
    UI_FULL_DATE("dd.MM.yyyy");

    @Getter
    private final DateTimeFormatter formatter;

    DatePattern(String format) {
        this.formatter = DateTimeFormatter.ofPattern(format);
    }
}
