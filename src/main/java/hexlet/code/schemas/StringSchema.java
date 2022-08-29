package hexlet.code.schemas;

public final class StringSchema extends BaseSchema {

    public StringSchema required() {
        addChecker(text -> text instanceof String && !((String) text).isEmpty());
        return this;
    }

    public StringSchema minLength(int min) {
        addChecker(text -> text instanceof String && ((String) text).length() >= min);
        return this;
    }

    public StringSchema contains(String subText) {
        addChecker(text -> text instanceof String && ((String) text).contains(subText));
        return this;
    }
}
