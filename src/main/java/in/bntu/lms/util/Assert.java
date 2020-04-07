package in.bntu.lms.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.assertj.core.api.SoftAssertions;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Assert {
    private static final ThreadLocal<SoftAssertions> THREAD_LOCAL = ThreadLocal.withInitial(SoftAssertions::new);

    public static synchronized SoftAssertions getTHREAD_LOCAL() {
        if (THREAD_LOCAL.get() == null) {
            THREAD_LOCAL.set(new SoftAssertions());
        }
        return THREAD_LOCAL.get();
    }
}
