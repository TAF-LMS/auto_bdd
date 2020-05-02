package in.bntu.lms.framework.ui;

import in.bntu.lms.framework.ui.annotations.EnumType;
import in.bntu.lms.framework.ui.annotations.TableMap;
import in.bntu.lms.framework.ui.interfaces.Table;
import in.bntu.lms.framework.uiparser.ElementEnumTypeParser;
import in.bntu.lms.framework.uiparser.StringToTypeParser;
import in.bntu.lms.framework.uiparser.StringToTypeParserBuilder;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.SortedMap;
import java.util.TreeMap;

import static in.bntu.lms.util.FunctionUtils.ifValueNotNull;
import static in.bntu.lms.util.ReflectionUtils.createInstance;
import static in.bntu.lms.util.ReflectionUtils.putValueInMapField;
import static in.bntu.lms.util.ReflectionUtils.writeField;
import static java.lang.String.format;

/**
 * The type Table handler.
 * Use to map ui table to model.
 * For example:
 * <table>
 *     <thead>
 *         <tr>
 *             <th>Column1</th>
 *             <th>Column2</th>
 *         </tr>
 *    </thead>
 *    <tbody>
 *        <tr>
 *            <td>1</td>
 *            <td>2</td>
 *        </tr>
 *        <tr>
 *            <td>3</td>
 *            <td>4</td>
 *        </tr>
 *    </tbody>
 * </table>
 * <p>
 * Model:
 * class Example implements Table {
 *     \@TableMap(title = "Column1")
 *     private String col1;
 *     \@TableMap(title = "Column2")
 *     private Integer col2;
 * }
 * <p>
 * Element: new TableHandler<Example>(By.tagName("table"), "Table of Example", By.cssSelector(":scope thead th"), By.cssSelector(":scope tbody tr"), "td",
 *                                    Example.class)
 *
 * @param <T> the type parameter
 */
@Slf4j
public class TableHandler<T extends Table> extends BaseElement {
    private final String tag;
    private final Class<T> clazz;
    private final Field[] tableFields;
    private final By rowLocator;
    private final By headerLocator;
    private SortedMap<Integer, Field> fieldMapping;

    /**
     * Instantiates a new Table handler.
     *
     * @param tableLocator          the table locator (locator with ui element which contains table)
     * @param placeholder           the placeholder
     * @param internalHeaderLocator the internal header locator (locator to get header column !Not header row)
     * @param internalRowLocator    the internal row locator (locator to get rows !Not row column)
     * @param includeColumnTagInRow the include column tag in row (tag to split element from internalRowLocator by columns)
     * @param clazz                 the clazz
     */
    public TableHandler(By tableLocator, String placeholder, By internalHeaderLocator, By internalRowLocator, String includeColumnTagInRow, Class<T> clazz) {
        super(tableLocator, placeholder, ElementState.EXISTS);
        this.tag = includeColumnTagInRow;
        this.rowLocator = internalRowLocator;
        this.headerLocator = internalHeaderLocator;
        this.clazz = clazz;
        this.tableFields = createInstance(this.clazz).getFieldsForTable();
    }

    public void setValue(int rowNumber, Map<String, String> mapTable) {
        Map<Integer, String> mapColumnByColumnName = new HashMap<>();
        List<WebElement> headers = getTableHeader();

        for (int i = 0; i < headers.size(); i++) {
            String headerName = headers.get(i).getText();
            if (mapTable.containsKey(headerName)) {
                mapColumnByColumnName.put(i, headerName);
            }
        }

        List<WebElement> rowElements = getTableRows().get(rowNumber).findElements(By.cssSelector(format(":scope > %s", tag)));
        mapColumnByColumnName.forEach((key, value) -> getColumnInput(rowElements.get(key)).sendKeys(mapTable.get(value)));

    }

    /**
     * Map table to list of T.
     *
     * @return the list
     */
    public List<T> getModelsFromTable() {
        List<T> result = new ArrayList<>();
        initFieldMapping();
        getTableRows().forEach(element -> result.add(mapRowToObject(element)));
        return result;
    }

    protected List<WebElement> getTableHeader() {
        return new ArrayList<>(findElement().findElements(this.headerLocator));
    }

    protected List<WebElement> getTableRows() {
        return new ArrayList<>(findElement().findElements(this.rowLocator));
    }

    /**
     * Init field mapping.
     * the Method initializes fieldMapping field. The field use to map annotation field to column header index
     */
    protected void initFieldMapping() {
        log.debug("Init field mapping for Table");
        if (this.fieldMapping == null || this.fieldMapping.isEmpty()) {
            List<WebElement> headerElements = getTableHeader();
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

    /**
     * Get class fields with @TableMap annotation for header columns;
     */
    private Optional<Field> getClassFieldMapToGridElement(Field[] fields, WebElement element) {
        log.debug("Get field map to grid element use @TableMap annotation");
        return Arrays.stream(fields)
                .filter(field -> {
                    TableMap tableMap = field.getAnnotation(TableMap.class);
                    String attributeValue = tableMap.attrName().isEmpty() ? "" : element.getAttribute(tableMap.attrName());
                    return tableMap.isMap() ||
                            (attributeValue.contains(tableMap.attrValue()) &&
                                    (tableMap.title().isEmpty() || element.getText().equalsIgnoreCase(tableMap.title())));
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
                if (field.getType().equals(Map.class)) {
                    putValueInMapField(customer, field, getTableHeader().get(key).getText(), text);
                } else {
                    writeField(customer, field, text, builder.build());
                }
            } catch (IllegalAccessException e) {
                log.error("Error mapping grid to object. FieldName: {} and value: {}. %n{}", field.getName(), text == null ? "null" : text, e.toString());
            }
        });
        return customer;
    }

    private WebElement getColumnInput(WebElement element) {
        return element.findElement(By.tagName("input"));
    }
}
