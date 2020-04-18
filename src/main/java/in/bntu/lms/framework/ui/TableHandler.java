package in.bntu.lms.framework.ui;

import in.bntu.lms.framework.ui.annotations.EnumType;
import in.bntu.lms.framework.ui.interfaces.Table;
import in.bntu.lms.framework.ui.annotations.TableMap;
import in.bntu.lms.framework.uiparser.ElementEnumTypeParser;
import in.bntu.lms.framework.uiparser.StringToTypeParser;
import in.bntu.lms.framework.uiparser.StringToTypeParserBuilder;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.SortedMap;
import java.util.TreeMap;

import static in.bntu.lms.util.FunctionUtils.ifValueNotNull;
import static in.bntu.lms.util.ReflectionUtils.createInstance;
import static in.bntu.lms.util.ReflectionUtils.writeField;
import static java.lang.String.format;
import static org.apache.commons.lang3.StringUtils.EMPTY;

@Slf4j
public class TableHandler<T extends Table> extends BaseElement {
    private final String tag;
    private final Class<T> clazz;
    private final Field[] tableFields;
    private final By rowLocator;
    private final By headerLocator;
    private SortedMap<Integer, Field> fieldMapping;

    public TableHandler(By tableLocator, String placeholder, By internalHeaderLocator, By internalRowLocator, String includeColumnTagInRow, Class<T> clazz) {
        super(tableLocator, placeholder, ElementState.EXISTS);
        this.tag = includeColumnTagInRow;
        this.rowLocator = internalRowLocator;
        this.headerLocator = internalHeaderLocator;
        this.clazz = clazz;
        this.tableFields = createInstance(this.clazz).getFieldsForTable();
    }

    public List<T> getModelsFromTable() {
        List<T> result = new ArrayList<>();
        initFieldMapping(getTableHeader());
        getTableRows().forEach(element -> result.add(mapRowToObject(element)));
        return result;
    }

    public String getGridTextEmpty() {
        return getTableRows().isEmpty() ? EMPTY : getTableRows().get(0).getText();
    }

    protected List<WebElement> getTableHeader() {
        return new ArrayList<>(findElement().findElements(this.headerLocator));
    }

    protected List<WebElement> getTableRows() {
        return new ArrayList<>(findElement().findElements(this.rowLocator));
    }

    protected void initFieldMapping(List<WebElement> headerElements) {
        log.debug("Init field mapping for GRID");
        if (this.fieldMapping == null || this.fieldMapping.isEmpty()) {
            SortedMap<Integer, Field> mapping = new TreeMap<>();
            for (int i = 0; i < headerElements.size(); i++) {
                final int finalI = i;
                getClassFieldMapToGridElement(this.tableFields, headerElements.get(i))
                        .ifPresent(value -> mapping.put(finalI, value));
            }

            this.fieldMapping = mapping;
        }
        log.debug("Field Mapping: {}", fieldMapping.toString());
    }

    private Optional<Field> getClassFieldMapToGridElement(Field[] fields, WebElement element) {
        log.debug("Get field map to grid element use @TableMap annotation");
        return Arrays.stream(fields)
                .filter(field -> {
                    TableMap tableMap = field.getAnnotation(TableMap.class);
                    String attributeValue = tableMap.attrName().isEmpty() ? "" : element.getAttribute(tableMap.attrName());
                    return attributeValue.contains(tableMap.attrValue()) && (tableMap.title().isEmpty() || element.getText().equalsIgnoreCase(tableMap.title()));

                })
                .findFirst();
    }

    private T mapRowToObject(WebElement element) {
        log.debug("Map row to Object");
        T customer = createInstance(this.clazz);
        List<WebElement> elements = element.findElements(By.cssSelector(format(":scope > %s", tag)));
        fieldMapping.forEach((key, field) -> {

            StringToTypeParserBuilder builder = StringToTypeParser.newBuilder();

            ifValueNotNull(field.getDeclaredAnnotation(EnumType.class), enumType0 ->
                    builder.registerTypeParser(enumType0.enumType(), new ElementEnumTypeParser<>(enumType0.enumType())));

            String text = elements.get(key).getText();
            try {
                writeField(customer, field, text, builder.build());
            } catch (IllegalAccessException e) {
                log.error("Error mapping grid to object. FieldName: {} and value: {}. %n{}", field.getName(), text == null ? "null" : text, e.toString());
            }
        });
        return customer;
    }
}
