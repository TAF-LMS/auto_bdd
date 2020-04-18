package in.bntu.lms.framework.ui;

import in.bntu.lms.framework.ui.annotations.EnumType;
import in.bntu.lms.framework.ui.annotations.FieldLocator;
import in.bntu.lms.framework.uiparser.ElementEnumTypeParser;
import in.bntu.lms.framework.uiparser.StringToTypeParser;
import in.bntu.lms.framework.uiparser.StringToTypeParserBuilder;
import in.bntu.lms.util.ReflectionUtils;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static in.bntu.lms.framework.configuration.SeleniumConfig.seleniumConfig;
import static in.bntu.lms.util.FunctionUtils.ifValueNotNull;
import static in.bntu.lms.util.ReflectionUtils.writeField;

@Slf4j
public class ElementsContainerHandler<T> extends BaseElement {

    private final Class<T> parseClass;

    public ElementsContainerHandler(By locator, String elementName, Class<T> parseClass) {
        super(locator, elementName, ElementState.EXISTS);
        this.parseClass = parseClass;
    }

    public List<WebElement> getElements() {
        return findElements(seleniumConfig().getConditionTimeOut().getTimeOut(), state);
    }

    public int count() {
        return getElements().size();
    }

    public List<T> mapToObjects() {
        List<T> result = new ArrayList<>();
        Field[] fields = parseClass.getDeclaredFields();
        getElements().forEach(row -> result.add(parseToObject(row, fields)));
        return result;
    }

    private T parseToObject(WebElement row, Field[] fields) {
        T obj = ReflectionUtils.createInstance(parseClass);
        Arrays.stream(fields).forEach(field -> setClassField(row, field, obj));
        return obj;
    }

    private void setClassField(WebElement row, Field field, T obj) {
        field.setAccessible(true);
        if (field.isAnnotationPresent(FieldLocator.class)) {
            FieldLocator fieldLocator = field.getDeclaredAnnotation(FieldLocator.class);
            String value = "";
            try {
                By locatorValue = fieldLocator.locatorType().getLocator(fieldLocator.locator());
                StringToTypeParserBuilder builder = StringToTypeParser.newBuilder();

                ifValueNotNull(field.getDeclaredAnnotation(EnumType.class), enumType0 ->
                        builder.registerTypeParser(enumType0.enumType(), new ElementEnumTypeParser<>(enumType0.enumType())));

                if (row.findElement(locatorValue).isDisplayed()) {
                    value = row.findElement(locatorValue).getText();
                    writeField(obj, field, value, builder.build());
                }
            } catch (IllegalAccessException e) {
                log.error(String.format("Error mapping element container to object. FieldName: %s and value: %s. %n%s", field.getName(), value, e));
            }
        }
    }
}
