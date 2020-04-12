package in.bntu.lms.util.yaml;


import com.fasterxml.jackson.core.type.TypeReference;

class YAMLParseException extends RuntimeException {

    YAMLParseException(String typeName, String fileName, Throwable cause) {
        super(String.format("YAML parse exception for class '%s' from the file: '%s'", typeName, fileName), cause);
    }

    YAMLParseException(Class<?> type, String fileName, Throwable cause) {
        this(type.getTypeName(), fileName, cause);
    }

    YAMLParseException(TypeReference<?> type, String fileName, Throwable cause) {
        this(type.getType().getTypeName(), fileName, cause);
    }
}
