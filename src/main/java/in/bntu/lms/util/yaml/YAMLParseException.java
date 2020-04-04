package in.bntu.lms.util.yaml;

class YAMLParseException extends RuntimeException {

    YAMLParseException(String typeName, String fileName, Throwable cause) {
        super(String.format("YAML parse exception for class '%s' from the file: '%s'", typeName, fileName), cause);
    }
}
