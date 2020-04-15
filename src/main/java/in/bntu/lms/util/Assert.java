package in.bntu.lms.util;

import com.google.common.collect.Iterables;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Assert {
    private static final String DEFAULT_ASSERT_MESSAGE = "Expecting: %s to be equal to: %s, but was not.";
    private static final ThreadLocal<Assert> INSTANCE = ThreadLocal.withInitial(Assert::new);
    private final SoftAssertions softAssertions = new SoftAssertions();
    private final Assertions assertions = new Assertions(softAssertions);

    public static Assert getAssert() {
        if (INSTANCE.get() == null) {
            INSTANCE.set(new Assert());
        }
        return INSTANCE.get();
    }

    public SoftAssertions softAssert() {
        return softAssertions;
    }

    public Assertions hardAssert() {
        return assertions;
    }

    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    public static class Assertions {
        private final SoftAssertions softAssertions;

        public <T> void isEqual(T actual, T expected, String message, Object... args) {
            try {
                org.assertj.core.api.Assertions.assertThat(actual).overridingErrorMessage(message, args).isEqualTo(expected);
            } catch (AssertionError error) {
                softAssertions.isEqual(actual, expected, message, args);
                softAssertions.assertAll();
            }
        }

        public <T> void isEqual(T actual, T expected) {
            isEqual(actual, expected, DEFAULT_ASSERT_MESSAGE, expected, actual);
        }
    }

    public static class SoftAssertions extends org.assertj.core.api.SoftAssertions {
        public <T> void isEqual(T actual, T expected) {
            isEqual(actual, expected, DEFAULT_ASSERT_MESSAGE, expected, actual);
        }

        public <T> void isEqual(T actual, T expected, String message, Object... args) {
            assertThat(actual).overridingErrorMessage(message, args).isEqualTo(expected);
        }

        public List<Throwable> getErrors() {
            return this.errorsCollected();
        }

        public Throwable getLastError() {
            return Iterables.getLast(getErrors());
        }
    }
}
