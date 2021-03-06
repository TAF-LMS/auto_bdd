package in.bntu.lms.listeners;

import in.bntu.lms.util.FileUtils;
import in.bntu.lms.util.ScreenshotUtils;
import io.qameta.allure.listener.StepLifecycleListener;
import io.qameta.allure.model.Attachment;
import io.qameta.allure.model.Status;
import io.qameta.allure.model.StepResult;
import org.apache.commons.lang3.builder.EqualsBuilder;

import java.io.File;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import static in.bntu.lms.framework.configuration.AllureConfig.allureConfig;
import static in.bntu.lms.util.Assert.getAssert;
import static org.assertj.core.util.Lists.list;

public class AllureListener implements StepLifecycleListener {

    private Throwable lastError;

    @Override
    public void afterStepStop(StepResult result) {
        if (result.getName().endsWith("Assert all")) {
            List<Throwable> errors = getAssert().softAssert().getErrors();
            if (!errors.isEmpty()) {
                //get all asserts and attach to test results
                result.setStatus(Status.FAILED);
                File errorLogFile = FileUtils.writeStringToFile(errors.stream().map(Throwable::getMessage).collect(Collectors.joining("\n")),
                        Paths.get(allureConfig().getResultsDirectory(), UUID.randomUUID() + "-error-log").toString());
                result.setAttachments(list(getErrorLogAttachment(errorLogFile)));
            }
            return;
        }

        //if it's the new error
        if (!getAssert().softAssert().wasSuccess() && (lastError == null || !equals(lastError, getAssert().softAssert().getLastError()))) {
            //attach assertion error message and screenshot to method results
            lastError = getAssert().softAssert().getLastError();
            result.setStatus(Status.FAILED);

            List<Attachment> attachments = result.getAttachments();

            File fullPageScreenFile = FileUtils.writeBytesToFile(ScreenshotUtils.makeFullPageScreen(),
                    Paths.get(allureConfig().getResultsDirectory(), UUID.randomUUID() + "-screen").toString());
            File errorLogFile = FileUtils.writeStringToFile(lastError.getMessage(),
                    Paths.get(allureConfig().getResultsDirectory(), UUID.randomUUID() + "-error-log").toString());
            attachments.add(getScreenShotAttachment(fullPageScreenFile));
            attachments.add(getErrorLogAttachment(errorLogFile));

            result.setAttachments(attachments);
        }
    }

    private boolean equals(Throwable o1, Throwable o2) {
        return new EqualsBuilder()
                .append(o1.getMessage(), o2.getMessage())
                .append(o1.getCause(), o2.getCause())
                .isEquals();
    }

    private Attachment getErrorLogAttachment(File source) {
        return getAttachment("Error log",  "text/plain", source);
    }

    private Attachment getScreenShotAttachment(File source) {
        return getAttachment("Page screenshot",  "image/png", source);
    }

    private Attachment getAttachment(String name, String type, File source) {
        return new Attachment()
                .setName(name)
                .setType(type)
                .setSource(source.getAbsolutePath());
    }
}
