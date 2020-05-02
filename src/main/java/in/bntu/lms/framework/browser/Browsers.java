package in.bntu.lms.framework.browser;

import in.bntu.lms.framework.browser.chrome.ChromeBrowserFactory;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * The class is custom browser factory.
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Browsers {
    public static IBrowserFactory getFactory() {
        return new ChromeBrowserFactory();
    }
}
