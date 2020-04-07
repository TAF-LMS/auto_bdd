package in.bntu.lms.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.assertj.core.api.SoftAssertions;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Assert {
    private static ThreadLocal<SoftAssertions> instance = ThreadLocal.withInitial(SoftAssertions::new);

    public static synchronized SoftAssertions getInstance() {
        if (instance.get() == null) {
            instance.set(new SoftAssertions());
        }
        return instance.get();
    }
}
