package in.bntu.lms.steps.modal;

import in.bntu.lms.framework.base.BasePage;
import in.bntu.lms.models.Lecture;
import in.bntu.lms.pages.modal.EditLectureModalPage;
import in.bntu.lms.steps.BasePageSteps;
import io.cucumber.java.DataTableType;
import io.cucumber.java.en.When;

import java.util.Map;

public class EditLecturesModalPageSteps extends BasePageSteps {
    private final EditLectureModalPage editLectureModalPage = new EditLectureModalPage();

    @DataTableType
    public Lecture lectureEntry(Map<String, String> entry) {
        return new Lecture(entry);
    }

    @When("^Type and save lecture information and upload the file '(.*)':$")
    public void typeAndSaveLectureInfo(String fileName, Lecture lecture) {
        checkPageHasOpened()
                .typeValue(editLectureModalPage.getLectureNameTextArea(), lecture.getName())
                .typeValue(editLectureModalPage.getLectureDurationTextArea(), String.valueOf(lecture.getTime()))
                .typeValue(editLectureModalPage.getLectureOrderInput(), "0")
                .uploadFile(editLectureModalPage.getUploadFileInput(), fileName)
                .click(editLectureModalPage.getSaveButton());
    }

    @When("Type and save lecture information:")
    public void typeAndSaveLectureInfo(Lecture lecture) {
        checkPageHasOpened()
                .typeValue(editLectureModalPage.getLectureNameTextArea(), lecture.getName())
                .typeValue(editLectureModalPage.getLectureDurationTextArea(), String.valueOf(lecture.getTime()))
                .typeValue(editLectureModalPage.getLectureOrderInput(), "0")
                .click(editLectureModalPage.getSaveButton());
    }

    @Override
    protected BasePage getPage() {
        return editLectureModalPage;
    }
}
