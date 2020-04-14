package in.bntu.lms.framework.uiparser;

import lombok.AllArgsConstructor;

import java.util.Map;

@AllArgsConstructor
public class StringToTypeParserBuilder {
    private final Map<Class<?>, TypeParser<?>> typeParsers;

    StringToTypeParserBuilder() {
        typeParsers = DefaultTypeParser.copy();
    }

    public StringToTypeParserBuilder unregisterTypeParser(Class<?> type) {
        if (type == null) {
            return this;
        }
        typeParsers.remove(type);
        return this;
    }

    public <T> StringToTypeParserBuilder registerTypeParser(Class<T> type, TypeParser<? extends T> typeParser) {
        if (type == null || typeParser == null) {
            return this;
        }
        typeParsers.put(type, typeParser);
        return this;
    }

    public StringToTypeParser build() {
        return new StringToTypeParser(typeParsers);
    }
}
