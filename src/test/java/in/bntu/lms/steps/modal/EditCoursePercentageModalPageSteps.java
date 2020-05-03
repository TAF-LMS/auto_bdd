package in.bntu.lms.steps.modal;

import com.google.common.collect.ImmutableMap;
import in.bntu.lms.framework.base.BasePage;
import in.bntu.lms.framework.ui.interfaces.Settable;
import in.bntu.lms.models.CoursePercentage;
import in.bntu.lms.pages.modal.EditCoursePercentageModalPage;
import in.bntu.lms.steps.BasePageSteps;
import io.cucumber.java.DataTableType;
import io.cucumber.java.en.When;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Map;

public class EditCoursePercentageModalPageSteps extends BasePageSteps {
    private final EditCoursePercentageModalPage editCoursePercentageModalPage = new EditCoursePercentageModalPage();
    private static final Map<Integer, String> MONTH_MAPPING;

    @DataTableType
    public CoursePercentage entry(Map<String, String> entry) {
        return new CoursePercentage(entry);
    }

    @When("Type the course percentage information:")
    public void typeCoursePercentageInfo(CoursePercentage coursePercentage) {
        LocalDate date = coursePercentage.getDate() == null ? LocalDate.now() : LocalDate.parse(coursePercentage.getDate(),
                DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        checkPageHasOpened()
                .typeValues(ImmutableMap.<Settable<String>, String>builder()
                        .put(editCoursePercentageModalPage.getNameInput(), coursePercentage.getName())
                        .put(editCoursePercentageModalPage.getPercentInput(), coursePercentage.getPercent())
                        .build())
                .click(editCoursePercentageModalPage.getOpenDatePickerButton())
                .click(editCoursePercentageModalPage.getHeaderInDatePicker())
                .click(editCoursePercentageModalPage.getMonthInDatePicker(MONTH_MAPPING.get(date.getMonth().getValue())))
                .click(editCoursePercentageModalPage.getDateInDatePicker(date.format(DateTimeFormatter.ofPattern("dd"))));
    }

    @When("Save the course percentage information")
    public void save() {
        checkPageHasOpened().click(editCoursePercentageModalPage.getSaveButton());
    }

    @Override
    protected BasePage getPage() {
        return editCoursePercentageModalPage;
    }

    static {
        MONTH_MAPPING = ImmutableMap.<Integer, String>builder()
                .put(1, "января")
                .put(2, "февраля")
                .put(3, "марта")
                .put(4, "апреля")
                .put(5, "мая")
                .put(6, "июня")
                .put(7, "июля")
                .put(8, "августа")
                .put(9, "сентября")
                .put(10, "октября")
                .put(11, "ноября")
                .put(12, "декабря")
                .build();
    }
}
