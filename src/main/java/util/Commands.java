package util;

public enum Commands {

    HELP("-h", "-\\h", "", "This help menu"),
    W("-w", "(?i)-w", ".*(?i)-w( +)(\\d+).*", "word count (mandatory)"),
    L("-l", "(?i)-l", ".*(?i)-l( +)(\\d+).*", "word length (mandatory)"),
    D("-d", "(?i)-d", ".*(?i)-d( +)(\\d+).*", "min distance between word (mandatory)");

    private String parameter;
    private String regex;
    private String regexWithParameter;
    private String description;

    Commands(String parameter, String regex, String regexWithParameter, String description) {
        this.parameter = parameter;
        this.regex = regex;
        this.regexWithParameter = regexWithParameter;
        this.description = description;
    }

    public String getRegex() {
        return regex;
    }

    public String getParameter() {
        return parameter;
    }

    public String getRegexWithParameter() {
        return regexWithParameter;
    }

    public String getDescription() {
        return description;
    }
}
