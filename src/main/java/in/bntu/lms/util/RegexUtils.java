package in.bntu.lms.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.apache.commons.lang3.StringUtils.EMPTY;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class RegexUtils {

    public static String getValueByPattern(String text, String pattern) {
        Matcher matcher = getMatcher(text, pattern);
        if (matcher.find()) {
            return matcher.group(1);
        }
        return EMPTY;
    }

    private static Matcher getMatcher(String text, String pattern) {
        return Pattern.compile(pattern, Pattern.UNICODE_CHARACTER_CLASS).matcher(text);
    }
}
