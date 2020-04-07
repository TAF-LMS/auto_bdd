package in.bntu.lms.extensions;

import in.bntu.lms.util.AllureAttachmentUtils;
import in.bntu.lms.util.Assert;
import org.junit.jupiter.api.extension.AfterEachCallback;
import org.junit.jupiter.api.extension.Extension;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.TestExecutionExceptionHandler;

public class ScreenshotExtension implements TestExecutionExceptionHandler, AfterEachCallback, Extension {

    @Override
    public void afterEach(ExtensionContext context) {
        if (!Assert.getInstance().wasSuccess()) {
            AllureAttachmentUtils.attachScreenshot();
        }
    }

    @Override
    public void handleTestExecutionException(ExtensionContext context, Throwable throwable) throws Throwable {
        if (throwable != null) {
            AllureAttachmentUtils.attachScreenshot();
            throw throwable;
        }
    }
}
