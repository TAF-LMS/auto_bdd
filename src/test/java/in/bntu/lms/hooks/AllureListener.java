package in.bntu.lms.hooks;

import com.google.common.collect.ImmutableList;
import in.bntu.lms.util.FileUtils;
import in.bntu.lms.util.ScreenshotUtils;
import io.qameta.allure.listener.StepLifecycleListener;
import io.qameta.allure.model.Attachment;
import io.qameta.allure.model.Status;
import io.qameta.allure.model.StepResult;

import java.io.File;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

import static in.bntu.lms.framework.configuration.AllureConfig.allureConfig;
import static in.bntu.lms.util.Assert.getAssert;

public class AllureListener implements StepLifecycleListener {

    private Throwable lastError;

    @Override
    public void afterStepStop(StepResult result) {
        if (!getAssert().softAssert().wasSuccess() && (lastError == null || !lastError.equals(getAssert().softAssert().getLastError()))) {
            lastError = getAssert().softAssert().getLastError();
            result.setStatus(Status.FAILED);

            File fullPageScreenFile = FileUtils.writeBytesToFile(ScreenshotUtils.makeFullPageScreen(),
                    Paths.get(allureConfig().getResultsDirectory(), UUID.randomUUID() + "-screen").toString());
            File errorLogFile = FileUtils.writeStringToFile(lastError.getMessage(),
                    Paths.get(allureConfig().getResultsDirectory(), UUID.randomUUID() + "-error-log").toString());

            List<Attachment> attachments = ImmutableList.<Attachment>builder()
                    .add(new Attachment()
                            .setName("Page screenshot")
                            .setType("image/png")
                            .setSource(fullPageScreenFile.getAbsolutePath()))
                    .add(new Attachment()
                            .setName("Error log")
                            .setType("text/plain")
                            .setSource(errorLogFile.getAbsolutePath()))
                    .build();
            result.setAttachments(attachments);
        }
    }
}
